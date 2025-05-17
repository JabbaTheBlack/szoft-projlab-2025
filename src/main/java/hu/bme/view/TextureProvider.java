package hu.bme.view;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class TextureProvider {
    private BufferedImage Image;

    public void setImage(String path) {
        try {
            Image = ImageIO.read(getClass().getResourceAsStream(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage getImage() {
        return Image;
    }
}
