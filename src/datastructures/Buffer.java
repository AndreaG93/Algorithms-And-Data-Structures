package datastructures;

/**
 * This class represents a generic buffer data structure.
 *
 * @param <Value> - It represents a generic object.
 * @author Andrea Graziani
 * @version 1.3
 */
public interface Buffer<Value> {

    /**
     * This method is used to push a specified {@code Value} object in current buffer.
     * <p>
     * If current buffer is full an {@code Exception} is thrown.
     *
     * @param pValue - It represents a {@code Value} object
     * @throws Exception - An {@code Exception} is thrown when current buffer is full.
     */
    void push(Value pValue) throws Exception;

    /**
     * This method is used to pop a {@code Value} object from current buffer.
     * <p>
     * If current buffer is empty an {@code Exception} is thrown.
     *
     * @return - A {@code Value} object
     * @throws Exception - An {@code Exception} is thrown when current buffer is empty.
     */
    Value pop() throws Exception;

    /**
     * This method is used to removes all {@code Value} object from buffer.
     */
    void clear();
}
