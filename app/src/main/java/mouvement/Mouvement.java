package mouvement;

import java.util.List;

import clavier.Touche;

public sealed abstract class Mouvement permits Mouvement1, Mouvement2, Mouvement3 {
    private List<Touche> sequenceTouche;
    private int occurrence;
    // private double score;

    public Mouvement(List<Touche> t, int o) {
        this.sequenceTouche = t;
        this.occurrence = o;
    }

    public int getOccurrences() {
        return this.occurrence;
    }

    public List<Touche> getSqTouches() {
        return this.sequenceTouche;
    }

    @Override
    public String toString() {
        String s = "";
        for (Touche touche : sequenceTouche) {
            s += "\t\t" + touche.toString();
        }
        return "Mouvement {\n" +
                "\tsequenceTouche = \n" + s +
                "\toccurrence = " + occurrence +
                // "\n\tscore = " + score +
                "\n}\n";
    }
}
