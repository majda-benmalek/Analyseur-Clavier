public class Touche {

    private Coordonnee coord;
    private char etiq;
    private Doigt doigt;

    public Touche(char e, Coordonnee c, Doigt d) {
        this.coord = c;
        this.etiq = e;
        this.doigt = d;
    }

    public Coordonnee getCoord() {
        return coord;
    }

    public char getEtiq() {
        return etiq;
    }

    public Doigt getDoigt() {
        return doigt;
    }

    // @Override
    // public String toString() {
    //     return "Touche{" +
    //             "coord=" + coord +
    //             ", etiq=" + etiq +
    //             ", doigt=" + doigt +
    //             '}';
    // }

    @Override
    public String toString() {
        return "Touche { " +
                coord +
                ", etiq = " + etiq +
                " , "+ doigt +
                " }\n";
    }
}
