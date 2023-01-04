public class Blob {
    private double sumX;    // Sum of all x values in blob
    private double sumY;    // Sum of all y values in blob
    private int mass;       // Pixels in the blob

    //  creates an empty blob
    public Blob() {
        mass = 0;
        sumX = 0;
        sumY = 0;
    }

    //  adds pixel (x, y) to this blob
    public void add(int x, int y) {
        // Increase the x, y sums as well as the mass(pixels).
        sumX += x;
        sumY += y;
        mass++;
    }

    // Calculates the x coordinate for center of mass
    public double centerOfMassX() {
        double centerOfMassX = sumX / mass;
        return centerOfMassX;
    }

    // Calculates the y coordinate for center of mass
    public double centerOfMassY() {
        double centerOfMassY = sumY / mass;
        return centerOfMassY;
    }

    //  number of pixels added to this blob
    public int mass() {
        return mass;
    }

    //  Euclidean distance between the center of masses of the two blobs
    public double distanceTo(Blob that) {
        double deltaX = this.centerOfMassX() - that.centerOfMassX();
        double deltaY = this.centerOfMassY() - that.centerOfMassY();
        double distance = Math.sqrt((deltaX * deltaX) + (deltaY * deltaY));
        return distance;
    }

    //  string representation of this blob (see below)
    public String toString() {
        // Taken from assignment checklist page
        return String.format("%2d (%8.4f, %8.4f)", mass, centerOfMassX(),
                             centerOfMassY());
    }

    public static void main(String[] args) {
        // Test on a 3, 4, 5 triangle

        Blob blob1 = new Blob();
        blob1.add(0, 0);
        StdOut.println();
        StdOut.println(blob1.mass());
        StdOut.println(blob1.centerOfMassX());
        StdOut.println(blob1.centerOfMassY());
        StdOut.println(blob1.centerOfMassY());
        Blob blob2 = new Blob();
        blob2.add(3, 4);

        StdOut.println(blob1.distanceTo(blob2));
    }
}
