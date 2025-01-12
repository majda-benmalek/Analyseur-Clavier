package ui;

/**
 * Interface Observer.
 * Cette interface doit être implémentée par les classes qui souhaitent observer un objet Observable.
 */
public interface Observer {
    /**
     * Méthode appelée lorsque l'objet Observable notifie ses observateurs sans message spécifique.
     */
    void update();

    /**
     * Méthode appelée lorsque l'objet Observable notifie ses observateurs avec un message spécifique.
     * @param message le message envoyé par l'objet Observable.
     */
    void update(String message);
}