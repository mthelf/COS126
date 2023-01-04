public class Ordered {
    public static void main(String[] args) {

        // create boolean for order and parse args to int variables
        boolean isOrdered;

        int x = Integer.parseInt(args[0]);
        int y = Integer.parseInt(args[1]);
        int z = Integer.parseInt(args[2]);

        // run check for if the variables are ordered, either ascending or descending
        isOrdered = ((x > y) && (y > z)) || ((x < y) && (y < z));

        // output result
        System.out.println(isOrdered);
    }
}
