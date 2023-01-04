public class Bits {
    public static void main(String[] args) {

        // parse in input as int
        int n = Integer.parseInt(args[0]);

        // create counter variable
        int counter = 0;

        // check for negative value and display error message if so
        if (n < 0) {
            System.out.println("Illegal input");
        }

        // otherwise perform calculation until we reach below 1 and print the counter
        else {
            while (n >= 1) {
                counter++;
                n = n / 2;
            }

            System.out.println(counter);
        }
    }
}
