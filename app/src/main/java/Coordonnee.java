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

    // @Override
    // public String toString() {
    //     return "Coordonnee { " +
    //             " x = " + x +
    //             ", y = " + y +
    //             " } ";
    // }

    @Override
    public String toString() {
        return "Coordonnées = { " +
                " x = " + x +
                " , y = " + y +
                " } ";
    }
}
