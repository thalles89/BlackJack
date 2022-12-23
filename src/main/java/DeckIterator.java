import java.util.Iterator;
import java.util.function.Consumer;

public interface DeckIterator extends Iterator {
    @Override
    boolean hasNext();

    @Override
    Object next();

    @Override
    default void remove() {
        Iterator.super.remove();
    }

    @Override
    default void forEachRemaining(Consumer action) {
        Iterator.super.forEachRemaining(action);
    }

    void first();

    boolean isDone();
}
