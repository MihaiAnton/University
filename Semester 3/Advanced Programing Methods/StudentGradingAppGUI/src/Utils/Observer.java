package Utils;

public interface Observer<E extends Event> {
    void addObservable(Observable<E> observable);
    void removeObserver(Observable<E> observable);
    void notifyObservers(E event);
}
