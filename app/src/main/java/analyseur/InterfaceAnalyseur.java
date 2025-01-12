package analyseur;

import java.util.List;

import clavier.Clavier;
import clavier.TouchNotFound;
import mouvement.Mouvement;

public interface InterfaceAnalyseur {
    void analyse(int paquet,String fichier);
    List<List<Mouvement>> transformeEnTouche(Clavier c) throws TouchNotFound ;
}
