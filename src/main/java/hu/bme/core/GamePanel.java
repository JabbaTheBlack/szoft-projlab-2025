package hu.bme.core;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import hu.bme.fungi.Mycologist;
import hu.bme.insect.Entomologist;
import hu.bme.managers.InsectManager;
import hu.bme.managers.MycologistManager;
import hu.bme.managers.TektonManager;
import hu.bme.view.CentralMouseHandler;

import hu.bme.view.InsectView;
import hu.bme.view.MyceliumView;
import hu.bme.view.TektonView;

public class GamePanel extends JPanel {
    private DefaultListModel<String> commandListModel; // A parancsok listájának modellje
    private TektonManager tektonManager;
    private TektonView tektonView;
    private InsectView insectView;
    private MyceliumView myceliumView;
    private CentralMouseHandler CentralMouseHandler;

    public GamePanel() {

        JPanel overlayPanel = new JPanel();
        tektonView = new TektonView();
        tektonManager = TektonManager.getInstance();
        commandListModel = new DefaultListModel<>();
        overlayPanel.setOpaque(false);
        overlayPanel.setBounds(0, 0, 4 * 1980 / 5, 1080);
        CentralMouseHandler = new CentralMouseHandler(commandListModel, tektonView);
        tektonView.setMouseHandler(CentralMouseHandler);
        insectView = new InsectView(commandListModel);
        overlayPanel.addMouseListener(CentralMouseHandler);
        overlayPanel.addMouseMotionListener(CentralMouseHandler);
        myceliumView = new MyceliumView();
        tektonView.setOpaque(false); // Átlátszó háttér);
        insectView.setOpaque(false); // Átlátszó háttér
        myceliumView.setOpaque(false); // Átlátszó háttér
        tektonView.setEnabled(true);
        tektonView.setFocusable(true); // Fókuszálható
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
        gameArea.add(myceliumView);
        gameArea.add(tektonView);
        gameArea.add(insectView);
        gameArea.add(overlayPanel);
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
        for (Entomologist entomologist : InsectManager.getInstance().geEntomologists()) {
            JLabel label = new JLabel(entomologist.getName());
            commandPanel1.add(label);
        }

        for (Mycologist mycologist : MycologistManager.getInstance().getMycologists()) {
            JLabel label = new JLabel(mycologist.getName());
            commandPanel1.add(label);
        }

        JPanel commandPanel2 = new JPanel();
        commandPanel2.setBackground(Color.GRAY);
        JLabel label2 = new JLabel("parancsok");
        commandPanel2.add(label2);
        // Parancsok listája

        JList<String> commandList = new JList<>(commandListModel);
        commandList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Egyetlen elem választható
        commandList.setVisibleRowCount(10); // Látható sorok száma
        JScrollPane scrollPane = new JScrollPane(commandList); // Görgethető lista
        commandPanel2.add(scrollPane, BorderLayout.CENTER);

        // Eseménykezelő a listaelemekhez
        JLabel label4 = new JLabel("aktuális parancs: " + CentralMouseHandler.getSelectedCommand());
        commandList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) { // Csak akkor fut le, ha a kiválasztás befejeződött
                String selectedCommand = commandList.getSelectedValue();
                System.out.println("Kiválasztott parancs: " + selectedCommand);
                CentralMouseHandler.setSelectedCommand(selectedCommand);
                label4.setText("aktuális parancs: " + selectedCommand);

            }
        });

        JPanel commandPanel3 = new JPanel(new BorderLayout()); // BorderLayout a gomb aljára helyezéséhez

        commandPanel3.setBackground(Color.DARK_GRAY);
        JLabel label3 = new JLabel("itt lesz majd az aktuális parancs és a gomb");
        commandPanel3.add(label3, BorderLayout.NORTH); // Szöveg a panel tetején
        commandPanel3.add(label4, BorderLayout.CENTER); // Szöveg középen

        JButton executeButton = new JButton("Parancs kiadása");
        commandPanel3.add(executeButton, BorderLayout.SOUTH); // Gomb a panel aljára
        executeButton.addActionListener(e -> {
            CentralMouseHandler.executeCommand();
            tektonView.repaint(); // Panel újrarajzolása
            insectView.repaint(); // Panel újrarajzolása
        });

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
