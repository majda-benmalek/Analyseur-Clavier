import java.util.List;

public interface InterfaceClavier {
    void creerClavier();
    Touche chercheTouche(char etiquette);
    List<Touche> getTouches();
}
