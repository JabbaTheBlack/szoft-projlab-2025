package hu.bme.core;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import hu.bme.managers.TektonManager;
import hu.bme.view.TektonView;

public class GamePanel extends JPanel{
    private TektonManager tektonManager;
    private TektonView tektonView;

    public GamePanel() {
        tektonManager = TektonManager.getInstance();
        tektonView = new TektonView();
        setBackground(Color.BLUE);
        setPreferredSize(new Dimension(1980, 1080));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        // Render Tektons using TektonView
        tektonView.render(tektonManager, g2d);
    }
}
