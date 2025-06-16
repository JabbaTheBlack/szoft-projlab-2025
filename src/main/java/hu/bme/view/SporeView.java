package hu.bme.view;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

import hu.bme.fungi.spore.Spore;
import hu.bme.tekton.Tekton;
import hu.bme.managers.TektonManager;

/**
 * A custom JPanel for rendering spores in the game.
 * This class is responsible for drawing spore images at their respective positions on the game board.
 */
public class SporeView extends JPanel {

    /** Model for the list of commands displayed in the UI. */
    public SporeView() {

    }

    BufferedImage sporeImage = null; // Spóra kép inicializálása

    /**
     * Overrides the default paintComponent method to render spores on the panel.
     * Draws each spore's image at the position of its current Tekton.
     *
     * @param g the Graphics object used for painting
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Spórák kirajzolása
        List<Tekton> tektons = TektonManager.getInstance().getTektons();

        for (Tekton tekton : tektons) {
            if (tekton.getSporeCount() > 0) {
                for (Spore spore : tekton.getSpores()) {
                    int x = tekton.getX();
                    int y = tekton.getY();
                    sporeImage = spore.textureProvider.getImage();
                    int size = 15; // Spóra mérete
                    if (sporeImage != null) {
                        g2d.drawImage(sporeImage, x - size / 2, y - size / 2, size, size, null);
                    }
                }
            }
        }
    }
}