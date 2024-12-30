import java.util.ArrayList;
import java.util.HashMap;

public interface InterfaceAnalyseur {
    void analyse(int paquet,String fichier);

    // TODO : a changé pour List et Map pour laissé de la flexibilité sur ce choix la 
    ArrayList<HashMap<ArrayList<Touche>,Integer>> transformeEnTouche(Clavier c);
}
