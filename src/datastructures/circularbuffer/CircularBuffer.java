package datastructures.circularbuffer;

import datastructures.Buffer;


/**
 * This class represents a Java-Implementation of a circular buffer.
 *
 * @param <Value> - It represents a generic object.
 * @author Andrea Graziani
 * @version 1.0
 */
public class CircularBuffer<Value> implements Buffer<Value> {

    private Value buffer[];
    private int size;
    private int readPointer;
    private int writePointer;

    /**
     * Constructor.
     *
     * @param buffer - Represents an already allocated {@code T} objects array.
     */
    public CircularBuffer(Value[] buffer) {
        this.buffer = buffer;
        this.size = 0;
        this.readPointer = this.writePointer = 0;
    }

    /**
     * This method is used to check if current circular buffer is empty.
     *
     * If this circular buffer is empty, {@code true} is returned; otherwise {@code false} is returned.
     *
     * @return A {@code boolean} value.
     */
    private boolean isEmpty() {
        return (this.size == 0);
    }

    /**
     * This method is used to check if current circular buffer is full.
     *
     * If this circular buffer is full, {@code true} is returned; otherwise {@code false} is returned.
     *
     * @return A {@code boolean} value.
     */
    private boolean isFull() {
        return (this.size == this.buffer.length);
    }

    @Override
    public synchronized void push(Value pObject) throws InterruptedException {

        if (isFull())
            wait();

        this.buffer[this.writePointer] = pObject;
        this.writePointer = (this.writePointer + 1) % this.buffer.length;
        this.size++;

        notify();
    }

    @Override
    public synchronized Value pop() throws InterruptedException {

        if (isEmpty())
            wait();

        Value output = this.buffer[this.readPointer];
        this.readPointer = (this.readPointer + 1) % this.buffer.length;
        this.size--;

        notify();
        return output;
    }

    @Override
    public void clear() {
        this.size = this.readPointer = this.writePointer = 0;
    }
}