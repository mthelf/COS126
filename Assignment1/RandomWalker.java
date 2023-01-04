public class RandomWalker {
    public static void main(String[] args) {

        // take in value as int
        int n = Integer.parseInt(args[0]);

        // start of walk at origin
        int x = 0;
        int y = 0;

        // for loop to go through number of steps requested
        for (int i = 0; i < n; i++) {

            // generates new random number from 0.0 <= x < 1.0 every loop
            double r = Math.random();

            // even distribution of probability for step in each direction
            if (r < 0.25) {
                x++;
                System.out.println("(" + x + ", " + y + ")");
            }
            else if (r < 0.5) {
                x--;
                System.out.println("(" + x + ", " + y + ")");
            }
            else if (r < 0.75) {
                y++;
                System.out.println("(" + x + ", " + y + ")");
            }
            else {
                y--;
                System.out.println("(" + x + ", " + y + ")");
            }
        }

        // calculate the squared distance
        int sd = (x * x) + (y * y);

        // display the squared distance
        System.out.println("squared distance = " + sd);
    }
}
