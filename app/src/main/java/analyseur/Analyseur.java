package analyseur;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import clavier.Clavier;
import clavier.TouchNotFound;
import clavier.Touche;
import mouvement.Mouvement;
import mouvement.Mouvement1;
import mouvement.Mouvement2;
import mouvement.Mouvement3;
import ui.Observable;
import ui.Observer;
import ui.ObserverImplm;

public class Analyseur implements InterfaceAnalyseur, Observable {
    private List<Map<String, Integer>> nGrammes;
    private String fichier;
    private List<Observer> observers = new ArrayList<>();

    public List<Observer> getObservers() {
        return observers;
    }

    public List<Map<String,Integer>> getNgrammes(){
        return nGrammes;
    }

    /**
     * Constructeur qui initialise l'analyseur et crée les nGrammes possibles
     * @param fichier      Le chemin du corpus
     */
    public Analyseur(String fichier) {
        this.nGrammes = new ArrayList<>();
        this.fichier = fichier;
        for (int i = 0; i < 3; i++) {
            this.nGrammes.add(new HashMap<>());
        }
        this.analyse(3, fichier);
    }

    /**
     * renvoie la position grammes s'il est dans la liste de gramme
     * @param s      le String qu'on veut vérifier
     * @return      la position du string renvoie -1 s'il n'est pas dans la liste 
     */
   public int dansNgrammes(String s) {
        int res = -1;
        for (int i = 0; i < 3; i++) {
            if (nGrammes.get(i).containsKey(s)) {
                res = i;
            }
        }
        return res;
    }

    /**
        met à jour la liste des grammes, soit il ajoute le string s'il ne le trouve pas dans la liste soit il augmente son occurrence
        @param s le string qu'on veut mettre à jour
    */
    public void majGrammes(String s) {
        int pos = dansNgrammes(s);
        if (pos == -1) {
            pos = s.length() - 1;
            if (pos < 0) {
                pos = 0;
            }
            nGrammes.get(pos).put(s, 1);
        } else {
            Integer ancienneOcc = nGrammes.get(pos).get(s);
            Integer nouvelleOcc = Integer.valueOf(ancienneOcc.intValue() + 1);
            nGrammes.get(pos).put(s, nouvelleOcc);
        }
    }
    

    /**
        analyse le fichier donné en argument et crée des paquets de n grammes au plus
        @param fichier le corpus qu'on analyse
        @param n le nombres de n grammes au maximum
    */
    @Override
    public void analyse(int n, String fichier) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fichier))) {
            String ligne = bufferedReader.readLine();
            while (ligne != null) {
                ligne += "\n";
                int taille = ligne.length();
                for (int i = 0; i < taille; i++) {
                    for (int j = 1; j < n + 1; j++) {
                        if (i + j < taille + 1) {
                            String gramme = ligne.substring(i, i + j);
                            majGrammes(gramme);
                        }
                    }
                }
                ligne = bufferedReader.readLine();
            }

            ObserverImplm o = new ObserverImplm(this);
            notifyObservers("analyse", o, observers);
        } catch (IOException e) {
            System.out.println("Mauvais chemin de fichiers pour le corpus");
        }
    }

    /**
       copie la liste donné en argument autant de fois qu'il a été demandé
        @param l la liste qu'on copie
        @param occ le nombres de fois qu'on doit copier chaque élément
    */
    public List<List<Touche>> copieOcc(List<List<Touche>> l, int occ) {
        List<List<Touche>> res = new ArrayList<>();
        for (List<Touche> list : l) {
            for (int i = 0; i < occ; i++) {
                res.add(list);
            }
        }
        return res;
    }

    /**
       transforme les nGrammes en séquence de touches
        @param c le clavier dont on veut étudier 
        @return la liste séquence de touches
    */
    @Override
    public List<List<Mouvement>> transformeEnTouche(Clavier c) throws TouchNotFound {
        List<List<Mouvement>> res = new ArrayList<>();

        for (Map<String, Integer> hashMap : nGrammes) {
            for (Map.Entry<String, Integer> entry : hashMap.entrySet()) {
                String nGrammes = entry.getKey();
                int taille = nGrammes.length();
                Integer occ = entry.getValue();
                List<List<Touche>> l = new ArrayList<>();
                List<Mouvement> lm = new ArrayList<>();
                for (int i = 0; i < taille; i++) { // îa
                    char caractere = nGrammes.charAt(i);
                    List<List<Touche>> sequenceTouches = null;
                    sequenceTouches = c.chercheTouche(Character.toString(caractere));
                    int tailleSequence = sequenceTouches.size();
                    if (tailleSequence == 1) {
                        if (i == 0) { 
                            for (int j = 0; j < tailleSequence; j++) {
                                l.add(sequenceTouches.get(j));
                            }
                        } else {
                            for (List<Touche> list : l) {
                                for (Touche touche : sequenceTouches.get(0)) {
                                    list.add(touche);
                                }
                            }
                        }
                    } else {
                        if (i == 0) { // je n'ai rien à copier
                            for (int j = 0; j < tailleSequence; j++) {
                                l.add(sequenceTouches.get(j));
                            }
                        } else {
                            List<List<Touche>> inter = copieOcc(l, tailleSequence);
                            int tailleInter = inter.size();
                            for (int j = 1; j < tailleInter + 1; j++) {
                                int k = j % tailleSequence;
                                for (Touche touche : sequenceTouches.get(k)) {
                                    inter.get(j - 1).add(touche);
                                }
                            }
                            l = inter;
                        }
                    }
                }
                for (List<Touche> list : l) {
                    int t = list.size();
                    Mouvement m = null;
                    if (t == 1) {
                        m = new Mouvement1(list, occ);
                    } else if (t == 2) {
                        m = new Mouvement2(list, occ);
                    } else if (t == 3) {
                        m = new Mouvement3(list, occ);
                    }
                    if (m != null) {
                        lm.add(m);
                    }
                }
                if (lm.isEmpty() == false) {
                    res.add(lm);
                }
            }
        }
        return res;
    }

    /**
     @return retourne le nombres de 3 grammes  
    */
    public int getNombre3Gramme() {
        return nGrammes.get(2).size();
    }
}