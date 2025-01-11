package clavierTest;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import clavier.Doigt;
import mouvement.Coordonnee;

public class DoigtTest {

    @BeforeEach
    public void setUp() {

        // Réinitialiser les occurrences et poids des doigts avant chaque test
        for (Doigt doigt : Doigt.values()) {
            doigt.setOcc(); // Réinitialiser les occurrences
        }
        Doigt.calculPoids();
    }

    @Test
    public void testSetPosRepos() {
        assertEquals(new Coordonnee(2, 2), Doigt.ANNULAIRE_G.setPosRepos(Doigt.ANNULAIRE_G));
        assertEquals(new Coordonnee(9, 2), Doigt.ANNULAIRE_D.setPosRepos(Doigt.ANNULAIRE_D));
        assertEquals(new Coordonnee(1, 2), Doigt.AURICULAIRE_G.setPosRepos(Doigt.AURICULAIRE_G));
        assertEquals(new Coordonnee(10, 2), Doigt.AURICULAIRE_D.setPosRepos(Doigt.AURICULAIRE_D));
        assertEquals(new Coordonnee(4, 2), Doigt.INDEX_G.setPosRepos(Doigt.INDEX_G));
        assertEquals(new Coordonnee(7, 2), Doigt.INDEX_D.setPosRepos(Doigt.INDEX_D));
        assertEquals(new Coordonnee(3, 2), Doigt.MAJEUR_G.setPosRepos(Doigt.MAJEUR_G));
        assertEquals(new Coordonnee(8, 2), Doigt.MAJEUR_D.setPosRepos(Doigt.MAJEUR_D));
        assertEquals(new Coordonnee(4, 0), Doigt.POUCE_G.setPosRepos(Doigt.POUCE_G));
        assertEquals(new Coordonnee(8, 0), Doigt.POUCE_D.setPosRepos(Doigt.POUCE_D));
    }

    @Test
    public void testCompteOccDoigts() {
        // test que l'incrémentation est effectif
        Doigt.INDEX_G.compteOccDoigts();
        assertEquals(1, Doigt.INDEX_G.getOcc());
        Doigt.INDEX_G.compteOccDoigts();
        assertEquals(2, Doigt.INDEX_G.getOcc());
    }

    @Test
    public void testCalculPoids() {
        Doigt.INDEX_G.compteOccDoigts();
        Doigt.INDEX_G.compteOccDoigts();
        Doigt.MAJEUR_D.compteOccDoigts();
        Doigt.calculPoids();
        assertEquals(2.0 / 3.0 * 10, Doigt.INDEX_G.getPoids(), 0.01);
        assertEquals(1.0 / 3.0 * 10, Doigt.MAJEUR_D.getPoids(), 0.01);
    }

    @Test
    public void testToString() {
        String expected = "Doigt { Main = GAUCHE INDEX_G  , poids = 0.0 , Coordonnées = {  x = 4 , y = 2 }  , 0}";
        assertEquals(expected, Doigt.INDEX_G.toString());
    }
}