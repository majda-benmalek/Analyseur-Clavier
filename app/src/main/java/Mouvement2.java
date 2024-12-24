
import java.util.List;

public class Mouvement2 extends Mouvement {

    public Mouvement2(List<Touche> l, int o) {
        super(l, o);
    }

    @Override
    public void calculScore() {
        // Mouvement à minimisé :
        // Mouvement à un seul doigt : "hy"(pas ouf) donc les touches qui sont dans le
        // rayon du même doigt "je" => chaque touche est touché par un doigt diff

        // Ciseaux : Mouvement de la même mains mais rangée différente "ji" même main
        // diff doigt mais pas la même rangé

        // Mouvement a extension latéral : Touche qui ne sont pas sur la même colonne
        // que la colonne de repods du doigt : "h" (6,2) index droit = (7,2)

        // Mouvement à maximisé :
        // Les alternanaces : 2 touches chaque touche est faite par une main
        // Roulement : 2 doigts différent d'une même main
        double score = 0;
        if (this.isSFB()) {
            System.out.println("isSFB");
            score += 2;
        }
        if (this.isCiseaux()) {
            System.out.println("isCiseaux");
            score += 2;
        }

        if (this.isLSB()) {
            System.out.println("isLSB");
            score += 2;
        }

        if (this.isAlternance()) {
            System.out.println("isAlternance");
            score += 1;
        }

        int roulement = this.isRoulement();
        if (roulement != 0) {
            System.out.println("isRoulement");
            score += roulement;
        }

        this.setScore(score * (getOccurrences()));

    }

    // 3 méthode verifiant que c'est pas SFB ciseaux ou LSB

    public boolean isSFB() {
        // les 2 touches sont faites par le même doigt
        Touche t1 = this.getSqTouches().get(0);
        Touche t2 = this.getSqTouches().get(1);
        return t1.getDoigt() == t2.getDoigt();
    }

    public boolean isCiseaux() {
        // même mains diiférente rangées
        Touche t1 = this.getSqTouches().get(0);
        Touche t2 = this.getSqTouches().get(1);
        return (t1.getDoigt().getMain() == t2.getDoigt().getMain()) && (t1.getCoord().getY() != t2.getCoord().getY());
    }

    public boolean isLSB() {
        Touche t1 = this.getSqTouches().get(0);
        Touche t2 = this.getSqTouches().get(1);
        // les 2 touches sont sur une colonne diff que la position de repos de leur
        // doigt
        Coordonnee posReposDT1 = t1.getDoigt().getCord();
        Coordonnee posReposDT2 = t2.getDoigt().getCord();

        return (posReposDT1.getY() != t1.getCoord().getY()) && (posReposDT2.getY() != t2.getCoord().getY());
    }

    public boolean isAlternance() {
        // les touches sont faites par 2 mains différentes
        Touche t1 = this.getSqTouches().get(0);
        Touche t2 = this.getSqTouches().get(1);

        return t1.getDoigt().getMain() != t2.getDoigt().getMain();
    }

    public int isRoulement() {
        // Mouvement fais par 2 doigts diff d'une même main
        // mieux que t1 exterieur (ex auriculaire) et t2 interieur (ex index)
        Touche t1 = this.getSqTouches().get(0);
        Touche t2 = this.getSqTouches().get(1);
        Doigt d1 = t1.getDoigt();
        Doigt d2 = t2.getDoigt();
        int r = 0;

        // verfier le doigt (si c'est autre chose que index ou auriculaire)
        if (!isCiseaux() && !isLSB() && !isSFB() && (d1 != d2 && d1.getMain() == d2.getMain())) {
            // verfier le doigt (si c'est autre chose que index ou auriculaire)
            if (d1.getMain() == Main.GAUCHE) { // Si la touche 1 est faite par l'index
                if (d1.getCord().getX() > d2.getCord().getX()) {
                    // ! interieur -> exterieur G
                    r = 2; // TODO : revoir les chiffres a ajouté ou -
                } else if (d1.getCord().getX() < d2.getCord().getX()) {
                    // ! exterieur -> interieur
                    r = 3;
                }
            } else if (d1.getMain() == Main.DROITE) {
                if (d1.getCord().getX() < d2.getCord().getX()) {
                    // ! interieur -> exterieur D
                    r = 2;
                }

                if (d1.getCord().getX() > d2.getCord().getX()) {
                    // ! exterieur -> interieur
                    r = 3;
                }
            }

            // Pour les auriculaire voir si on compte les touche autres que les lettres
            // (shift, tab, ^...)

        }
        // auriculaire -> annulaire -> majeur -> index
        return r;
    }

    @Override
    public String toString() {
        return "Mouvement2{" +
                "sequenceTouche=" + getSqTouches() +
                ", occurrence=" + getOccurrences() +
                ", score=" + getScore() +
                '}';
    }

}
