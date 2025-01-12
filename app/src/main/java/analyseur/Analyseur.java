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
    
    //! méthode osef
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
            // ligne+="\n";
            // int cmpRetourLigne = -1;
            while (ligne != null) {
                ligne+="\n";
                int taille = ligne.length();
                // cmpRetourLigne++;
                for (int i = 0; i < taille; i++) {
                    for (int j = 1; j < n + 1; j++) {
                        if (i + j < taille + 1) { // TODO POUR LA COMPLEXITE ???????
                            String gramme = ligne.substring(i, i + j);
                            
                            // if (gramme.length() == 1){
                            //     System.out.println("gramme = "+gramme);
                            // }
                            majGrammes(gramme);
                            // if (gramme.length() == 0) {
                            //     cmpRetourLigne++;
                            // }
                        }
                    }
                }
                ligne = bufferedReader.readLine();
            }

            // System.out.println("nombre de retour à la ligne = " + cmpRetourLigne);
        } catch (IOException e) {
            System.out.println("Il y a un problème avec le fichier donné.");
        }
    }

    // TODO LE \N compter;
    // // TODO la aussi c'est List<List<Mouvement>>

    /*
     * - Majda m'envoie toutes les combinaisons // [[^,i],[alt, ^,i ] ] = î ->
     * parcourir ça et tej si c + grand que 3 grammes
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

        //! méthode osef
    public int tailleMouvement1(Mouvement m){
        if (m instanceof Mouvement1){
            return 1;
        } else if (m instanceof Mouvement2) {
            return 2;
        } else {
            return 3;
        }
    }

    @Override
     public List<List<Mouvement>> transformeEnTouche(Clavier c) throws TouchNotFound {
        List<List<Mouvement>> res = new ArrayList<>();

        for (HashMap<String, Integer> hashMap : nGrammes) {
            for (Map.Entry<String, Integer> entry : hashMap.entrySet()) {
                String nGrammes = entry.getKey();
                int taille = nGrammes.length();
                Integer occ = entry.getValue();
                List<List<Touche>> l = new ArrayList<>();
                List<Mouvement> lm = new ArrayList<>();
                for (int i = 0; i < taille; i++) { // îa
                    char caractere = nGrammes.charAt(i);
                    List<List<Touche>> sequenceTouches = null;
                    try {
                        sequenceTouches = c.chercheTouche(Character.toString(caractere));
                    } catch (TouchNotFound e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    int tailleSequence = sequenceTouches.size();
                    if (tailleSequence == 1) {
                        if (i == 0) { // j'ai rien a copier
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
                        // System.out.println("taille sequence != "+1);
                        if (i == 0) { // j'ai rien a copier
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
                    // *
                }
                for (List<Touche> list : l) {
                        int t = list.size();
                        // System.out.println("t = "+t);
                        Mouvement m = null;
                        if (t == 1){
                            m = new Mouvement1(list, occ);
                        }
                        else if ( t == 2){
                            m = new Mouvement2(list, occ);
                        }
                        else if (t == 3){
                            m = new Mouvement3(list, occ);
                        }
                        if (m != null){
                            // System.out.println("----------");
                            // System.out.println(tailleMouvement1(m));
                            // for (Touche touche : m.getSqTouches()) {
                            //     System.out.println(touche.getEtiq());
                            // }
                            lm.add(m);
                        }
                }
                // System.out.println("lm size "+lm.size());
                if (lm.isEmpty() == false){
                    res.add(lm);
                }
            }
        }
        // affichePitie(res);
        // System.out.println("taille res "+res.size());
        return res;
    }

    //! méthode osef
    public void affichePitie(List<List<Mouvement>> res){
        for (List<Mouvement> list : res) {
            for (Mouvement m : list) {
                // System.out.println("chui la");
                // System.out.println(tailleMouvement1(m));
                System.out.println("------------");
                for (Touche touche : m.getSqTouches()) {
                    System.out.println(touche.getEtiq());
                }
            }
        }
    }

    public int getNombre3Gramme(){
        // System.out.println("dans analyseur nombre de 3 grammes = "+ nGrammes.get(2).size());
        return nGrammes.get(2).size();
    }

    //! méthode osef
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
                if (ngrammes.equals("\n")){
                    System.out.println("gramme :\\n occ : "+occ);
                }else{
                    System.out.println("gramme: " + ngrammes + " occ: " + occ);
                }
                // }
            }
            i++;
        }
    }
}





    // // TODO LE \N compter;
    // @Override
    // // TODO la aussi c'est List<List<Mouvement>>
    // public List<Mouvement> transformeEnTouche(Clavier c) {
    //     List<Mouvement> res = new ArrayList<>();
    //     for (HashMap<String, Integer> hashMap : nGrammes) {
    //         for (Map.Entry<String, Integer> entry : hashMap.entrySet()) {
    //             String nGrammes = entry.getKey();
    //             int taille = nGrammes.length();
    //             ArrayList<Touche> sequenceTouches = new ArrayList<>();
    //             for (int i = 0; i < taille; i++) {
    //                 System.out.println("["+nGrammes+"]");
    //                 char caractere = nGrammes.charAt(i);
    //                 // System.out.println("[" + caractere + "]" + "dans transformeEnTocuhes");
    //                 List<List<Touche>> touches = c.chercheTouche(Character.toString(caractere));
    //                 sequenceTouches.add(touches.get(0).get(0)); // TODO TEJ SI C + GD QUE 3 GRAMMES
    //             }
    //             Integer occ = entry.getValue();
    //             Mouvement m;
    //             if (taille == 1) {
    //                 m = new Mouvement1(sequenceTouches, occ);
    //             } else if (taille == 2) {
    //                 m = new Mouvement2(sequenceTouches, occ);
    //             } else {
    //                 m = new Mouvement3(sequenceTouches, occ);
    //             }
    //             res.add(m);
    //         }
    //     }
    //     return res;
    // }




   // public List<List<Mouvement>> transformeEnTouche(Clavier c) {
        //     List<List<Mouvement>> res = new ArrayList<>();
        //     for (HashMap<String, Integer> hashMap : nGrammes) {
        //         for (Map.Entry<String, Integer> entry : hashMap.entrySet()) {
        //             String nGrammes = entry.getKey();
        //             int taille = nGrammes.length();
        //             Integer occ = entry.getValue();
        //             // List<Mouvement> combinaisonPossible = new ArrayList<>() ; // si je met pas null ça râle
        //             // List<List<Touche>> touches = new ArrayList<>();
        //             List<List<List<Touche>>> l = new ArrayList<>();
        //             // System.out.println("taille ngrammes  = "+taille);
        //             System.out.println(nGrammes);
        //             for (int i = 0; i < taille; i++) { // îa
        //                 // System.out.println("["+nGrammes+"]");
        //                 char caractere = nGrammes.charAt(i);
        //                 List<List<Touche>> sequenceTouches = c.chercheTouche(Character.toString(caractere));
        //                 // l.add(sequenceTouches);
        //                 // System.out.println("[" + caractere + "]" + "dans transformeEnTocuhes");
        //                 // touches.add(c.chercheTouche(Character.toString(caractere)));
        //                 // cherche Touche -> pour un char par exemple pr i ou î List<List<Touche>>
        //             }
        //             // int tailleM = l.size();
                    
    
        //             // System.out.println("l size = "+l.size());
        //             // for (List<List<Touche>> list : l) {
        //             //     int t = list.size();
        //             //     System.out.println("list taille = "+t);  
        //             //     // System.out.println("taille list list touche = "+ list.get(0)); 
        //             // }
        //             // for (List<Touche> combinaison : touches ) {
        //             //     Mouvement m=null;
        //             //     int tailleCombi = combinaison.size();
        //             //     if (tailleCombi == 1) {
        //             //         System.out.println("taille combi = "+1);
        //             //         m = new Mouvement1(combinaison, occ);
        //             //     } else if (tailleCombi == 2) {
        //             //             System.out.println("taille combi = "+2);
        //             //         m = new Mouvement2(combinaison, occ);
        //             //     } else if (tailleCombi == 3 ) {
        //             //         System.out.println("taille combi = "+3);
        //             //         m = new Mouvement3(combinaison, occ);
        //             //     }
        //             //     if (m!=null){
        //             //         combinaisonPossible.add(m);
        //             //         // System.out.println(m);
        //             //     }
        //             // }
        //             // res.add(combinaisonPossible);
        //         }
        //     }
        //     return res;
        // }


        
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