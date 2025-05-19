package hu.bme.view;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.event.MouseEvent;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import hu.bme.insect.Entomologist;
import hu.bme.insect.Insect;
import hu.bme.managers.InsectManager;

/**
 * A custom JPanel for rendering insects in the game.
 * This class is responsible for drawing insect images at their respective positions on the game board.
 */
public class InsectView extends JPanel {
    /** Model for the list of commands displayed in the UI. */
    private DefaultListModel<String> commandListModel;
    /** Buffered image for storing the insect texture. */
    private BufferedImage insectImage = null;

    /**
     * Constructs a new InsectView instance, initializing the command list model.
     *
     * @param commandListModel the model for the list of commands in the UI
     */
    public InsectView(DefaultListModel<String> commandListModel) {
        this.commandListModel = commandListModel;
    }

    /**
     * Overrides the default paintComponent method to render insects on the panel.
     * Draws each insect's image at the position of its current Tekton.
     *
     * @param g the Graphics object used for painting
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Draw insects
        List<Entomologist> entomologists = InsectManager.getInstance().geEntomologists();
        for (Entomologist entomologist : entomologists) {
            List<Insect> insects = entomologist.getInsects();
            for (Insect insect : insects) {
                int x = insect.getCurrentTekton().getX();
                int y = insect.getCurrentTekton().getY();
                int size = 21; // Insect size

                // Load PNG image
                insectImage = insect.textureProvider.getImage();

                if (insectImage != null) {
                    g2d.drawImage(insectImage, x - size / 2, y - size / 2, size, size, null);
                }
            }
        }
    }
}