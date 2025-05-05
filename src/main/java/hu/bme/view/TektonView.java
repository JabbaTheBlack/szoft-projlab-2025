package hu.bme.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Graphics;

import javax.swing.JPanel;

import hu.bme.managers.TektonManager;
import hu.bme.tekton.Tekton;

public class TektonView extends JPanel {
    private Tekton hoveredTekton = null;

    public TektonView() {
        TektonMouseHandler mouseHandler = new TektonMouseHandler(this);
        addMouseMotionListener(mouseHandler); // Egérmozgás figyelése
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
            g2d.setStroke(new BasicStroke(3));
            g2d.setColor(Color.BLACK);
            g2d.drawOval(x - radius / 2, y - radius / 2, radius, radius); // Üres karika
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