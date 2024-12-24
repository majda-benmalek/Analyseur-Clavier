public class Coordonnee {
    private int x;
    private int y;

    public Coordonnee(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int newX) {
        this.x = newX;
    }

    public void setY(int newY) {
        this.y = newY;
    }

    /**
     * Calcule la distance entre les coordonnées de l'objet courant et
     * une coordonnée donnée.
     * <p>
     * Avec la formule suivante : sqrt((x2 - x1)^2 + (y2 - y1)^2).
     * <p>
     * (x1,y2) : les coordonnées de l'objet courant.
     * (x2,y2) : les coordonnées donnés.
     * </p>
     * </p>
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

    @Override
    public String toString() {
        return "Coordonnées = { " +
                " x = " + x +
                " , y = " + y +
                " } ";
    }
}
