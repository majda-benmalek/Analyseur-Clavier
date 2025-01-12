package ui;

/**
 * Implémentation de l'interface Observer.
 * Cette classe observe un objet Observable et réagit aux notifications de celui-ci.
 */
public class ObserverImplm implements Observer {

    private Observable observable;

    /**
     * Constructeur de la classe ObserverImplm.
     * @param o l'objet Observable à observer.
     */
    public ObserverImplm(Observable o) {
        this.observable = o;
        observable.addObserver(this, observable.getObservers());
    }

    /**
     * Méthode appelée lorsque l'objet Observable notifie ses observateurs sans message spécifique.
     */
    @Override
    public void update() {
        // Implémentation vide
    }

    /**
     * Méthode appelée lorsque l'objet Observable notifie ses observateurs avec un message spécifique.
     * @param message le message envoyé par l'objet Observable.
     */
    @Override
    public void update(String message) {
        switch (message) {
            case "Clavier":
                System.out.println("Le clavier a été chargé avec succès");
                break;
            case "Combinaison":
                System.out.println("Les combinaisons ont été chargées avec succès");
                break;
            case "analyse":
                System.out.println("L'analyse du corpus est terminée.");
                break;
            case "evaluation":
                System.out.println("L'évaluation est terminée");
                break;
            default:
                System.out.println("Message inconnu: " + message);
                break;
        }
    }
}