package hu.bme;

import hu.bme.console.CommandProcessor;
import hu.bme.console.ConsoleApp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ConsoleApp consoleApp = new ConsoleApp();
        CommandProcessor commandProcessor = new CommandProcessor(consoleApp);

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
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Exiting...");
                break;
            }
            commandProcessor.processCommand(input);
        }
    }
}