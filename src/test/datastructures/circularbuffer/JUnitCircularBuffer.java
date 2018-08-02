package test.datastructures.circularbuffer;

import datastructures.circularbuffer.CircularBuffer;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class JUnitCircularBuffer {


    @Test
    public void test() {

        CircularBuffer<Integer> myCircularBuffer = new CircularBuffer<>(new Integer[4]);

        Runnable writer = new Runnable() {
            @Override
            public void run() {

                try {

                    for (int i = 0; i < 100000000 ; i++)
                        myCircularBuffer.push((Integer) i);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Runnable reader = new Runnable() {
            @Override
            public void run() {

                try {

                    for (int i = 0; i < 100000000 ; i++)
                        assertEquals((Integer) i,  myCircularBuffer.pop());

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread writerThread = new Thread(writer);
        Thread readerThread = new Thread(reader);


        readerThread.start();
        writerThread.start();


        try {
            writerThread.join();
            readerThread.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }


}
