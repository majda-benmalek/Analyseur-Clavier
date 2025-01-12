package mouvementTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import clavier.Doigt;
import clavier.Touche;
import mouvement.Mouvement2;

public class Mouvement2Test {

    @Test
    public void testIsSFB() {
        Touche t1 = new Touche("h", 6, 2, Doigt.INDEX_D);
        Touche t2 = new Touche("j", 7, 2, Doigt.INDEX_D);

        Mouvement2 m = new Mouvement2(List.of(t1, t2), 1);

        assertTrue(m.isSFB());
        assertFalse(m.isLSB());
        assertFalse(m.isAlternance());
        assertFalse(m.isCiseaux());
    }

    @Test
    public void testIsCiseaux() {
        Touche t1 = new Touche("d", 3, 2, Doigt.MAJEUR_G);
        Touche t2 = new Touche("r", 4, 3, Doigt.INDEX_G);

        Mouvement2 m = new Mouvement2(List.of(t1, t2), 1);

        assertTrue(m.isCiseaux());
        assertFalse(m.isSFB());
        assertFalse(m.isLSB());
        assertFalse(m.isAlternance());
    }

    @Test
    public void testIsLSB() {
        Touche t1 = new Touche("o", 10, 3, Doigt.ANNULAIRE_D);
        Touche t2 = new Touche("t", 7, 3, Doigt.INDEX_D);

        Mouvement2 m = new Mouvement2(List.of(t1, t2), 1);

        assertTrue(m.isLSB());
        assertFalse(m.isCiseaux());
        assertFalse(m.isSFB());
        assertFalse(m.isAlternance());
    }

    @Test
    public void testIsAlternance() {
        Touche t1 = new Touche("s", 2, 2, Doigt.ANNULAIRE_G);
        Touche t2 = new Touche("k", 8, 2, Doigt.INDEX_D);

        Mouvement2 m = new Mouvement2(List.of(t1, t2), 1);

        assertFalse(m.isLSB());
        assertFalse(m.isCiseaux());
        assertFalse(m.isSFB());
        assertTrue(m.isAlternance());
    }

    @Test
    public void testIsRoulement_IntExt() {
        Touche t1 = new Touche("j", 7, 2, Doigt.INDEX_D);
        Touche t2 = new Touche("m", 10, 2, Doigt.AURICULAIRE_D);

        Mouvement2 m = new Mouvement2(List.of(t1, t2), 1);

        assertEquals(2, m.isRoulement());
    }

    @Test
    public void testIsLSBAndAlternance() {
        Touche t1 = new Touche("y", 6, 3, Doigt.INDEX_D);
        Touche t2 = new Touche("t", 6, 3, Doigt.INDEX_G);
        Mouvement2 m = new Mouvement2(List.of(t1, t2), 1);

        assertFalse(m.isCiseaux());
        assertFalse(m.isSFB());
        assertTrue(m.isLSB());
        assertTrue(m.isAlternance());
    }

    @Test
    public void testIsCiseauxAndIsSFB() {
        Touche t1 = new Touche("h", 6, 2, Doigt.INDEX_D);
        Touche t2 = new Touche("y", 6, 3, Doigt.INDEX_D);

        Mouvement2 m = new Mouvement2(List.of(t1, t2), 1);

        assertTrue(m.isCiseaux());
        assertTrue(m.isSFB());
        assertFalse(m.isLSB());
        assertFalse(m.isAlternance());
    }

    @Test
    public void testIsRoulement_ExtInt() {
        Touche t1 = new Touche("s", 2, 2, Doigt.ANNULAIRE_G);
        Touche t2 = new Touche("d", 3, 2, Doigt.MAJEUR_G);

        Mouvement2 m = new Mouvement2(List.of(t1, t2), 1);

        assertFalse(m.isLSB());
        assertFalse(m.isCiseaux());
        assertFalse(m.isSFB());
        assertFalse(m.isAlternance());
        assertEquals(1, m.isRoulement());
    }

    @Test
    public void testIsSFBAndRoulement() {
        Touche t1 = new Touche("h", 6, 2, Doigt.INDEX_D);
        Touche t2 = new Touche("j", 7, 2, Doigt.INDEX_D);

        Mouvement2 m = new Mouvement2(List.of(t1, t2), 1);

        assertTrue(m.isSFB());
        assertFalse(m.isLSB());
        assertFalse(m.isAlternance());
        assertFalse(m.isCiseaux());
        assertEquals(0, m.isRoulement());
    }

    


}