public class Art2 {

    //  Draws a filled square whose centre is (x, y)
    //  of the specified side length.
    private static void filledSquare(double x, double y, double length) {
        double[] vx = { x - (length / 2), x - (length / 2), x + (length / 2), x + (length / 2) };
        double[] vy = { y - (length / 2), y + (length / 2), y + (length / 2), y - (length / 2) };

        StdDraw.filledPolygon(vx, vy);
    }

    //  Draws a Fractal Square of order n, such that the largest filled
    //  square has centre vertex (x, y) and sides of the specified length.
    private static void Fractal(int n, double x, double y, double length) {
        filledSquare(x, y, length);
        if (Math.log(1 / length) / Math.log(3) < n) {
            Fractal(n, x - length, y + length, length / 3);
            Fractal(n, x, y + length, length / 3);
            Fractal(n, x + length, y + length, length / 3);
            Fractal(n, x - length, y, length / 3);
            Fractal(n, x + length, y, length / 3);
            Fractal(n, x - length, y - length, length / 3);
            Fractal(n, x, y - length, length / 3);
            Fractal(n, x + length, y - length, length / 3);
        }
    }

    //  Takes an integer command-line argument n;
    //  draws the outline of Square of length 1;
    //  whose bottom-left vertex is (0, 0) and bottom-right vertex is (1, 0); and
    //  draws a Fractal Square of order n that fits snugly inside the outline.
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        double[] vx = { 0, 0, 1, 1 };
        double[] vy = { 0, 1, 1, 0 };
        StdDraw.polygon(vx, vy);
        Fractal(n, 0.5, 0.5, 0.333333);
    }
}
