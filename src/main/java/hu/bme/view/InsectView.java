package hu.bme.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import hu.bme.insect.Entomologist;
import hu.bme.insect.Insect;
import hu.bme.managers.InsectManager;

public class InsectView extends JPanel {
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
                if (insect != null) {
                    System.out.println(
                            "Rovar: " + insect.getCurrentTekton().getX() + ", " + insect.getCurrentTekton().getY());
                }
                int x = insect.getCurrentTekton().getX();
                int y = insect.getCurrentTekton().getY();
                int size = 21; // Rovar mérete
                try {
                    // PNG kép betöltése
                    insectImage = ImageIO.read(getClass().getResource("/images/fekete.png"));
                } catch (IOException e) {
                    System.out.println("Nem sikerült betölteni a rovar képet.");
                    e.printStackTrace();
                }
                if (insectImage != null) {

                    g2d.drawImage(insectImage, x, y, insectImage.getWidth(), insectImage.getHeight(), null);
                    System.out.println("Rovar kép betöltve: " + insectImage.getWidth() + "x" + insectImage.getHeight());
                }
            }
        }
    }
}