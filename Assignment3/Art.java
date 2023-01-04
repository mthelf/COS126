import java.awt.Color;

public class Art {
    // Draws a filled square with center at the previous squares respective corner
    public static void filledSquare(double x, double y, double radius) {
        double[] newX = {
                x - radius / 2, x - radius / 2,
                x + radius / 2, x + radius / 2
        };
        double[] newY = {
                y - radius / 2, y + radius / 2,
                y + radius / 2, y - radius / 2
        };

        // Sets color of each square randomly
        StdDraw.setPenColor(Color.WHITE);
        int red = (int) (StdRandom.uniform() * 255);
        int green = (int) (StdRandom.uniform() * 255);
        int blue = (int) (StdRandom.uniform() * 255);
        StdDraw.setPenColor(red, green, blue);
        StdDraw.polygon(newX, newY);
        StdDraw.filledPolygon(newX, newY);
    }


    // Draws a filled square at the corner of each square to the order of n
    public static void squares(int n, double x, double y, double radius) {
        if (n > 0) {
            filledSquare(x, y, radius);
            // Calls recursively for top-right, top-left, bottom-right, bottom-left
            // squares
            squares(n - 1, x - radius / 2, y + radius / 2, radius / 2);
            squares(n - 1, x + radius / 2, y - radius / 2, radius / 2);
            squares(n - 1, x + radius / 2, y + radius / 2, radius / 2);
            squares(n - 1, x - radius / 2, y - radius / 2, radius / 2);
        }
    }

    //  Takes an integer command-line argument n;
    //  Draws a Square with length 1;
    //  1st order square has center at (0.5, 0.5);
    //  Higher orders, such as 8, could represents random pixel colors on a screen
    //  while lower orders are simple square patterns
    //  Receive a random color pattern each time.
    public static void main(String[] args) {
        if (args.length > 0) {
            int n = Integer.parseInt(args[0]);
            double[] startX = { 0, 0, 1, 1 };
            double[] startY = { 0, 1, 1, 0 };
            StdDraw.polygon(startX, startY);
            squares(n, 0.5, 0.5, 0.5);
        }
    }
}

