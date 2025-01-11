package clavier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.*;

import java.io.FileReader;
import java.io.IOException;

/**
 * La classe Clavier représente un clavier avec des touches et leurs
 * combinaisons possibles
 * Elle permet de charger un clavier à partir de fichiers JSON et de donné les
 * touches et les combinaisons possibles par rapport a une etiquette donné
 */
public class Clavier implements InterfaceClavier {
    private List<Touche> touches;
    private String path;
    // String = î List<String> = ["^","i"]
    private Map<String, List<String>> combinaisons;
    private String pathCombi;

    /**
     * Constructeur par défaut qui initialise le clavier avec les fichiers JSON par
     * défaut.
     */
    public Clavier() {
        this.touches = new ArrayList<>();
        this.combinaisons = new HashMap<>();
        this.path = "ressources/azerty.json";
        creerClavier();
        this.pathCombi = "ressources/combinaison.json";
        combinaison();
    }

    /**
     * Constructeur qui initialise le clavier avec un fichier JSON spécifié.
     *
     * @param path Le chemin du fichier JSON pour le clavier.
     */
    public Clavier(String path) {
        this.touches = new ArrayList<>();
        this.path = path;
        this.combinaisons = new HashMap<>();
        creerClavier();
    }

    /**
     * Constructeur qui initialise le clavier avec des fichiers JSON spécifiés pour
     * le clavier et les combinaisons.
     *
     * @param path      Le chemin du fichier JSON pour le clavier.
     * @param pathCombi Le chemin du fichier JSON pour les combinaisons.
     */
    public Clavier(String path, String pathCombi) {
        this.touches = new ArrayList<>();
        this.path = path;
        this.pathCombi = pathCombi;
        this.combinaisons = new HashMap<>();
        creerClavier();
        combinaison();
    }

    /**
     * Crée le clavier en chargeant les données à partir du fichier JSON spécifié.
     */
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

    /**
     * Charge les combinaisons de touches à partir du fichier JSON spécifié.
     */
    public void combinaison() {
        try (FileReader reader = new FileReader(this.pathCombi)) {
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
            JsonArray combi = jsonObject.getAsJsonArray("combinaison");

            for (JsonElement elem : combi) {
                JsonObject object = elem.getAsJsonObject();
                String etiq = object.get("char").getAsString();
                List<String> comList = new ArrayList<>();
                JsonArray com = object.getAsJsonArray("combi");
                for (JsonElement comElem : com) {
                    comList.add(comElem.getAsString());
                }
                this.combinaisons.put(etiq, comList);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * /**
     * Ajoute des touches mortes à une liste de touches courante.
     *
     * @param current  La liste actuelle de touches.
     * @param touches  La liste de toutes les touches disponibles.
     * @param deadKeys La liste des étiquettes des touches mortes à ajouter.
     * @param result   La liste des combinaisons résultantes.
     */
    private void addDeadKeys(List<Touche> current, List<Touche> touches, List<String> deadKeys,
            List<List<Touche>> result) {
        for (String mort : deadKeys) {
            for (Touche mortT : touches) {
                if (mortT.getEtiq().equals(mort)) {
                    // Ajoute différente combinaisons avec les différentes touches mortes trouvés
                    List<Touche> newCombination = new ArrayList<>(current);
                    newCombination.add(mortT);
                    result.add(newCombination);
                }
            }
        }
    }

    /**
     * Génère toutes les combinaisons possibles de touches en utilisant une liste de
     * combinaisons.
     *
     * @param combo   La liste des étiquettes des touches à combiner.
     * @param current La liste actuelle de touches.
     * @param result  La liste des combinaisons résultantes.
     * @param index   L'index actuel dans la liste combo.
     */
    private void generateCombinations(List<String> combo, List<Touche> current, List<List<Touche>> result, int index) {
        if (index == combo.size()) {
            result.add(new ArrayList<>(current));
            return;
        }

        String etiq = combo.get(index);

        for (Touche t : touches) {
            if (t.getEtiq().equals(etiq)) {
                // Gestion des touches mortes
                if (!t.getMorte().isEmpty()) {
                    List<List<Touche>> tempResults = new ArrayList<>();
                    addDeadKeys(current, touches, t.getMorte(), tempResults);
                    for (List<Touche> tempCurrent : tempResults) {
                        tempCurrent.add(t);
                        generateCombinations(combo, tempCurrent, result, index + 1);
                    }
                } else {
                    current.add(t);
                    // Passer a la touches suivante a ajouté dans la combinaison
                    generateCombinations(combo, current, result, index + 1);
                    // retour en arriére pour explorer les autres possibilités de touches à ajouté
                    current.remove(current.size() - 1);
                }
            }
        }
    }

    /**
     * Recherche toutes les combinaisons possibles de touches pour une étiquette
     * donnée.
     *
     * @param etiquette L'étiquette de la touche à rechercher.
     * @return La liste des combinaisons trouvées.
     */
    @Override
    public List<List<Touche>> chercheTouche(String etiquette) throws TouchNotFound {

        List<List<Touche>> res = new ArrayList<>();

        // Étape 1 : Rechercher les touches correspondantes directement dans le clavier
        for (Touche t : touches) {
            if (etiquette.equals(t.getEtiq())) {
                if (!t.getMorte().isEmpty()) {
                    // Ajout des touches mortes
                    List<List<Touche>> tempResults = new ArrayList<>();
                    addDeadKeys(new ArrayList<>(), touches, t.getMorte(), tempResults);
                    for (List<Touche> tempCurrent : tempResults) {
                        tempCurrent.add(t);
                        res.add(tempCurrent);
                    }
                } else {
                    List<Touche> singleToucheList = new ArrayList<>();
                    singleToucheList.add(t);
                    res.add(singleToucheList);
                }
            }
        }

        // Étape 2 : Chercher dans les combinaisons si aucun résultat trouvé
        if (res.isEmpty() && this.combinaisons != null) {
            for (Map.Entry<String, List<String>> entry : combinaisons.entrySet()) {
                if (entry.getKey().equals(etiquette)) {
                    List<String> combo = entry.getValue(); // Exemple : [^, i]
                    List<List<Touche>> tempCombinaisons = new ArrayList<>();

                    // générer toutes les combinaisons possibles
                    generateCombinations(combo, new ArrayList<>(), tempCombinaisons, 0);

                    // ajouter les combinaisons générées au résultat final
                    res.addAll(tempCombinaisons);
                }
            }
        }

        // Print res
        System.out.println(etiquette);
        System.out.println("Contenu de res:");
        for (List<Touche> list : res) {
            System.out.println(list);
        }

        if (res.isEmpty()) {
            throw new TouchNotFound();
        }

        return res;
    }

    public void afficherCombinaisons() {
        for (Map.Entry<String, List<String>> entry : combinaisons.entrySet()) {
            System.out.println("tour de boucle");
            System.out.println("Combinaison: " + entry.getKey() + " -> " + entry.getValue());
        }
    }

    /**
     * Retourne la liste des touches du clavier.
     *
     * @return La liste des touches.
     */
    @Override
    public List<Touche> getTouches() {
        return touches;
    }

    /**
     * Retourne la map des combinaisons de touches.
     *
     * @return La map des combinaisons de touches.
     */
    public Map<String, List<String>> getCombinaisons() {
        return combinaisons;
    }

}
