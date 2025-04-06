package hu.bme.console;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

import hu.bme.core.GameController;
import hu.bme.fungi.Mycologist;
import hu.bme.insect.Entomologist;
import hu.bme.managers.InsectManager;
import hu.bme.managers.MycologistManager;

public class ConsoleApp {
    
    private static GameController gameController = new GameController(); 
    private static InsectManager insectManager = InsectManager.getInstance();
    private static MycologistManager mycologistManager = MycologistManager.getInstance();
    private static HashMap<Integer, Entomologist> entomologistWithIds = new HashMap<>();
    private static HashMap<Integer, Mycologist> mycologistWithIds = new HashMap<>();
    int playerId = 0;

    private String getInput(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    private void addPlayer(String player) {
        if(player.toLowerCase().equals("entomologist")){
            Entomologist entomologist = new Entomologist();
            insectManager.addEntomologist(entomologist);

            entomologistWithIds.put(playerId, entomologist);

            System.out.println("ID: " + playerId++ + " entomologist added");
        } else if(player.toLowerCase().equals("mycologist")){
            Mycologist mycologist = new Mycologist();
            mycologistManager.addMycologist(mycologist);
            
            mycologistWithIds.put(playerId, mycologist);

            System.out.println("ID: " + playerId++ + " mycologist added");
        }
    }

    private void listPlayers() {

        if(entomologistWithIds.size() == 0 && mycologistWithIds.size() == 0){
            System.out.println("No player");
            return;
        }
        for (HashMap.Entry<Integer, Entomologist> entry : entomologistWithIds.entrySet()) {
            Integer id = entry.getKey();
            Entomologist entomologist = entry.getValue();
            System.out.println("ID: " + id + ", Entomologist: " + entomologist);
        }
        for(HashMap.Entry<Integer, Mycologist> entry : mycologistWithIds.entrySet()) {
            Integer id = entry.getKey();
            Mycologist mycologist = entry.getValue();
            System.out.println("ID: " + id + ", Mycologist: " + mycologist);
        }
    }

    private void roundElapsed(){
        
    }

    private void printMap(){
        gameController.getTektonManager().getTektons().forEach(tekton -> {
            System.out.println(tekton);
            tekton.getConnectedNeighbours().forEach(neighbour -> {
                System.out.println("  " + neighbour);
            });
        }); 
    }
    
    public void run(){
        System.out.println("Baszodj meg");
        String input = "";
        do {
            input = getInput();
            switch (input) {
                case "":
                    
                    break;
                case "listPlayer":
                    listPlayers();
                    break;
                case "addPlayer":
                    addPlayer(getInput());
                    break;
                case "roundElapsed":

                    break;
                // Jani csinálta: 
                case "printMap":
                    printMap();
                    break;
                default:
                    break;
            }
               
        } while (!input.equals("Exit"));
    }
}
