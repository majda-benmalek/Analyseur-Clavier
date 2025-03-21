package clavierTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import clavier.Doigt;
import clavier.Touche;
import mouvement.Coordonnee;

public class ToucheTest {
    private Touche a = new Touche("a", 1, 3, Doigt.AURICULAIRE_G);
    private Touche retourLigne = new Touche("\n", 13, 3, null);

    @Test
    public void testGetCoord() {
        Coordonnee c = a.getCoord();
        assertEquals(c, new Coordonnee(1, 3));
    }

    @Test
    public void testGetEtiquette() {
        String s = a.getEtiq();
        assertEquals(s, "a");
    }

    @Test
    public void testGetDoigt() {
        Doigt d = a.getDoigt();
        assertEquals(d, Doigt.AURICULAIRE_G);
    }

    @Test
    public void testGetDoigt_Null() {
        Doigt d = retourLigne.getDoigt();
        assertEquals(d, null);
    }


    @Test
    public void testToString() {
        assertEquals("Touche = a Coordonnées = {  x = 1 , y = 3 } ", a.toString());
    }
}
