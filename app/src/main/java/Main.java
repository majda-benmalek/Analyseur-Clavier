import java.util.ArrayList;
import java.util.List;

public enum Main {
    DROITE, GAUCHE;

    private static List<Doigt> d = new ArrayList<>();
    private static List<Doigt> g = new ArrayList<>();

    @Override
    public String toString() {
        return "Main{" +
                "name=" + name() +
                '}';
    }

    public List<Doigt> getListDoigt() {
        if (this == Main.DROITE) {
            return this.d;
        } else {
            return this.g;
        }
    }

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
        double effortG = poidsG / poidsTotal; // > 0.5 = main gauche fais + d'effort  
        double effortD = poidsD / poidsTotal; // > 0.5 = main droite fais + d'effort 

        double distanceEquilibre = Math.abs(effortG - 0.5) + Math.abs(effortD - 0.5); // = 0 alors l'effort est équilibré 

        // indique si l'effort entre les 2 mains est equilibré
        return distanceEquilibre;
    }
}