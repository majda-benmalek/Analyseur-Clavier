package clavierTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import clavier.Doigt;
import clavier.Main;

import java.util.List;

public class MainTest {

    @BeforeEach
    public void setUp() {
        for (Doigt d : Doigt.values()) {
            d.setOcc();
        }
    }

    @Test
    public void testGetListDoigt() {
        Doigt doigtD = Doigt.INDEX_D;
        Doigt doigtG = Doigt.INDEX_G;

        List<Doigt> doigtsDroite = Main.DROITE.getListDoigt();
        List<Doigt> doigtsGauche = Main.GAUCHE.getListDoigt();

        assertFalse(doigtsDroite.isEmpty());
        assertTrue(doigtsDroite.contains(doigtD));
        assertTrue(doigtsGauche.contains(doigtG));
        assertFalse(doigtsDroite.contains(doigtG));
    }

    @Test
    public void testCalculEquilibre_DROITE() {
        Doigt.INDEX_D.compteOccDoigts();
        Doigt.INDEX_D.compteOccDoigts();
        Doigt.INDEX_G.compteOccDoigts();
        Doigt.calculPoids();

        double equilibre = Main.calculEquilibre();
        assertTrue(0 != equilibre);
        assertEquals(0.33, equilibre, 0.01);
    }

    @Test
    public void testCalculEquilibre_GAUCHE() {
        Doigt.INDEX_G.compteOccDoigts();
        Doigt.INDEX_G.compteOccDoigts();
        Doigt.INDEX_D.compteOccDoigts();
        Doigt.calculPoids();

        double equilibre = Main.calculEquilibre();
        assertTrue(0 != equilibre);
        assertEquals(0.33, equilibre, 0.01);
    }

    @Test
    public void testCalculEquilibre_EQUILIBRE() {
        Doigt.INDEX_G.compteOccDoigts();
        Doigt.INDEX_G.compteOccDoigts();
        Doigt.INDEX_D.compteOccDoigts();
        Doigt.INDEX_D.compteOccDoigts();
        Doigt.calculPoids();

        double equilibre = Main.calculEquilibre();
        assertTrue(0 == equilibre);
        assertEquals(0, equilibre, 0.01);
    }

    @Test
    public void testToString() {
        assertEquals("Main = DROITE", Main.DROITE.toString());
        assertEquals("Main = GAUCHE", Main.GAUCHE.toString());
    }
}
