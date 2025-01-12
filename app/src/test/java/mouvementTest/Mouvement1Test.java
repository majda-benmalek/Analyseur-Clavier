package mouvementTest;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import clavier.Doigt;
import clavier.Main;
import clavier.Touche;
import mouvement.Mouvement1;

public class Mouvement1Test {

    private Touche touche;
    private Doigt doigt;
    private List<Touche> touches;

    @BeforeEach
    public void setUp() {
        for (Doigt doigt : Doigt.values()) {
            doigt.setOcc();
        }
        Doigt.calculPoids();
        touche = new Touche(" ", 8, 0, Doigt.POUCE_G);
        doigt = touche.getDoigt();
        doigt.compteOccDoigts();
        doigt.compteOccDoigts();
        doigt.compteOccDoigts();
        Doigt.calculPoids();
        touches = List.of(touche);
    }

    
    @Test
    public void testCalculScore_SansOcc() {
        Mouvement1 mouvement = new Mouvement1(touches, 1);

        int distance = touche.getCoord().calculDistance(doigt.getPosRepos());
        double poids = doigt.getPoids();
        double equilibre = Main.calculEquilibre();

        assertEquals(4, distance);
        assertEquals((3.0 / 3.0) * 10.0, poids, 0.01);
        assertEquals(1.0, equilibre);

        mouvement.calculScore();
        double expectedScore = ((distance * poids) * 1) * equilibre;
        assertEquals(expectedScore, mouvement.getScore(), 0.001);
    }

    @Test
    public void testCalculScore_AvecOcc() {
        Mouvement1 mouvement = new Mouvement1(touches, 7);

        int distance = touche.getCoord().calculDistance(doigt.getPosRepos());
        double poids = doigt.getPoids();
        double equilibre = Main.calculEquilibre();

        assertEquals(4, distance);
        assertEquals((3.0 / 3.0) * 10.0, poids);
        assertEquals(1.0, equilibre);

        mouvement.calculScore();
        double expectedScore = ((distance * poids) * 7) * equilibre;
        assertEquals(expectedScore, mouvement.getScore(), 0.001);
    }

    @Test
    public void testGetScore() {
        Mouvement1 mouvement = new Mouvement1(List.of(touche), 1);
        mouvement.setScore(5.0);

        assertEquals(5.0, mouvement.getScore());

    }

    @Test
    public void testSetScore() {
        Mouvement1 mouvement = new Mouvement1(List.of(touche), 1);
        mouvement.setScore(3.0);

        assertEquals(3.0, mouvement.getScore());
    }

    @Test
    public void testGetOccu() {
        Mouvement1 mouvement = new Mouvement1(touches, 100);
        assertEquals(100, mouvement.getOccurrences());
    }

    @Test
    public void testGetSqTouches() {
        Mouvement1 mouvement = new Mouvement1(touches, 100);
        assertEquals(touche, mouvement.getSqTouches().get(0));
    }

}