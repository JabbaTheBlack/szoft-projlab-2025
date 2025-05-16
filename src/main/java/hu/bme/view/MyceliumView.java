package hu.bme.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import hu.bme.fungi.Hyphae;
import hu.bme.fungi.Mycelium;
import hu.bme.fungi.Mycologist;
import hu.bme.insect.Entomologist;
import hu.bme.insect.Insect;
import hu.bme.managers.FungalManager;
import hu.bme.managers.InsectManager;
import hu.bme.managers.MycologistManager;
import hu.bme.tekton.Tekton;

public class MyceliumView extends JPanel {

    BufferedImage myceliumImage = null; // Mycelium kép inicializálása

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Gombatest kirajzolása
        List<Mycologist> Mycologists = MycologistManager.getInstance().getMycologists();
        int i = 0;
        for (Mycologist mycologist : Mycologists) {
            
            List<Mycelium> myceliums = mycologist.getMyceliums();
            for (Mycelium mycelium : myceliums) {

                int x = mycelium.getCurrentTekton().getX();
                int y = mycelium.getCurrentTekton().getY();
                int size = 21; // Gombatest mérete

                // PNG kép betöltése
                myceliumImage = mycelium.textureProvider.getImage();

                if (myceliumImage != null) {

                    g2d.drawImage(myceliumImage, x - 21 / 2, y - 21 / 2, 21, 21, null);
                    // System.out.println("Gombatest kép betöltve: ");
                }
            }
            for(Hyphae hyphae : mycologist.getHyphaes()) {
                // 2 tekton közötti fonal kirajzolása
                if(hyphae.getCurrentTekton().size() > 1){
                    g2d.setColor(mycologist.getColor());
                    g2d.drawLine(hyphae.getCurrentTekton().get(0).getX(), hyphae.getCurrentTekton().get(0).getY(),
                            hyphae.getCurrentTekton().get(1).getX(), hyphae.getCurrentTekton().get(1).getY());
                } else {
                    int tx = hyphae.getCurrentTekton().get(0).getX();
                    int ty = hyphae.getCurrentTekton().get(0).getY();
                    double r = 20; // sugar
                    double angle = Math.toRadians(60 * i);
                    int vx = tx + (int)(r * Math.cos(angle));
                    int vy = ty + (int)(r * Math.sin(angle));
                    angle = Math.toRadians(60 * (i+1));
                    int ux = tx + (int)(r * Math.cos(angle));
                    int uy = ty + (int)(r * Math.sin(angle));

                    g2d.setColor(mycologist.getColor());
                    g2d.setStroke(new java.awt.BasicStroke(3)); // 4 is the line width in pixels
                    g2d.drawLine(vx, vy, ux, uy);

                }

                // mycologiston vegigmenni es az alapjan rajzolni a fonalat a tektonra

                i++;
            }
        

        }
    }    
}

