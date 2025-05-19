package hu.bme;


import javax.swing.ImageIcon;
import javax.swing.JFrame;

import hu.bme.view.MainMenu;

//* Main method */
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