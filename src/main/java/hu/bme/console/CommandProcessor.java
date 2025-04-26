package hu.bme.console;

import java.io.File;
import java.util.Scanner;

public class CommandProcessor {
    private ConsoleApp consoleApp;

    public CommandProcessor(ConsoleApp consoleApp) {
        this.consoleApp = consoleApp;
    }

    public void processCommand(String command) {
        if (command.trim().isEmpty()) return;

        String[] inputStrings = command.split(" ");
        switch (inputStrings[0]) {
            case "listPlayer":
                consoleApp.listPlayers();
                break;
            case "addPlayer":
                consoleApp.addPlayer(inputStrings[1]);
                break;
            case "roundElapsed":
                consoleApp.roundElapsed();
                break;
            case "addInsect":
                consoleApp.addInsect(inputStrings[1], inputStrings[2]);
                break;
            case "addMycelium":
                consoleApp.addMycelium(inputStrings[1], inputStrings[2]);
                break;
            case "moveInsect":
                consoleApp.moveInsect(inputStrings[1], inputStrings[2]);
                break;
            case "listInsects":
                consoleApp.listInsects(inputStrings[1]);
                break;
            case "listHyphae":
                consoleApp.listHyphae(inputStrings[1]);
                break;
            case "printMap":
                consoleApp.printMap();
                break;
            case "listSpore":
                consoleApp.listSpore(inputStrings[1]);
                break;
            case "growHyphaefromHyphae":
                consoleApp.growHyphaeFromHyphae(inputStrings[1], inputStrings[2], inputStrings[3]);
                break;
            case "growHyphaefromMycelium":
                consoleApp.growHyphaefromMycelium(inputStrings[1], inputStrings[2]);
                break;
            case "addSpore":
                consoleApp.addSpore(inputStrings[1], inputStrings[2]);
                break;
            case "addHyphae":
                consoleApp.addHyphae(inputStrings[1], inputStrings[2], inputStrings[3]);
                break;
            case "listNeighbour":
                consoleApp.listNeighbour(inputStrings[1]);
                break;
            case "eatSpore":
                consoleApp.eatSpore(inputStrings[1], inputStrings[2], inputStrings[3]);
                break;
            case "upgradeMycelium":
                consoleApp.upgradeMycelium(inputStrings[1], inputStrings[2]);
                break;
            case "growNewMycelium":
                consoleApp.growNewMycelium(inputStrings[1], inputStrings[2]);
                break;
            case "addTekton":
                consoleApp.addTekton(inputStrings[1]);
                break;
            case "addTektonNeighbour": {
                String tektonId = inputStrings[1];
                consoleApp.addTektonNeighbour(tektonId, java.util.Arrays.asList(inputStrings).subList(2, inputStrings.length));
                break;
            }
            case "loadMap":
                loadMap(inputStrings[1]);
                break;
            case "releaseSpore":
                consoleApp.releaseSpore(inputStrings[1]);
                break;
            case "breakApart":
                consoleApp.breakApart(inputStrings[1]);
                break;
            case "chooseFungalType":
                consoleApp.setMyceliumType(inputStrings[1], inputStrings[2]);
                break;
            case "cutHyphae":
                consoleApp.cutHyphae(inputStrings[1], inputStrings[2], inputStrings[3]);
                break;
            case "eatInsect":
                consoleApp.eatInsect(inputStrings[1], inputStrings[2]);
                break;
            case "putSporeToMycelium":
                consoleApp.putSporeToMycelium(inputStrings[1], inputStrings[2]);
                break;
            default:
                System.out.println("Unknown command: " + inputStrings[0]);
                break;
        }
    }

      public void loadMap(String file) {
        // Construct the path to the testConfig folder
        String filePath = "src/main/java/hu/bme/console/testConfig/" + file;
    
        File loadedFile = new File(filePath);
        System.out.println("Attempting to open file: " + loadedFile.getAbsolutePath());
    
        // Check if the file exists
        if (!loadedFile.exists()) {
            System.out.println("File doesn't exist: " + loadedFile.getAbsolutePath());
            return;
        }
    
        // Read and process the file line by line
        try (Scanner fileScanner = new Scanner(loadedFile)) {
            while (fileScanner.hasNextLine()) {
                String command = fileScanner.nextLine().trim();
                if (!command.isEmpty()) {
                    processCommand(command); // Reuse the existing command processing logic
                }
            }
            System.out.println("File loaded successfully.");
        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}