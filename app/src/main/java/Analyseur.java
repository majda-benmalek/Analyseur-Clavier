import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Map;

public class Analyseur implements InterfaceAnalyseur {
    private ArrayList<Map<String,Integer>> nGrammes;

    public Analyseur(String fichier){
        this.nGrammes= new ArrayList<>();
        this.analyse(1, fichier);
        this.analyse(2, fichier);
        this.analyse(3, fichier);
    }

    @Override
    public void analyse(int n, String fichier) {
        try {
            FileReader fileReader = new FileReader(fichier);
            char[] paquet = new char[1];
            fileReader.read(paquet);
        } catch (FileNotFoundException e) {
            System.out.println("Le fichier donné est invalide");
        }

        // BufferedReader bufferedReader = new BufferedReader(fileReader);
        
    }

    @Override
    public void transformeEnTouche(Clavier c) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'transformeEnTouche'");
    }
    
}
