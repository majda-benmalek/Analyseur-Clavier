import java.util.ArrayList;
import java.util.HashMap;

public interface InterfaceAnalyseur {
    void analyse(int paquet,String fichier);
    ArrayList<HashMap<ArrayList<Touche>,Integer>> transformeEnTouche(Clavier c);
}
