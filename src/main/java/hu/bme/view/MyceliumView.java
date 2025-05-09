package hu.bme.view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import hu.bme.fungi.Mycelium;
import hu.bme.fungi.Mycologist;
import hu.bme.insect.Entomologist;
import hu.bme.insect.Insect;
import hu.bme.managers.FungalManager;
import hu.bme.managers.InsectManager;
import hu.bme.managers.MycologistManager;

public class MyceliumView extends JPanel {

    BufferedImage myceliumImage = null; // Mycelium kép inicializálása

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Rovarok kirajzolása
        List<Mycologist> Mycologists = MycologistManager.getInstance().getMycologists();
        for (Mycologist mycologist : Mycologists) {
            List<Mycelium> myceliums = mycologist.getMyceliums();
            for (Mycelium mycelium : myceliums) {

                int x = mycelium.getCurrentTekton().getX();
                int y = mycelium.getCurrentTekton().getY();
                int size = 21; // Rovar mérete

                // PNG kép betöltése
                myceliumImage = mycelium.textureProvider.getImage();

                if (myceliumImage != null) {

                    g2d.drawImage(myceliumImage, x - 21 / 2, y - 21 / 2, 21, 21, null);
                    // System.out.println("Rovar kép betöltve: ");
                }
            }
        }
    }
}
