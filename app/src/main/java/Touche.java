package app.src.main.java;

public class Touche {
    private enum Main {
        DROITE, GAUCHE;
    }

    public enum Doigt {
        POUCE_D(Main.DROITE), ANNUAIRE_D(Main.DROITE), MAJEUR_D(Main.DROITE), AURICULAIRE_D(Main.DROITE),
        INDEX_D(Main.DROITE),
        POUCE_G(Main.GAUCHE), ANNUAIRE_G(Main.GAUCHE), MAJEUR_G(Main.GAUCHE), AURICULAIRE_G(Main.GAUCHE),
        INDEX_G(Main.GAUCHE);

        private Main main;

        private Doigt(Main m) {
            this.main = m;
        }

        public Main getMain(){
            return this.main;
        }
    }

    private Coordonnee coord;
    private char etiq;
    private Touche touche;
    private Doigt doigt;

    public Touche(char e, Touche t, Coordonnee c,Doigt d) {
        this.coord = c;
        this.etiq = e;
        this.touche = t;
        this.doigt=d;
    }

    public Coordonnee getCoord() {
        return coord;
    }

    public char getEtiq() {
        return etiq;
    }

    public Touche getTouche() {
        return touche;
    }

    public Doigt getDoigt() {
        return doigt;
    }

}
