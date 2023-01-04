/* *****************************************************************************
 * Name: Michael Helf
 * NetID:
 * Precept:
 *
 * Description: Linear-Feedback Shift Register Program
 *
 **************************************************************************** */

public class LFSR {
    // variable for bits key(register)
    private String reg;
    // variable for position of tap in array
    private int tapPosition;

    // creates an LFSR with the specified seed and tap
    public LFSR(String seed, int tap) {
        // Add x to stop while loop checking for bits
        reg = seed + 'x';
        tapPosition = reg.length() - tap - 1;
    }

    // returns the number of bits in this LFSR
    public int length() {
        int length = 0; // is it okay to use length here as a variable name
        // for a method named the same?
        while ((bitAt(length) == '1') || (bitAt(length) == '0')) {
            length = length + 1;
        }
        return length;
    }

    // returns the ith bit of this LFSR (as 0 or 1)
    public int bitAt(int i) {
        return Integer.parseInt(String.valueOf(reg.charAt(i)));
    }

    // returns a string representation of this LFSR
    public String toString() {
        return reg.substring(0, reg.length() - 1);
    }

    // simulates one step of this LFSR and returns the new bit (as 0 or 1)
    public int step() {
        int tapBit = bitAt(tapPosition);
        int firstBit = bitAt(0);
        int lastBit = tapBit ^ firstBit;
        char[] chars = reg.toCharArray();

        for (int i = 0; i < reg.length() - 2; i++) {
            chars[i] = chars[i + 1];
        }

        chars[reg.length() - 2] = (char) (lastBit + '0');
        int temp = reg.length();
        reg = "";
        for (int i = 0; i < temp; i++) {
            reg = reg + chars[i];
        }
        return lastBit;
    }

    // simulates k steps of this LFSR and returns the k bits as a k-bit integer
    public int generate(int k) {
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum = 2 * sum + step(); // formula from assignment page
        }
        return sum;
    }

    // tests this class by directly calling all instance methods
    public static void main(String[] args) {
        LFSR lfsr20 = new LFSR("01101000010100010000", 17);
        StdOut.println(lfsr20);
        for (int i = 0; i < 10; i++) {
            int r = lfsr20.generate(8);
            StdOut.println(lfsr20 + " " + r);
        }
    }
}


