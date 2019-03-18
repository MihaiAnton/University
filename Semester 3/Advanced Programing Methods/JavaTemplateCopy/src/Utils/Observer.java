package Utils;

public interface Observer<E extends Event> {
    void notify(E event);
}
