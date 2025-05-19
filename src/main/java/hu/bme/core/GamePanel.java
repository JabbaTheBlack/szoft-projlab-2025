package hu.bme.core;

import java.awt.*;
import javax.swing.*;

import java.util.ArrayList;

import hu.bme.fungi.Mycologist;

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

/**
 * A custom JPanel representing the main game interface for a strategy game involving entomologists and mycologists.
 * This panel manages the game area, player interactions, command inputs, and visual representations of game elements.
 */
public class GamePanel extends JPanel {
    /** Model for the list of commands displayed in the UI. */
    private DefaultListModel<String> commandListModel;
    /** Manager for handling Tekton objects in the game. */
    private TektonManager tektonManager;
    /** View component for rendering Tekton elements. */
    private TektonView tektonView;
    /** View component for rendering Insect elements. */
    private InsectView insectView;
    /** View component for rendering Mycelium elements. */
    private MyceliumView myceliumView;
    /** View component for rendering Spore elements. */
    private SporeView sporeView;
    /** Handler for mouse events across the game panel. */
    private CentralMouseHandler CentralMouseHandler;
    /** List of players in the game, including Entomologists and Mycologists. */
    private ArrayList<Object> players;
    /** The currently active player taking their turn. */
    private Object activePlayer;
    /** Index of the current player in the players list. */
    private int currentPlayerIndex = 0;
    /** Ticker instance for managing game time and updates. */
    private Ticker ticker;
    /** Current round number in the game. */
    private int round = 1;

    /**
     * Constructs a new GamePanel, initializing the game environment, players, UI components, and event handlers.
     * Sets up the layout with a game area and a command panel on the right side.
     */
    public GamePanel() {
        players = new ArrayList<>();
        players.addAll(InsectManager.getInstance().geEntomologists()); // Rovarászok hozzáadása
        players.addAll(MycologistManager.getInstance().getMycologists()); // Gombászok hozzáadása

        ticker = Ticker.getInstance();
        ticker.addObserver(MycologistManager.getInstance());
        ticker.addObserver(InsectManager.getInstance());
        ticker.addObserver(TektonManager.getInstance());

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
        tektonView.setOpaque(false); // Átlátszó háttér
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

    /**
     * Overrides the default paintComponent method to handle custom rendering of the game panel.
     *
     * @param g the Graphics object used for painting
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Alapértelmezett rajzolás
    }

    /**
     * Advances the game to the next player in the turn order and updates the UI accordingly.
     * Also triggers game ticks and checks for game end conditions or Tekton breaking events.
     *
     * @param activePlayerLabel the JLabel displaying the current player's name
     */
    private void nextPlayer(JLabel activePlayerLabel) {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size(); // Körkörös váltás
        activePlayer = players.get(currentPlayerIndex);

        // Frissítsd a felületet az aktuális játékos nevével
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
            if (checkGameEnd()) {
                // End the game after 20 rounds
                return;
            }
            if (round % 5 == 0) {
                breakEmptyTektons();
            }
        }
    }

    /**
     * Breaks apart empty Tekton objects that have no Mycelium or Insects on them.
     * This method is called every 5 rounds to clean up the game board.
     */
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
            }
        }
    }

    /**
     * Checks if the game has reached its end condition (20 rounds).
     * If the condition is met, calculates and displays the best Entomologist and Mycologist based on their scores.
     *
     * @return true if the game has ended, false otherwise
     */
    private boolean checkGameEnd() {
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
        return false; // Játék nem ért véget
    }
}
