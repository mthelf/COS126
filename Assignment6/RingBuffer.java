/*******************************************************************************
 *
 *  This is a template file for RingBuffer.java. It lists the constructors
 *  and methods you need to implement, along with descriptions of what they're
 *  supposed to do.
 *
 *  Note: it won't compile until you fill in the constructors and methods
 *        (or at least commment out the ones whose return type is non-void).
 *
 ******************************************************************************/

public class RingBuffer {
    private int cap;        // size capacity of buffer
    private int first;      // index variable for peek and enqueue
    private int last;       // index variable for dequeue
    private double[] rb;    // array for buffer
    private int size;       // # of elements in buffer

    // creates an empty ring buffer with the specified capacity
    public RingBuffer(int capacity) {
        rb = new double[capacity];
        cap = capacity;
        first = 0;
        last = 0;
        size = 0;
    }

    // return the capacity of this ring buffer
    public int capacity() {
        return cap;
    }

    // return number of items currently in this ring buffer
    public int size() {
        return size;
    }

    // is this ring buffer empty (size equals zero)?
    public boolean isEmpty() {
        switch (size) {
            case 0:
                return true;
            default:
                return false;
        } // checkstyle recommended a switch here vs. an if-else statement?
    }

    // is this ring buffer full (size equals capacity)?
    public boolean isFull() {
        if (size == cap) {
            return true;
        }
        else {
            return false;
        }
    }

    // adds item x to the end of this ring buffer
    public void enqueue(double x) {
        // first check if buffer is full
        if (isFull()) {
            throw new RuntimeException("Ring buffer is full.");
        }
        rb[last] = x;
        last += 1;
        size = size + 1;
        if (last == cap) {
            last = 0;
        }
    }

    // deletes and returns the item at the front of this ring buffer
    public double dequeue() {
        // first checks if buffer is empty
        if (isEmpty()) {
            throw new RuntimeException("Ring Buffer is empty.");
        }
        else {
            double oldFirst = rb[first];
            first++;
            if (first == cap) {
                first = 0;
            }
            size = size - 1;
            return oldFirst;
        }
    }

    // returns the item at the front of this ring buffer
    public double peek() {
        // first check if buffer is empty
        if (isEmpty()) {
            throw new RuntimeException("Ring Buffer is empty.");
        }
        else {
            return rb[first];
        }
    }

    // tests and calls every instance method in this class
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        RingBuffer buffer = new RingBuffer(n);
        for (int i = 1; i <= n; i++) {
            buffer.enqueue(i);
        }
        double t = buffer.dequeue();
        buffer.enqueue(t);
        StdOut.println("Size after wrap-around is " + buffer.size());
        while (buffer.size() >= 2) {
            double x = buffer.dequeue();
            double y = buffer.dequeue();
            buffer.enqueue(x + y);
        }
        StdOut.println(buffer.peek());
    }

}
