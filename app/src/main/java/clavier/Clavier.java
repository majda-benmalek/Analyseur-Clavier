package clavier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.*;
import ui.Observable;
import ui.Observer;
import ui.ObserverImplm;

import java.io.FileReader;
import java.io.IOException;

/**
 * La classe Clavier représente un clavier avec des touches et leurs
 * combinaisons possibles
 * Elle permet de charger un clavier à partir de fichiers JSON et de donné les
 * touches et les combinaisons possibles par rapport a une etiquette donné
 */
public class Clavier implements InterfaceClavier, Observable {
    private List<Touche> touches;
    private String path;
    // String = î List<String> = ["^","i"]
    private Map<String, List<String>> combinaisons;
    private String pathCombi;
    private List<Observer> observers = new ArrayList<>();

    /**
     * Constructeur par défaut qui initialise le clavier avec les fichiers JSON par
     * défaut.
          * @throws IOException 
          */
         public Clavier() throws IOException {
        this.touches = new ArrayList<>();
        this.combinaisons = new HashMap<>();
        this.path = "ressources/clavier/azerty.json"; // TODO pb d'accès
        creerClavier();
        this.pathCombi = "ressources/clavier/combinaison_azerty.json";
        combinaison();
    }

    /**
     * Constructeur qui initialise le clavier avec un fichier JSON spécifié.
     *
     * @param path Le chemin du fichier JSON pour le clavier.
          * @throws IOException 
          */
         public Clavier(String path) throws IOException {
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
    public Clavier(String path, String pathCombi) throws IOException{
        this.touches = new ArrayList<>();
        this.path = path;
        this.pathCombi = pathCombi;
        this.combinaisons = new HashMap<>();
        creerClavier();
        combinaison();
    }

    /**
     * Crée le clavier en chargeant les données à partir du fichier JSON spécifié.
          * @throws IOException 
          */
         @Override
         public void creerClavier() throws IOException {
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
                if (!toucheJson.get("doigt").isJsonNull()) {
                    doigt = Doigt.valueOf(toucheJson.get("doigt").getAsString().toUpperCase());
                    doigt.compteOccDoigts();
                }
                this.touches.add(new Touche(touche, x, y, doigt));
            }
            Doigt.calculPoids();
            ObserverImplm c = new ObserverImplm(this);
            notifyObservers("Clavier", c, observers);
        } catch (IOException e) {
            System.out.println("Mauvais chemin de fichier pour le clavier.");
            throw e;
        }
    }

    /**
     * Charge les combinaisons de touches à partir du fichier JSON spécifié.
          * @throws IOException 
          */
         public void combinaison() throws IOException {
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
            ObserverImplm c = new ObserverImplm(this);
            notifyObservers("Combinaisons", c, observers);

        } catch (IOException e) {
            System.out.println("Mauvais chemin de fichiers pour les combinaisons.");
            throw e;
        }
    }

    /**
     * Génère toutes les combinaisons possibles de touches à partir d'une liste de
     * combinaisons de chaînes.
     *
     * @param combo   La liste des combinaisons de chaînes à traiter.
     * @param current La liste actuelle des touches en cours de combinaison.
     * @param result  La liste des résultats contenant toutes les combinaisons
     *                générées.
     * @param index   L'index actuel dans la liste des combinaisons de chaînes.
     * @throws TouchNotFound Si une touche n'est pas trouvée lors de la recherche.
     */
    private void generateCombinations(List<String> combo, List<Touche> current, List<List<Touche>> result, int index)
            throws TouchNotFound {
        if (index == combo.size()) {
            result.add(new ArrayList<>(current));
            return;
        }

        List<List<Touche>> toucheCombos = null;
        toucheCombos = chercheTouche(combo.get(index));
        for (List<Touche> toucheCombo : toucheCombos) {
            current.addAll(toucheCombo);
            generateCombinations(combo, current, result, index + 1);
            current.removeAll(toucheCombo);
        }
    }

    /**
     * Recherche toutes les combinaisons possibles de touches pour une étiquette
     * donnée.
     *
     * @param etiquette L'étiquette de la touche à rechercher.
     * @return La liste des combinaisons trouvées.
     * @throws TouchNotFound Si une touche n'est pas trouvée lors de la
     *                       recherche.
     */
    @Override
    public List<List<Touche>> chercheTouche(String etiquette) throws TouchNotFound {

        List<List<Touche>> res = new ArrayList<>();

        // Étape 1 : Rechercher les touches correspondantes directement dans le clavier
        for (Touche t : touches) {
            if (etiquette.equals(t.getEtiq())) {
                List<Touche> singleToucheList = new ArrayList<>();
                singleToucheList.add(t);
                res.add(singleToucheList);
            }
        }

        // Étape 2 : Chercher dans les combinaisons si aucun résultat trouvé
        if (this.combinaisons != null) {
            for (Map.Entry<String, List<String>> entry : combinaisons.entrySet()) {
                if (entry.getKey().equals(etiquette)) {
                    List<String> combo = entry.getValue(); // Exemple : [^, i]
                    List<List<Touche>> tempCombinaisons = new ArrayList<>();
                    // générer toutes les combinaisons possibles
                    generateCombinations(combo, new ArrayList<>(), tempCombinaisons, 0);
                    res.addAll(tempCombinaisons);
                }
            }
        }

        if (res.isEmpty()) {
            System.out.println(etiquette);
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

    public List<Observer> getObservers() {
        return observers;
    }

}
