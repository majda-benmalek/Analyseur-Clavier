/*
 * This source file was generated by the Gradle 'init' task
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class App {
    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        // MOUVEMENT 1
        Touche t = new Touche("j", 7, 2, Doigt.INDEX_D);
        Touche t2 = new Touche("n", 7, 1, Doigt.INDEX_D);
        Touche t3 = new Touche("d", 3, 2, Doigt.INDEX_D);
        List<Touche> l = new ArrayList<>();
        l.add(t);
        List<Touche> l2 = new ArrayList<>();
        l2.add(t2);
        List<Touche> l3 = new ArrayList<>();
        l3.add(t3);
        Mouvement1 m = new Mouvement1(l, 20);
        Mouvement1 m2 = new Mouvement1(l2, 20);
        Mouvement1 m3 = new Mouvement1(l3, 20);
        // System.out.println(m);
        // System.out.println(m2);
        // System.out.println(m3);

        // MOUVEMENT 2

        // LSB + Alternance
        Touche t4 = new Touche("y", 6, 3, Doigt.INDEX_D);
        Touche t5 = new Touche("t", 6, 3, Doigt.INDEX_G);
        List<Touche> l4 = new ArrayList<>();
        l4.add(t4);
        l4.add(t5);
        Mouvement2 m4 = new Mouvement2(l4, 1);
        // System.out.println(m4);

        // SFB + ciseaux
        Touche t6 = new Touche("h", 6, 2, Doigt.INDEX_D);
        Touche t7 = new Touche("y", 6, 3, Doigt.INDEX_D);
        List<Touche> l5 = new ArrayList<>();
        l5.add(t6);
        l5.add(t7);
        Mouvement2 m5 = new Mouvement2(l5, 100);
        // System.out.println(m5);

        // Roulement interieur -> exterieur
        Touche t8 = new Touche("j", 7, 2, Doigt.INDEX_D);
        Touche t9 = new Touche("m", 10, 2, Doigt.AURICULAIRE_D);
        List<Touche> l6 = new ArrayList<>();
        l6.add(t8);
        l6.add(t9);
        Mouvement2 m6 = new Mouvement2(l6, 10);
        // System.out.println(m6);

        Analyseur a = new Analyseur("ressources/clavier_characters.txt");
        // a.afficheGramme();

        Clavier c = new Clavier();
        ArrayList<HashMap<ArrayList<Touche>, Integer>> touches = a.transformeEnTouche(c);
        // printf touches
        if (touches != null) {
            System.out.println("pas null");
            for (HashMap<ArrayList<Touche>, Integer> hashMap : touches) {
                for (HashMap.Entry<ArrayList<Touche>, Integer> entry : hashMap.entrySet()) {
                    ArrayList<Touche> ngrammes = entry.getKey();
                    Integer occ = entry.getValue();
                    System.out.println("je suis la");
                    System.out.println(ngrammes);
                }
            }
        } else {
            System.out.println("bien null");
        }

        // List<Touche> rr = new ArrayList<>();
        // // 14 = < 
        // rr.add(c.getTouches().get(14));
        // Mouvement1 e = new Mouvement1(rr, 3);
        // System.out.println(e);

        // System.out.println(Doigt.ANNULAIRE_D);
        // System.out.println(Doigt.INDEX_D);
        // System.out.println(Doigt.INDEX_G);

    }
}
