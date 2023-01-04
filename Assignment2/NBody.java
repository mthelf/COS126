public class NBody {
    public static void main(String[] args) {

        final double GRAVITY = 6.67e-11;

        // Duration of simulation
        double tau = Double.parseDouble(args[0]);
        // Time increment for simulation
        double dT = Double.parseDouble(args[1]);

        // Read from file and store values in respective arrays
        while (!StdIn.isEmpty()) {
            // Number of planets and radius of universe
            int n = StdIn.readInt();
            double r = StdIn.readDouble();

            // Declare and initialize arrays for position, velocity, mass, and image file of planets.
            double[] px = new double[n];
            double[] py = new double[n];
            double[] vx = new double[n];
            double[] vy = new double[n];
            double[] mass = new double[n];
            String[] image = new String[n];

            for (int i = 0; i < n; i++) {
                px[i] = StdIn.readDouble();
                py[i] = StdIn.readDouble();
                vx[i] = StdIn.readDouble();
                vy[i] = StdIn.readDouble();
                mass[i] = StdIn.readDouble();
                image[i] = StdIn.readString();
            }
            // Stores excess string at end of file
            String[] extra = StdIn.readAllLines();

            // Initialize standard drawing to radius of universe.
            StdDraw.setXscale(-r, r);
            StdDraw.setYscale(-r, r);
            StdDraw.enableDoubleBuffering();

            // Play music on standard audio during simulation.
            StdAudio.playInBackground("2001.wav");

            // Simulate universe at every time interval
            for (double t = 0.0; t < tau; t = t + dT) {

                // Calculate net forces between each celestial body.
                double[] fx = new double[n];
                double[] fy = new double[n];
                for (int i = 0; i < n; i++) {
                    fx[i] = 0;
                    fy[i] = 0;
                    for (int j = 0; j < n; j++) {
                        if (i != j) {
                            double dx = px[j] - px[i];
                            double dy = py[j] - py[i];
                            double distance = Math.sqrt((dx * dx) + (dy * dy));
                            double force = GRAVITY * mass[i] * mass[j] /
                                    (distance * distance);
                            fx[i] = fx[i] + (force * dx / distance);
                            fy[i] = fy[i] + (force * dy / distance);
                        }
                    }

                }

                // Update velocities and positions of each planet.
                for (int i = 0; i < n; i++) {
                    double ax = fx[i] / mass[i];
                    double ay = fy[i] / mass[i];
                    vx[i] = vx[i] + ax * dT;
                    vy[i] = vy[i] + ay * dT;
                    px[i] = px[i] + vx[i] * dT;
                    py[i] = py[i] + vy[i] * dT;
                }

                // Draw universe to standard drawing.
                StdDraw.picture(0, 0, "starfield.jpg");
                for (int i = 0; i < n; i++) {
                    StdDraw.picture(px[i], py[i], image[i]);

                }
                StdDraw.show();
                StdDraw.pause(20);


            }
            // Once the simulation is finished, prints universe details to standard output.
            StdOut.printf("%d\n", n);
            StdOut.printf("%.2e\n", r);
            for (int i = 0; i < n; i++) {
                StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                              px[i], py[i], vx[i], vy[i], mass[i], image[i]);
            }
        }
    }
}



