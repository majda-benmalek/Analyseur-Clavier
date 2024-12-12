package app.src.main.java;

import java.util.List;

public class Mouvement1 extends Mouvement{

    public Mouvement1(List<Touche> l, int o){
        super(l,o);
    }

    @Override
    public int calculScore() {
        return 0;
    }
    
}
