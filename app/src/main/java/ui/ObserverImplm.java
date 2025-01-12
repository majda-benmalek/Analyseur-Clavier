package ui;

public class ObserverImplm implements Observer {

    private Observable observable;

    public ObserverImplm(Observable o) {
        this.observable = o;
        observable.addObserver(this, observable.getObservers());
    }

    @Override
    public void update() {
    }

    @Override
    public void update(String message) {
        switch (message) {
            case "Clavier":
                System.out.println("Le clavier a été chargé avec succés");
            case "Combinaison":
                System.out.println("Les combinaisons ont été chargé avec succés");
            case "analyse":
                System.out.println("L'analyse du corpus est fini.");
            case "evaluation":
                System.out.println("L'évaluation est fini");
        }
    }

}