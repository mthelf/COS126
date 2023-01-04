public final class MarkovModel {
    private static final int ASCII = 128;
    int order;
    int length;
    ST<String, Integer> table1;
    ST<String, int[]> table2;

    // creates a Markov model of order k for the specified text
    public MarkovModel(String text, int k) {
        order = k;
        length = text.length();
        table1 = new ST<String, Integer>();
        table2 = new ST<String, int[]>();

        // To make circular, initialize extra to first char and add chars
        // based on order. Then add the extra to the text to ensure we get
        // all possible combinations of kgrams.
        String extra = String.valueOf(text.charAt(0));
        for (int i = 1; i < order; i++) {
            extra += String.valueOf(text.charAt(i));
        }
        String circularText = text + extra;


        // Iterate through all possible substrings in the circular text.
        for (int i = 0; i < length; i++) {
            String kGram = circularText.substring(i, (i + order));
            // Check if kGram has been seen. If it has been seen, increase its Integer
            // count value, if not, add it to the symbol table and set its
            // initial count to 1.
            if (table1.contains(kGram)) {
                int seen = table1.get(kGram);
                table1.put(kGram, seen + 1);
            }
            else {
                table1.put(kGram, 1);
            }

            // Sets char at end of kGram
            char next = circularText.charAt(i + order);

            if (table2.contains(kGram)) {
                int[] values = table2.get(kGram);
                values[next] += 1;
                table2.put(kGram, values);
            }
            else {
                int[] values = new int[ASCII];
                values[next] = 1;
                table2.put(kGram, values);
            }
        }
    }


    // returns the order k of this Markov model
    public int order() {
        return order;
    }

    // returns a string representation of the Markov model
    public String toString() {
        String values;
        int count = 0;
        String[] results = new String[length];
        for (Object key : table2.keys()) {
            String keyString = (String) key;
            values = keyString + ":";
            int[] value = table2.get(keyString);
            int lngth = value.length;
            for (int i = 0; i < lngth; i++) {
                if (value[i] > 0) {
                    values += " " + Character.toString((char) i) + " " +
                            Integer.toString(value[i]);
                }
            }
            results[count] = values;
            count += 1;
        }
        String finalValues = results[0] + "\n";
        for (int i = 0; i < count; i++) {
            finalValues += results[i] + "\n";
        }
        return finalValues;
    }

    // returns the number of times the specified kgram appears in the text
    public int freq(String kgram) {
        if (kgram.length() != order) {
            throw new IllegalArgumentException("kGram must be of length k.");
        }
        // Returns 0 if the kgram is not in the table
        if (!table1.contains(kgram)) {
            return 0;
        }
        return table1.get(kgram);
    }

    // returns the number of times the character c follows the specified
    // kgram in the text
    public int freq(String kgram, char c) {
        if (kgram.length() != order) {
            throw new IllegalArgumentException("kGram must be of length k.");
        }
        int[] array = table2.get(kgram);
        return array[c];
    }

    // returns a random character that follows the specified kgram in the text,
    // chosen with weight proportional to the number of times that character
    // follows the specified kgram in the text
    public char random(String kgram) {
        if (kgram.length() != order) {
            throw new IllegalArgumentException("kGram must be of length k.");
        }
        if (!table1.contains(kgram)) {
            throw new IllegalArgumentException("Text does not contain kGram.");
        }
        int[] array = table2.get(kgram);
        int position = StdRandom.discrete(array);
        char character = Character.toString((char) position).charAt(0);
        return character;
    }

    // tests this class by directly calling all instance methods
    public static void main(String[] args) {
        String text1 = "banana";
        MarkovModel model1 = new MarkovModel(text1, 2);
        StdOut.println("freq(\"an\", 'a')    = " + model1.freq("an", 'a'));
        StdOut.println("freq(\"na\", 'b')    = " + model1.freq("na", 'b'));
        StdOut.println("freq(\"na\", 'a')    = " + model1.freq("na", 'a'));
        StdOut.println("freq(\"na\")         = " + model1.freq("na"));
        StdOut.println();
        StdOut.println(model1.order());
        StdOut.println(model1.toString());
        StdOut.println(model1.random("an"));
        StdOut.println();


        String text3 = "one fish two fish red fish blue fish";
        MarkovModel model3 = new MarkovModel(text3, 4);
        StdOut.print(model3.order());
        StdOut.println("freq(\"ish \", 'r') = " + model3.freq("ish ", 'r'));
        StdOut.println("freq(\"ish \", 'x') = " + model3.freq("ish ", 'x'));
        StdOut.println("freq(\"ish \")      = " + model3.freq("ish "));
        StdOut.println("freq(\"tuna\")      = " + model3.freq("tuna"));
        StdOut.println(model3.order());
        StdOut.println(model3.toString());
        StdOut.println(model3.random("ish "));

    }
}
