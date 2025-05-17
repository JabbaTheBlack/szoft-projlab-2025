package hu.bme;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import hu.bme.core.GamePanel;
import hu.bme.fungi.Hyphae;
import hu.bme.fungi.Mycelium;
import hu.bme.fungi.Mycologist;
import hu.bme.fungi.spore.DefensiveSpore;
import hu.bme.fungi.spore.SlowingSpore;
import hu.bme.insect.Entomologist;
import hu.bme.insect.Insect;
import hu.bme.managers.InsectManager;
import hu.bme.managers.MycologistManager;
import hu.bme.managers.TektonManager;
import hu.bme.tekton.MultiTypeTekton;
import hu.bme.tekton.Tekton;
import hu.bme.view.MainMenu;

public class Main {
    public static void main(String[] args) {
        // Tekton t1 = new MultiTypeTekton();
        // Tekton t2 = new MultiTypeTekton();
        // Tekton t3 = new MultiTypeTekton();
        // Tekton t4 = new MultiTypeTekton();
        // t1.addNeighbour(t2);
        // t2.addNeighbour(t1);
        // t1.addNeighbour(t4);
        // t4.addNeighbour(t1);
        // t3.addNeighbour(t4);
        // t4.addNeighbour(t3);
        // t1.connectToTekton(t4);
        // t4.connectToTekton(t1);

        // int numberOfTektons = 1; // Max 82, utána megöli a programot

        // TektonManager manager = TektonManager.getInstance();

        // for (int i = 0; i < numberOfTektons; i++) {
        // Tekton tekton = new MultiTypeTekton(); // Replace with your Tekton subclass
        // manager.addTekton(tekton);
        // }

        // TektonManager.getInstance().addTekton(t1);
        // TektonManager.getInstance().addTekton(t2);
        // TektonManager.getInstance().addTekton(t3);
        // TektonManager.getInstance().addTekton(t4);
        // DefensiveSpore spore = new DefensiveSpore();
        // SlowingSpore spore2 = new SlowingSpore();
        // DefensiveSpore spore3 = new DefensiveSpore();
        // DefensiveSpore spore4 = new DefensiveSpore();
        // //t4.addSpore(spore2);
        // t3.addSpore(spore);
        // t3.addSpore(spore3);
        // t3.addSpore(spore4);
        // Entomologist entomologist = new Entomologist();
        // Insect insect = new Insect(t1, 10);
        // insect.setEntomologist(entomologist);
        // insect.textureProvider.setImage("/images/ant_images/fekete.png");
        // entomologist.addInsect(insect);
        // InsectManager.getInstance().addEntomologist(entomologist);

        // Mycelium mycelium = new Mycelium(t2);
        // Mycelium mycelium2 = new Mycelium(t1);
        // mycelium.textureProvider.setImage("/images/fungi_images/defensive_fungi.png");
        // mycelium2.textureProvider.setImage("/images/fungi_images/clone_fungi.png");
        // Mycologist mycologist = new Mycologist();
        // Mycologist mycologist2 = new Mycologist();
        // mycologist.addMycelium(mycelium);
        // mycologist2.addMycelium(mycelium2);
        // mycologist.growHyphaeOnTekton(mycelium, t2);
        // mycologist2.growHyphaeOnTekton(mycelium2, t1);
        // Hyphae hyphae = mycologist.getHyphaes().get(0);
        // mycologist.growHyphaeToTekton(hyphae, t1);
        // mycologist.growHyphaeOnTekton(mycologist.getHyphaes().get(1), t1);
        // mycologist.setColor(Color.RED);
        // mycologist2.setColor(Color.BLUE);
        // MycologistManager.getInstance().addMycologist(mycologist);
        // MycologistManager.getInstance().addMycologist(mycologist2);

        // Creating 5 Tekton objects
        // Tekton t1 = new MultiTypeTekton();
        // Tekton t2 = new MultiTypeTekton();
        // Tekton t3 = new MultiTypeTekton();
        // Tekton t4 = new MultiTypeTekton();
        // Tekton t5 = new MultiTypeTekton();

        // // Making all Tektons neighbors of each other
        // t1.addNeighbour(t2);
        // t1.addNeighbour(t3);
        // t1.addNeighbour(t4);

        // t2.addNeighbour(t1);
        // t2.addNeighbour(t3);

        // t3.addNeighbour(t1);
        // t3.addNeighbour(t2);
        // t3.addNeighbour(t4);
        // t3.addNeighbour(t5);

        // t4.addNeighbour(t1);
        // t4.addNeighbour(t3);
        // t4.addNeighbour(t5);

        // t5.addNeighbour(t3);
        // t5.addNeighbour(t4);

        // // Connect 4 Tektons in a line (T1-T2-T3-T4)
        // t1.connectToTekton(t2);
        // t2.connectToTekton(t1);

        // t2.connectToTekton(t3);
        // t3.connectToTekton(t2);

        // t3.connectToTekton(t4);
        // t4.connectToTekton(t3);

        // t4.connectToTekton(t5);
        // t5.connectToTekton(t4);

        // // Add all Tektons to the manager
        // TektonManager manager = TektonManager.getInstance();
        // manager.addTekton(t1);
        // manager.addTekton(t2);
        // manager.addTekton(t3);
        // manager.addTekton(t4);
        // manager.addTekton(t5);

        // System.out.println("Created 5 Tektons - all are neighbors, 4 connected in
        // line");

        // // After adding all Tektons to the manager and before the Fungorium ASCII art

        // // Create mycelium on different tektons
        // Mycelium mycelium1 = new Mycelium(t1);
        // Mycelium mycelium2 = new Mycelium(t5);
        // mycelium1.textureProvider.setImage("/images/fungi_images/defensive_fungi.png");
        // mycelium2.textureProvider.setImage("/images/fungi_images/clone_fungi.png");

        // // Create mycologists and assign mycelium
        // Mycologist redMycologist = new Mycologist();
        // Mycologist blueMycologist = new Mycologist();
        // redMycologist.addMycelium(mycelium1);
        // blueMycologist.addMycelium(mycelium2);

        // // Set colors for the mycologists
        // redMycologist.setColor(Color.RED);
        // blueMycologist.setColor(Color.BLUE);

        // // Grow hyphae along the connected tektons
        // redMycologist.growHyphaeOnTekton(mycelium1, t1); // Initial hyphae on t1
        // Hyphae firstHyphae = redMycologist.getHyphaes().get(0); // Get the first
        // hyphae
        // redMycologist.growHyphaeToTekton(firstHyphae, t2); // Grow to t2
        // Hyphae secondHyphae = redMycologist.getHyphaes().get(1); // Get the second
        // hyphae
        // redMycologist.growHyphaeToTekton(secondHyphae, t3); // Grow to t3
        // Hyphae thirdHyphae = redMycologist.getHyphaes().get(2); // Get the third
        // hyphae
        // redMycologist.growHyphaeToTekton(thirdHyphae, t4); // Grow to t4

        // // Add mycologists to the manager
        // MycologistManager.getInstance().addMycologist(redMycologist);
        // MycologistManager.getInstance().addMycologist(blueMycologist);

        // System.out.println("Added two mycologists with hyphae networks");

        // Entomologist entomologist = new Entomologist();
        // Insect insect = new Insect(t1, 2); // Create insect on t1 with 10 health
        // insect.setEntomologist(entomologist);
        // insect.textureProvider.setImage("/images/ant_images/fekete.png");
        // entomologist.addInsect(insect);
        // InsectManager.getInstance().addEntomologist(entomologist);

        System.out.println(" /$$$$$$$$                                                /$$          \r\n" + //
                "| $$_____/                                               |__/          \r\n" + //
                "| $$    /$$   /$$ /$$$$$$$   /$$$$$$   /$$$$$$   /$$$$$$  /$$  /$$$$$$ \r\n" + //
                "| $$$$$| $$  | $$| $$__  $$ /$$__  $$ /$$__  $$ /$$__  $$| $$ |____  $$\r\n" + //
                "| $$__/| $$  | $$| $$  \\ $$| $$  \\ $$| $$  \\ $$| $$  \\__/| $$  /$$$$$$$\r\n" + //
                "| $$   | $$  | $$| $$  | $$| $$  | $$| $$  | $$| $$      | $$ /$$__  $$\r\n" + //
                "| $$   |  $$$$$$/| $$  | $$|  $$$$$$$|  $$$$$$/| $$      | $$|  $$$$$$$\r\n" + //
                "|__/    \\______/ |__/  |__/ \\____  $$ \\______/ |__/      |__/ \\_______/\r\n" + //
                "                            /$$  \\ $$                                  \r\n" + //
                "                           |  $$$$$$/                                  \r\n" + //
                "                            \\______/                                   \r\n\n" + //
                "#---------------------------------------------------------------------#");
        JFrame frame = new JFrame("Fungorium - Main Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        frame.setIconImage(
                new ImageIcon(Main.class.getResource("/images/fungi_images/defensive_fungi.png")).getImage());

        frame.add(new MainMenu(frame)); // Átadjuk a JFrame-et a MainMenu-nak
        frame.setVisible(true);

    }
}