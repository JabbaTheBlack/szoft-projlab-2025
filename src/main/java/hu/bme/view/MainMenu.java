package hu.bme.view;

import hu.bme.Main;
import hu.bme.core.GamePanel;
import hu.bme.fungi.Mycelium;
import hu.bme.fungi.Mycologist;
import hu.bme.insect.Entomologist;
import hu.bme.insect.Insect;
import hu.bme.managers.InsectManager;
import hu.bme.managers.MycologistManager;
import hu.bme.managers.TektonManager;
import hu.bme.tekton.AbsrobingTekton;
import hu.bme.tekton.KeeperTekton;
import hu.bme.tekton.MultiTypeTekton;
import hu.bme.tekton.MyceliumFreeTekton;
import hu.bme.tekton.SingleTypeTekton;
import hu.bme.tekton.Tekton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class MainMenu extends JPanel {

    private JFrame parentFrame; // A szülő JFrame tárolása
    private Map<String, JTextField> gombaszokTextFields = new HashMap<>();
    private Map<String, JTextField> rovaraszokTextFields = new HashMap<>();
    int players = 0;

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
                        // Mycologist mycologist = new Mycologist(name); // Feltételezve, hogy van ilyen
                        // konstruktor
                        // MycologistManager.getInstance().addMycologist(mycologist); // Gombász
                        // hozzáadása a
                        // MycologistManagerhez
                        players++;
                        System.out.println("Létrehozott gombász: " + name);
                    }
                }

                for (Map.Entry<String, JTextField> entry : rovaraszokTextFields.entrySet()) {
                    String name = entry.getValue().getText().trim();
                    if (!name.isEmpty()) {
                        // Entomologist entomologist = new Entomologist(name); // Feltételezve, hogy van
                        // ilyen konstruktor
                        // InsectManager.getInstance().addEntomologist(entomologist); // Rovarász
                        // hozzáadása az
                        // InsectManagerhez
                        players++;
                        System.out.println("Létrehozott rovarász: " + name);
                    }
                }
                createTektons(players);

                createInsects();
                createMyceliums();
                // Bezárjuk az aktuális ablakot
                parentFrame.dispose();

                // Új ablak megnyitása a GamePanel megjelenítéséhez
                JFrame gameFrame = new JFrame("Fungorium - Game");
                gameFrame.setIconImage(
                        new ImageIcon(Main.class.getResource("/images/fungi_images/defensive_fungi.png")).getImage());
                // Max size window to see all tektons
                gameFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);

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

    private void createTektons(int count) {
        TektonManager tektonManager = TektonManager.getInstance();

        MultiTypeTekton multitypeTekton = new MultiTypeTekton();
        KeeperTekton keeperTekton = new KeeperTekton();
        MyceliumFreeTekton myceliumFreeTekton = new MyceliumFreeTekton();
        SingleTypeTekton singleTypeTekton = new SingleTypeTekton();
        AbsrobingTekton absrobingTekton = new AbsrobingTekton();
        Tekton tekton;
        int randomType = (int) (Math.random() * 5); // 0, 1 vagy 2
        for (int i = 0; i < count; i++) {
            switch (randomType) {
                case 0:
                    tekton = new MultiTypeTekton();
                    break;
                case 1:
                    tekton = new KeeperTekton();
                    break;
                case 2:
                    tekton = new AbsrobingTekton();
                    break;
                case 3:
                    tekton = new MyceliumFreeTekton();
                    break;
                case 4:
                    tekton = new SingleTypeTekton();
                default:
                    tekton = new MultiTypeTekton();
                    break;
            }
            tektonManager.addTekton(tekton);

        }
        initializeNeighbors();
    }

    private void initializeNeighbors() {
        TektonManager tektonManager = TektonManager.getInstance();

        int neighborRadius = 50; // Szomszédság határa (50 pixel)

        for (Tekton tekton1 : tektonManager.getTektons()) {
            for (Tekton tekton2 : tektonManager.getTektons()) {
                double distance = Math.sqrt(Math.pow(tekton1.getX() - tekton2.getX(), 2) +
                        Math.pow(tekton1.getY() - tekton2.getY(), 2));
                if (tekton1 != tekton2) {
                    if (distance <= 200) {

                        tekton1.addNeighbour(tekton2);
                    }
                }
            }
        }
        ensureGraphConnectivity();

    }

    private void ensureGraphConnectivity() {
        while (!isGraphConnected()) {
            ArrayList<Tekton> isolated = findIsolatedTektons();
            connectIsolatedTektons(isolated);
        }
    }

    private boolean isGraphConnected() {

        Set<Tekton> visited = new HashSet<>();
        Tekton start = TektonManager.getInstance().getTektons().get(0); // Kezdő csúcs
        dfs(start, visited);

        // Ha az összes csúcsot bejártuk, akkor a gráf összefüggő
        return visited.size() == TektonManager.getInstance().getTektons().size();
    }

    private void dfs(Tekton tekton, Set<Tekton> visited) {
        if (visited.contains(tekton)) {
            return;
        }
        visited.add(tekton);
        for (Tekton neighbour : tekton.getNeighbours()) {
            dfs(neighbour, visited);
        }
    }

    private ArrayList<Tekton> findIsolatedTektons() {
        Set<Tekton> visited = new HashSet<>();
        Tekton start = TektonManager.getInstance().getTektons().get(0); // Kezdő csúcs
        dfs(start, visited);

        ArrayList<Tekton> isolated = new ArrayList<>();
        for (Tekton tekton : TektonManager.getInstance().getTektons()) {
            if (!visited.contains(tekton)) {
                isolated.add(tekton);
            }
        }
        return isolated;
    }

    private void connectIsolatedTektons(ArrayList<Tekton> isolated) {
        for (Tekton isolatedTekton : isolated) {
            Tekton closestTekton = null;
            double minDistance = Double.MAX_VALUE;

            for (Tekton tekton : TektonManager.getInstance().getTektons()) {
                if (tekton == isolatedTekton || isolatedTekton.getNeighbours().contains(tekton)) {
                    continue; // Ha már szomszédosak, ugorjunk a következőre
                }

                double distance = Math.sqrt(Math.pow(isolatedTekton.getX() - tekton.getX(), 2) +
                        Math.pow(isolatedTekton.getY() - tekton.getY(), 2));
                if (distance < minDistance) {
                    minDistance = distance;
                    closestTekton = tekton;
                }
            }

            // Kapcsoljuk össze az elszigetelt Tekton-t a legközelebbi Tekton-nal
            if (closestTekton != null) {
                isolatedTekton.addNeighbour(closestTekton);
                closestTekton.addNeighbour(isolatedTekton);
            }
        }
    }

    private void createInsects() {

        Random random = new Random();

        for (Map.Entry<String, JTextField> entry : rovaraszokTextFields.entrySet()) {
            String color = entry.getKey();
            String name = entry.getValue().getText().trim();

            if (!name.isEmpty()) {
                Entomologist entomologist = new Entomologist(name);
                InsectManager.getInstance().addEntomologist(entomologist);

                Tekton randomTekton = TektonManager.getInstance().getTektons()
                        .get(random.nextInt(TektonManager.getInstance().getTektons().size()));
                Insect insect = new Insect(randomTekton, 10);
                switch (color) {
                    case "Fekete":
                        insect.textureProvider.setImage("/images/ant_images/fekete.png");
                        break;
                    case "Kék":
                        insect.textureProvider.setImage("/images/ant_images/kek.png");
                        break;
                    case "Lila":
                        insect.textureProvider.setImage("/images/ant_images/lila.png");
                        break;
                    case "Narancs":
                        insect.textureProvider.setImage("/images/and_images/narancs.png");
                        break;
                    case "Neon":
                        insect.textureProvider.setImage("/images/ant_images/neon.png");
                        break;
                    case "Pink":
                        insect.textureProvider.setImage("/images/ant_images/pink.png");
                        break;
                    case "Piros":
                        insect.textureProvider.setImage("/images/ant_images/piros.png");
                        break;
                    case "Zold":
                        insect.textureProvider.setImage("/images/ant_images/zold.png");
                    default:
                        break;
                }
                entomologist.addInsect(insect);
                insect.setEntomologist(entomologist);
                System.out.println("Létrehozott rovar: " + name + " szín: " + color);
            }
        }
    }

    private void createMyceliums() {
        TektonManager tektonManager = TektonManager.getInstance();

        Random random = new Random();

        for (Map.Entry<String, JTextField> entry : gombaszokTextFields.entrySet()) {
            String type = entry.getKey(); // A gomba típusa (pl. "Stun", "Defensive", stb.)
            String name = entry.getValue().getText().trim(); // A gombász neve

            if (!name.isEmpty()) {
                Mycologist mycologist = new Mycologist(name); // Gombász létrehozása
                MycologistManager.getInstance().addMycologist(mycologist); // Hozzáadás a MycologistManagerhez

                // Véletlenszerű Tekton kiválasztása
                Tekton randomTekton = tektonManager.getTektons().get(random.nextInt(tektonManager.getTektons().size()));

                // Gomba létrehozása
                while (randomTekton.hasMycelium()) {
                    randomTekton = tektonManager.getTektons().get(random.nextInt(tektonManager.getTektons().size()));
                }
                Mycelium mycelium = new Mycelium(randomTekton);
                mycologist.addMycelium(mycelium); // Hozzáadjuk a gombásznak
                randomTekton.addMycelium(mycelium);

                // Spórafajta kiválasztása
                switch (type) {
                    case "Stun":
                        mycologist.chooseSpore(1);
                        mycologist.setColor(new Color(109, 62, 144)); // RGB színkód
                        mycelium.textureProvider.setImage("/images/fungi_images/stun_fungi.png");
                        break;
                    case "Defensive":
                        mycologist.chooseSpore(2);
                        mycologist.setColor(Color.RED);
                        mycelium.textureProvider.setImage("/images/fungi_images/defensive_fungi.png");
                        break;
                    case "Speedboost":
                        mycologist.chooseSpore(3);
                        mycologist.setColor(Color.YELLOW);
                        mycelium.textureProvider.setImage("/images/fungi_images/speed_fungi.png");
                        break;
                    case "Slowing":
                        mycologist.chooseSpore(4);
                        mycologist.setColor(Color.GREEN);
                        mycelium.textureProvider.setImage("/images/fungi_images/slowing_fungi.png");
                        break;
                    case "Clone":
                        mycologist.chooseSpore(5);
                        mycologist.setColor(new Color(112,226,255));
                        mycelium.textureProvider.setImage("/images/fungi_images/clone_fungi.png");
                        break;
                    default:
                        System.out.println("Ismeretlen spórafajta: " + type);
                        break;
                }

                // Hozzáadjuk a kiválasztott spórát a gombához
                mycologist.growSpore(mycelium);
                mycologist.addMycelium(mycelium);

                System.out.println("Létrehozott gomba: " + name + " típus: " + type);
            }
        }
    }
}