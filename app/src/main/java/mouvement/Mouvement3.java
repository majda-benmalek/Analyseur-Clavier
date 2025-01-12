package mouvement;

import java.util.List;

import clavier.Doigt;
import clavier.Touche;

public final class Mouvement3 extends Mouvement {

    public Mouvement3(List<Touche> l, int o) {
        super(l, o);
    }

    public boolean notRedirection() {
        Touche t1 = this.getSqTouches().get(0);
        Touche t2 = this.getSqTouches().get(1);
        Touche t3 = this.getSqTouches().get(2);
        boolean versInterieur = t1.getCoord().getX() < t2.getCoord().getX()
                && t2.getCoord().getX() < t3.getCoord().getX();
        boolean versExterieur = t1.getCoord().getX() > t2.getCoord().getX()
                && t2.getCoord().getX() > t3.getCoord().getX();

        return versInterieur || versExterieur;
    }

    public boolean redirectionSansIndex() {
        Touche t1 = this.getSqTouches().get(0);
        Touche t2 = this.getSqTouches().get(1);
        Touche t3 = this.getSqTouches().get(2);
        boolean b1 = t1.getDoigt() == Doigt.INDEX_D || t1.getDoigt() == Doigt.INDEX_G;
        boolean b2 = t2.getDoigt() == Doigt.INDEX_D || t2.getDoigt() == Doigt.INDEX_G;
        boolean b3 = t3.getDoigt() == Doigt.INDEX_D || t3.getDoigt() == Doigt.INDEX_G;
        return b1 || b2 || b3;
    }

    public boolean skipgramme() {
        Touche t1 = this.getSqTouches().get(0);
        Touche t2 = this.getSqTouches().get(1);
        Touche t3 = this.getSqTouches().get(2);
        boolean memeDoigt = t1.getDoigt() == t3.getDoigt() && t1.getDoigt().getMain() == t3.getDoigt().getMain();
        boolean mainDiff = t2.getDoigt().getMain() != t1.getDoigt().getMain()
                && t2.getDoigt().getMain() != t3.getDoigt().getMain();

        return memeDoigt && mainDiff;
    }
}
