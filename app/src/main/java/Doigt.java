public enum Doigt {
    //TODO : les poids ne doivent pas être codé en dur donc ca c'est a modif 
    POUCE_D(Main.DROITE, 0.5), ANNULAIRE_D(Main.DROITE, 1.2), MAJEUR_D(Main.DROITE, 1), AURICULAIRE_D(Main.DROITE, 2),
    INDEX_D(Main.DROITE, 1.2),
    POUCE_G(Main.GAUCHE, 0.5), ANNULAIRE_G(Main.GAUCHE, 1.2), MAJEUR_G(Main.GAUCHE, 1), AURICULAIRE_G(Main.GAUCHE, 1.5),
    INDEX_G(Main.GAUCHE, 1.2);
    // AURICULAIRE_D = 2 psq il est petit est doit atteindre des touche chiantes = %^$` entrée shift effacé - ) .....

    private Main main;
    private double poids;
    private Coordonnee coord;

    private Doigt(Main m, double p) {
        this.main = m;
        main.getListDoigt().add(this);
        this.poids = p;
        this.coord = getPosBase(this);
    }

    public Main getMain() {
        return this.main;
    }

    public double getPoids() {
        return poids;
    }

    public Coordonnee getCord() {
        return this.coord;
    }

    //Les positions de base restent les mêmes et pour calculé le poids d'un doigt
    // Voir le nombre de touches qu'il atteint + la distance entre la pos de base et la touche qu'il doit atteindre 
    // + la distance est grande + le doigts doit faire d'effort + son poids est fort 

    public Coordonnee getPosBase(Doigt d) {
        Coordonnee c = null;
        switch (d) {
            case ANNULAIRE_G:
                c = new Coordonnee(2, 2);
                break;
            case ANNULAIRE_D:
                c = new Coordonnee(9, 2);
                break;
            case AURICULAIRE_G:
                c = new Coordonnee(1, 2);
                break;
            case AURICULAIRE_D:
                c = new Coordonnee(10, 2);
                break;
            case INDEX_G:
                c = new Coordonnee(4, 2);
                break;
            case INDEX_D:
                c = new Coordonnee(7, 2);
                break;
            case MAJEUR_G:
                c = new Coordonnee(3, 2);
                break;
            case MAJEUR_D:
                c = new Coordonnee(8, 2);
                break;
            case POUCE_G:
                c = new Coordonnee(4, 0);
                break;
            case POUCE_D:
                c = new Coordonnee(8, 0);
                break;
        }
        return c;
    }

    public String toString() {
        return "Doigt{" +
                "main=" + main +
                ", poids=" + poids +
                ", coord=" + coord +
                '}';
    }
}
