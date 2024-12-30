import java.util.ArrayList;
import java.util.List;

public class Touche {

    private Coordonnee coord;
    private String etiq;
    private Doigt doigt;
    // avoir les noms des touches mortes dont on a besoin pour les utilisé dans
    // l'analyseur et savoir quel coté on tape
    private List<String> touchesMortes;

    public Touche(String e, int x, int y, Doigt d) {
        this.coord = new Coordonnee(x, y);
        this.etiq = e;
        this.doigt = d;
    }

    public Touche(String e, int x, int y, Doigt d, List<String> morte) {
        this.coord = new Coordonnee(x, y);
        this.etiq = e;
        this.doigt = d;
        if (morte.isEmpty())
            this.touchesMortes = new ArrayList<>();
        else
            this.touchesMortes = morte;
    }

    public Coordonnee getCoord() {
        return coord;
    }

    public String getEtiq() {
        return etiq;
    }

    public Doigt getDoigt() {
        return doigt;
    }

    public List<String> getMorte() {
        return touchesMortes;
    }

    @Override
    public String toString() {
        // return "Touche { " +
        // coord +
        // ", etiq = " + etiq +
        // " , " + doigt +
        // " , morte = " + (touchesMortes!=null) +
        // " }\n";
        // if (!touchesMortes.isEmpty()) {
        //     return "Touche = " + etiq + " " + coord + "[Morte : " + touchesMortes.get(0) + "]";
        // }
        return "Touche = " + etiq + " " + coord;
    }
}
