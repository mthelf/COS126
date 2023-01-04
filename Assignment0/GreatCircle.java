public class GreatCircle {
    public static void main(String[] args) {

        // take in values and convert to radians
        double x1 = Math.toRadians(Double.parseDouble(args[0]));
        double x2 = Math.toRadians(Double.parseDouble(args[2]));
        double y1 = Math.toRadians(Double.parseDouble(args[1]));
        double y2 = Math.toRadians(Double.parseDouble(args[3]));

        // find the angle in radians using values
        double angle = Math.acos(Math.sin(x1) * Math.sin(x2) +
                                         Math.cos(x1) * Math.cos(x2) *
                                                 Math.cos(y2 - y1));

        // convert back to degrees and multiply by 60 to get nautical miles
        double distance = 60 * Math.toDegrees(angle);

        // display result
        System.out.println(distance + " nautical miles");

    }
}
