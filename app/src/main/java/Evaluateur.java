import java.util.List;
//TODO gérer le cas où le clavier est pas adaptée au texte donnée exemple QWERTY donné sur un texte français dc les accents éè sont pas dans le claviers
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
    public int donneLeScore() { // TODO utiliser les streams
        int res = 0;
        for (Mouvement mouvement : mouvementListe) {
            if (mouvement instanceof Mouvement1){
                score1touche+=((Mouvement1) mouvement).getScore();
            }   
            else if (mouvement instanceof Mouvement2){
                Mouvement2 inter = (Mouvement2) mouvement;
                int occ= inter.getOccurrences();
                if (inter.isAlternance()){
                    alternance+=occ;
                }
                if (inter.isCiseaux()){
                    ciseaux+=occ;
                }
                if (inter.isLSB()){
                    lsb+=occ;
                }
                if (inter.isSFB()){
                    sfb+=occ;
                }
                roulement+=inter.isRoulement();
            }else{
                Mouvement3 interMouvement3 = (Mouvement3) mouvement;
                int occ = interMouvement3.getOccurrences();
                if (interMouvement3.notRedirection() == false){
                    redirection+=occ;
                    if (interMouvement3.redirectionSansIndex()){
                        redirection+=occ;
                    }
                }
                if (interMouvement3.skipgramme()){
                    skipgramme+=occ;
                }
            }
        }
        return res;
    }
    
}
