package clavier;

import java.util.ArrayList;
import java.util.List;

import mouvement.Coordonnee;

/**
 * La classe Touche représente une touche sur un clavier.
 * Chaque touche a une coordonnée, une étiquette, un doigt associé et une liste
 * de touches mortes.
 */
public class Touche {

    private Coordonnee coord;
    private String etiq;
    private Doigt doigt;
    private List<String> touchesMortes;

    /**
     * Constructeur pour initialiser une touche avec une étiquette, des coordonnées
     * et un doigt associé.
     *
     * @param e L'étiquette de la touche.
     * @param x La coordonnée x de la touche.
     * @param y La coordonnée y de la touche.
     * @param d Le doigt associé à la touche.
     */
    public Touche(String e, int x, int y, Doigt d) {
        this.coord = new Coordonnee(x, y);
        this.etiq = e;
        this.doigt = d;
        this.touchesMortes = new ArrayList<>();
    }

    /**
     * Constructeur pour initialiser une touche avec une étiquette, des coordonnées,
     * un doigt associé et une liste de touches mortes.
     *
     * @param e     L'étiquette de la touche.
     * @param x     La coordonnée x de la touche.
     * @param y     La coordonnée y de la touche.
     * @param d     Le doigt associé à la touche.
     * @param morte La liste des touches mortes associées à cette touche.
     */
    public Touche(String e, int x, int y, Doigt d, List<String> morte) {
        this.coord = new Coordonnee(x, y);
        this.etiq = e;
        this.doigt = d;
        if (morte.isEmpty())
            this.touchesMortes = new ArrayList<>();
        else
            this.touchesMortes = morte;
    }

    /**
     * Retourne la coordonnée de la touche.
     *
     * @return La coordonnée de la touche.
     */
    public Coordonnee getCoord() {
        return coord;
    }

    /**
     * Retourne l'étiquette de la touche.
     *
     * @return L'étiquette de la touche.
     */
    public String getEtiq() {
        return etiq;
    }

    /**
     * Retourne le doigt associé à la touche.
     *
     * @return Le doigt associé à la touche.
     */
    public Doigt getDoigt() {
        return doigt;
    }

    /**
     * Retourne la liste des touches mortes associées à cette touche.
     *
     * @return La liste des touches mortes associées à cette touche.
     */
    public List<String> getMorte() {
        return touchesMortes;
    }

    /**
     * Retourne une représentation sous forme de chaîne de caractères de cet objet
     * Touche.
     *
     * @return Une chaîne de caractères représentant cet objet Touche.
     */
    @Override
    public String toString() {
        return "Touche = " + etiq + " " + coord ;
    }
}