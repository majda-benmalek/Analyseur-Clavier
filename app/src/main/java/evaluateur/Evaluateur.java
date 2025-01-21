package evaluateur;

import java.util.ArrayList;
import java.util.List;

import analyseur.Analyseur;
import analyseur.InterfaceAnalyseur;
import clavier.InterfaceClavier;
import clavier.TouchNotFound;
import mouvement.Mouvement;
import mouvement.Mouvement1;
import mouvement.Mouvement2;
import mouvement.Mouvement3;
import ui.Observable;
import ui.Observer;
import ui.ObserverImplm;

public class Evaluateur implements InterfaceEvaluateur, Observable {
    private InterfaceClavier clavier;
    private InterfaceAnalyseur analyseur;
    private List<List<Mouvement>> mouvementListe;
    
    private static double poidsFaible = 0.25;
    private static double poidsMoyen = 0.5;
    private static double poidsFort = 1.0;

    private int redirection;
    private int skipgramme;
    private int lsb;
    private int sfb;
    private int ciseaux;
    private int roulement;
    private int alternance;
    private int score1touche;
    private int nombre3Grammes;
    private List<Observer> observers = new ArrayList<>();

    /** construit l'évaluateur
     * @param analyseur le résultat de l'analyse
     * @param clavier le clavier qu'on veut étudier
     */
    public Evaluateur(InterfaceAnalyseur analyseur, InterfaceClavier clavier) throws TouchNotFound { 
        this.analyseur = analyseur;
        this.clavier = clavier;

        this.mouvementListe = this.analyseur.transformeEnTouche(this.clavier);
        this.alternance = 0;
        this.ciseaux = 0;
        this.lsb = 0;
        this.redirection = 0;
        this.roulement = 0;
        this.sfb = 0;
        this.skipgramme = 0;
        this.score1touche = 0;
        Analyseur a = (Analyseur) this.analyseur;
        this.nombre3Grammes = a.getNombre3Gramme();
    }

    /** retourne la taille du mouvement
     * @param m le mouvement dont on veut la taille
     * @return la taille du mouvement
    */
    public int tailleMouvement(Mouvement m) {
        if (m instanceof Mouvement1) {
            return 1;
        } else if (m instanceof Mouvement2) {
            return 2;
        } else {
            return 3;
        }

    }
    /** retourne le meilleur mouvement parmis les choix proposées
     * @param l la liste de choix
     * @return le meilleur mouvement pour le même gramme
    */
    public Mouvement meilleurMouvement(List<Mouvement> l) {
        Mouvement res = l.get(0);
        double scoreRes = scoreMouvement(res);
        for (Mouvement mouvement : l) {
            double scoreCandidat = scoreMouvement(mouvement);
            if (scoreCandidat < scoreRes) {
                res = mouvement;
                scoreRes = scoreCandidat;
            }
        }
        return res;
    }
   /** retourne le score d'un mouvement
     * @param m le mouvement dont on veut le score
     * @return le score du mouvement
    */
    public double scoreMouvement(Mouvement m){
        double score=0 ; 
       int taille = tailleMouvement(m);
       if (taille == 1){
        score= m.getOccurrences()*poidsMoyen;
       }else if (taille == 2){
            Mouvement2 mm = (Mouvement2)m;
            if (mm.isAlternance() || mm.isRoulement() == 0){
                score= m.getOccurrences()*poidsFaible;
            }
       }else{
            Mouvement3 m3 = (Mouvement3) m;
            if (m3.notRedirection() == false){
                if (m3.redirectionSansIndex()){
                    score= m.getOccurrences()*poidsFort;
                }
                else{
                    score= m.getOccurrences()*poidsMoyen;
                }
            }
            else{
                score = m.getOccurrences()*poidsMoyen;
            }
       }
       return score;
    }

    /**
     renvoie le score du clavier 
     @return le score
    */
    @Override
    public double donneLeScore() {
        double res = 0;
        for (List<Mouvement> combList : mouvementListe) {
            Mouvement mouvement = meilleurMouvement(combList);
            if (mouvement instanceof Mouvement1) {
                score1touche += ((Mouvement1) mouvement).getScore();
            } else if (mouvement instanceof Mouvement2) {
                Mouvement2 inter = (Mouvement2) mouvement;
                int occ = inter.getOccurrences();
                if (inter.isAlternance()) {
                    alternance += occ;
                }
                if (inter.isCiseaux()) {
                    ciseaux += occ;
                }
                if (inter.isLSB()) {
                    lsb += occ;
                }
                if (inter.isSFB()) {
                    sfb += occ;
                }
                roulement += inter.isRoulement();
            } else {
                Mouvement3 interMouvement3 = (Mouvement3) mouvement;
                int occ = interMouvement3.getOccurrences();
                if (interMouvement3.notRedirection() == false) {
                    redirection += occ;
                    if (interMouvement3.redirectionSansIndex()) {
                        redirection += occ;
                    }
                }
                if (interMouvement3.skipgramme()) {
                    skipgramme += occ;
                }
            }
        }
        double numerateur = redirection + skipgramme + lsb + sfb + ciseaux - roulement - alternance + score1touche; 
        if (nombre3Grammes != 0) {
            res = numerateur / nombre3Grammes;
            res *=10;
        }
        ObserverImplm o = new ObserverImplm(this);
        notifyObservers("evaluation",o,this.observers);
        return res;
    }
    @Override
    public List<Observer> getObservers() {
        return this.observers;
    }
}