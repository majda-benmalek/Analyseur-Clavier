package mouvement;

import java.util.List;

import clavier.Doigt;
import clavier.Main;
import clavier.Touche;

/**
 * La classe Mouvement1 représente un mouvement sur le clavier avec un score
 * calculé basé sur la difficulté d'accès à la touche.
 * Elle hérite de la classe Mouvement.
 */
public final class Mouvement1 extends Mouvement {
    private double score;

    public Mouvement1(List<Touche> l, int o) {
        super(l, o);
    }

    public void calculScore() {
        Touche t = this.getSqTouches().get(0); // on sait qur ya un seul élement Mouvement une touche
        // Pour les mains :
        // Pour les y les 2 mains on le même efforts vu qu'ils sont au même niveau
        // ? donné des coordonnées au doigts/main
        // main gauche : x < 7 + dur a accéder
        // main droite : x > 7 + dur a accéder
        // + le score est haut plus la touche est compliqué d'accés
        // + injecté la distance entre les 2 main et l'equilibre absolue

        // Dans le repére : x vas de 0 a 14, y vas de 0 a 4

        // Pour les doigts :
        // PouceG PouceD : La touche espace(x = 4 ou 3<x<9, y = 0) (coord de base :
        // (4,0) / (8,0))
        // Index G : x = 6/7 , y = 0 à 3 (coord de base : (7,2))
        // Majeur G : x = 8, y = 0 à 3 (coord de base : (8,2))
        // Annuaire G : x = 9, y = 0 à 4 (coord de base : (9,2))
        // Auriculaire G : x = de 10 à 14, y = 0 à 4 (coord de base : (10,2))

        // Index D : x = 4/5, y = 0 à 3 (coord de base : (4,2))
        // Majeur D : x = 3 , y = 0 à 3 (coord de base : (3,2))
        // Annuaire D : x = 2, y = 0 à 4 (coord de base : (2,2))
        // Auriculaire D : x = 0/1 , y = 0 à 4 (coord de base : (1,2))

        Doigt d = t.getDoigt();
        Coordonnee coordT = t.getCoord();
        Coordonnee coordD = d.getPosRepos();

        int distance = coordT.calculDistance(coordD);   
        // System.out.println(distance);
        // System.out.println((t.getCoord().calculDistance(d.getPosRepos()) * d.getPoids()));
        // System.out.println((t.getCoord().calculDistance(d.getPosRepos())));
        double score = ((distance * d.getPoids()) * this.getOccurrences()) * Main.calculEquilibre();

        // Plus le score est grand plus l'effort est haut => mouvement a minimiser (pas
        // ouf)
        this.setScore(score);
    }

    public double getScore() {
        this.calculScore();
        return this.score;
    }

    public void setScore(double s) {
        this.score = s;
    }
}