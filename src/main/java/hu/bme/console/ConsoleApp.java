package hu.bme.console;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

import hu.bme.core.GameController;
import hu.bme.fungi.Mycologist;
import hu.bme.insect.Entomologist;
import hu.bme.managers.InsectManager;
import hu.bme.managers.MycologistManager;
import hu.bme.insect.Insect;
import hu.bme.tekton.Tekton;

public class ConsoleApp {
    
    private static GameController gameController = new GameController(); 
    private static InsectManager insectManager = InsectManager.getInstance();
    private static MycologistManager mycologistManager = MycologistManager.getInstance();
    private static HashMap<Integer, Entomologist> entomologistWithIds = new HashMap<>();
    private static HashMap<Integer, Mycologist> mycologistWithIds = new HashMap<>();
    private static HashMap<Integer, Insect> insectsWithIds = new HashMap<>();
    private static HashMap<Integer, Tekton> tektonsWithIds = new HashMap<>();
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

    private void moveInsect(Integer insectId, Integer tektonId){
        if(!(insectsWithIds.containsKey(insectId) && tektonsWithIds.containsKey(tektonId))){
            System.out.println("Invalid insect or tekton ID");
            return;
        } else {

            Insect insect = insectsWithIds.get(insectId);
            Tekton tekton = tektonsWithIds.get(tektonId);
            insect.move(tekton);
        }
    }

    public void run(){
        System.out.println("Baszodj meg");
        String[] inputStrings;
        do {
            inputStrings = getInput().split(" ");
            switch (inputStrings[0]) {
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
                case "moveInsect":
                    moveInsect(Integer.parseInt(inputStrings[1]), Integer.parseInt(inputStrings[2]));

                    break;
                case "listInsects":
                    listInsects(inputStrings[1]);
                    break;
                default:
                    break;
            }
               
        } while (!inputStrings[0].equals("Exit"));
    }
}
