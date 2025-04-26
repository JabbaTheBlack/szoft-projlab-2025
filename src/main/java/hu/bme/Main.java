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

        System.out.println("Welcome to the Console App");
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