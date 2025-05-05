package hu.bme.core;

import java.awt.*;
import javax.swing.*;

import hu.bme.managers.TektonManager;
import hu.bme.view.TektonView;

public class GamePanel extends JPanel {
    private TektonManager tektonManager;
    private TektonView tektonView;

    public GamePanel() {
        tektonManager = TektonManager.getInstance();
        tektonView = new TektonView();

        setLayout(new BorderLayout()); // BorderLayout a fő elrendezéshez

        // Játékterület (középső rész)
        JPanel gameArea = new JPanel();
        gameArea.setBackground(Color.BLUE);
        gameArea.setPreferredSize(new Dimension(4 * 1980 / 5, 1080)); // Az ablak 4/5-ét foglalja el
        gameArea.setLayout(null); // Manuális elrendezés
        tektonView.setBounds(0, 0, 4 * 1980 / 5, 1080); // Méret és pozíció beállítása
        gameArea.add(tektonView);
        add(gameArea, BorderLayout.CENTER);

        // Jobb oldali panel (parancsok)
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayout(3, 1, 10, 10)); // 3 sor, 1 oszlop, 10px térköz
        rightPanel.setPreferredSize(new Dimension(1980 / 5, 1080)); // Az ablak 1/5-ét foglalja el

        // Parancs panelek
        JPanel commandPanel1 = new JPanel();
        commandPanel1.setBackground(Color.LIGHT_GRAY);
        JLabel label1 = new JLabel("Játékosok és kör");
        commandPanel1.add(label1);

        JPanel commandPanel2 = new JPanel();
        commandPanel2.setBackground(Color.GRAY);
        JLabel label2 = new JLabel("parancsok");
        commandPanel2.add(label2);

        JPanel commandPanel3 = new JPanel();
        commandPanel3.setBackground(Color.DARK_GRAY);
        JLabel label3 = new JLabel("itt lesz majd az aktuális parancs és a gomb");
        commandPanel3.add(label3);

        // Parancs panelek hozzáadása a jobb oldali panelhez
        rightPanel.add(commandPanel1);
        rightPanel.add(commandPanel2);
        rightPanel.add(commandPanel3);

        // Jobb oldali panel hozzáadása a fő panelhez
        add(rightPanel, BorderLayout.EAST);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Alapértelmezett rajzolás
        Graphics2D g2d = (Graphics2D) g;

        // A TektonView rajzolási logikájának meghívása
        tektonView.paintComponent(g2d);
    }
}