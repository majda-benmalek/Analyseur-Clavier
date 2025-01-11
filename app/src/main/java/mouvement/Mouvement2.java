package mouvement;

import java.util.List;

import clavier.Doigt;
import clavier.Main;
import clavier.Touche;

public final class Mouvement2 extends Mouvement {

    public Mouvement2(List<Touche> l, int o) {
        super(l, o);
    }
    // 3 méthode verifiant que c'est pas SFB ciseaux ou LSB

    public boolean isSFB() {
        // les 2 touches sont faites par le même doigt
        Touche t1 = this.getSqTouches().get(0);
        Touche t2 = this.getSqTouches().get(1);
        return t1.getDoigt() == t2.getDoigt();
    }

    public boolean isCiseaux() {
        // même mains diférente rangées
        Touche t1 = this.getSqTouches().get(0);
        Touche t2 = this.getSqTouches().get(1);
        return (t1.getDoigt().getMain() == t2.getDoigt().getMain()) && (t1.getCoord().getY() != t2.getCoord().getY());
    }

    public boolean isLSB() {
        Touche t1 = this.getSqTouches().get(0);
        Touche t2 = this.getSqTouches().get(1);
        // les 2 touches sont sur une colonne diff que la position de repos de leur
        // doigt
        Coordonnee posReposDT1 = t1.getDoigt().getPosRepos();
        Coordonnee posReposDT2 = t2.getDoigt().getPosRepos();

        return (posReposDT1.getY() != t1.getCoord().getY()) && (posReposDT2.getY() != t2.getCoord().getY());
    }

    // MOUVEMENTS A MAXIMISÉ
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
            if (d1.getMain() == Main.GAUCHE) { // Si la touche 1 est faite par l'index
                if (d1.getPosRepos().getX() < d2.getPosRepos().getX()) {
                    r = 1;
                } else if (d1.getPosRepos().getX() > d2.getPosRepos().getX()) {
                    r = 2;
                }
            } else if (d1.getMain() == Main.DROITE) {
                if (d1.getPosRepos().getX() > d2.getPosRepos().getX()) {
                    r = 1; 
                }
                if (d1.getPosRepos().getX() < d2.getPosRepos().getX()) {
                    r = 2;
                }
            }
            // Pour les auriculaire voir si on compte les touche autres que les lettres
            // (shift, tab, ^...)
        }
        return r;
    }

    // @Override
    // public void calculScore() {
    //     double score = 0;
    //     if (this.isSFB()) {
    //         System.out.println("isSFB");
    //         score -= 1;
    //     }
    //     if (this.isCiseaux()) {
    //         System.out.println("isCiseaux");
    //         score -= 1;
    //     }

    //     if (this.isLSB()) {
    //         System.out.println("isLSB");
    //         score -= 1;
    //     }

    //     if (this.isAlternance()) {
    //         System.out.println("isAlternance");
    //         score += 1;
    //     }

    //     int roulement = this.isRoulement();
    //     if (roulement != 0) {
    //         System.out.println("isRoulement");
    //         score += roulement;
    //     }

    //     score = score * getOccurrences();
    //     this.setScore(score);

    // }

}
