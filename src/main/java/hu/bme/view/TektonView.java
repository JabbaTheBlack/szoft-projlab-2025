package hu.bme.view;

import java.awt.geom.Point2D;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.HashSet;
import java.util.Set;
import java.awt.Graphics;

import javax.swing.JPanel;

import hu.bme.fungi.spore.Spore;
import hu.bme.managers.TektonManager;
import hu.bme.tekton.Tekton;

/**
 * A custom JPanel for rendering tektons in the game.
 * This class is responsible for drawing tektons images at their respective positions on the game board.
 */
public class TektonView extends JPanel {
    private CentralMouseHandler CentralMouseHandler;
    private Tekton hoveredTekton = null;

    /**
     * Constructs a new TektonView instance.
     */
    public void setMouseHandler(CentralMouseHandler mouseHandler) {
        this.CentralMouseHandler = mouseHandler;
    }

    /**
     * Sets the hovered tekton for displaying additional information.
     *
     * @param tekton the tekton to be hovered
     */
    public void setHoveredTekton(Tekton tekton) {
        this.hoveredTekton = tekton;
    }

    /**
     * Overrides the default paintComponent method to render tektons on the panel.
     * Draws each tekton's image at the position of its current Tekton.
     *
     * @param g the Graphics object used for painting
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); // Default panel drawing
        Graphics2D g2d = (Graphics2D) g; // Convert Graphics to Graphics2D

        TektonManager manager = TektonManager.getInstance();
        Set<Point2D> occupiedPositions = new HashSet<>(); // To track Tekton positions
        int radius = 50;
        int minDistance = 2 * radius;

        int panelWidth = getWidth(); // Get panel width
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
                if (x > panelWidth - radius)
                    x = radius;
                if (y > panelHeight - radius)
                    y = radius;
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

            Color lightBrown = new Color(205, 133, 63);
            g2d.setPaint(lightBrown);
            g2d.fillOval(x - radius / 2, y - radius / 2, radius, radius);


            g2d.setStroke(new BasicStroke(3));
            if (tekton.equals(CentralMouseHandler.getSelectedTekton())) {
                g2d.setColor(Color.BLUE); // Selected Tekton outline
            } else {
                g2d.setColor(Color.BLACK); // Default outline
            }
            g2d.drawOval(x - radius / 2, y - radius / 2, radius, radius); // Draw circle
        }

        if (hoveredTekton != null) {
            for (Tekton neighbour : hoveredTekton.getNeighbours()) {
                g2d.setColor(Color.LIGHT_GRAY); // Set line color to gray

                // Set a jagged (dashed) stroke
                float[] dashPattern = { 10, 5 }; // Dash length and gap length
                g2d.setStroke(new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10, dashPattern, 0));

                g2d.drawLine(hoveredTekton.getX(), hoveredTekton.getY(), neighbour.getX(), neighbour.getY());
                int x = hoveredTekton.getX();
                int y = hoveredTekton.getY();
                int place2 = hoveredTekton.toString().lastIndexOf("@");

                String name = hoveredTekton.toString().substring(14, place2); //14
                g2d.setColor(Color.BLACK); // Set text color to red
                g2d.drawString(name, x - 20, y + 40); // 10 pixellel a gomba fölé írja

                // Get the spores from the tektons
                java.util.List<Spore> spores = hoveredTekton.getSpores();
                int offsetY = 55;

                // Draw spore types below the Tekton type
                for(Spore spore : spores) {
                    String sporeType = spore.getClass().getSimpleName();
                    g2d.drawString(sporeType, x - 20, y + offsetY);
                }
            }
        }
    }

    /**
     * Checks if a given position is too close to any occupied positions.
     *
     * @param x the x-coordinate of the position to check
     * @param y the y-coordinate of the position to check
     * @param occupiedPositions a set of occupied positions
     * @param minDistance the minimum distance to maintain from occupied positions
     * @return true if the position is too close, false otherwise
     */
    private boolean isTooClose(int x, int y, Set<Point2D> occupiedPositions, int minDistance) {
        for (Point2D point : occupiedPositions) {
            if (point.distance(x, y) < minDistance) {
                return true;
            }
        }
        return false;
    }
}