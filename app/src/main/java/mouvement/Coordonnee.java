package mouvement;
/**
 * La classe Coordonnee représente une paire de coordonnées (x, y).
 * Elle fournit des méthodes pour obtenir et définir les valeurs des
 * coordonnées,
 * calculer la distance entre deux coordonnées, et comparer des objets
 * Coordonnee.
 */

public class Coordonnee {
    private int x;
    private int y;

    /**
     * Constructeur pour initialiser les coordonnées avec des valeurs spécifiques.
     *
     * @param x La valeur de la coordonnée x.
     * @param y La valeur de la coordonnée y.
     */
    public Coordonnee(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Retourne la valeur de la coordonnée x.
     *
     * @return La valeur de la coordonnée x.
     */
    public int getX() {
        return x;
    }

    /**
     * Retourne la valeur de la coordonnée y.
     *
     * @return La valeur de la coordonnée y.
     */
    public int getY() {
        return y;
    }

    /**
     * Définit une nouvelle valeur pour la coordonnée x.
     *
     * @param newX La nouvelle valeur de la coordonnée x.
     */
    public void setX(int newX) {
        this.x = newX;
    }

    /**
     * Définit une nouvelle valeur pour la coordonnée y.
     *
     * @param newY La nouvelle valeur de la coordonnée y.
     */
    public void setY(int newY) {
        this.y = newY;
    }

    /**
     * Calcule la distance entre les coordonnées de l'objet courant et une coordonnée donnée.
     * Utilise la formule suivante : sqrt((x2 - x1)^2 + (y2 - y1)^2).
     *
     * @param c1 L'autre coordonnée.
     * @return La distance entre les deux coordonnées.
     */
    public int calculDistance(Coordonnee c1) {
        int tX = this.getX();
        int tY = this.getY();
        int cX = c1.getX();
        int cY = c1.getY();

        double x = Math.pow((cX - tX), 2);
        double y = Math.pow((cY - tY), 2);

        int distance = (int) Math.sqrt(x + y);

        return distance;

    }

    /**
     * Compare cet objet Coordonnee avec l'objet spécifié pour l'égalité.
     *
     * @param o L'objet à comparer avec cet objet Coordonnee.
     * @return true si les objets sont égaux, false sinon.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Coordonnee that = (Coordonnee) o;
        return x == that.x && y == that.y;
    }

    @Override
    public String toString() {
        return "Coordonnées = { " +
                " x = " + x +
                " , y = " + y +
                " } ";
    }
}
