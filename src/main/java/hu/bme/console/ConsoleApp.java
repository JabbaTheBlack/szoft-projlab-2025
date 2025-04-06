package hu.bme.console;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

import hu.bme.core.GameController;
import hu.bme.fungi.Hyphae;
import hu.bme.core.Ticker;
import hu.bme.fungi.Mycelium;
import hu.bme.fungi.Mycologist;
import hu.bme.fungi.spore.CloneSpore;
import hu.bme.fungi.spore.DefensiveSpore;
import hu.bme.fungi.spore.SlowingSpore;
import hu.bme.fungi.spore.SpeedBoostSpore;
import hu.bme.fungi.spore.Spore;
import hu.bme.fungi.spore.StunSpore;
import hu.bme.insect.Entomologist;
import hu.bme.insect.Insect;
import hu.bme.managers.InsectManager;
import hu.bme.managers.MycologistManager;
import hu.bme.managers.TektonManager;
import hu.bme.insect.Insect;
import hu.bme.tekton.Tekton;

public class ConsoleApp {
    int id;
    private static GameController gameController = new GameController(); 
    private static InsectManager insectManager = InsectManager.getInstance();
    private static MycologistManager mycologistManager = MycologistManager.getInstance();
    private static HashMap<Integer, Entomologist> entomologistWithIds = new HashMap<>();
    private static HashMap<Integer, Mycologist> mycologistWithIds = new HashMap<>();
    private static HashMap<Integer, Tekton> tektonsWithIds = new HashMap<>();
    private static HashMap<Integer, Mycelium> myceliumsWithIds = new HashMap<>();
    private static HashMap<Integer, Insect> insectsWithIds = new HashMap<>();
    private static TektonManager tektonManager = TektonManager.getInstance();
    private static HashMap<Integer, Spore> sporesWithIds = new HashMap<>();
    private static HashMap<Integer, Hyphae> hyphaesWithIds = new HashMap<>();
    private static Ticker ticker = Ticker.getInstance();
    
    public ConsoleApp() {
        id = 0;
    }
    
    

    private String getInput(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    private void addPlayer(String player) {
        if(player.toLowerCase().equals("entomologist")){
            Entomologist entomologist = new Entomologist();
            insectManager.addEntomologist(entomologist);

            entomologistWithIds.put(id, entomologist);

            System.out.println("ID: " + id++ + " entomologist added");
        } else if(player.toLowerCase().equals("mycologist")){
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
        Tekton tekton = tektonsWithIds.get(tektonId);
        Entomologist entomologist = entomologistWithIds.get(entomologistId);

        if(entomologist == null || tekton == null) {
            System.out.println("Entomologist or Tekton doesn't exist");
        }
        Insect insect = new Insect();
        insect.setEntomologist(entomologist);
        insect.setCurrentTekton(tekton);
        entomologist.addInsect(insect);
        insectsWithIds.put(id, insect);

        System.out.println(id++ + " insect added to " + entomologistId + " entomologist and to " + tektonId + " tekton");
    }

    private void addMycelium(int tektonId, int mycologistId) {
        Mycelium<? extends Spore> mycelium = myceliumsWithIds.get(mycologistId);
        Mycelium<? extends Spore> clone = mycelium.clone();
        Tekton tekton = tektonsWithIds.get(tektonId);
        Mycologist mycologist = mycologistWithIds.get(mycologistId);

        if(tekton != null && clone != null && mycelium != null) {
            System.out.println("Tekton with ID: " + tektonId + " or myceium with ID");
        }

        tekton.addMycelium(clone);
        mycelium.setCurrentTekton(tekton);
        mycologist.addMycelium(mycelium);

        mycologistWithIds.put(id, mycologist);
        System.out.println(id++ + "mycelium added to " + mycologistId + " mycologist and to " + tektonId + " tekton");
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
    private void listInsects(String entomologistID){
        if(!entomologistWithIds.containsKey(Integer.parseInt(entomologistID))){
            System.out.println("Invalid entomologist ID");

        } else {
            Entomologist entomologist = entomologistWithIds.get(Integer.parseInt(entomologistID));
            List<Insect> insects = entomologist.getInsects();
            for(Insect insect : insects){
                System.out.println(insect);
            }
        }
    }

    private void addSpore(int tektonId, String sporeType) {
        Tekton tekton = tektonsWithIds.get(tektonId);

        switch (sporeType) {
            case "DefensiveSpore":
                DefensiveSpore spore = new DefensiveSpore();
                tekton.addSpore(spore);
                sporesWithIds.put(id++, spore);
                break;
            case "StunSpore":
                StunSpore stunSpore = new StunSpore();
                tekton.addSpore(stunSpore);
                sporesWithIds.put(id++, stunSpore);
                break;
            case "SlowingSpore":
                SlowingSpore slowingSpore = new SlowingSpore();
                tekton.addSpore(slowingSpore);
                sporesWithIds.put(id++, slowingSpore);
                break;
            case "SpeedBoostSpore":
                SpeedBoostSpore speedBoostSpore = new SpeedBoostSpore();
                tekton.addSpore(speedBoostSpore);
                sporesWithIds.put(id++, speedBoostSpore);
                break;
            case "CloneSpore":
                CloneSpore cloneSpore = new CloneSpore();
                tekton.addSpore(cloneSpore);
                sporesWithIds.put(id++, cloneSpore);
                break;
            default:
                break;
        }

        System.out.println(id++ + " spore added to " + tektonId + " tekton");
    }

    private void addHyphae(int hyphaeOrMyceliumId, int tektonId, int mycologistId) {
        Mycologist mycologist = mycologistWithIds.get(mycologistId);
        if (mycologist == null) {
            System.out.println("Mycologist with ID " + mycologistId + " not found.");
            return;
        }
        
        Tekton tekton = tektonsWithIds.get(tektonId);
        if (tekton == null) {
            System.out.println("Tekton with ID " + tektonId + " not found.");
            return;
        }

        Hyphae hyphae = hyphaesWithIds.get(hyphaeOrMyceliumId);
        if (hyphae != null) {
            mycologist.growHyphaeToTekton(hyphae, tekton);
            System.out.println("Hyphae with ID " + hyphaeOrMyceliumId + " added to Tekton with ID " + tektonId);
        } else {
            Mycelium mycelium = myceliumsWithIds.get(hyphaeOrMyceliumId);
            if (mycelium != null) {
                mycologist.growHyphaeOnTekton(mycelium);
                System.out.println("Mycelium with ID " + hyphaeOrMyceliumId + " added to Tekton with ID " + tektonId);
            } else {
                System.out.println("No Hyphae or Mycelium found with ID " + hyphaeOrMyceliumId);
            }
        }
    }

    private void listHyphae(String string) {
        switch(string){
            case "all":
                for (Tekton tekton : tektonsWithIds.values()) {
                    for (Hyphae hyphae : tekton.getHyphaes()) {
                        System.out.println(hyphae);
                    }
                }
                break;
            case "tektonID":
                Tekton tempTekton = tektonsWithIds.get(Integer.parseInt(string));
                for (Hyphae hyphae : tempTekton.getHyphaes()) {
                    System.out.println(hyphae);
                }
                break;
            default:
                System.out.println("Invalid input");
                break;
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
                    roundElapsed();
                    break;
                case "addInsect":
                    addInsect(Integer.parseInt(inputStrings[1]), Integer.parseInt(inputStrings[2]));
                    break;
                case "addMycelium":
                    addMycelium(Integer.parseInt(inputStrings[1]), Integer.parseInt(inputStrings[2]));
                    break;
                case "moveInsect":
                    moveInsect(Integer.parseInt(inputStrings[1]), Integer.parseInt(inputStrings[2]));
                    break;
                case "listInsects":
                    listInsects(inputStrings[1]);
                    break;
                case "listHyphae":
                    listHyphae(inputStrings[1]);
                    break;
                case "addSpore":
                    addSpore(Integer.parseInt(inputStrings[1]), inputStrings[2]);
                    break;
                case "addHyphae":
                    addHyphae(Integer.parseInt(inputStrings[1]), Integer.parseInt(inputStrings[2]), Integer.parseInt(inputStrings[3]));
                    break;
                default:
                    break;
            }
               
        } while (!inputStrings[0].equals("Exit"));
    }
}
