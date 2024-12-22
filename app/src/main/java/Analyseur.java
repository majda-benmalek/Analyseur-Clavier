import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Analyseur implements InterfaceAnalyseur {
    private ArrayList<HashMap<String,Integer>> nGrammes;

    public Analyseur(String fichier){
        this.nGrammes= new ArrayList<>();
        this.nGrammes.add(new HashMap<>());
        this.nGrammes.add(new HashMap<>());
        this.nGrammes.add(new HashMap<>());
        this.analyse(1, fichier);
        this.analyse(2, fichier);
        // this.analyse(3, fichier);
    }


    int dansNgrammes(String s){
        int res = -1;
        for (int i = 0; i < 3; i++) {
            if (nGrammes.get(i).containsKey(s)){
                res = i;
            }
        }
        return res;
    }

    void majGrammes(String s){
        int pos = dansNgrammes(s);
        if ( pos == -1)
        {
            // System.out.println("s = "+s);
            pos = s.length()-1;
            // System.out.println("if pos = "+pos);
            if (pos<0){
                pos = 0;
            }
            nGrammes.get(pos).put(s,1);
        }
        else
        {
            // System.out.println("else pos = "+pos);
            Integer ancienneOcc = nGrammes.get(pos).get(s);
            Integer nouvelleOcc = Integer.valueOf(ancienneOcc.intValue()+1);
            nGrammes.get(pos).put(s, nouvelleOcc);
        }
    }

    boolean aLaisser(String s){
        return s.equals("\n") || s.equals("\r") || s.equals("\t") || s.isBlank();
    }

    String transformeTabenString(char [] c){
        String s="";
        for (int i = 0; i < c.length; i++) {
            if (!aLaisser(String.valueOf(c[i]))){
                s+=c[i];
            }
        }
        return s;
    }

    @Override
    public void analyse(int n, String fichier) {
        try {
            FileReader fileReader = new FileReader(fichier);
            if (n == 1)
            {
                try {
                    int i = fileReader.read();
                    String gramme;
                    while( i != -1)
                    {
                        gramme = String.valueOf((char)i);
                        if (!aLaisser(gramme))
                        {
                            majGrammes(gramme);
                        }
                        i = fileReader.read();
                    }                    
                } catch (IOException e) 
                {
                    System.out.println("Il y a une problème avec le fichier");
                }
            }
            else
            {
                try {
                    char[] paquet = new char[n];
                    int nbCaractereLu = fileReader.read(paquet);
                    while (nbCaractereLu != -1) {
                        majGrammes(transformeTabenString(paquet));
                        nbCaractereLu = fileReader.read(paquet);
                    }
                } catch (IOException e) {
                    System.out.println("Il y a une problème avec le fichier");
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Le fichier donné est invalide");
        }        
    }

    @Override
    public void transformeEnTouche(Clavier c) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'transformeEnTouche'");
    }


    void afficheGramme(){
        int i = 0;
        for (HashMap<String,Integer> hashMap : nGrammes) {
            System.out.println("i = "+i);
            for (Map.Entry<String,Integer> entry : hashMap.entrySet()){
                String ngrammes = entry.getKey();
                Integer occ = entry.getValue();
                // if (ngrammes == null || ngrammes.isEmpty()) {
                //     System.out.println("Entrée invalide détectée : clé vide ou nulle !");
                // }else{
                    System.out.println("gramme: " + ngrammes + " occ: " + occ);
                // }            
            }
            i++;
        }
    }
}



// BufferedReader bufferedReader = new BufferedReader(fileReader);

            // char[] paquet = new char[1];
            // fileReader.read(paquet);