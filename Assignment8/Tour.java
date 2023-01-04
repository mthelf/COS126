public class Tour {

    private class Node {
        private Point p;
        private Node next;
    }

    private Node first; // first node in linked list

    // creates an empty tour
    public Tour() {
        first = new Node();
    }

    // creates the 4-point tour a->b->c->d->a (for debugging)
    public Tour(Point a, Point b, Point c, Point d) {
        Node nodeB = new Node();
        Node nodeC = new Node();
        Node nodeD = new Node();

        // Create linked list using Node class of all points
        first = new Node();
        first.p = a;
        first.next = nodeB;
        nodeB.p = b;
        nodeB.next = nodeC;
        nodeC.p = c;
        nodeC.next = nodeD;
        nodeD.p = d;
        nodeD.next = first;
    }

    // returns the number of points in this tour
    public int size() {
        if (first.p == null) return 0;
        // create counter variable, cycle through starting and ending at first point
        // increasing counter each time
        int count = 0;
        Node node = first;

        do {
            node = node.next;
            count++;
        } while (!node.equals(first));
        return count;
    }

    // returns the length of this tour
    public double length() {
        if (first.p == null) {
            return 0.0;
        }

        else {
            // Create distance variable and cycle through like in size to get length
            Node node = first;
            double dist = 0.0;

            do {
                dist += node.p.distanceTo(node.next.p);
                first = first.next;
            } while (!node.equals(first));
            return dist;
        }
    }

    // returns a string representation of this tour
    public String toString() {
        if (first.p == null) return "";
        // Creates a string builder object and appends each node, starting at first,
        // to the object and returns once it reaches the first node.
        Node node = first;
        StringBuilder stringBuilder = new StringBuilder();

        do {
            stringBuilder.append(node.p.toString() + "\n");
            node = node.next;
        } while (!node.equals(first));
        return stringBuilder.toString();
    }

    // draws this tour to standard drawing
    public void draw() {
        if (first.p != null) {
            Node node = first;
            do {
                node.p.drawTo(node.next.p);
                node = node.next;
            } while (!node.equals(first));
        }
    }

    // inserts p using the nearest neighbor heuristic
    public void insertNearest(Point p) {
        Node insert = new Node();
        insert.p = p;

        // Set first point read to first node.
        if (first.p == null) {
            first = insert;
            insert.next = insert;
        }
        else {
            Node current = first;
            double distance = Double.POSITIVE_INFINITY;
            Node chosen = first;
            do {
                if (insert.p.distanceTo(current.p) < distance) {
                    distance = insert.p.distanceTo(current.p);
                    chosen = current;
                }
                current = current.next;
            } while (!current.equals(first));
            Node temp = chosen.next;
            chosen.next = insert;
            insert.next = temp;
        }
    }

    // inserts p using the smallest increase heuristic
    public void insertSmallest(Point p) {
        Node insert = new Node();
        insert.p = p;

        // Set first point read to first node
        if (first.p == null) {
            first = insert;
            insert.next = insert;
        }
        else {
            Node current = first;
            double increase = Double.POSITIVE_INFINITY;
            Node chosen = first;
            do {
                double increment = current.p.distanceTo(insert.p)
                        + insert.p.distanceTo(current.next.p)
                        - current.p.distanceTo(current.next.p);
                if (increment < increase) {
                    increase = increment;
                    chosen = current;
                }
                current = current.next;
            } while (!current.equals(first));
            Node temp = chosen.next;
            chosen.next = insert;
            insert.next = temp;
        }
    }


    // tests this class by directly calling all constructors and instance methods

    public static void main(String[] args) {

        // define 4 points that are the corners of a square
        Point a = new Point(100.0, 100.0);
        Point b = new Point(500.0, 100.0);
        Point c = new Point(500.0, 500.0);
        Point d = new Point(100.0, 500.0);

        // create the tour a -> b -> c -> d -> a
        Tour squareTour = new Tour(a, b, c, d);

        // Testing all the methods required
        int size = squareTour.size();
        StdOut.println("Number of points = " + size);
        double length = squareTour.length();
        StdOut.println("Tour length = " + length);
        StdOut.println(squareTour);
        StdDraw.setXscale(0, 600);
        StdDraw.setYscale(0, 600);
        squareTour.draw();
        StdDraw.show();
        StdOut.print(squareTour.toString());
    }
}

