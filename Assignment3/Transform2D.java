public class Transform2D {

    // Returns a new array object that is an exact copy of the given array.
    // The given array is not mutated.
    public static double[] copy(double[] array) {
        double[] cArray = new double[array.length];

        for (int i = 0; i < array.length; i++) {
            cArray[i] = array[i];
        }
        return cArray;
    }

    // Scales the polygon by the factor alpha.
    // Both double arrays x and y are of the same length.
    public static void scale(double[] x, double[] y, double alpha) {
        for (int i = 0; i < x.length; i++) {
            x[i] = alpha * x[i];
            y[i] = alpha * y[i];
        }
    }

    // Translates the polygon by (dx, dy).
    public static void translate(double[] x, double[] y, double dx, double dy) {
        for (int i = 0; i < x.length; i++) {
            x[i] = x[i] + dx;
            y[i] = y[i] + dy;
        }
    }

    // Rotates the polygon theta degrees counterclockwise, about the origin.
    public static void rotate(double[] x, double[] y, double theta) {
        double[] rx = copy(x);
        double[] ry = copy(y);
        
        for (int i = 0; i < x.length; i++) {
            x[i] = rx[i] * Math.cos(Math.toRadians(theta))
                    - ry[i] * Math.sin(Math.toRadians(theta));

            y[i] = ry[i] * Math.cos(Math.toRadians(theta))
                    + rx[i] * Math.sin(Math.toRadians(theta));
        }
    }

    // Tests each of the API methods by directly calling them.
    public static void main(String[] args) {
        StdDraw.setScale(-5.0, +5.0);
        double[] x = { 0, 1, 1, 0 };
        double[] y = { 0, 0, 2, 1 };
        double theta = 45.0;
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.polygon(x, y);
        rotate(x, y, theta);
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.polygon(x, y);
    }
}
