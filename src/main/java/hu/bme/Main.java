package hu.bme;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import hu.bme.core.GamePanel;
import hu.bme.fungi.Mycelium;
import hu.bme.fungi.Mycologist;
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

        TektonManager.getInstance().addTekton(t1);
        TektonManager.getInstance().addTekton(t2);
        TektonManager.getInstance().addTekton(t3);
        TektonManager.getInstance().addTekton(t4);
        Entomologist entomologist = new Entomologist();
        Insect insect = new Insect(t1, 10);
        insect.setEntomologist(entomologist);
        insect.textureProvider.setImage("/images/ant_images/fekete.png");
        entomologist.addInsect(insect);
        InsectManager.getInstance().addEntomologist(entomologist);

        Mycelium mycelium = new Mycelium(t2);
        mycelium.textureProvider.setImage("/images/fungi_images/defensive_fungi.png");
        Mycologist mycologist = new Mycologist();
        mycologist.addMycelium(mycelium);
        MycologistManager.getInstance().addMycologist(mycologist);
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
        frame.add(new MainMenu(frame)); // Átadjuk a JFrame-et a MainMenu-nak
        frame.setVisible(true);

    }
}