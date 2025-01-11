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

//TODO est ce qu'on peut avoir abcde\nazertyuik,nbv Et du coup faudrait compter e\n et pas e\ ???

public class Analyseur implements InterfaceAnalyseur {
    private ArrayList<HashMap<String, Integer>> nGrammes;
    private String fichier;

    public Analyseur(String fichier) {// TODO Mettre un int ds le param du constructeur pr savoir on construit des ...
                                      // grammes ?
        this.nGrammes = new ArrayList<>();
        this.fichier = fichier;
        for (int i = 0; i < 3; i++) {
            this.nGrammes.add(new HashMap<>());
        }
        this.analyse(3, fichier);
    }

    int dansNgrammes(String s) {
        int res = -1;
        for (int i = 0; i < 3; i++) {
            if (nGrammes.get(i).containsKey(s)) {
                res = i;
            }
        }
        return res;
    }

    void majGrammes(String s) {
        int pos = dansNgrammes(s);
        if (pos == -1) {
            // System.out.println("s = "+s);
            pos = s.length() - 1;
            // System.out.println("if pos = "+pos);
            if (pos < 0) {
                pos = 0;
            }
            nGrammes.get(pos).put(s, 1);
        } else {
            // System.out.println("else pos = "+pos);
            Integer ancienneOcc = nGrammes.get(pos).get(s);
            Integer nouvelleOcc = Integer.valueOf(ancienneOcc.intValue() + 1);
            nGrammes.get(pos).put(s, nouvelleOcc);
        }

        // if (s.equals("\n")){
        // System.out.println("c'est un slash n");
        // }
    }

    boolean aLaisser(String s) {
        return s.equals("\r") || s.equals("\t");
        // || s.isBlank();
        // s.equals("\n") ||
    }

    @Override
    public void analyse(int n, String fichier) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fichier))) {
            // BufferedReader bufferedReader = new BufferedReader(new FileReader(fichier));
            String ligne = bufferedReader.readLine();
            int cmpRetourLigne = -1;
            while (ligne != null) {
                int taille = ligne.length();
                cmpRetourLigne++;
                for (int i = 0; i < taille; i++) {
                    for (int j = 1; j < n + 1; j++) {
                        if (i + j < taille + 1) { // TODO POUR LA COMPLEXITE ???????
                            String gramme = ligne.substring(i, i + j);
                            // if (gramme.length() == 1){
                            // System.out.println("gramme = "+gramme);
                            // }
                            majGrammes(gramme);
                            if (gramme.length() == 0) {
                                cmpRetourLigne++;
                            }
                        }
                    }
                }
                ligne = bufferedReader.readLine();
            }
            System.out.println("nombre de retour à la ligne = " + cmpRetourLigne);
        } catch (IOException e) {
            System.out.println("Il y a un problème avec le fichier donné.");
        }
    }

    // @Override
    // public void transformeEnTouche(Clavier c) {
    // ArrayList<HashMap<ArrayList<Touche>,Integer>> res=new ArrayList<>();
    // for (int i = 0; i < nGrammes.size(); i++) {
    // res.add(new HashMap<>());
    // }
    // for (HashMap<String,Integer> hashMap : nGrammes) {
    // for (Map.Entry<String,Integer> entry : hashMap.entrySet())
    // {
    // String nGrammes = entry.getKey();
    // ArrayList<Touche> l = new ArrayList<>();
    // for (int i = 0;i<nGrammes.length();i++){
    // char caractere = nGrammes.charAt(i);
    // Touche t = c.chercheTouche(caractere);
    // l.add(t);
    // }
    // Integer occ = entry.getValue();
    // res.get(nGrammes.length()).put(l, occ);
    // }
    // }
    // }

    // TODO LE \N compter;
    @Override
    // TODO la aussi c'est List<List<Mouvement>>
    public List<Mouvement> transformeEnTouche(Clavier c) {
        List<Mouvement> res = new ArrayList<>();
        for (HashMap<String, Integer> hashMap : nGrammes) {
            for (Map.Entry<String, Integer> entry : hashMap.entrySet()) {
                String nGrammes = entry.getKey();
                int taille = nGrammes.length();
                ArrayList<Touche> sequenceTouches = new ArrayList<>();
                for (int i = 0; i < taille; i++) {
                    System.out.println("[" + nGrammes + "]");
                    char caractere = nGrammes.charAt(i);
                    // System.out.println("[" + caractere + "]" + "dans transformeEnTocuhes");
                    List<List<Touche>> touches = null;
                    try {
                        touches = c.chercheTouche(Character.toString(caractere));
                    } catch (TouchNotFound e) {
                        e.printStackTrace();
                    }
                    sequenceTouches.add(touches.get(0).get(0)); // TODO TEJ SI C + GD QUE 3 GRAMMES
                }
                Integer occ = entry.getValue();
                Mouvement m;
                if (taille == 1) {
                    m = new Mouvement1(sequenceTouches, occ);
                } else if (taille == 2) {
                    m = new Mouvement2(sequenceTouches, occ);
                } else {
                    m = new Mouvement3(sequenceTouches, occ);
                }
                res.add(m);
            }
        }
        return res;
    }

    public void afficheGramme() {
        int i = 0;
        for (HashMap<String, Integer> hashMap : nGrammes) {
            System.out.println("i = " + i);
            for (Map.Entry<String, Integer> entry : hashMap.entrySet()) {
                String ngrammes = entry.getKey();
                Integer occ = entry.getValue();
                // if (ngrammes == null || ngrammes.isEmpty()) {
                // System.out.println("Entrée invalide détectée : clé vide ou nulle !");
                // }else{
                System.out.println("gramme: " + ngrammes + " occ: " + occ);
                // }
            }
            i++;
        }
    }
}
