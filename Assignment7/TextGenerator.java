public class TextGenerator {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);
        String text = StdIn.readAll();

        // Create a Markov Model of the text and print out first k characters
        // as initial kGram
        MarkovModel textGen = new MarkovModel(text, k);
        String kGram = text.substring(0, k);
        StdOut.print(kGram);

        // Subtract already printed number of characters from T.
        // Generate new characters to add to end of the original kGram until
        // we reach T characters.
        for (int i = 0; i < (t - k); i++) {
            char character = textGen.random(kGram);
            StdOut.print(character);
            kGram = kGram.substring(1) + character;
        }
        // Adds line after outputting entire T length text
        StdOut.println();
    }
}
