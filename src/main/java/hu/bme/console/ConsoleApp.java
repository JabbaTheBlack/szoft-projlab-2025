package hu.bme.console;

import java.sql.Time;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

import hu.bme.core.GameController;
import hu.bme.core.Ticker;
import hu.bme.fungi.Mycologist;
import hu.bme.insect.Entomologist;
import hu.bme.managers.InsectManager;
import hu.bme.managers.MycologistManager;
import hu.bme.managers.TektonManager;

public class ConsoleApp {
    int id;
    private static GameController gameController = new GameController(); 
    private static InsectManager insectManager = InsectManager.getInstance();
    private static MycologistManager mycologistManager = MycologistManager.getInstance();
    private static HashMap<Integer, Entomologist> entomologistWithIds = new HashMap<>();
    private static HashMap<Integer, Mycologist> mycologistWithIds = new HashMap<>();
    private static TektonManager tektonManager = TektonManager.getInstance();
    private static Ticker ticker = Ticker.getInstance();
    
    public ConsoleApp() {
        id = 0;
    }
    
    

    private String getInput(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    private void addPlayer(String player) { 
        if(player.equalsIgnoreCase("entomologist")){
            Entomologist entomologist = new Entomologist();
            insectManager.addEntomologist(entomologist);

            entomologistWithIds.put(id, entomologist);

            System.out.println("ID: " + id++ + " entomologist added");
        } else if(player.equalsIgnoreCase("mycologist")){
            Mycologist mycologist = new Mycologist();
            mycologistManager.addMycologist(mycologist);
            
            mycologistWithIds.put(id, mycologist);

            System.out.println("ID: " + id++ + " mycologist added");
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

    private void roundElapsed() {
        ticker.tick();
        System.out.println("1 round has passed");
    }

    private void addInsect(int tektonId, int entomologistId) {
        // Tekton tekton = tektonManager.getTektons().get(tektonId);
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
        String[] intputStrings;
        do {
            intputStrings = getInput().split(" ");
            switch (intputStrings[0]) {
                case "":
                    
                    break;
                case "listPlayer":
                    listPlayers();
                    break;
                case "addPlayer":
                    addPlayer(getInput());
                    break;
                case "roundElapsed":
                    roundElapsed();
                    break;
                case "addInsect":
                    addInsect(id, id);
                    break;
                // Jani csinálta: 
                case "printMap":
                    printMap();
                    break;
                default:
                    break;
            }
               
        } while(!intputStrings[0].equals("Exit"));
    }
}
