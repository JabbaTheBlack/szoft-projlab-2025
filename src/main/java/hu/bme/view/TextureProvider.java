package hu.bme.view;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * A class that provides texture images for the game.
 * It loads images from the resources and provides them to other classes.
 */
public class TextureProvider {
    private BufferedImage Image;

    /**
     * Constructs a new TextureProvider instance.
     * Loads the image from the specified path.
     *
     * @param path the path to the image file
     */
    public void setImage(String path) {
        try {
            Image = ImageIO.read(getClass().getResourceAsStream(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the loaded image.
     *
     * @return the loaded image
     */
    public BufferedImage getImage() {
        return Image;
    }
}
