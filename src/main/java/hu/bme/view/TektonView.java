package hu.bme.view;

import java.awt.geom.Point2D;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.HashSet;
import java.util.Set;
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
        super.paintComponent(g); // Default panel drawing
        Graphics2D g2d = (Graphics2D) g; // Convert Graphics to Graphics2D

        TektonManager manager = TektonManager.getInstance();
        Set<Point2D> occupiedPositions = new HashSet<>(); // To track Tekton positions
        int radius = 50;
        int minDistance = 2 * radius;

        int panelWidth = getWidth();  // Get panel width
        int panelHeight = getHeight(); // Get panel height

        for (Tekton tekton : manager.getTektons()) {
            int x = tekton.getX();
            int y = tekton.getY();

            // Ensure Tekton is not closer to the sides than r
            x = Math.max(radius, Math.min(x, panelWidth - radius));
            y = Math.max(radius, Math.min(y, panelHeight - radius));

            // Adjust position if too close to another Tekton
            while (isTooClose(x, y, occupiedPositions, minDistance)) {
                x += minDistance;
                y += minDistance;

                // Ensure Tekton stays within bounds
                if (x > panelWidth - radius) x = radius;
                if (y > panelHeight - radius) y = radius;
            }

            // Update Tekton's position
            tekton.setX(x);
            tekton.setY(y);
            occupiedPositions.add(new Point2D.Double(x, y));

            // Check if this is the selected Tekton
            if (tekton.equals(CentralMouseHandler.getSelectedTekton())) {
                g2d.setColor(Color.BLUE); // Selected Tekton color
            } else {
                g2d.setColor(Color.BLACK); // Default color
            }

            g2d.setStroke(new BasicStroke(3));
            g2d.drawOval(x - radius / 2, y - radius / 2, radius, radius); // Draw circle
        }

        for (Tekton tekton : manager.getTektons()) {
            for (Tekton connectedTekton : tekton.getConnectedNeighbours()) {
                g2d.setColor(Color.GRAY); // Connection line color
                g2d.setStroke(new BasicStroke(3));
                g2d.drawLine(tekton.getX(), tekton.getY(), connectedTekton.getX(), connectedTekton.getY());
            }
        }

        if (hoveredTekton != null) {
            for (Tekton neighbour : hoveredTekton.getNeighbours()) {
                g2d.setColor(Color.LIGHT_GRAY); // Set line color to gray

                // Set a jagged (dashed) stroke
                float[] dashPattern = {10, 5}; // Dash length and gap length
                g2d.setStroke(new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10, dashPattern, 0));

                g2d.drawLine(hoveredTekton.getX(), hoveredTekton.getY(), neighbour.getX(), neighbour.getY());
            }
        }
    }

    private boolean isTooClose(int x, int y, Set<Point2D> occupiedPositions, int minDistance) {
        for (Point2D point : occupiedPositions) {
            if (point.distance(x, y) < minDistance) {
                return true;
            }
        }
        return false;
    }
}