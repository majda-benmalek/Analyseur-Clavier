package ui;

import java.util.List;

public interface Observable {
    // void addObserver(Observer observer);
    // void removeObserver(Observer observer);
    // public void notifyObservers(String message,Observer o) ;
    public List<Observer> getObservers();

    public default void addObserver(Observer observer, List<Observer> observers) {
        observers.add(observer);
    }

    public default void removeObserver(Observer observer, List<Observer> observers) {
        observers.remove(observer);
    }

    public default void notifyObservers(String message, Observer o, List<Observer> observers) {
        o.update(message);
        removeObserver(o, observers);
    }
}