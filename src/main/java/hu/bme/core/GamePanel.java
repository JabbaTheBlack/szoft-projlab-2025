package hu.bme.core;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import hu.bme.fungi.Mycologist;
import hu.bme.fungi.spore.Spore;
import hu.bme.insect.Entomologist;
import hu.bme.insect.Insect;
import hu.bme.managers.InsectManager;
import hu.bme.managers.MycologistManager;
import hu.bme.managers.TektonManager;
import hu.bme.tekton.Tekton;
import hu.bme.view.CentralMouseHandler;

import hu.bme.view.InsectView;
import hu.bme.view.MyceliumView;
import hu.bme.view.SporeView;
import hu.bme.view.TektonView;

public class GamePanel extends JPanel {
    private DefaultListModel<String> commandListModel; // A parancsok listájának modellje
    private TektonManager tektonManager;
    private TektonView tektonView;
    private InsectView insectView;
    private MyceliumView myceliumView;
    private SporeView sporeView;
    private CentralMouseHandler CentralMouseHandler;
    private ArrayList<Object> players; // A játékosok listája (Entomologist és Mycologist)
    private Object activePlayer; // Az aktuális játékos
    private int currentPlayerIndex = 0; // Az aktuális játékos indexe
    private Ticker ticker;
    private int round = 0;

    public GamePanel() {
        players = new ArrayList<>();
        players.addAll(InsectManager.getInstance().geEntomologists()); // Rovarászok hozzáadása
        players.addAll(MycologistManager.getInstance().getMycologists()); // Gombászok hozzáadása

        ticker = Ticker.getInstance();
        ticker.addObserver(MycologistManager.getInstance());
        ticker.addObserver(InsectManager.getInstance());

        if (!players.isEmpty()) {
            activePlayer = players.get(0); // Az első játékos az aktív
        }
        JPanel overlayPanel = new JPanel();
        tektonView = new TektonView();
        sporeView = new SporeView();
        sporeView.setOpaque(false); // Átlátszó háttér
        sporeView.setBounds(0, 0, 4 * 1980 / 5, 1080); // Méret és pozíció beállítása
        sporeView.setVisible(true); // Spóra nézet látható
        sporeView.setFocusable(true); // Fókuszálható
        tektonManager = TektonManager.getInstance();
        commandListModel = new DefaultListModel<>();
        overlayPanel.setOpaque(false);
        overlayPanel.setBounds(0, 0, 4 * 1980 / 5, 1080);
        myceliumView = new MyceliumView();
        CentralMouseHandler = new CentralMouseHandler(commandListModel, tektonView, myceliumView, sporeView,
                activePlayer);
        tektonView.setMouseHandler(CentralMouseHandler);
        insectView = new InsectView(commandListModel);
        overlayPanel.addMouseListener(CentralMouseHandler);
        overlayPanel.addMouseMotionListener(CentralMouseHandler);
        tektonView.setOpaque(false); // Átlátszó háttér);
        insectView.setOpaque(false); // Átlátszó háttér
        myceliumView.setOpaque(false); // Átlátszó háttér
        tektonView.setEnabled(true);
        tektonView.setFocusable(true); // Fókuszálható
        setLayout(new BorderLayout()); // BorderLayout a fő elrendezéshez

        // Játékterület (középső rész)
        JPanel gameArea = new JPanel();
        gameArea.setBackground(new Color(173, 216, 230));
        gameArea.setPreferredSize(new Dimension(4 * 1980 / 5, 1080)); // Az ablak 4/5-ét foglalja el
        gameArea.setLayout(null); // Manuális elrendezés
        tektonView.setBounds(0, 0, 4 * 1980 / 5, 1080); // Méret és pozíció beállítása
        insectView.setBounds(0, 0, 4 * 1980 / 5, 1080);
        myceliumView.setBounds(0, 0, 4 * 1980 / 5, 1080); // Méret és pozíció beállítása
        tektonView.setOpaque(false); // Átlátszó háttér

        gameArea.add(myceliumView);

        gameArea.add(sporeView); // Spóra nézet hozzáadása a játékterülethez
        gameArea.add(insectView);
        gameArea.add(tektonView);

        gameArea.add(overlayPanel);

        add(gameArea, BorderLayout.CENTER);

        // Jobb oldali panel (parancsok)
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayout(3, 1, 10, 10)); // 3 sor, 1 oszlop, 10px térköz
        rightPanel.setPreferredSize(new Dimension(1980 / 5, 1080)); // Az ablak 1/5-ét foglalja el

        // Parancs panelek
        JPanel commandPanel1 = new JPanel();
        commandPanel1.setBackground(Color.LIGHT_GRAY);
        JLabel label1 = new JLabel("Játékosok: ");
        commandPanel1.add(label1);
        for (Entomologist entomologist : InsectManager.getInstance().geEntomologists()) {
            JLabel label = new JLabel(entomologist.getName());
            commandPanel1.add(label);
        }

        for (Mycologist mycologist : MycologistManager.getInstance().getMycologists()) {
            JLabel label = new JLabel(mycologist.getName());
            commandPanel1.add(label);
        }
        JLabel activePlayerLabel = new JLabel("Aktuális játékos: " + activePlayer);
        commandPanel1.add(activePlayerLabel);
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
            String result = CentralMouseHandler.executeCommand();
            tektonView.repaint(); // Panel újrarajzolása
            insectView.repaint(); // Panel újrarajzolása
            myceliumView.repaint(); // Panel újrarajzolása
            if (result != null) {
                JOptionPane.showMessageDialog(this, result, "Hiba", JOptionPane.ERROR_MESSAGE);
                commandListModel.clear(); // Parancsok törlése
                return;
            }

            nextPlayer(activePlayerLabel); // Következő játékosra váltás
            commandListModel.clear();
        });

        // Parancs panelek hozzáadása a jobb oldali panelhez
        rightPanel.add(commandPanel1);
        rightPanel.add(commandPanel2);
        rightPanel.add(commandPanel3);

        // Jobb oldali panel hozzáadása a fő panelhez
        add(rightPanel, BorderLayout.EAST);
        nextPlayer(activePlayerLabel);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Alapértelmezett rajzolás

    }

    private void nextPlayer(JLabel activePlayerLabel) {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size(); // Körkörös váltás
        activePlayer = players.get(currentPlayerIndex);

        // Frissítsd a felületet az aktuális játékos nevével ez a logika még nem
        // működik, a nextplayert nem tudtam még befejezniw

        if (InsectManager.getInstance().geEntomologists().contains(activePlayer)) {
            System.out.println("Aktuális játékos (Rovarász): " + ((Entomologist) activePlayer).getName());
            activePlayerLabel.setText("Aktuális játékos (Rovarász): " + ((Entomologist) activePlayer).getName());
        } else if (MycologistManager.getInstance().getMycologists().contains(activePlayer)) {
            System.out.println("Aktuális játékos (Gombász): " + ((Mycologist) activePlayer).getName());
            activePlayerLabel.setText("Aktuális játékos (Gombász): " + ((Mycologist) activePlayer).getName());
        }
        CentralMouseHandler.setActivePlayer(activePlayer); // Frissítjük a kiválasztott játékost

        if (currentPlayerIndex % players.size() == 0) {
            ticker.tick();
            round++;
            if (round % 5 == 0) {

                breakEmptyTektons();
            }
        }

    }

    private void breakEmptyTektons() {
        for (Tekton tekton : TektonManager.getInstance().getTektons()) {
            // 1. Ha van rajta gombatest, nem törjük ketté
            if (!tekton.hasMycelium()) {

                // 2. Megnézzük, van-e rajta rovar
                boolean hasInsect = false;
                for (Entomologist entomologist : InsectManager.getInstance().geEntomologists()) {
                    for (Insect insect : entomologist.getInsects()) {
                        if (insect.getCurrentTekton() == tekton) {
                            hasInsect = true;
                            break;
                        }
                    }
                    if (hasInsect)
                        break;
                }

                // 3. Ha nincs rajta rovar sem, akkor kettétörjük
                if (!hasInsect) {
                    tektonManager.breakApart(tekton);
                    return;
                }

                checkGameEnd();
            }
        }
    }

    private void checkGameEnd() {
        if (round >= 20) {
            // Legjobb rovarász
            Entomologist bestEntomologist = null;
            int maxNutrition = Integer.MIN_VALUE;
            for (Entomologist entomologist : InsectManager.getInstance().geEntomologists()) {
                if (entomologist.getNutrition() > maxNutrition) {
                    maxNutrition = entomologist.getNutrition();
                    bestEntomologist = entomologist;
                }
            }

            // Legjobb gombász
            Mycologist bestMycologist = null;
            int maxScore = Integer.MIN_VALUE;
            for (Mycologist mycologist : MycologistManager.getInstance().getMycologists()) {
                if (mycologist.getScore() > maxScore) {
                    maxScore = mycologist.getScore();
                    bestMycologist = mycologist;
                }
            }

            String message = "Játék vége!\n\n";
            if (bestEntomologist != null) {
                message += "Legjobb rovarász: " + bestEntomologist.getName() + " (" + bestEntomologist.getNutrition()
                        + " pont)\n";
            }
            if (bestMycologist != null) {
                message += "Legjobb gombász: " + bestMycologist.getName() + " (" + bestMycologist.getScore()
                        + " pont)\n";
            }

            JOptionPane.showMessageDialog(this, message, "Végeredmény", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
    }

}
