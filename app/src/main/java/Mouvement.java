
import java.util.List;

public abstract class Mouvement { //TODO SCELLER LES CLASSES
    private List<Touche> sequenceTouche;
    private int occurrence;
    // private double score;

    public Mouvement(List<Touche> t, int o) {
        this.sequenceTouche = t;
        this.occurrence = o;
        // this.calculScore();
    }

    // public abstract void calculScore();

    public int getOccurrences() {
        return this.occurrence;
    }

    public List<Touche> getSqTouches() {
        return this.sequenceTouche;
    }

    // public double getScore() {
    //     return this.score;
    // }

    // public void setScore(double s){
    //     this.score = s;
    // }

    @Override
    public String toString() {
        String s="";
        for (Touche touche : sequenceTouche) {
            s+="\t\t"+touche.toString();
        }
        return "Mouvement {\n" +
                "\tsequenceTouche = \n" + s +
                "\toccurrence = " + occurrence +
                // "\n\tscore = " + score +
                "\n}\n";
    }
}
