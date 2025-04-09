package hu.bme.console;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import hu.bme.tekton.AbsrobingTekton;
import hu.bme.tekton.KeeperTekton;
import hu.bme.tekton.MultiTypeTekton;
import hu.bme.tekton.MyceliumFreeTekton;
import hu.bme.tekton.SingleTypeTekton;
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
        Tekton tekton = tektonsWithIds.get(tektonId);
        Mycelium<? extends Spore> mycelium = new Mycelium<>(tekton);
       
        Mycologist mycologist = mycologistWithIds.get(mycologistId);

        if(tekton != null  && mycelium != null) {
            System.out.println("Tekton with ID: " + tektonId + " or myceium with ID");
            tekton.addMycelium(mycelium);
        }

        mycologist.addMycelium(mycelium);

       myceliumsWithIds.put(id, mycelium);
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

    private void addTekton(String tektonType) {
        switch (tektonType) {
            case "absorbing":
                AbsrobingTekton absrobingTekton = new AbsrobingTekton();
                tektonsWithIds.put(id, absrobingTekton);
                gameController.getTektonManager().addTekton(absrobingTekton);
                System.out.println(id++ + " absorbing tekton added to the map");
                break;
            case "multitype":
                MultiTypeTekton multiTypeTekton = new MultiTypeTekton();
                tektonsWithIds.put(id, multiTypeTekton);
                gameController.getTektonManager().addTekton(multiTypeTekton);
                System.out.println(id++ + " multiType tekton added");
                break;
            case "singletype":
                SingleTypeTekton singleTypeTekton = new SingleTypeTekton();
                tektonsWithIds.put(id, singleTypeTekton);
                gameController.getTektonManager().addTekton(singleTypeTekton);
                System.out.println(id++ + " multiType tekton added");
                break;
            case "keeper":
                KeeperTekton keeperTekton = new KeeperTekton();
                tektonsWithIds.put(id, keeperTekton);
                gameController.getTektonManager().addTekton(keeperTekton);
                System.out.println(id++ + " keeper tekton added");
                break;
            case "myceliumfree":
                MyceliumFreeTekton myceliumFreeTekton = new MyceliumFreeTekton();
                tektonsWithIds.put(id, myceliumFreeTekton);
                gameController.getTektonManager().addTekton(myceliumFreeTekton);
                System.out.println(id++ + " myceliumFree tekton added");
                break;
            default:
                System.out.println("Invalid type of tekton");
                break;
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
            case "tektonid":
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
    private void printMap(){  
        for (Map.Entry<Integer, Tekton> entry : tektonsWithIds.entrySet()) {
            Integer tektonId = entry.getKey();
            Tekton tekton = entry.getValue();
    
            System.out.println("-" + tektonId + " " + tekton + ":");
    
            List<Tekton> neighbours = tekton.getNeighbours();
            for (Tekton neighbour : neighbours) {
                Integer neighbourId = getKeyByValue(neighbour);
    
                if (neighbourId != null) {
                    boolean isConnected = tekton.getConnectedNeighbours().contains(neighbour);
                    String connection = isConnected ? "is connected by hyphae" : "is not connected by hyphae";
                    System.out.println("\t-" + neighbourId + " tekton, " + connection);
                } else {
                    System.out.println("\t-unknown tekton (not in map)");
                }
            }
        }
    }

    private static Integer getKeyByValue(Tekton value) {
        for (Map.Entry<Integer, Tekton> entry : tektonsWithIds.entrySet()) {
            if (entry.getValue() == value) {
                return entry.getKey();
            }
        }
        return null;
    }

    private void listSpore(String tektronid) {
        int id = Integer.parseInt(tektronid);
        Tekton tekton = gameController.getTektonManager().getTektons().get(id);
        System.out.println(tekton.getSporeCount());
        tekton.getSpores().forEach(spore -> {
            System.out.println(spore);
        });
    }

    private void growHyphaeFromHyphae(String tektronid, String sporeid, String mycologistid, String hyphaeId) {
        int id = Integer.parseInt(tektronid);
    
        Tekton tekton = gameController.getTektonManager().getTektons().get(id);
        Mycologist mycologist = mycologistWithIds.get(Integer.parseInt(mycologistid));
        Hyphae hyphae = tekton.getHyphaes().get(Integer.parseInt(hyphaeId));
        mycologist.growHyphaeToTekton(hyphae, tekton);
       }

       private void growHyphaefromMycelium(String tektronid, String sporeid, String mycologistid, String myceliumId) {
        int id = Integer.parseInt(tektronid);
       
        
        Mycologist mycologist = mycologistWithIds.get(Integer.parseInt(mycologistid));
        Mycelium mycelium = mycologist.getMyceliums().get(Integer.parseInt(myceliumId));
        mycologist.growHyphaeOnTekton(mycelium);
    }

    private void loadMap(String file) {
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

    private void addTektonNeighbour(Tekton tekton, List<Tekton> tektons){
        if(tekton == null || tektons == null){
            System.out.println("Tekton or tektons is null");
            return;
        }
        for(Tekton t : tektons){
            if(t == null){
                System.out.println("Tekton is null");
                return;
            }
            tekton.addNeighbour(t);
            t.addNeighbour(tekton);
        }
        System.out.println("Tekton neighbours added");
    }

    private void releaseSpore(String MyceliumID){
        Mycelium mycelium = myceliumsWithIds.get(Integer.parseInt(MyceliumID));
        if(mycelium == null){
            System.out.println("No mycelium with this ID");
            return;
        } else {
            mycelium.releaseSpores();
            System.out.println(MyceliumID + " mycelium released spores.");
        }
    }

    private void listNeighbour(int id){
        Tekton tekton = tektonsWithIds.get(id);
        if(tekton == null){
            System.out.println("No tekton with this ID");
            return;
        } else{
            System.out.println("All neighbours of "+ id + " tekton:");
            for (Tekton t : tekton.getNeighbours()) {
                if(tekton.isConnectedTo(t)){
                    System.out.println("- " + getTektonId(t) + "tekton, is connected by hyphae");
                } else {
                    System.out.println("- " + getTektonId(t) + "tekton, is notconnected by hyphae");
                }
               
            }
        }
    }

    private void eatSpore(Integer entimologistID, Integer insectId, Integer sporeId){
        if(!insectsWithIds.containsKey(insectId)){
            System.out.println("Invalid insect ID");
            return;
        } else {
            Insect insect = insectsWithIds.get(insectId);
            Entomologist entomologist = entomologistWithIds.get(entimologistID);
            Spore spore = sporesWithIds.get(sporeId);
            if(entomologist.getInsects().contains(spore)){
                insect.eatSpore(spore);
                System.out.println(insectId + " insect ate " + sporeId+" spore, effect applied");
            } else {
                System.out.println("Insect " + insectId + " does not belong to entomologist " + entimologistID);
            }
        }
    }

    private void upgradeMycelium(Integer mycologistId , Integer myceliumId) {
        Mycelium mycelium = myceliumsWithIds.get(myceliumId);
        Mycologist mycologist = mycologistWithIds.get(mycologistId);
        if(mycelium == null){
            System.out.println("No mycelium with this ID");
            return;
        } else {
            if(mycologist == null){
                System.out.println("No mycologist with this ID");
                return;
            } else {
                mycelium.upgrade();
                System.out.println(myceliumId + " mycelium is upgraded, it can release further.");
            }
        }
    }

      private void growNewMycelium(Integer mycologistID, Integer hyphaeID) {
        Mycologist mycologist = mycologistWithIds.get(mycologistID);
        Hyphae hyphae = hyphaesWithIds.get(hyphaeID);
        if (mycologist == null) {
            System.out.println("Mycologist with ID " + mycologistID + " not found.");
            return;
        }
        if (hyphae == null) {
            System.out.println("Hyphae with ID " + hyphaeID + " not found.");
            return;
        }
        if(hyphae.getCurrentTekton().size() != 1){
            System.out.println("Hyphae with ID " + hyphaeID + " is not on a tekton or between two tektons.");
            return;
        }
        mycologist.growMycelium(hyphae, hyphae.getCurrentTekton().get(0));
        Mycelium mycelium = mycologist.getMyceliums().getLast();
        Integer newid = Collections.max(myceliumsWithIds.keySet()) + 1;
        myceliumsWithIds.put(newid, mycelium);
        System.out.println(newid+" mycelium grown on "+ getTektonId(hyphae.getCurrentTekton().get(0)) + " tekton");
    }


    private Integer getTektonId(Tekton tekton) {
        for (HashMap.Entry<Integer, Tekton> entry : tektonsWithIds.entrySet()) {
            if (entry.getValue().equals(tekton)) {
                return entry.getKey(); // Return the ID if the Tekton matches
            }
        }
        return null; // Return null if the Tekton is not found
    }

    private void processCommand(String command) {
        if (command.trim().isEmpty()) return;
    
        String[] inputStrings = command.split(" ");
        for (int i = 1; i < inputStrings.length; i++) {
            inputStrings[i] = inputStrings[i].toLowerCase();
        }
    
        switch (inputStrings[0]) {
            case "listPlayer":
                listPlayers();
                break;
            case "addPlayer":
                addPlayer(command.substring(command.indexOf(" ") + 1));
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
            case "printMap":
                printMap();
                break;
            case "listSpore":
                listSpore(inputStrings[1]);
                break;
            case "growHyphaefromHyphae":
                growHyphaeFromHyphae(inputStrings[1], inputStrings[2], inputStrings[3], inputStrings[4]);
                break;
            case "growHyphaefromMycelium":
                growHyphaefromMycelium(inputStrings[1], inputStrings[2], inputStrings[3], inputStrings[4]);
                break;
            case "addSpore":
                addSpore(Integer.parseInt(inputStrings[1]), inputStrings[2]);
                break;
            case "addHyphae":
                addHyphae(Integer.parseInt(inputStrings[1]), Integer.parseInt(inputStrings[2]), Integer.parseInt(inputStrings[3]));
                break;
            case "listNeighbour":
                listNeighbour(Integer.parseInt(inputStrings[1]));
                break;
            case "eatSpore":
                eatSpore(Integer.parseInt(inputStrings[1]), Integer.parseInt(inputStrings[2]), Integer.parseInt(inputStrings[3]));
                break;
            case "upgradeMycelium":
                upgradeMycelium(Integer.parseInt(inputStrings[1]), Integer.parseInt(inputStrings[2]));
                break;
            case "growNewMycelium":
                growNewMycelium(Integer.parseInt(inputStrings[1]), Integer.parseInt(inputStrings[2]));
                break;
            case "addTekton":
                addTekton(inputStrings[1]);
                break;
            case "addTektonNeighbour": {
                List<Tekton> tektons = new ArrayList<>();
                for (int i = 2; i < inputStrings.length; i++) {
                    tektons.add(tektonsWithIds.get(Integer.parseInt(inputStrings[i])));
                }
                Tekton tekton = tektonsWithIds.get(Integer.parseInt(inputStrings[1]));
                addTektonNeighbour(tekton, tektons);
                break;
            }
            case "loadMap":
                loadMap(inputStrings[1]);
                break;
            case "releaseSpore":
                releaseSpore(inputStrings[1]);
                break;
            case "breakApart":
                Tekton tekton = tektonsWithIds.get(Integer.parseInt(inputStrings[1]));
                tekton.breakApart();
                break;
            default:
                System.out.println("Unknown command: " + inputStrings[0]);
                break;
        }
    }

    public void run() {
        System.out.println("Baszodj meg");
        String input;
        do {
            input = getInput();
            processCommand(input);
        } while (!input.trim().equalsIgnoreCase("Exit"));
    }
       
}

