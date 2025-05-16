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
        Tekton t1 = new MultiTypeTekton();
        Tekton t2 = new MultiTypeTekton();
        Tekton t3 = new MultiTypeTekton();
        Tekton t4 = new MultiTypeTekton();
        t1.addNeighbour(t2);
        t2.addNeighbour(t1);
        t1.addNeighbour(t4);
        t4.addNeighbour(t1);
        t3.addNeighbour(t4);
        t4.addNeighbour(t3);
        t1.connectToTekton(t4);
        t4.connectToTekton(t1);

        int numberOfTektons = 1; // Max 82, utána megöli a programot

        TektonManager manager = TektonManager.getInstance();

        // for (int i = 0; i < numberOfTektons; i++) {
        //     Tekton tekton = new MultiTypeTekton(); // Replace with your Tekton subclass
        //     manager.addTekton(tekton);
        // }

        TektonManager.getInstance().addTekton(t1);
        TektonManager.getInstance().addTekton(t2);
        TektonManager.getInstance().addTekton(t3);
        TektonManager.getInstance().addTekton(t4);
        DefensiveSpore spore = new DefensiveSpore();
        SlowingSpore spore2 = new SlowingSpore();
        t4.addSpore(spore2);
        t3.addSpore(spore);
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