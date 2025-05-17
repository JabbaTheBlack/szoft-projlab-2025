package hu.bme.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

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
    private Mycelium hoverMycelium = null;

    public void setHoverMycelium(Mycelium mycelium) {
        this.hoverMycelium = mycelium;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Gombatest kirajzolása
        int i = 0;
        ArrayList<Mycologist> Mycologists = MycologistManager.getInstance().getMycologists();
        
        for (Mycologist mycologist : Mycologists) {
            
            ArrayList<Mycelium> myceliums = mycologist.getMyceliums();
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
            if (hoverMycelium != null) {

                int x = hoverMycelium.getCurrentTekton().getX();
                int y = hoverMycelium.getCurrentTekton().getY();
                String level = hoverMycelium.isUpgraded() ? "2" : "1";
                String szoveg = "Spórák: " + hoverMycelium.getSporeCount() + " Level: " + level;
                g.setColor(Color.BLACK);
                g.drawString(szoveg, x - 20, y - 30); // 10 pixellel a gomba fölé írja
            }
            for (Hyphae hyphae : mycologist.getHyphaes()) {
                // 2 tekton közötti fonal kirajzolása
                if (hyphae.getCurrentTekton().size() > 1 && hyphae.getTimeToLive() != 0) {
                    g2d.setColor(mycologist.getColor());
                    int x1 = hyphae.getCurrentTekton().get(0).getX();
                    int y1 = hyphae.getCurrentTekton().get(0).getY();
                    int x2 = hyphae.getCurrentTekton().get(1).getX();
                    int y2 = hyphae.getCurrentTekton().get(1).getY();
                    // g2d.drawLine(ux, uy, vx, vy);
                    // hyphae.setPosition(ux, uy, vx, vy);
                    double dx = x2 - x1;
                    double dy = y2 - y1;
                    double dist = Math.sqrt(dx * dx + dy * dy);
                    int radius = 25; // Tekton sugara (állítsd be a tényleges értékre)

                    if (dist > 0.1) {
                        double ux = dx / dist;
                        double uy = dy / dist;
                        int startX = (int) Math.round(x1 + ux * radius);
                        int startY = (int) Math.round(y1 + uy * radius);
                        int endX = (int) Math.round(x2 - ux * radius);
                        int endY = (int) Math.round(y2 - uy * radius);
                        g2d.setStroke(new java.awt.BasicStroke(3));
                        g2d.drawLine(startX, startY, endX, endY);
                        hyphae.setPosition(startX, startY, endX, endY);
                    }

                } else if (hyphae.getTimeToLive() != 0) {
                    int tx = hyphae.getCurrentTekton().get(0).getX();
                    int ty = hyphae.getCurrentTekton().get(0).getY();
                    double r = 20; // sugar
                    double angle = Math.toRadians(72 * i);
                    int vx = tx + (int) (r * Math.cos(angle));
                    int vy = ty + (int) (r * Math.sin(angle));
                    angle = Math.toRadians(72 * (i + 1));
                    int ux = tx + (int) (r * Math.cos(angle));
                    int uy = ty + (int) (r * Math.sin(angle));

                    g2d.setColor(mycologist.getColor());
                    g2d.setStroke(new java.awt.BasicStroke(3)); // 3 is the line width in pixels
                    g2d.drawLine(vx, vy, ux, uy);
                    hyphae.setPosition(ux, uy, vx, vy);

                }

            }
            i++;
            if( i == MycologistManager.getInstance().getMycologists().size()) {
                i=0;
            }
        }
    }
}
