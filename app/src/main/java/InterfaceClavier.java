import java.util.List;

public interface InterfaceClavier {
    void creerClavier();
    List<Touche> chercheTouche(String etiquette);
    List<Touche> getTouches();
}
