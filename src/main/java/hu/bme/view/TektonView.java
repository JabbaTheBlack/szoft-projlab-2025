package hu.bme.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Graphics;

import javax.swing.JPanel;

import hu.bme.managers.TektonManager;
import hu.bme.tekton.Tekton;

public class TektonView extends JPanel {
    private CentralMouseHandler CentralMouseHandler;
    private Tekton hoveredTekton = null;

    public void setMouseHandler(CentralMouseHandler mouseHandler) {
        this.CentralMouseHandler = mouseHandler;
    }

    public void setHoveredTekton(Tekton tekton) {
        this.hoveredTekton = tekton;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); // A panel alapértelmezett rajzolása
        Graphics2D g2d = (Graphics2D) g; // Graphics átalakítása Graphics2D-re

        TektonManager manager = TektonManager.getInstance();
        for (Tekton tekton : manager.getTektons()) {
            int x = tekton.getX();
            int y = tekton.getY();
            int radius = 50;

            // Ellenőrizzük, hogy ez a kiválasztott Tekton-e
            if (tekton.equals(CentralMouseHandler.getSelectedTekton())) {
                g2d.setColor(Color.BLUE); // Kiválasztott Tekton színe
            } else {
                g2d.setColor(Color.BLACK); // Alapértelmezett szín
            }

            g2d.setStroke(new BasicStroke(3));
            g2d.drawOval(x - radius / 2, y - radius / 2, radius, radius); // Kör rajzolása
        }
        for (Tekton tekton : manager.getTektons()) {
            for (Tekton connectedTekton : tekton.getConnectedNeighbours()) { // Feltételezve, hogy van egy
                                                                             // `getConnectedTektons` metódus
                g2d.setColor(Color.GRAY); // Kapcsolatok vonalának színe
                g2d.setStroke(new BasicStroke(3)); // Vékony vonal
                g2d.drawLine(tekton.getX(), tekton.getY(), connectedTekton.getX(), connectedTekton.getY());
            }
        }

        if (hoveredTekton != null) {
            for (Tekton neighbour : hoveredTekton.getNeighbours()) {
                g2d.setColor(Color.RED); // A vonal színe
                g2d.drawLine(hoveredTekton.getX(), hoveredTekton.getY(), neighbour.getX(), neighbour.getY()); // Vonal
                                                                                                              // rajzolása
            }
        }
    }
}