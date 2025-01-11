package clavierTest;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import clavier.Clavier;
import clavier.Touche;

import java.util.List;

public class ClavierTest {

    private Clavier clavier;

    @BeforeEach
    public void setUp() {
        clavier = new Clavier("ressources/azerty.json", "ressources/combinaison.json");
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
        List<List<Touche>> result = clavier.chercheTouche("a");
        assertNotNull(result);
        assertFalse(result.isEmpty());
        boolean found = result.stream().anyMatch(list -> list.stream().anyMatch(t -> t.getEtiq().equals("a")));
        assertTrue(found);
    }

    @Test
    public void testChercheTouche_WithDeadKeys() {
        List<List<Touche>> result = clavier.chercheTouche("A");
        assertNotNull(result);
        assertFalse(result.isEmpty());
        boolean found = result.stream().anyMatch(list -> list.stream().anyMatch(t -> t.getEtiq().equals("A")));
        assertTrue(found);
    }

    @Test
    public void testChercheTouche_WithCombinations() {
        List<List<Touche>> result = clavier.chercheTouche("ê");
        assertNotNull(result);
        assertFalse(result.isEmpty());
        // Vérifier que la combinaison de touches pour "ê" est présente dans les
        // résultats
        boolean found = result.stream().anyMatch(list -> list.stream().anyMatch(t -> t.getEtiq().equals("e")));
        assertTrue(found);
    }

    @Test
    public void testChercheTouche_NonExistant(){
        List<List<Touche>> result = clavier.chercheTouche("æ");
        assertNotNull(result);
        assertTrue(result.isEmpty());
        boolean found = result.stream().anyMatch(list -> list.stream().anyMatch(t -> t.getEtiq().equals("æ")));
        assertFalse(found);
    }

    @Test
    public void testAfficherCombinaisons() {
        // Juste pour s'assurer que la méthode ne lance pas d'exception
        assertDoesNotThrow(() -> clavier.afficherCombinaisons());
    }
}