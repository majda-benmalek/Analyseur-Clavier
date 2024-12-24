/*
 * This source file was generated by the Gradle 'init' task
 */

import java.util.ArrayList;
import java.util.List;

public class App {
    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        Analyseur a = new Analyseur("app/src/main/java/fic.txt");
        // a.afficheGramme();
        // Pas une redirection vers intérieur
        // Touche t = new Touche('a', new Coordonnee(1,3), Doigt.AURICULAIRE_G);
        // Touche t2 = new Touche('z',new Coordonnee(2, 3),Doigt.ANNULAIRE_G);
        // Touche t3 = new Touche('e',new Coordonnee(3, 3),Doigt.MAJEUR_G);
        // List<Touche> l = new ArrayList<>();
        // l.add(t);
        // l.add(t2);
        // l.add(t3);
        // System.out.println("Pas une redirection vers intérieur");
        // Mouvement3 m = new Mouvement3(l, 1);
        // m.calculScore();
        // System.out.println("m score = "+m.getScore());
        // System.out.println(m);
        // System.out.println("-------------------");
        
        // pas une redi vers l'extérieur
        // List<Touche> l2 = new ArrayList<>();
        // l2.add(t3);
        // l2.add(t2);
        // l2.add(t);
        // System.out.println("pas une redi vers l'extérieur");
        // Mouvement3 m2 = new Mouvement3(l2, 1);
        // m2.calculScore();
        // System.out.println("m2 score = "+m2.getScore());
        // System.out.println("-------------------");
        // System.out.println(m2);

        // redi int ext int
        // List<Touche> l3 = new ArrayList<>();
        // l3.add(t3);
        // l3.add(t);
        // l3.add(t3);
        // System.out.println("redi int ext int");
        // Mouvement3 m3 = new Mouvement3(l3, 1);
        // m3.calculScore();
        // System.out.println("m3 score = "+m3.getScore());
        // System.out.println("-------------------");
        // System.out.println(m3);

        // redi ext int ext

        // List<Touche> l4 = new ArrayList<>();
        // l4.add(t);
        // l4.add(t3);
        // l4.add(t);
        // System.out.println("redi ext int ext");
        // Mouvement3 m4 = new Mouvement3(l4, 1);
        // m4.calculScore();
        // System.out.println("m4 score = "+m4.getScore());
        // System.out.println("-------------------");
        // // System.out.println(m4);

        // // skipgramme 

        // // Touche t4 = new Touche('e', new Coordonnee(1,3), Doigt.AURICULAIRE_G);
        // Touche t5 = new Touche('m',new Coordonnee(10, 2),Doigt.AURICULAIRE_D);
        // Touche t6 = new Touche('d',new Coordonnee(3, 2),Doigt.MAJEUR_G);
        // List<Touche> l5 = new ArrayList<>();
        // l5.add(t3);
        // l5.add(t5);
        // l5.add(t6);
        // System.out.println("test skipgramme ");
        // Mouvement3 m5 = new Mouvement3(l5, 1);
        // m5.calculScore();
        // System.out.println("m5 score = "+m5.getScore());
        // System.out.println("-------------------");
        // // System.out.println(m5);

        // Analyseur a = new Analyseur("app/src/main/java/fic.txt");
        // a.afficheGramme();
        // Touche t = new Touche('j', new Coordonnee(7,2), Doigt.INDEX_D);
        // Touche t2 = new Touche('n',new Coordonnee(7, 1),Doigt.INDEX_D);
        // Touche t3 = new Touche('d',new Coordonnee(3, 2),Doigt.INDEX_D);
        // List<Touche> l = new ArrayList<>();
        // l.add(t);
        // List<Touche> l2 = new ArrayList<>();
        // l2.add(t2);
        // List<Touche> l3 = new ArrayList<>();
        // l3.add(t3);
        // Mouvement1 m = new Mouvement1(l, 20);
        // Mouvement1 m2 = new Mouvement1(l2, 20);
        // Mouvement1 m3 = new Mouvement1(l3, 20);
        // m.calculScore();
        // m2.calculScore();;
        // m3.calculScore();
        // System.out.println(m);
        // System.out.println(m2);
        // System.out.println(m3);
        // MOUVEMENT 1
        Touche t = new Touche('j', new Coordonnee(7, 2), Doigt.INDEX_D);
        Touche t2 = new Touche('n', new Coordonnee(7, 1), Doigt.INDEX_D);
        Touche t3 = new Touche('d', new Coordonnee(3, 2), Doigt.INDEX_D);
        List<Touche> l = new ArrayList<>();
        l.add(t);
        List<Touche> l2 = new ArrayList<>();
        l2.add(t2);
        List<Touche> l3 = new ArrayList<>();
        l3.add(t3);
        Mouvement1 m = new Mouvement1(l, 20);
        Mouvement1 m2 = new Mouvement1(l2, 20);
        Mouvement1 m3 = new Mouvement1(l3, 20);
        System.out.println(m);
        System.out.println(m2);
        System.out.println(m3);

        // MOUVEMENT 2

        // LSB + Alternance
        Touche t4 = new Touche('y', new Coordonnee(6, 3), Doigt.INDEX_D);
        Touche t5 = new Touche('t', new Coordonnee(6, 3), Doigt.INDEX_G);
        List<Touche> l4 = new ArrayList<>();
        l4.add(t4);
        l4.add(t5);
        Mouvement2 m4 = new Mouvement2(l4, 1);
        System.out.println(m4);

        // SFB + ciseaux
        Touche t6 = new Touche('h', new Coordonnee(6, 2), Doigt.INDEX_D);
        Touche t7 = new Touche('y', new Coordonnee(6, 3), Doigt.INDEX_D);
        List<Touche> l5 = new ArrayList<>();
        l5.add(t6);
        l5.add(t7);
        Mouvement2 m5 = new Mouvement2(l5, 100);
        System.out.println(m5);

        // Roulement interieur -> exterieur
        Touche t8 = new Touche('j', new Coordonnee(7, 2), Doigt.INDEX_D);
        Touche t9 = new Touche('m', new Coordonnee(10, 2), Doigt.AURICULAIRE_D);
        List<Touche> l6 = new ArrayList<>();
        l6.add(t8);
        l6.add(t9);
        Mouvement2 m6 = new Mouvement2(l6, 10);
        System.out.println(m6);

    }
}
