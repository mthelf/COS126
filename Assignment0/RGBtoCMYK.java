public class RGBtoCMYK {
    public static void main(String[] args) {
        // parse int values from string input for final display
        int red = Integer.parseInt(args[0]);
        int green = Integer.parseInt(args[1]);
        int blue = Integer.parseInt(args[2]);

        // take in double values to perform conversion with equation for white
        double dRed = Double.parseDouble(args[0]);
        double dGreen = Double.parseDouble(args[1]);
        double dBlue = Double.parseDouble(args[2]);

        // use doubles of RGB to calculate white from 0-1.0
        double white = Math.max(Math.max((dRed / 255), (dGreen / 255)), (dBlue / 255));

        // use white to calculate final CMYK values
        double cyan = (white - (dRed / 255)) / white;
        double magenta = (white - (dGreen / 255)) / white;
        double yellow = (white - (dBlue / 255)) / white;
        double black = 1 - white;

        // use int variables to display RGB values
        System.out.println("red      = " + red);
        System.out.println("green    = " + green);
        System.out.println("blue     = " + blue);

        // display CMYK conversion below
        System.out.println("cyan     = " + cyan);
        System.out.println("magenta  = " + magenta);
        System.out.println("yellow   = " + yellow);
        System.out.println("black    = " + black);
    }
}
