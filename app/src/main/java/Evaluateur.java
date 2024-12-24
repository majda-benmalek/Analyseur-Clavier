import java.util.List;

public class Evaluateur implements InterfaceEvaluateur {
    private Clavier clavier;
    private Analyseur analyseur;
    private List<Mouvement> mouvementListe;
    private int redirection;
    private int skipgramme;
    private int lsb;
    private int sfb;
    private int ciseaux;
    private int roulement;
    private int alternance;
    private int score1touche;


    public Evaluateur(Analyseur analyseur , Clavier clavier){ // ? est ce qu'on donne le type du clavier genre Azery qwerty etc..?
        this.analyseur = analyseur;
        this.clavier = clavier;
        this.mouvementListe = this.analyseur.transformeEnTouche(this.clavier);
        this.alternance=0;
        this.ciseaux=0;
        this.lsb= 0;
        this.redirection = 0;
        this.roulement = 0;
        this.sfb = 0;
        this.skipgramme = 0;
        this.score1touche = 0;
    }


    @Override
    public int donneLeScore() {
        int res = 0;
        for (Mouvement mouvement : mouvementListe) {
            if (mouvement instanceof Mouvement1){
                score1touche+=((Mouvement1) mouvement).getScore()*mouvement.getOccurrences();
            }   
            else if (mouvement instanceof Mouvement2){
                Mouvement2 inter = (Mouvement2) mouvement;
                // if (inter.)
            }else{
                Mouvement3 interMouvement3 = (Mouvement3) mouvement;
                if (interMouvement3.notRedirection() == false){
                    redirection+=interMouvement3.getOccurrences();
                    if (interMouvement3.redirectionSansIndex()){
                        redirection+=interMouvement3.getOccurrences();
                    }
                }
                if (interMouvement3.skipgramme()){
                    skipgramme+=interMouvement3.getOccurrences();
                }
            }
        }
        return res;
    }
    
}
