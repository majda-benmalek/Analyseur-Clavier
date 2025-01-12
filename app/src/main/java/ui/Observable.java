package ui;

import java.util.List;

/**
 * Interface Observable.
 * Cette interface doit être implémentée par les classes qui souhaitent être observées par des observateurs.
 */
public interface Observable {
    /**
     * Retourne la liste des observateurs.
     * @return la liste des observateurs.
     */
    public List<Observer> getObservers();

    /**
     * Ajoute un observateur à la liste des observateurs.
     * @param observer l'observateur à ajouter.
     * @param observers la liste des observateurs.
     */
    public default void addObserver(Observer observer, List<Observer> observers) {
        observers.add(observer);
    }

    /**
     * Supprime un observateur de la liste des observateurs.
     * @param observer l'observateur à supprimer.
     * @param observers la liste des observateurs.
     */
    public default void removeObserver(Observer observer, List<Observer> observers) {
        observers.remove(observer);
    }

    /**
     * Notifie tous les observateurs avec un message spécifique.
     * @param message le message à envoyer aux observateurs.
     * @param o l'observateur à notifier.
     * @param observers la liste des observateurs.
     */
    public default void notifyObservers(String message, Observer o, List<Observer> observers) {
        o.update(message);
        removeObserver(o, observers);
    }
}