import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.*;
import java.io.FileReader;
import java.io.IOException;

public class Clavier implements InterfaceClavier {
    private List<Touche> touches;
    private String path;
    private Map<String, List<Touche>> combinaisons;
    private String pathCombi;

    public Clavier() {
        this.touches = new ArrayList<>();
        this.combinaisons = new HashMap<>();
        this.path = "ressources/azerty.json";
        chargerAzerty();
        this.pathCombi = "ressources/combinaison.json";
        combinaison();
        // for (Touche touche : touches) {
        // System.out.println(touche);
        // }
    }

    public Clavier(String path) {
        this.touches = new ArrayList<>();
        this.path = path;
        this.combinaisons = new HashMap<>();
        creerClavier();
    }

    public Clavier(String path, String pathCombi) {
        this.touches = new ArrayList<>();
        this.path = path;
        this.pathCombi = pathCombi;
        this.combinaisons = new HashMap<>();
    }

    public void chargerAzerty() {
        creerClavier();
    }

    @Override
    public void creerClavier() {
        try (FileReader reader = new FileReader((this.path))) {
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
            JsonArray clavier = jsonObject.getAsJsonArray("clavier");

            for (JsonElement element : clavier) {
                JsonObject toucheJson = element.getAsJsonObject();
                int x = toucheJson.get("x").getAsInt();
                int y = toucheJson.get("y").getAsInt();
                String touche = toucheJson.get("touche").getAsString();
                Doigt doigt = null;
                List<String> touchesMortes = new ArrayList<>();
                if (!toucheJson.get("doigt").isJsonNull()) {
                    doigt = Doigt.valueOf(toucheJson.get("doigt").getAsString().toUpperCase());
                    doigt.compteOccDoigts();
                }
                if (toucheJson.has("morte") && toucheJson.get("morte").isJsonArray()) {
                    JsonArray jsonMortes = toucheJson.getAsJsonArray("morte");
                    for (JsonElement morteElement : jsonMortes) {
                        String morteTouche = morteElement.getAsString();
                        if (morteTouche != null) {
                            touchesMortes.add(morteTouche);
                        }
                    }
                }
                this.touches.add(new Touche(touche, x, y, doigt, touchesMortes));
            }
            Doigt.calculPoids();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static <T> List<T> flattenList(List<List<T>> listOfLists) {
        List<T> flatList = new ArrayList<>();
        for (List<T> list : listOfLists) {
            flatList.addAll(list);
        }
        return flatList;
    }

    public void combinaison() {
        try (FileReader reader = new FileReader(this.pathCombi)) {
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
            JsonArray combi = jsonObject.getAsJsonArray("combinaison");

            for (JsonElement elem : combi) {
                JsonObject object = elem.getAsJsonObject();
                String etiq = object.get("char").getAsString();
                List<List<Touche>> l = new ArrayList<>();
                JsonArray com = object.getAsJsonArray("combi");
                for (JsonElement comElem : com) {
                    List<Touche> t = this.chercheTouche(comElem.getAsString());
                    if (t.isEmpty()) {
                        System.out.println("Aucune touche trouvée pour: " + comElem.getAsString());
                    } else {
                        System.out.println("Touches trouvées pour " + comElem.getAsString() + ": " + t);
                    }
                    l.add(t);
                }
                List<Touche> touche = flattenList(l);
                this.combinaisons.put(etiq, touche);

            }
            afficherCombinaisons();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    // retourne une liste pour les touches en double (shift, alt, ctrl)
    public List<Touche> chercheTouche(String etiquette) {
        List<Touche> res = new ArrayList<>();
        for (Touche t : touches) {
            if (etiquette.equals(t.getEtiq())) {
                res.add(t);
            }
        }
        if (this.combinaisons != null) {
            if (res.isEmpty()) {
                for (Map.Entry<String, List<Touche>> entry : combinaisons.entrySet()) {
                    if (entry.getKey().equals(etiquette)) {
                        return entry.getValue();
                    }
                }
            }
        }
        return res;
    }

    public void afficherCombinaisons() {
        for (Map.Entry<String, List<Touche>> entry : combinaisons.entrySet()) {
            System.out.println("tour de boucle");
            System.out.println("Combinaison: " + entry.getKey() + " -> " + entry.getValue());
        }
    }

    @Override
    public List<Touche> getTouches() {
        return touches;
    }

}
