import java.awt.Color;

public class PhotoMagic {
    // returns a transformed copy of the specified picture, using the specified lfsr.
    public static Picture transform(Picture picture, LFSR lfsr) {

        // cycle through pixels
        for (int i = 0; i < picture.width(); i++) {
            for (int j = 0; j < picture.height(); j++) {
                // get original color of each pixel
                Color rgb = picture.get(i, j);
                int red = rgb.getRed();
                int green = rgb.getGreen();
                int blue = rgb.getBlue();
                // generate new color for each pixel
                int red1 = lfsr.generate(8) ^ red;
                int green1 = lfsr.generate(8) ^ green;
                int blue1 = lfsr.generate(8) ^ blue;
                Color color1 = new Color(red1, green1, blue1);
                // set pixel to new color
                picture.set(i, j, color1);
            }
        }
        return picture;
    }

    // takes the name of an image file and a description of an lfsr as command-line arguments;
    // displays a copy of the image that is "encrypted" using the LFSR.
    public static void main(String[] args) {

        Picture picture = new Picture(args[0]);
        LFSR lfsr = new LFSR(args[1], Integer.parseInt(args[2]));
        transform(picture, lfsr).show();

    }
}


