package hu.bme.view;

import hu.bme.core.GamePanel;
import hu.bme.fungi.Mycologist;
import hu.bme.insect.Entomologist;
import hu.bme.managers.InsectManager;
import hu.bme.managers.MycologistManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainMenu extends JPanel {

    private JFrame parentFrame; // A szülő JFrame tárolása
    private Map<String, JTextField> gombaszokTextFields = new HashMap<>();
    private Map<String, JTextField> rovaraszokTextFields = new HashMap<>();

    public MainMenu(JFrame parentFrame) {
        this.parentFrame = parentFrame; // A szülő JFrame mentése
        setLayout(new BorderLayout()); // BorderLayout a komponensek elrendezéséhez

        // Bal oldali panel: Gombászok
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        JLabel gombaszokLabel = new JLabel("Gombászok:");
        leftPanel.add(gombaszokLabel);

        String[] gombaszok = { "Stun", "Slowing", "Clone", "Defensive", "Speedboost" };
        for (String gombasz : gombaszok) {
            JPanel row = new JPanel();
            row.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5)); // Térköz a komponensek között
            JLabel label = new JLabel(gombasz);
            label.setPreferredSize(new Dimension(100, 30)); // Fix méret a címkének
            JTextField textField = new JTextField(10); // 10 oszlop szélességű szövegmező
            textField.setPreferredSize(new Dimension(150, 30)); // Fix méret a szövegmezőnek
            gombaszokTextFields.put(gombasz, textField);

            row.add(label);
            row.add(textField);
            leftPanel.add(row);
        }

        // Jobb oldali panel: Rovarászok
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        JLabel rovaraszokLabel = new JLabel("Rovarászok:");
        rightPanel.add(rovaraszokLabel);

        String[] rovaraszok = { "Kék", "Piros", "Lila", "Zöld", "Sárga" };
        for (String rovarasz : rovaraszok) {
            JPanel row = new JPanel();
            row.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5)); // Térköz a komponensek között
            JLabel label = new JLabel(rovarasz);
            label.setPreferredSize(new Dimension(100, 30)); // Fix méret a címkének
            JTextField textField = new JTextField(10); // 10 oszlop szélességű szövegmező
            textField.setPreferredSize(new Dimension(150, 30)); // Fix méret a szövegmezőnek
            rovaraszokTextFields.put(rovarasz, textField);

            row.add(label);
            row.add(textField);
            rightPanel.add(row);
        }

        // Alsó panel: Start gomb
        JPanel bottomPanel = new JPanel();
        JButton startButton = new JButton("Start");
        startButton.setFont(new Font("Arial", Font.BOLD, 24)); // Nagyobb betűméret
        bottomPanel.add(startButton);

        // Start gomb eseménykezelő
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Debug: Kiírjuk a beírt neveket
                System.out.println("Gombászok nevei:");
                for (Map.Entry<String, JTextField> entry : gombaszokTextFields.entrySet()) {
                    System.out.println(entry.getKey() + ": " + entry.getValue().getText());
                }

                System.out.println("Rovarászok nevei:");
                for (Map.Entry<String, JTextField> entry : rovaraszokTextFields.entrySet()) {
                    System.out.println(entry.getKey() + ": " + entry.getValue().getText());
                }

                // Gombászok és rovarászok létrehozása

                for (Map.Entry<String, JTextField> entry : gombaszokTextFields.entrySet()) {
                    String name = entry.getValue().getText().trim();
                    if (!name.isEmpty()) {
                        Mycologist mycologist = new Mycologist(name); // Feltételezve, hogy van ilyen konstruktor
                        MycologistManager.getInstance().addMycologist(mycologist); // Gombász hozzáadása a
                                                                                   // MycologistManagerhez
                        System.out.println("Létrehozott gombász: " + name);
                    }
                }

                for (Map.Entry<String, JTextField> entry : rovaraszokTextFields.entrySet()) {
                    String name = entry.getValue().getText().trim();
                    if (!name.isEmpty()) {
                        Entomologist entomologist = new Entomologist(name); // Feltételezve, hogy van ilyen konstruktor
                        InsectManager.getInstance().addEntomologist(entomologist); // Rovarász hozzáadása az
                                                                                   // InsectManagerhez

                        System.out.println("Létrehozott rovarász: " + name);
                    }
                }

                // Bezárjuk az aktuális ablakot
                parentFrame.dispose();

                // Új ablak megnyitása a GamePanel megjelenítéséhez
                JFrame gameFrame = new JFrame("Fungorium - Game");
                gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                gameFrame.setSize(800, 600);

                // GamePanel példány létrehozása és a listák átadása
                GamePanel gamePanel = new GamePanel();
                gameFrame.add(gamePanel); // GamePanel hozzáadása
                gameFrame.setVisible(true);
            }
        });

        // Panelek hozzáadása a fő panelhez
        add(leftPanel, BorderLayout.WEST); // Bal oldal
        add(rightPanel, BorderLayout.EAST); // Jobb oldal
        add(bottomPanel, BorderLayout.SOUTH); // Alsó rész
    }
}