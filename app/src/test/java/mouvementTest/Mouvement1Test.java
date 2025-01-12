package mouvementTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import clavier.Doigt;
import clavier.Main;
import clavier.Touche;
import mouvement.Mouvement1;

public class Mouvement1Test {

    @Test
    public void testCalculScore_SansOcc() {
        Doigt doigt = Doigt.POUCE_G;
        Touche touche = new Touche(" ", 8, 0, doigt);
        List<Touche> touches = List.of(touche);

        Mouvement1 mouvement = new Mouvement1(touches, 1);

        Main.calculEquilibre();

        mouvement.calculScore();

        System.out.println((touche.getCoord().calculDistance(doigt.getPosRepos()) * doigt.getPoids()));
        System.out.println((touche.getCoord().calculDistance(doigt.getPosRepos())));
        double expectedScore = ((touche.getCoord().calculDistance(doigt.getPosRepos()) * doigt.getPoids()) * 1) * 1.0;
        assertEquals(expectedScore, mouvement.getScore(), 0.001);
    }

    @Test
    public void testGetScore() {
        Mouvement1 mouvement = new Mouvement1(List.of(), 1);
        mouvement.setScore(5.0);

        assertEquals(5.0, mouvement.getScore());
    }

    @Test
    public void testSetScore() {
        Mouvement1 mouvement = new Mouvement1(List.of(), 1);
        mouvement.setScore(3.0);

        assertEquals(3.0, mouvement.getScore());
    }
}