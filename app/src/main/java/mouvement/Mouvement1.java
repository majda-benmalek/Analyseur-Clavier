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
        this.calculScore();
    }

    public void calculScore() {
        Touche t = this.getSqTouches().get(0); // on sait qur ya un seul élement Mouvement une touche

        Doigt d = t.getDoigt();
        Coordonnee coordT = t.getCoord();
        Coordonnee coordD = d.getPosRepos();

        int distance = coordT.calculDistance(coordD);
        double score = ((distance * d.getPoids()) * this.getOccurrences()) * Main.calculEquilibre();

        // Plus le score est grand plus l'effort est haut => mouvement a minimiser
        this.score = score;
    }

    public double getScore() {
        return this.score;
    }

    public void setScore(double s) {
        this.score = s;
    }
}