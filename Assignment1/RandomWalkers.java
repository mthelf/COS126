public class RandomWalkers {
    public static void main(String[] args) {

        // initialize variables needed
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        double totalDistance = 0;

        for (int j = 0; j <= trials; j++) {
            
            // start at origin for every loop
            int x = 0;
            int y = 0;

            for (int i = 0; i < n; i++) {

                // generates new random number from 0.0 <= x < 1.0 every loop
                double r = Math.random();

                // even distribution of probability for step in each direction
                if (r < 0.25) {
                    x++;
                }
                else if (r < 0.5) {
                    x--;
                }
                else if (r < 0.75) {
                    y++;
                }
                else {
                    y--;
                }
            }

            // calculate the squared distance 
            int sd = (x * x) + (y * y);
            // display the squared distance
            totalDistance = totalDistance + sd;
        }

        // create double variable for trials
        double trials1 = trials;

        // calculate the mean squared distance and output
        double meanSqDist = (totalDistance / trials1);
        System.out.println("mean squared distance = " + meanSqDist);
    }
}
