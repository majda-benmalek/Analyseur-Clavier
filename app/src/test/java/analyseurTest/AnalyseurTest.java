package analyseurTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import analyseur.Analyseur;

import static org.junit.jupiter.api.Assertions.*;

public class AnalyseurTest {

    private Analyseur analyseur;
    private String CHEMIN = "ressources/corpus/ficTest.txt";
    
    @BeforeEach
    public void setUp(){
        analyseur = new Analyseur("ressources/corpus/ficTest.txt");
        analyseur = new Analyseur(CHEMIN);
    }    
    
    @Test
    public void testDansNgrammes(){
        
        // pour les 1 grammes
        analyseur.getNgrammes().get(0).put("c", 3);
        assertTrue(analyseur.dansNgrammes("c") >= 0);
        assertTrue(analyseur.dansNgrammes("q") < 0);

        // pour les 2 grammes 
        analyseur.getNgrammes().get(1).put("bc", 7);
        assertTrue(analyseur.dansNgrammes("bc") >= 0);
        assertTrue(analyseur.dansNgrammes("qw") < 0);

        // pour les 3 grammes
        analyseur.getNgrammes().get(2).put("abc", 6);
        assertTrue(analyseur.dansNgrammes("abc") >= 0);
        assertTrue(analyseur.dansNgrammes("aqw") < 0);
    }

    @Test
    public void testMajGramme(){

        // pour les 1 grammes
        analyseur.majGrammes("j");
        assertTrue(analyseur.getNgrammes().get(0).containsKey("j"));

        // pour les 2 grammes
        analyseur.majGrammes("jm");
        assertTrue(analyseur.getNgrammes().get(1).containsKey("jm"));

        // pour les 3 grammes
        analyseur.majGrammes("ooo");
        assertTrue(analyseur.getNgrammes().get(2).containsKey("ooo"));
    }

}
