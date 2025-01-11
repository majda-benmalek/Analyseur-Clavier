package clavier;

import mouvement.Coordonnee;

/**
 * L'énumération Doigt représente les doigts de la main utilisés pour taper sur
 * un clavier.
 * Chaque doigt a une main associée, un poids, une position de repos, un nom et
 * un compteur d'occurrences.
 */
public enum Doigt {
    POUCE_D(Main.DROITE), ANNULAIRE_D(Main.DROITE), MAJEUR_D(Main.DROITE), AURICULAIRE_D(Main.DROITE),
    INDEX_D(Main.DROITE),
    POUCE_G(Main.GAUCHE), ANNULAIRE_G(Main.GAUCHE), MAJEUR_G(Main.GAUCHE), AURICULAIRE_G(Main.GAUCHE),
    INDEX_G(Main.GAUCHE);

    private Main main;
    private double poids;
    private Coordonnee posRepos;
    private String nomDoigt;
    private int occ;

    /**
     * Constructeur pour initialiser un doigt avec la main associée.
     *
     * @param m La main associée au doigt.
     */
    private Doigt(Main m) {
        this.main = m;
        main.getListDoigt().add(this);
        this.posRepos = setPosRepos(this);
    }

    /**
     * Retourne la main associée au doigt.
     *
     * @return La main associée au doigt.
     */
    public Main getMain() {
        return this.main;
    }

    /**
     * Retourne le poids du doigt.
     *
     * @return Le poids du doigt.
     */
    public double getPoids() {
        return poids;
    }

    /**
     * Retourne la position de repos du doigt.
     *
     * @return La position de repos du doigt.
     */
    public Coordonnee getPosRepos() {
        return this.posRepos;
    }

    /**
     * Retourne le nom du doigt.
     *
     * @return Le nom du doigt.
     */
    public String getNomDoigt() {
        return nomDoigt;
    }

    /**
     * Retourne le compteur d'occurrences du doigt.
     *
     * @return Le compteur d'occurrences du doigt.
     */
    public int getOcc() {
        return occ;
    }

    /**
     * Réinitialise le compteur d'occurrences du doigt à zéro.
     */
    public void setOcc() {
        this.occ = 0;
    }

    /**
     * Définit la position de repos du doigt en fonction du doigt spécifié.
     *
     * @param d Le doigt pour lequel définir la position de repos.
     * @return La position de repos du doigt.
     */
    public Coordonnee setPosRepos(Doigt d) {
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

    /**
     * Incrémente le compteur d'occurrences du doigt.
     */
    public void compteOccDoigts() {
        this.occ = this.occ + 1;
    }

    /**
     * Calcule le poids de chaque doigt en fonction de ses occurrences.
     * Le poids est calculé comme le ratio des occurrences d'un doigt par rapport au
     * total des occurrences, multiplié par 10 pour obtenir un pourcentage.
     */
    public static void calculPoids() {
        int total = 0;
        for (Doigt doigt : Doigt.values()) {
            total = total + doigt.occ;
        }
        // System.out.println(total);
        for (Doigt doigt : Doigt.values()) {
            if (total == 0) {
                doigt.poids = 0.0;
            } else {
                doigt.poids = (double) doigt.occ / total;
                doigt.poids = doigt.poids * 10; // pourcentage
            }

        }
    }

    /**
     * Retourne une représentation sous forme de chaîne de caractères de cet objet
     * Doigt.
     *
     * @return Une chaîne de caractères représentant cet objet Doigt.
     */
    public String toString() {
        return "Doigt { " +
                main + " "
                + nomDoigt + " " +
                " , poids = " + poids +
                " , " + posRepos +
                " , " + occ +
                '}';
    }
}
