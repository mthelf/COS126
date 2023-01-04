/*******************************************************************************
 *
 *  This is a template file for GuitarString.java. It lists the constructors
 *  and methods you need, along with descriptions of what they're supposed
 *  to do.
 *
 *  Note: it won't compile until you fill in the constructors and methods
 *        (or at least commment out the ones whose return type is non-void).
 *
 ******************************************************************************/

public class GuitarString {
    // Ring buffer declaration
    private RingBuffer string;

    // creates a guitar string of the specified frequency,
    // using sampling rate of 44,100
    public GuitarString(double frequency) {
        int samplingRate = 44100;
        int n = (int) Math.ceil(samplingRate / frequency);
        string = new RingBuffer(n);
        for (int i = 0; i < string.capacity(); i++) {
            string.enqueue(0);
        }
    }

    // creates a guitar string whose size and initial values are given by
    // the specified array
    public GuitarString(double[] init) {
        string = new RingBuffer(init.length);
        for (int i = 0; i < init.length; i++) {
            string.enqueue(init[i]);
        }

    }

    // returns the number of samples in the ring buffer
    public int length() {
        return string.size();
    }

    // plucks the guitar string (by replacing the buffer with white noise)
    public void pluck() {
        for (int i = 0; i < string.size(); i++) {
            string.dequeue();
            // adds "white noise", a value from -.5 to .5
            string.enqueue(StdRandom.uniform(-0.5, 0.5));
        }
    }

    // advances the Karplus-Strong simulation one time step
    public void tic() {
        final double decayFactor = 0.996;
        double removed = string.dequeue();  // stores first element so second can
        // be accessed with peek
        double x = ((removed + string.peek()) / 2) * decayFactor;
        string.enqueue(x);
    }

    // returns the current sample(first element)
    public double sample() {
        return string.peek();
    }


    // tests and calls every constructor and instance method in this class
    public static void main(String[] args) {
        double[] samples = { 0.2, 0.4, 0.5, 0.3, -0.2, 0.4, 0.3, 0.0, -0.1, -0.3 };
        GuitarString testString = new GuitarString(samples);
        int m = 25; // 25 tics
        for (int i = 0; i < m; i++) {
            double sample = testString.sample();
            StdOut.printf("%6d %8.4f\n", i, sample);
            testString.tic();
        }
    }

}
