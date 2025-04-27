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

            case "help":
                System.out.println("Available commands:");
                System.out.println("\t- listPlayer - Lists all players");
                System.out.println("\t- addPlayer <playerType> - Adds a player (entomologist or mycologist)");
                System.out.println("\t- troundElapsed - Advances the game by one round");
                System.out.println(
                        "\t- addInsect <tektonId> <entomologistId> - Adds an insect to a tekton and entomologist");
                System.out.println(
                        "\t- addMycelium <tektonId> <mycologistId> - Adds a mycelium to a tekton and mycologist");
                System.out.println("\t- moveInsect <insectId> <tektonId> - Moves an insect to a new tekton");
                System.out.println("\t- listInsects <entomologistId> - Lists all insects of an entomologist");
                System.out.println("\t- listHyphae <id> - Lists hyphae by ID or all hyphae");
                System.out.println("\t- printMap - Prints the map of tektons");
                System.out.println("\t- listSpore <tektonId> - Lists spores in a tekton");
                System.out.println(
                        "\t- growHyphaefromHyphae <hyphaeId> <tektonId> <myceliumId> - Grows hyphae from another hyphae");
                System.out.println("\t- growHyphaefromMycelium <myceliumId> <tektonId> - Grows hyphae from a mycelium");
                System.out.println("\t- addSpore <tektonId> <sporeType> - Adds a spore to a tekton");
                System.out.println(
                        "\t- addHyphae <hyphaeId> <tektonId> <myceliumId> - Adds hyphae to a tekton and mycelium");
                System.out.println("\t- listNeighbour <tektonId> - Lists neighbors of a tekton");
                System.out.println("\t- eatSpore <mycologistId> <tektonId> <sporeId> - Mycologist eats a spore");
                System.out.println("\t- upgradeMycelium <myceliumId> <upgradeType> - Upgrades a mycelium");
                System.out.println("\t- growNewMycelium <tektonId> <myceliumId> - Grows a new mycelium");
                System.out.println("\t- addTekton <tektonType> - Adds a tekton of a specific type");
                System.out.println("\t- addTektonNeighbour <tektonId> <neighbourIds...> - Adds neighbors to a tekton");
                System.out.println("\t- loadMap <fileName> - Loads a map from a file");
                System.out.println("\t- releaseSpore <sporeId> - Releases a spore");
                System.out.println("\t- breakApart <tektonId> - Breaks apart a tekton");
                System.out
                        .println("\t- chooseFungalType <myceliumId> <fungalType> - Sets the fungal type of a mycelium");
                System.out.println("\t- cutHyphae <hyphaeId> <tektonId> <myceliumId> - Cuts a hyphae");
                System.out.println("\t- eatInsect <mycologistId> <insectId> - Mycologist eats an insect");
                System.out.println("\t- putSporeToMycelium <sporeId> <myceliumId> - Puts a spore into a mycelium");
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