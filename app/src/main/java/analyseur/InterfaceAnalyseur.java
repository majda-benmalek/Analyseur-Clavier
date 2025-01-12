package analyseur;

import java.util.List;

import clavier.InterfaceClavier;
import clavier.TouchNotFound;
import mouvement.Mouvement;

public interface InterfaceAnalyseur {
    void analyse(int paquet,String fichier);
    List<List<Mouvement>> transformeEnTouche(InterfaceClavier c) throws TouchNotFound ;
}
