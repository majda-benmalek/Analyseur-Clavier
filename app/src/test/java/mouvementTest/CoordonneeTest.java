package mouvementTest;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import mouvement.Coordonnee;

public class CoordonneeTest {

    private Coordonnee c;

    @BeforeEach
    public void setUp() {
        c = new Coordonnee(2, 2);
    }

    @Test
    public void testGetX() {
        assertEquals(2, c.getX());
    }

    @Test
    public void testGetY() {
        assertEquals(2, c.getY());
    }

    @Test
    public void testSetY() {
        assertEquals(2, c.getY());
        c.setY(4);
        assertEquals(4, c.getY());
    }

    @Test
    public void testSetX() {
        assertEquals(2, c.getX());
        c.setX(4);
        assertEquals(4, c.getX());
    }

    @Test
    public void calculDistance() {
        Coordonnee c2 = new Coordonnee(4, 1);

        assertEquals(2, c.calculDistance(c2));
    }

    @Test
    public void testEquals() {
        Coordonnee c2 = new Coordonnee(2, 2);

        assertTrue(c.equals(c2));

    }
}
