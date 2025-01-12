package evaluateur;

import java.util.ArrayList;
import java.util.List;

import analyseur.Analyseur;
import clavier.Clavier;
import clavier.TouchNotFound;
import clavier.Touche;
import mouvement.Mouvement;
import mouvement.Mouvement1;
import mouvement.Mouvement2;
import mouvement.Mouvement3;
import ui.Observable;
import ui.Observer;
import ui.ObserverImplm;

//TODO gérer le cas où le clavier est pas adaptée au texte donnée exemple QWERTY donné sur un texte français dc les accents éè sont pas dans le claviers
public class Evaluateur implements InterfaceEvaluateur, Observable {
    private Clavier clavier;
    private Analyseur analyseur;
    private List<List<Mouvement>> mouvementListe;
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

    public Evaluateur(Analyseur analyseur, Clavier clavier) { // ? est ce qu'on donne le type du clavier genre Azery
                                                              // qwerty etc..?
        this.analyseur = analyseur;
        this.clavier = clavier;
        try {
            this.mouvementListe = this.analyseur.transformeEnTouche(this.clavier);
        } catch (Exception TouchNotFound) {
            System.out.println("La touche n'existe pas dans le clavier !");
        }
        this.alternance = 0;
        this.ciseaux = 0;
        this.lsb = 0;
        this.redirection = 0;
        this.roulement = 0;
        this.sfb = 0;
        this.skipgramme = 0;
        this.score1touche = 0;
        this.nombre3Grammes = this.analyseur.getNombre3Gramme();
    }

    public int tailleMouvement(Mouvement m) {
        if (m instanceof Mouvement1) {
            return 1;
        } else if (m instanceof Mouvement2) {
            return 2;
        } else {
            return 3;
        }

    }
    // * V2 mais si V1 marche pas
    // public Mouvement meilleurMouvement(List<Mouvement> l){
    // Mouvement res = l.get(0);
    // for (Mouvement mouvement : l) {
    // int tailleMouvementChoisi = tailleMouvement(res);
    // int tailleMouvementCandidat = tailleMouvement(mouvement);
    // if (tailleMouvementCandidat<tailleMouvementChoisi){
    // res = mouvement;
    // }
    // }
    // return res;
    // }

    public Mouvement meilleurMouvement(List<Mouvement> l) {
        Mouvement res = l.get(0);
        int tailleMouvementChoisi = tailleMouvement(res);
        for (Mouvement mouvement : l) {
            int tailleMouvementCandidat = tailleMouvement(mouvement);
            if (tailleMouvementCandidat < tailleMouvementChoisi) { // TODO changer le critère nan ?
                res = mouvement;
                tailleMouvementChoisi = tailleMouvementCandidat;
            }
        }
        return res;
    }

    @Override
    public double donneLeScore() { // TODO utiliser les streams
        double res = 0;
        for (List<Mouvement> combList : mouvementListe) {
            Mouvement mouvement = meilleurMouvement(combList);
            // System.out.println(mouvement);
            if (mouvement instanceof Mouvement1) {
                // System.out.println("dans M1");
                score1touche += ((Mouvement1) mouvement).getScore();
                // System.out.println(" score1touche = "+((Mouvement1) mouvement).getScore());
            } else if (mouvement instanceof Mouvement2) {
                Mouvement2 inter = (Mouvement2) mouvement;
                // System.out.println(inter);
                // System.out.println("dans M2");
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
                // System.out.println("dans M3");
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
        double numerateur = redirection + skipgramme + lsb + sfb + ciseaux - roulement - alternance + score1touche; // TODO
                                                                                                                    // Chef
                                                                                                                    // ?
                                                                                                                    // yakak
        // System.out.println("numérateur = "+numerateur);
        // System.out.println("Nombre de 3 grammes "+ nombre3Grammes);
        if (nombre3Grammes != 0) {
            res = numerateur / nombre3Grammes;
        }
        double i = 20.0 / (1.0 + res);
        res = i;
        return res;
    }

    //! méthode osef
    public void afficheListeTouche(){
        int cmp = 0;
        for (List<Mouvement> list : mouvementListe) {
            // System.out.println("itération : "+cmp);
            System.out.println("----------");
            for (Mouvement mouvement : list) {
                System.out.println("type de mouvement " + tailleMouvement(mouvement));
                for (Touche touche : mouvement.getSqTouches()) {
                    System.out.println("touche = " + touche.getEtiq());
                }
            }
            // System.out.println("taille list = "+list.size());
            cmp++;
        }
    }

    @Override
    public List<Observer> getObservers() {
        return this.observers;
    }
}

// @Override
// public int donneLeScore() { // TODO utiliser les streams
// int res = 0;
// for (Mouvement mouvement : mouvementListe) {
// if (mouvement instanceof Mouvement1){
// score1touche+=((Mouvement1) mouvement).getScore();
// }
// else if (mouvement instanceof Mouvement2){
// Mouvement2 inter = (Mouvement2) mouvement;
// int occ= inter.getOccurrences();
// if (inter.isAlternance()){
// alternance+=occ;
// }
// if (inter.isCiseaux()){
// ciseaux+=occ;
// }
// if (inter.isLSB()){
// lsb+=occ;
// }
// if (inter.isSFB()){
// sfb+=occ;
// }
// roulement+=inter.isRoulement();
// }else{
// Mouvement3 interMouvement3 = (Mouvement3) mouvement;
// int occ = interMouvement3.getOccurrences();
// if (interMouvement3.notRedirection() == false){
// redirection+=occ;
// if (interMouvement3.redirectionSansIndex()){
// redirection+=occ;
// }
// }
// if (interMouvement3.skipgramme()){
// skipgramme+=occ;
// }
// }
// }
// return res;
// }
