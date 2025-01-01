public enum Doigt {
    POUCE_D(Main.DROITE), ANNULAIRE_D(Main.DROITE), MAJEUR_D(Main.DROITE), AURICULAIRE_D(Main.DROITE),
    INDEX_D(Main.DROITE),
    POUCE_G(Main.GAUCHE), ANNULAIRE_G(Main.GAUCHE), MAJEUR_G(Main.GAUCHE), AURICULAIRE_G(Main.GAUCHE),
    INDEX_G(Main.GAUCHE);
    // AURICULAIRE_D = 2 psq il est petit est doit atteindre des touche chiantes =
    // %^$` entrée shift effacé - ) .....

    private Main main;
    private double poids;
    private Coordonnee coord;// TODO renommer coordoonée par pos de repos nan + logique
    private String nomDoigt;
    private int occ;

    private Doigt(Main m) {
        this.main = m;
        main.getListDoigt().add(this);
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

    // Les positions de base restent les mêmes et pour calculé le poids d'un doigt
    // Voir le nombre de touches qu'il atteint + la distance entre la pos de base et
    // la touche qu'il doit atteindre
    // + la distance est grande + le doigts doit faire d'effort + son poids est fort

    public Coordonnee getPosBase(Doigt d) {
        Coordonnee c = null;
        switch (d) {
            case ANNULAIRE_G:
                c = new Coordonnee(2, 2);
                this.nomDoigt = "ANNULAIRE_G";
                break;
            case ANNULAIRE_D:
                c = new Coordonnee(9, 2);
                this.nomDoigt = "ANNULAIRE_D";
                break;
            case AURICULAIRE_G:
                c = new Coordonnee(1, 2);
                this.nomDoigt = "AURICULAIRE_G";
                break;
            case AURICULAIRE_D:
                c = new Coordonnee(10, 2);
                this.nomDoigt = "AURICULAIRE_D";
                break;
            case INDEX_G:
                c = new Coordonnee(4, 2);
                this.nomDoigt = "INDEX_G";
                break;
            case INDEX_D:
                c = new Coordonnee(7, 2);
                this.nomDoigt = "INDEX_D";
                break;
            case MAJEUR_G:
                c = new Coordonnee(3, 2);
                this.nomDoigt = "MAJEUR_G";
                break;
            case MAJEUR_D:
                c = new Coordonnee(8, 2);
                this.nomDoigt = "MAJEUR_D";
                break;
            case POUCE_G:
                c = new Coordonnee(4, 0);
                this.nomDoigt = "POUCE_G";
                break;
            case POUCE_D:
                c = new Coordonnee(8, 0);
                this.nomDoigt = "POUCE_D";
                break;
        }
        return c;
    }

    public void compteOccDoigts() {
        this.occ = this.occ + 1;
    }

    public static void calculPoids() {
        int total = 0;
        for (Doigt doigt : Doigt.values()) {
            total = total + doigt.occ;
        }
        System.out.println(total);
        for (Doigt doigt : Doigt.values()) {
            doigt.poids = (double) doigt.occ / total;
            doigt.poids = doigt.poids * 10;  // pourcentage 
        }
    }

    public String toString() {
        return "Doigt { " +
                main + " "
                + nomDoigt + " " +
                " , poids = " + poids +
                " , " + coord +
                " , " + occ +
                '}';
    }
}
