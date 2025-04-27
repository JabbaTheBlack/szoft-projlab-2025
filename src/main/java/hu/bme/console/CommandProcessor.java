package hu.bme.console;

import java.io.File;
import java.util.Scanner;

public class CommandProcessor {
    public static String commands = "";
    private ConsoleApp consoleApp;

    public CommandProcessor(ConsoleApp consoleApp) {
        this.consoleApp = consoleApp;
    }

    public void processCommand(String command) {
        if (command.trim().isEmpty())
            return;
        commands += command + "\n";
        String[] inputStrings = command.split(" ");
        switch (inputStrings[0]) {
            case "listPlayer":
                consoleApp.listPlayers();
                break;

            case "addPlayer":
                if (inputStrings.length < 2) {
                    System.out.println("Specify which player");
                    return;
                }
                consoleApp.addPlayer(inputStrings[1]);
                break;

            case "roundElapsed":
                consoleApp.roundElapsed();
                break;

            case "addInsect":
                if (inputStrings.length < 3) {
                    System.out.println("Tekton id mycologist id");
                    return;
                }
                consoleApp.addInsect(inputStrings[1], inputStrings[2]);
                break;

            case "addMycelium":
                if (inputStrings.length < 3) {
                    System.out.println("Tekton id mycelium id");
                    return;
                }
                consoleApp.addMycelium(inputStrings[1], inputStrings[2]);
                break;

            case "moveInsect":
                if (inputStrings.length < 3) {
                    System.out.println("Insect id tekton id");
                    return;
                }
                consoleApp.moveInsect(inputStrings[1], inputStrings[2]);
                break;

            case "listInsects":
                if (inputStrings.length < 2) {
                    System.out.println("Entomologist id");
                    return;
                }
                consoleApp.listInsects(inputStrings[1]);
                break;

            case "listHyphae":
                if (inputStrings.length < 2) {
                    System.out.println("Hyphae id");
                    return;
                }
                consoleApp.listHyphae(inputStrings[1]);
                break;

            case "printMap":
                consoleApp.printMap();
                break;

            case "listSpore":
                if (inputStrings.length < 2) {
                    System.out.println("Tekton id");
                    return;
                }
                consoleApp.listSpore(inputStrings[1]);
                break;

            case "growHyphaefromHyphae":
                if (inputStrings.length < 4) {
                    System.out.println("Hyphae id, Tekton id, Mycelium id");
                    return;
                }
                consoleApp.growHyphaeFromHyphae(inputStrings[1], inputStrings[2], inputStrings[3]);
                break;

            case "growHyphaefromMycelium":
                if (inputStrings.length < 3) {
                    System.out.println("Mycelium id, Tekton id");
                    return;
                }
                consoleApp.growHyphaefromMycelium(inputStrings[1], inputStrings[2]);
                break;

            case "addSpore":
                if (inputStrings.length < 3) {
                    System.out.println("Tekton id, Spore type");
                    return;
                }
                consoleApp.addSpore(inputStrings[1], inputStrings[2]);
                break;

            case "addHyphae":
                if (inputStrings.length < 4) {
                    System.out.println("Hyphae id, Tekton id, Mycelium id");
                    return;
                }
                consoleApp.addHyphae(inputStrings[1], inputStrings[2], inputStrings[3]);
                break;

            case "listNeighbour":
                if (inputStrings.length < 2) {
                    System.out.println("Specify which Tekton");
                    return;
                }
                consoleApp.listNeighbour(inputStrings[1]);
                break;

            case "eatSpore":
                if (inputStrings.length < 4) {
                    System.out.println("Mycologist id, Tekton id, Spore id");
                    return;
                }
                consoleApp.eatSpore(inputStrings[1], inputStrings[2], inputStrings[3]);
                break;

            case "upgradeMycelium":
                if (inputStrings.length < 3) {
                    System.out.println("Mycelium id, Upgrade type");
                    return;
                }
                consoleApp.upgradeMycelium(inputStrings[1], inputStrings[2]);
                break;

            case "growNewMycelium":
                if (inputStrings.length < 3) {
                    System.out.println("Tekton id, Mycelium id");
                    return;
                }
                consoleApp.growNewMycelium(inputStrings[1], inputStrings[2]);
                break;

            case "addTekton":
                if (inputStrings.length < 2) {
                    System.out.println("Tekton type");
                    return;
                }
                consoleApp.addTekton(inputStrings[1]);
                break;

            case "addTektonNeighbour": {
                if (inputStrings.length < 3) {
                    System.out.println("Tekton id and at least one neighbour id");
                    return;
                }
                String tektonId = inputStrings[1];
                consoleApp.addTektonNeighbour(tektonId,
                        java.util.Arrays.asList(inputStrings).subList(2, inputStrings.length));
                break;
            }

            case "loadMap":
                if (inputStrings.length < 2) {
                    System.out.println("File name");
                    return;
                }
                loadMap(inputStrings[1]);
                break;

            case "releaseSpore":
                if (inputStrings.length < 2) {
                    System.out.println("Spore id");
                    return;
                }
                consoleApp.releaseSpore(inputStrings[1]);
                break;

            case "breakApart":
                if (inputStrings.length < 2) {
                    System.out.println("Tekton id");
                    return;
                }
                consoleApp.breakApart(inputStrings[1]);
                break;

            case "chooseFungalType":
                if (inputStrings.length < 3) {
                    System.out.println("Mycelium id, Fungal type");
                    return;
                }
                consoleApp.setMyceliumType(inputStrings[1], inputStrings[2]);
                break;

            case "cutHyphae":
                if (inputStrings.length < 4) {
                    System.out.println("Hyphae id, Tekton id, Mycelium id");
                    return;
                }
                consoleApp.cutHyphae(inputStrings[1], inputStrings[2], inputStrings[3]);
                break;

            case "eatInsect":
                if (inputStrings.length < 3) {
                    System.out.println("Mycologist id, Insect id");
                    return;
                }
                consoleApp.eatInsect(inputStrings[1], inputStrings[2]);
                break;

            case "putSporeToMycelium":
                if (inputStrings.length < 3) {
                    System.out.println("Spore id, Mycelium id");
                    return;
                }
                consoleApp.putSporeToMycelium(inputStrings[1], inputStrings[2]);
                break;
            case "savestate":
                if (inputStrings.length < 2) {
                    System.out.println("File name");
                    return;
                }
                consoleApp.saveState(inputStrings[1]);
                break;
            default:
                System.out.println("Unknown command: " + inputStrings[0]);
                break;
        }
    }

    public void loadMap(String file) {
        String filePath = "src/main/java/hu/bme/console/testConfig/" + file;

        File loadedFile = new File(filePath);
        System.out.println("Attempting to open file: " + loadedFile.getAbsolutePath());

        if (!loadedFile.exists()) {
            System.out.println("File doesn't exist: " + loadedFile.getAbsolutePath());
            return;
        }

        try (Scanner fileScanner = new Scanner(loadedFile)) {
            while (fileScanner.hasNextLine()) {
                String command = fileScanner.nextLine().trim();
                if (!command.isEmpty()) {
                    processCommand(command);
                }
            }
            System.out.println("File loaded successfully.");
        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}