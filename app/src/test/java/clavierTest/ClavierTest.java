package clavierTest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import clavier.Clavier;
import clavier.TouchNotFound;
import clavier.Touche;

import java.util.List;

public class ClavierTest {

    private Clavier clavier;

    @BeforeEach
    public void setUp() {
        clavier = new Clavier();
    }

    @Test
    public void testCreerClavier() {
        assertNotNull(clavier.getTouches());
        assertFalse(clavier.getTouches().isEmpty());
    }

    @Test
    public void testCombinaison() {
        assertNotNull(clavier.getCombinaisons());
        assertFalse(clavier.getCombinaisons().isEmpty());
    }

    @Test
    public void testChercheTouche_Simple() {
        try {
            List<List<Touche>> result = clavier.chercheTouche("a");
            assertNotNull(result);
            assertFalse(result.isEmpty());
            boolean found = result.stream().anyMatch(list -> list.stream().anyMatch(t -> t.getEtiq().equals("a")));
            assertTrue(found);
        } catch (TouchNotFound e) {
            fail("Exception should not be thrown for existing touch 'a'");
        }
    }

    @Test
    public void testChercheTouche_WithDeadKeys() {
        try {
            List<List<Touche>> result = clavier.chercheTouche("A");
            assertNotNull(result);
            assertFalse(result.isEmpty());
            boolean found = result.stream().anyMatch(list -> list.stream().anyMatch(t -> t.getEtiq().equals("A")));
            boolean found_morte = result.stream()
                    .anyMatch(list -> list.stream().anyMatch(t -> t.getEtiq().equals("shift")));
            assertTrue(found);
            assertTrue(found_morte);
        } catch (TouchNotFound e) {
            fail("Exception should not be thrown for existing touch 'A'");
        }
    }

    @Test
    public void testChercheTouche_WithCombinations() {
        try {
            List<List<Touche>> result = clavier.chercheTouche("ê");
            assertNotNull(result);
            assertFalse(result.isEmpty());
            // Vérifier que la combinaison de touches pour "ê" est présente dans les
            // résultats
            boolean found = result.stream().anyMatch(list -> list.stream().anyMatch(t -> t.getEtiq().equals("e")));
            boolean found_combi = result.stream()
                    .anyMatch(list -> list.stream().anyMatch(t -> t.getEtiq().equals("^")));
            assertTrue(found);
            assertTrue(found_combi);
        } catch (TouchNotFound e) {
            fail("Exception should not be thrown for existing touch 'ê'");
        }
    }

    @Test
    public void testChercheTouche_NonExistant() {
        assertThrows(TouchNotFound.class, () -> {
            clavier.chercheTouche("æ");
        });
    }
}