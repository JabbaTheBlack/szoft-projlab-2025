package hu.bme.view;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

import hu.bme.fungi.spore.Spore;
import hu.bme.tekton.Tekton;
import hu.bme.managers.FungalManager;
import hu.bme.managers.MycologistManager;
import hu.bme.managers.SporeManager;
import hu.bme.managers.TektonManager;

public class SporeView extends JPanel {

    public SporeView() {

    }

    BufferedImage sporeImage = null; // Spóra kép inicializálása

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
                // e - e - e - e - e - e - e - e - e - e - e - e - e - e - e - e - e - e - e - e
                // - e - e
            }
        }
    }
}