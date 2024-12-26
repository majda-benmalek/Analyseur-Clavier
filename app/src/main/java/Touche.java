import java.util.List;

public class Touche {

    private Coordonnee coord;
    private String etiq;
    private Doigt doigt;
    private List<Touche> touchesMortes;

    public Touche(String e, int x, int y, Doigt d) {
        this.coord = new Coordonnee(x, y);
        this.etiq = e;
        this.doigt = d;
    }

    public Touche(String e, int x, int y, Doigt d, List<Touche> morte) {
        this.coord = new Coordonnee(x, y);
        this.etiq = e;
        this.doigt = d;
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

    public List<Touche> getMorte(){
        return touchesMortes;
    }

    @Override
    public String toString() {
        // return "Touche { " +
        //         coord +
        //         ", etiq = " + etiq +
        //         " , " + doigt +
        //         " , morte = " + (touchesMortes!=null) +
        //         " }\n";
        return "Touche = " + etiq + " " ;
    }
}
