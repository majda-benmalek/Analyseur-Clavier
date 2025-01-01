import java.util.List;
public interface InterfaceAnalyseur {
    void analyse(int paquet,String fichier);
    List<Mouvement> transformeEnTouche(Clavier c);
}
