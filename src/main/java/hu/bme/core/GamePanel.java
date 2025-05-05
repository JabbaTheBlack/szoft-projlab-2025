package hu.bme.core;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

import hu.bme.managers.TektonManager;
import hu.bme.view.InsectView;
import hu.bme.view.MyceliumView;
import hu.bme.view.TektonView;

public class GamePanel extends JPanel {
    private DefaultListModel<String> commandListModel; // A parancsok listájának modellje
    private TektonManager tektonManager;
    private TektonView tektonView;
    private InsectView insectView;
    private MyceliumView myceliumView;

    public GamePanel() {
        tektonManager = TektonManager.getInstance();
        tektonView = new TektonView();
        insectView = new InsectView();
        myceliumView = new MyceliumView();

        insectView.setOpaque(false); // Átlátszó háttér
        myceliumView.setOpaque(false); // Átlátszó háttér
        setLayout(new BorderLayout()); // BorderLayout a fő elrendezéshez

        // Játékterület (középső rész)
        JPanel gameArea = new JPanel();
        gameArea.setBackground(Color.WHITE);
        gameArea.setPreferredSize(new Dimension(4 * 1980 / 5, 1080)); // Az ablak 4/5-ét foglalja el
        gameArea.setLayout(null); // Manuális elrendezés
        tektonView.setBounds(0, 0, 4 * 1980 / 5, 1080); // Méret és pozíció beállítása
        insectView.setBounds(0, 0, 4 * 1980 / 5, 1080);
        myceliumView.setBounds(0, 0, 4 * 1980 / 5, 1080); // Méret és pozíció beállítása
        tektonView.setOpaque(false); // Átlátszó háttér
        gameArea.add(myceliumView); // MyceliumView hozzáadása a játékterülethez
        gameArea.add(insectView);
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
        // Parancsok listája
        commandListModel = new DefaultListModel<>();
        JList<String> commandList = new JList<>(commandListModel);
        commandList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Egyetlen elem választható
        commandList.setVisibleRowCount(10); // Látható sorok száma
        JScrollPane scrollPane = new JScrollPane(commandList); // Görgethető lista
        commandPanel2.add(scrollPane, BorderLayout.CENTER);

        // Eseménykezelő a listaelemekhez
        commandList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) { // Csak akkor fut le, ha a kiválasztás befejeződött
                String selectedCommand = commandList.getSelectedValue();
                System.out.println("Kiválasztott parancs: " + selectedCommand);
            }
        });

        JPanel commandPanel3 = new JPanel(new BorderLayout()); // BorderLayout a gomb aljára helyezéséhez
        JLabel label4 = new JLabel("aktuális parancs:");
        commandPanel3.setBackground(Color.DARK_GRAY);
        JLabel label3 = new JLabel("itt lesz majd az aktuális parancs és a gomb");
        commandPanel3.add(label3, BorderLayout.NORTH); // Szöveg a panel tetején
        commandPanel3.add(label4, BorderLayout.CENTER); // Szöveg középen

        JButton button = new JButton("Parancs kiadása");
        commandPanel3.add(button, BorderLayout.SOUTH); // Gomb a panel aljára

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

    }
}