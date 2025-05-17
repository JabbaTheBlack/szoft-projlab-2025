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