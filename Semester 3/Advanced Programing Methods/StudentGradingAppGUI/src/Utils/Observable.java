package Utils;

public interface Observable<E extends Event> {
    void update(E event);
}
