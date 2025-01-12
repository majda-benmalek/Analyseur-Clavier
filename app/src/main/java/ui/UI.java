package ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import analyseur.Analyseur;
import clavier.Clavier;
import clavier.TouchNotFound;
import evaluateur.Evaluateur;

public class UI {
    private Scanner scan;
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_RED = "\u001B[31m";
    private boolean choix;
    private boolean choixClavier;
    private boolean combi;

    public UI() {
        this.scan = new Scanner(System.in);
    }

    public String corpusUser() {
        String path = "";
        System.out.println("Veuillez donner un corpus a teste :");
        String input = scan.nextLine();
        if (input != null) {
            path = input;
            return path;
        } else {
            System.out.println("Aucune ligne disponible.");
            return null;
        }
    }

    public void explication() {
        System.out.println(ANSI_GREEN + "Bienvenue dans notre logiciel d'evaluation de clavier!" + ANSI_RESET);
        System.out.println(
                "Ce logiciel permet de prendre un corpus et d'evaluer un clavier donne par rapport a celui-ci ");
    }

    public void corpus() {
        System.out.println("Souhaitez vous fournir votre corpus ? O / N");
        String input = scan.nextLine();
        while (!input.equalsIgnoreCase("O") && !input.equalsIgnoreCase("N")) {
            System.out.println("Entree invalide. Veuillez entrer 'O' pour Oui ou 'N' pour Non.");
            input = scan.nextLine();
        }
        switch (input.toUpperCase()) {
            case "O":
                choix = true;
                break;
            case "N":
                choix = false;
                break;
        }

    }

    public String choixCorpus() {
        String path = null;
        if (!choix) {
            System.out.println("Choisissez parmis les corpus par defaut : ");
            System.out.println("1 : corpus en français");
            System.out.println("2 : corpus en anglais");
            System.out.println("3 : corpus en espagnol");
            System.out.println("4 : tres grand corpus");
            System.out.println("5 : tres petit corpus");
            System.out.println("6 : corpus en java");
            int input = -1;
            while (input < 1 || input > 6) {
                try {
                    input = Integer.parseInt(scan.nextLine());
                    if (input < 1 || input > 6) {
                        System.out.println("Entree invalide. Veuillez entrer un nombre entre 1 et 6.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Entree invalide. Veuillez entrer un nombre entre 1 et 6.");
                }
            }

            switch (input) {
                case 1:
                    path = "ressources/corpus/corpus_francais.txt";
                    break;
                case 2:
                    path = "ressources/corpus/corpus_anglais.txt";
                    break;
                case 3:
                    path = "ressources/corpus/corpus_espagnol.txt";
                    break;
                case 4:
                    path = "ressources/corpus/tres_grand_corpus.txt";
                    break;
                case 5:
                    path = "ressources/corpus/tres_petit_corpus.txt";
                    break;
                case 6:
                    path = "ressources/corpus/corpus_java.txt";
                    break;
            }
        }
        return path;
    }

    public void clavier() {
        System.out.println("Souhaitez vous fournir votre propre clavier ? O / N");
        String input = scan.nextLine();
        while (!input.equalsIgnoreCase("O") && !input.equalsIgnoreCase("N")) {
            System.out.println("Entree invalide. Veuillez entrer 'O' pour Oui ou 'N' pour Non.");
            input = scan.nextLine();
        }
        switch (input.toUpperCase()) {
            case "O":
                choixClavier = true;
                break;
            case "N":
                choixClavier = false;
                break;
        }
        if (choixClavier == false)
            return;
        System.out.println(
                "Souhaitez vous fournir votre un fichier des touches de combinaisons possible avec votre clavier ? O / N");
        String input2 = scan.nextLine();
        while (!input2.equalsIgnoreCase("O") && !input2.equalsIgnoreCase("N")) {
            System.out.println("Entree invalide. Veuillez entrer 'O' pour Oui ou 'N' pour Non.");
            input2 = scan.nextLine();
        }
        switch (input2.toUpperCase()) {
            case "O":
                combi = true;
                break;
            case "N":
                combi = false;
                break;
        }
    }

    public String[] choixClavier() {
        String[] paths = new String[2];
        System.out.println("Choisissez parmi les claviers disponibles : ");
        System.out.println("1 : Clavier azerty");
        System.out.println("2 : Clavier qwerty");

        int input = -1;
        while (input < 1 || input > 2) {
            try {
                input = Integer.parseInt(scan.nextLine());
                if (input < 1 || input > 2) {
                    System.out.println("Entree invalide. Veuillez entrer un nombre entre 1 et 2.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entree invalide. Veuillez entrer un nombre entre 1 et 2.");
            }
        }

        switch (input) {
            case 1:
                paths[0] = "ressources/clavier/azerty.json";
                paths[1] = "ressources/clavier/combinaison_azerty.json";
                break;
            case 2:
                paths[0] = "ressources/clavier/qwerty.json";
                paths[1] = "ressources/clavier/combinaison_qwerty.json";
                break;
        }

        return paths;
    }

    public List<String> clavierUser() {
        List<String> paths = new ArrayList<>();
        System.out.println("Veuillez fournir un fichier clavier (.json) : ");
        String input = scan.nextLine();
        if (input != null) {
            paths.add(input);
        } else {
            System.out.println("Aucune ligne disponible.");
            return null;
        }

        if (combi) {
            System.out.println("Veuillez fournir le fichier de combinaisons (.json) : ");
            input = scan.nextLine();
            if (input != null) {
                paths.add(input);
            } else {
                System.out.println("Aucune ligne disponible.");
                return null;
            }
        }

        return paths;
    }

    public void app() {
        explication();

        corpus();
        String corpusPath;
        if (choix) {
            corpusPath = corpusUser();
        } else {
            corpusPath = choixCorpus();
        }
        System.out.println("Corpus selectionne : " + corpusPath);

        clavier();
        String[] clavierPaths;
        if (choixClavier) {
            List<String> clavierUserPaths = clavierUser();
            clavierPaths = clavierUserPaths.toArray(new String[0]);
        } else {
            clavierPaths = choixClavier();
        }
        System.out.println("Clavier selectionne : " + clavierPaths[0]);
        if (clavierPaths.length > 1) {
            System.out.println("Combinaison clavier selectionnee : " + clavierPaths[1]);
        }
        Analyseur a = new Analyseur(corpusPath);
        Clavier c;
        if (clavierPaths.length > 1) {
            c = new Clavier(clavierPaths[0], clavierPaths[1]);
        } else {
            c = new Clavier(clavierPaths[0]);
        }

        Evaluateur e;
        try {
            e = new Evaluateur(a, c);
        } catch (TouchNotFound ex) {
            System.out.println(ANSI_RED + "Un carctere du corpus n'existe pas le clavier." + ANSI_RESET);
            return;
        }
        System.out.println(ANSI_BLUE + "Score final : " + e.donneLeScore() + ANSI_RESET);

    }

}