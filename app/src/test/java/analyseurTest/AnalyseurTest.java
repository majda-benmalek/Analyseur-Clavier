package analyseurTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import analyseur.Analyseur;

import static org.junit.jupiter.api.Assertions.*;

public class AnalyseurTest {

    private Analyseur analyseur;
    private String CHEMIN = "app/ressources/fichierTest.txt";
    
    @BeforeEach
    public void setUp(){
        analyseur = new Analyseur(CHEMIN);
    }    
    
    // *GOOD
    // @Test
    // public void testDansNgrammes(){
        
    //     // pour les 1 grammes
    //     analyseur.getNgrammes().get(0).put("c", 3);
    //     assertTrue(analyseur.dansNgrammes("c") >= 0);
    //     assertTrue(analyseur.dansNgrammes("q") < 0);

    //     // pour les 2 grammes 
    //     analyseur.getNgrammes().get(1).put("bc", 7);
    //     assertTrue(analyseur.dansNgrammes("bc") >= 0);
    //     assertTrue(analyseur.dansNgrammes("qw") < 0);

    //     // pour les 3 grammes
    //     analyseur.getNgrammes().get(2).put("abc", 6);
    //     assertTrue(analyseur.dansNgrammes("abc") >= 0);
    //     assertTrue(analyseur.dansNgrammes("aqw") < 0);
    // }

    // @Test
    // // *GOOD
    // public void testMajGramme(){

    //     // pour les 1 grammes
    //     analyseur.majGrammes("j");
    //     assertTrue(analyseur.getNgrammes().get(0).containsKey("j"));
    //     // assertTrue(analyseur.dansNgrammes("j") > 0);

    //     // pour les 2 grammes
    //     analyseur.majGrammes("jm");
    //     assertTrue(analyseur.getNgrammes().get(1).containsKey("jm"));

    //     // pour les 3 grammes
    //     analyseur.majGrammes("ooo");
    //     assertTrue(analyseur.getNgrammes().get(2).containsKey("ooo"));
    // }

    @Test
    public void testGetNombre3Grammes(){
        analyseur.analyse(3, CHEMIN);
        assertEquals(10, analyseur.getNombre3Gramme());
    }

    // @Test
    // public void testAnalyse(){
    //     analyseur = new Analyseur(CHEMIN);
    //     analyseur.analyse(3,CHEMIN);
    //     // assertTrue(analyseur.getNgrammes().get(0).containsKey("a"));
    //     // assertTrue(analyseur.getNgrammes().get(1).containsKey("ab"));
    //     assertTrue(analyseur.getNgrammes().get(2).containsKey("abc"));

        
    //     // pour les 1 grammes
    //     // assertTrue(analyseur.dansNgrammes("b")> 0);
    //     // assertEquals(2, analyseur.dansNgrammes("a"));
    //     // assertTrue(analyseur.dansNgrammes("w")<0);

    //     // pour les 2 grammes
    //     // assertTrue(analyseur.dansNgrammes("ab")> 0);
    //     // assertEquals(2, analyseur.dansNgrammes("ab"));
    //     // assertTrue(analyseur.dansNgrammes("aq")<0);

    //     // pour les 3 grammes
    //     // assertTrue(analyseur.dansNgrammes("abc")> 0);
    //     // assertEquals(2, analyseur.dansNgrammes("abc"));
    //     // assertTrue(analyseur.dansNgrammes("aqw")<0);

    // }

}
