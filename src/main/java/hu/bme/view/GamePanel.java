package hu.bme.view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;

public class GamePanel {
    TektonView tektonView;

    public void draw() {
        tektonView = new TektonView();
        tektonView.setBounds(0, 0, 800, 600); // Set the size of the TektonView
        tektonView.setBackground(Color.WHITE); // Set the background color of the TektonView
        tektonView.setVisible(true); // Make the TektonView visible

        JFrame frame = new JFrame("Fungorium");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600); // Set the size of the JFrame
        frame.add(tektonView); // Add the TektonView to the JFrame
        frame.setVisible(true); // Make the JFrame visible
        tektonView.repaint();
    }

}
