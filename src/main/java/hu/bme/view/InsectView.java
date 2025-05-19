package hu.bme.view;

import javax.swing.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

import hu.bme.insect.Entomologist;
import hu.bme.insect.Insect;
import hu.bme.managers.InsectManager;

public class InsectView extends JPanel {
    private DefaultListModel<String> commandListModel; // A parancsok listája

    public InsectView(DefaultListModel<String> commandListModel) {
        this.commandListModel = commandListModel;

    }

    BufferedImage insectImage = null; // Rovar kép inicializálása

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Rovarok kirajzolása
        List<Entomologist> entomologists = InsectManager.getInstance().geEntomologists();
        for (Entomologist entomologist : entomologists) {
            List<Insect> insects = entomologist.getInsects();
            for (Insect insect : insects) {

                int x = insect.getCurrentTekton().getX();
                int y = insect.getCurrentTekton().getY();
                int size = 21; // Rovar mérete

                // PNG kép betöltése
                insectImage = insect.textureProvider.getImage();

                if (insectImage != null) {

                    g2d.drawImage(insectImage, x - 21 / 2, y - 21 / 2, 21, 21, null);
                    // System.out.println("Rovar kép betöltve: ");
                }
            }
        }
    }
}