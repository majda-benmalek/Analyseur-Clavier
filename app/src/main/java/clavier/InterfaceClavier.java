package clavier;

import java.io.IOException;
import java.util.List;

public interface InterfaceClavier {
    public void creerClavier() throws IOException;

    List<List<Touche>> chercheTouche(String etiquette) throws TouchNotFound;

    List<Touche> getTouches();
}
