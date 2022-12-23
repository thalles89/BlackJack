package interfaces;

import java.util.Iterator;
import java.util.function.Consumer;


/**
 * @author Thalles
 * @version 0.0.1
 * */
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
