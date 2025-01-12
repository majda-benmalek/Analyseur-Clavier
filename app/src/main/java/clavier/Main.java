package clavier;

import java.util.ArrayList;
import java.util.List;

/**
 * L'énumération Main représente les deux mains utilisées pour taper sur un
 * clavier.
 * Chaque main a une liste de doigts associés et peut calculer l'équilibre de
 * l'effort entre les deux mains.
 */
public enum Main {
    DROITE, GAUCHE;

    private static List<Doigt> d = new ArrayList<>();
    private static List<Doigt> g = new ArrayList<>();

    /**
     * Retourne une représentation sous forme de chaîne de caractères de cet objet
     * Main.
     *
     * @return Une chaîne de caractères représentant cet objet Main.
     */
    @Override
    public String toString() {
        return "Main = " + name();
    }

    /**
     * Retourne la liste des doigts associés à cette main.
     *
     * @return La liste des doigts associés à cette main.
     */
    public List<Doigt> getListDoigt() {
        if (this == Main.DROITE) {
            return d;
        } else if (this == Main.GAUCHE) {
            return g;
        }
        return null;
    }

    /**
     * Calcule l'équilibre de l'effort entre les deux mains.
     * Le poids de chaque main est calculé en additionnant les poids des doigts
     * associés.
     * L'équilibre est mesuré par la distance par rapport à un effort équilibré
     * (0.5).
     *
     * @return La distance par rapport à un effort équilibré entre les deux mains.
     */
    public static double calculEquilibre() {
        double poidsG = 0.0;
        double poidsD = 0.0;

        for (Doigt doigt : d) {
            poidsD += doigt.getPoids();
        }

        for (Doigt doigt : g) {
            poidsG += doigt.getPoids();
        }

        double poidsTotal = (poidsD + poidsG);
        double effortG = poidsG / poidsTotal; // > 0.5 = main gauche fait plus d'effort
        double effortD = poidsD / poidsTotal; // > 0.5 = main droite fait plus d'effort

        double distanceEquilibre = Math.abs(effortG - 0.5) + Math.abs(effortD - 0.5); // = 0 alors l'effort est
                                                                                      // équilibré

        // indique si l'effort entre les 2 mains est équilibré
        return distanceEquilibre;
    }
}