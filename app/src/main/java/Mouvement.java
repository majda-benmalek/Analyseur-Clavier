package app.src.main.java;

import java.util.List;

public abstract class Mouvement {
    private List<Touche> sequenceTouche; 
    private int occurrence ; 
    private int score;

    public Mouvement(List<Touche> t, int o){
        this.sequenceTouche = t;
        this.occurrence = o;
    }
    public abstract int calculScore();

}
