public class GuitarHero {
    public static void main(String[] args) {
        String keyboardString = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        GuitarString[] strings = new GuitarString[keyboardString.length()];
        // creates an array of Guitar Strings with corresponding frequencies
        for (int i = 0; i < keyboardString.length(); i++) {
            double frequency = 440 * Math.pow(2, (i - 24) / 12.0);
            strings[i] = new GuitarString(frequency);
        }

        // create main input loop
        Keyboard keyboard = new Keyboard();
        while (true) {

            // check if the user has played a key; if so, process it
            if (keyboard.hasNextKeyPlayed()) {

                // the key the user played
                char key = keyboard.nextKeyPlayed();

                // checks for valid input and plucks the corresponding string
                if (keyboardString.contains(String.valueOf(key))) {
                    strings[keyboardString.indexOf(key)].pluck();
                }
            }

            // compute the superposition of the samples
            double sample = 0;
            for (int i = 0; i < keyboardString.length(); i++) {
                sample = sample + strings[i].sample();
            }

            // play the sample on standard audio
            StdAudio.play(sample);

            // advance the simulation of each guitar string by one step
            for (int i = 0; i < keyboardString.length(); i++) {
                strings[i].tic();
            }
        }
    }
}
