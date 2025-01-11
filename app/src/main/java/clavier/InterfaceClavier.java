package clavier;

import java.util.List;

public interface InterfaceClavier {
    void creerClavier();

    List<List<Touche>> chercheTouche(String etiquette);

    List<Touche> getTouches();
}
