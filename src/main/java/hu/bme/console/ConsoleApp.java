package hu.bme.console;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import hu.bme.core.GameController;
import hu.bme.core.Ticker;
import hu.bme.fungi.Hyphae;
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
    public static GameController gameController = new GameController(); 
    public static InsectManager insectManager = InsectManager.getInstance();
    public static MycologistManager mycologistManager = MycologistManager.getInstance();
    public static HashMap<String, Entomologist> entomologistWithIds = new HashMap<>();
    public static HashMap<String, Mycologist> mycologistWithIds = new HashMap<>();
    public static HashMap<String, Tekton> tektonsWithIds = new HashMap<>();
    public static HashMap<String, Mycelium> myceliumsWithIds = new HashMap<>();
    public static HashMap<String, Insect> insectsWithIds = new HashMap<>();
    public static HashMap<String, Spore> sporesWithIds = new HashMap<>();
    public static HashMap<String, Hyphae> hyphaesWithIds = new HashMap<>();
    public static Ticker ticker = Ticker.getInstance();
    
    public ConsoleApp() {
       
    }
    
    
    public static String generateId(String prefix, int id) {
        return prefix + id;
    }

    public String getInput(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public void addPlayer(String player) {
        if(player.toLowerCase().equals("entomologist")){
            Entomologist entomologist = new Entomologist();
            insectManager.addEntomologist(entomologist);

            entomologistWithIds.put(generateId("E", entomologistWithIds.size()), entomologist);

            System.out.println("ID: E" + (entomologistWithIds.size()-1) + " entomologist added");
        } else if(player.toLowerCase().equals("mycologist")){
            Mycologist mycologist = new Mycologist();
            mycologistManager.addMycologist(mycologist);
            
            mycologistWithIds.put(generateId("Myc", mycologistWithIds.size()), mycologist);

            System.out.println("ID: Myc" + (mycologistWithIds.size() - 1) + " mycologist added");
        }
    }

    void listPlayers() {

        if(entomologistWithIds.size() == 0 && mycologistWithIds.size() == 0){
            System.out.println("No player");
            return;
        }
        for (HashMap.Entry<String, Entomologist> entry : entomologistWithIds.entrySet()) {
            String id = entry.getKey();
            Entomologist entomologist = entry.getValue();
            System.out.println("ID: " + id + ", Entomologist: " + entomologist);
        }
        for(HashMap.Entry<String, Mycologist> entry : mycologistWithIds.entrySet()) {
            String id = entry.getKey();
            Mycologist mycologist = entry.getValue();
            System.out.println("ID: " + id + ", Mycologist: " + mycologist);
        }
    }

    public void roundElapsed() {
        ticker.tick();
        System.out.println("1 round has passed");
    
        // Step 1: Collect all existing Hyphae from Mycologists
        List<Hyphae> existingHyphae = new ArrayList<>();
        for (Mycologist mycologist : mycologistManager.getMycologists()) {
            existingHyphae.addAll(mycologist.getHyphaes());
        }
    
        // Step 2: Collect all existing Hyphae from Tektons
        for (Tekton tekton : tektonsWithIds.values()) {
            existingHyphae.addAll(tekton.getHyphaes());
        }
    
        // Step 3: Collect IDs of Hyphae to remove
        List<String> hyphaeIdsToRemove = new ArrayList<>();
        for (Map.Entry<String, Hyphae> entry : hyphaesWithIds.entrySet()) {
            String hyphaeId = entry.getKey();
            Hyphae hyphae = entry.getValue();
    
            if (!existingHyphae.contains(hyphae)) {
                hyphaeIdsToRemove.add(hyphaeId);
            }
        }
    
        // Step 4: Remove Hyphae after iteration
        for (String hyphaeId : hyphaeIdsToRemove) {
            hyphaesWithIds.remove(hyphaeId);
            System.out.println("Hyphae with ID " + hyphaeId + " has been removed from hyphaesWithIds.");
        }
    
        // Step 5: Clean up Tektons (remove references to non-existent Hyphae)
        for (Tekton tekton : tektonsWithIds.values()) {
            tekton.getHyphaes().removeIf(hyphae -> !hyphaesWithIds.containsValue(hyphae));
        }
    }

    public void addInsect(String tektonId, String entomologistId) {
        Tekton tekton = tektonsWithIds.get(tektonId);
        Entomologist entomologist = entomologistWithIds.get(entomologistId);

        if(entomologist == null || tekton == null) {
            System.out.println("Entomologist or Tekton doesn't exist");
        }
        Insect insect = new Insect();
        insect.setEntomologist(entomologist);
        insect.setCurrentTekton(tekton);
        entomologist.addInsect(insect);
        insectsWithIds.put(generateId("I", insectsWithIds.size()), insect);

        System.out.println("I" + (insectsWithIds.size()-1) + " insect added to " + entomologistId + " entomologist and to " + tektonId + " tekton");
    }

    public void addMycelium(String tektonId, String mycologistId) {
        Tekton tekton = tektonsWithIds.get(tektonId);
        Mycologist mycologist = mycologistWithIds.get(mycologistId);
    
        if (tekton == null) {
            System.out.println("Tekton with ID " + tektonId + " not found.");
            return;
        }
    
        if (mycologist == null) {
            System.out.println("Mycologist with ID " + mycologistId + " not found.");
            return;
        }
    
        Mycelium mycelium = new Mycelium(tekton);
        tekton.addMycelium(mycelium);
        mycologist.addMycelium(mycelium);
    
        String myceliumId = generateId("M", myceliumsWithIds.size());
        myceliumsWithIds.put(myceliumId, mycelium);
    
        System.out.println(myceliumId + " mycelium added to " + mycologistId + " mycologist and to " + tektonId + " tekton");
    }

    public void moveInsect(String insectId, String tektonId){
        if(!(insectsWithIds.containsKey(insectId) && tektonsWithIds.containsKey(tektonId))){
            System.out.println("Invalid insect or tekton ID");
            return;
        } else {

            Insect insect = insectsWithIds.get(insectId);
            Tekton tekton = tektonsWithIds.get(tektonId);
            insect.move(tekton);
            System.out.println(insectId + " moves to " + tektonId + " tekton");
        }
    }
    
    public void listInsects(String entomologistID){
        if(!entomologistWithIds.containsKey(entomologistID)){
            System.out.println("Invalid entomologist ID");

        } else {
            Entomologist entomologist = entomologistWithIds.get(entomologistID);
            List<Insect> insects = entomologist.getInsects();
            for(Insect insect : insects){
                System.out.println(insect);
            }
        }
    }

    public void addTekton(String tektonType) {
        switch (tektonType) {
            case "absorbing":
                AbsrobingTekton absrobingTekton = new AbsrobingTekton();
                tektonsWithIds.put(generateId("T" , tektonsWithIds.size()), absrobingTekton);
                gameController.getTektonManager().addTekton(absrobingTekton);
                System.out.println("T" + (tektonsWithIds.size() - 1) + " absorbing tekton added");
                break;
            case "multitype":
                MultiTypeTekton multiTypeTekton = new MultiTypeTekton();
                tektonsWithIds.put(generateId("T", tektonsWithIds.size()), multiTypeTekton);
                gameController.getTektonManager().addTekton(multiTypeTekton);
                System.out.println("T" + (tektonsWithIds.size() - 1) + " multiType tekton added");
                break;
            case "singletype":
                SingleTypeTekton singleTypeTekton = new SingleTypeTekton();
                tektonsWithIds.put(generateId("T", tektonsWithIds.size()), singleTypeTekton);
                gameController.getTektonManager().addTekton(singleTypeTekton);
                System.out.println("T" + (tektonsWithIds.size() - 1) + " singleType tekton added");
                break;
            case "keeper":
                KeeperTekton keeperTekton = new KeeperTekton();
                tektonsWithIds.put(generateId("T", tektonsWithIds.size()), keeperTekton);
                gameController.getTektonManager().addTekton(keeperTekton);
                System.out.println("T" + (tektonsWithIds.size() - 1) + " keeper tekton added");
                break;
            case "myceliumfree":
                MyceliumFreeTekton myceliumFreeTekton = new MyceliumFreeTekton();
                tektonsWithIds.put(generateId("T", tektonsWithIds.size()), myceliumFreeTekton);
                gameController.getTektonManager().addTekton(myceliumFreeTekton);
                System.out.println("T" + (tektonsWithIds.size() - 1) + " myceliumFree tekton added");
                break;
            default:
                System.out.println("Invalid type of tekton");
                break;
        }
    }

    public void addSpore(String tektonId, String sporeType) {
        Tekton tekton = tektonsWithIds.get(tektonId);

        switch (sporeType) {
            case "DefensiveSpore":
            case "defensive":
                DefensiveSpore spore = new DefensiveSpore();
                tekton.addSpore(spore);
                sporesWithIds.put(generateId("S", sporesWithIds.size()), spore);
                break;
            case "StunSpore":
            case "stun":
            case "stunning":
                StunSpore stunSpore = new StunSpore();
                tekton.addSpore(stunSpore);
                sporesWithIds.put(generateId("S", sporesWithIds.size()), stunSpore);
                break;
            case "SlowingSpore":
            case "slowing":
                SlowingSpore slowingSpore = new SlowingSpore();
                tekton.addSpore(slowingSpore);
                sporesWithIds.put(generateId("S", sporesWithIds.size()), slowingSpore);
                break;
            case "SpeedBoostSpore":
            case "speed":
            case "speedboost":
            case "speedBoost":
                SpeedBoostSpore speedBoostSpore = new SpeedBoostSpore();
                tekton.addSpore(speedBoostSpore);
                sporesWithIds.put(generateId("S", sporesWithIds.size()), speedBoostSpore);
                break;
            case "CloneSpore":
            case "clone":
                CloneSpore cloneSpore = new CloneSpore();
                tekton.addSpore(cloneSpore);
                sporesWithIds.put(generateId("S", sporesWithIds.size()), cloneSpore);
                break;
            default:
                return;
        }

        System.out.println(generateId("S", sporesWithIds.size() - 1) + " spore added to " + tektonId + " tekton");
    }

    public void addHyphae(String hyphaeOrMyceliumId, String tektonId, String mycologistId) {
        // Step 1: Retrieve the Mycologist
        Mycologist mycologist = mycologistWithIds.get(mycologistId);
        if (mycologist == null) {
            System.out.println("Mycologist with ID " + mycologistId + " not found.");
            return;
        }
    
        // Step 2: Retrieve the Tekton
        Tekton tekton = tektonsWithIds.get(tektonId);
        if (tekton == null) {
            System.out.println("Tekton with ID " + tektonId + " not found.");
            return;
        }
    
        // Step 3: Check if the ID refers to a Hyphae
        Hyphae argumentHyphae = hyphaesWithIds.get(hyphaeOrMyceliumId);
        if (argumentHyphae == null) {
            // Step 4: Handle Mycelium Case
            Mycelium argumentMycelium = myceliumsWithIds.get(hyphaeOrMyceliumId);
            if (argumentMycelium == null) {
                System.out.println("Invalid ID: " + hyphaeOrMyceliumId + " is neither a Hyphae nor a Mycelium.");
                return;
            }
    
            // Grow a new Hyphae on the same Tekton as the Mycelium
            if (tekton == argumentMycelium.getCurrentTekton()) {
                Hyphae newHyphae = new Hyphae(tekton);
                mycologist.addHyphae(newHyphae);

                tekton.addHyphae(newHyphae);
                newHyphae.setOwner(mycologist);
                newHyphae.addMycelium(argumentMycelium);
                argumentMycelium.addHyphae(newHyphae);
    
                String generatedId = generateId("H", hyphaesWithIds.size());
                hyphaesWithIds.put(generatedId, newHyphae);
    
                System.out.println(generatedId + " hyphae added to " + mycologistId);
                return;
            } else {
                System.out.println("Tekton does not match the Mycelium's current Tekton.");
                return;
            }
        } else {
            // Step 5: Handle Hyphae Case
            if (argumentHyphae.getCurrentTekton().size() == 1) {
                // Case: Hyphae with one current Tekton
                Tekton connectedTekton = argumentHyphae.getCurrentTekton().get(0);
    
                if (tekton.getNeighbours().contains(connectedTekton)) {
                    Hyphae newHyphae = new Hyphae(connectedTekton);
                    mycologist.addHyphae(newHyphae);

                    newHyphae.addCurrentTekton(tekton);
                    newHyphae.setOwner(mycologist);
                    newHyphae.addHyphae(argumentHyphae);
                    argumentHyphae.addHyphae(newHyphae);
    
                    String generatedId = generateId("H", hyphaesWithIds.size());
                    hyphaesWithIds.put(generatedId, newHyphae);
    
                    System.out.println(generatedId + " hyphae added, spanning from " +
                        getTektonId(connectedTekton) + " to " + tektonId);
    
                    // Connect the Tektons
                    tekton.connectToTekton(connectedTekton);
                    System.out.println(tektonId + " connected to " + getTektonId(connectedTekton) + " via " + generatedId);
                    return;
                } else {
                    System.out.println("Tekton " + tektonId + " is not a neighbor of " + getTektonId(connectedTekton));
                    return;
                }
            } else if (argumentHyphae.getCurrentTekton().size() == 2) {
                // Case: Hyphae with two current Tektons
                Tekton secondTekton = argumentHyphae.getCurrentTekton().get(1);
    
                if (tekton == secondTekton) {
                    Hyphae newHyphae = new Hyphae(tekton);
                    mycologist.addHyphae(newHyphae);

                    tekton.addHyphae(newHyphae);
                    newHyphae.setOwner(mycologist);
                    argumentHyphae.addHyphae(newHyphae);
                    newHyphae.addHyphae(argumentHyphae);
    
                    String generatedId = generateId("H", hyphaesWithIds.size());
                    hyphaesWithIds.put(generatedId, newHyphae);
    
                    System.out.println(generatedId + " hyphae added to " + tektonId);
                    return;
                } else {
                    System.out.println("Tekton " + tektonId + " does not match the second Tekton of the Hyphae.");
                    return;
                }
            } else {
                System.out.println("Invalid Hyphae state: Hyphae has unexpected number of current Tektons.");
                return;
            }
        }
    }

    public void listHyphae(String string) {
        switch(string){
            case "all":
                for (Map.Entry<String, Hyphae> entry : hyphaesWithIds.entrySet()) {
                    System.out.println(entry.getKey() + " " + entry.getValue());
                }
                break;
            default:
                Tekton tempTekton = tektonsWithIds.get(string);
                if (tempTekton != null) {
                    for (Hyphae hyphae : tempTekton.getHyphaes()) {
                        for (Map.Entry<String, Hyphae> entry : hyphaesWithIds.entrySet()) {
                            if (entry.getValue() == hyphae) {
                                System.out.println(entry.getKey() + " " + entry.getValue());
                                break;
                            }
                        }
                    }
                } else {
                    System.out.println("No Tekton found with ID: " + string);
                }
                break;
        }
    }

    public void printMap(){  
        for (Map.Entry<String, Tekton> entry : tektonsWithIds.entrySet()) {
            String tektonId = entry.getKey();
            Tekton tekton = entry.getValue();
    
            System.out.println("-" + tektonId + " " + tekton + ":");
    
            List<Tekton> neighbours = tekton.getNeighbours();
            for (Tekton neighbour : neighbours) {
                String neighbourId = getKeyByValue(neighbour);
    
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

    public static String getKeyByValue(Tekton value) {
        for (Map.Entry<String, Tekton> entry : tektonsWithIds.entrySet()) {
            if (entry.getValue() == value) {
                return entry.getKey();
            }
        }
        return null;
    }

    public void listSpore(String tektronid) {
        String mode = tektronid.toLowerCase();
        if(mode.equals("all")){
            for (Map.Entry<String, Tekton> entry : tektonsWithIds.entrySet()) {
                if(!entry.getValue().getSpores().isEmpty()){
                    System.out.println(entry.getKey() + " contains these spores: ");
                    entry.getValue().getSpores().forEach(spore -> {
                        for(Map.Entry<String, Spore> entry2 : sporesWithIds.entrySet()){
                            if(entry2.getValue() == spore){
                                System.out.println("\t" + entry2.getKey() + " spore: " + spore);
                            }
                        }
                    });
                }   
            }
        } else {
            Tekton tekton = tektonsWithIds.get(tektronid);
            if(tekton != null){
                System.out.println(tektronid + " contains these spores: ");
                tekton.getSpores().forEach(spore -> {
                    System.out.println("\t"+spore);
                });
            }
        }
    }

    public void growHyphaeFromHyphae(String tektronid, String mycologistid, String hyphaeId) {
        Tekton tekton = tektonsWithIds.get(tektronid);
    
        
        Mycologist mycologist = mycologistWithIds.get(mycologistid);
        Hyphae hyphae = hyphaesWithIds.get(hyphaeId);
        mycologist.growHyphaeToTekton(hyphae, tekton);
    }

    public void growHyphaefromMycelium(String mycologistid, String myceliumId) {
     
        
        Mycologist mycologist = mycologistWithIds.get(mycologistid);
        Mycelium mycelium = myceliumsWithIds.get(myceliumId);
       
        Hyphae newHyphae = new Hyphae();
        mycologist.growHyphaeOnTekton(mycelium, newHyphae);
        newHyphae.addCurrentTekton(mycelium.getCurrentTekton());
        String newId = generateId("H", hyphaesWithIds.size());
        hyphaesWithIds.put(newId, newHyphae);
        System.out.println(newId + " hyphae grown from mycelium " + myceliumId);
    }

    public void addTektonNeighbour(String tektonId, List<String> neighbourIds) {
        // Ensure the Tekton ID is uppercase
        tektonId = tektonId.toUpperCase();
    
        // Get the main Tekton
        Tekton tekton = tektonsWithIds.get(tektonId);
        if (tekton == null) {
            System.out.println("Tekton with ID " + tektonId + " does not exist.");
            return;
        }
    
        // Process each neighbour ID
        for (String neighbourId : neighbourIds) {
            neighbourId = neighbourId.toUpperCase(); // Ensure uppercase
            Tekton neighbour = tektonsWithIds.get(neighbourId);
    
            if (neighbour == null) {
                System.out.println("Tekton with ID " + neighbourId + " does not exist.");
                continue; // Skip invalid neighbours
            }
    
            // Add neighbours bidirectionally
            tekton.addNeighbour(neighbour);
            neighbour.addNeighbour(tekton);
        }
    
        System.out.println("Tekton neighbours added for " + tektonId);
    }

    public void releaseSpore(String MyceliumID){
        Mycelium mycelium = myceliumsWithIds.get(MyceliumID);
        if(mycelium == null){
            System.out.println("No mycelium with this ID");
            return;
        } else {
            mycelium.releaseSpores();
            System.out.println(MyceliumID + " mycelium released spores.");
        }
    }

    public void listNeighbour(String id){
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
                    System.out.println("- " + getTektonId(t) + "tekton, is not connected by hyphae");
                }
               
            }
        }
    }

    public void eatSpore(String entimologistID, String insectId, String sporeId){
        if(!insectsWithIds.containsKey(insectId)){
            System.out.println("Invalid insect ID");
            return;
        } else {
            Insect insect = insectsWithIds.get(insectId);
            Entomologist entomologist = entomologistWithIds.get(entimologistID);
            Spore spore = sporesWithIds.get(sporeId);
            boolean sporeFoundOnTekton = gameController.getTektonManager().getTektons().stream()
                .anyMatch(tekton -> tekton.getSpores().contains(spore) && insect.getCurrentTekton().equals(tekton));

            if (sporeFoundOnTekton) {
                insect.eatSpore(spore);
                System.out.println(insectId + " insect ate " + sporeId + " spore, effect applied");
            } else {
                System.out.println("Spore " + sporeId + " is not on the same Tekton as insect " + insectId);
            }
        }
    }

    public void upgradeMycelium(String mycologistId , String myceliumId) {
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

    public void growNewMycelium(String mycologistID, String hyphaeID) {
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
        if(mycologist.getMyceliums().size() == 0){
            System.out.println("There is no mycologist here");
            return;
        } 
        Mycelium mycelium = mycologist.getMyceliums().get(mycologist.getMyceliums().size() - 1);
        String newid = "M" + (myceliumsWithIds.size());
        myceliumsWithIds.put(newid, mycelium);
        System.out.println(newid + " mycelium grown on "+ getTektonId(hyphae.getCurrentTekton().get(0)) + " tekton");
    }


    public String getTektonId(Tekton tekton) {
        for (HashMap.Entry<String, Tekton> entry : tektonsWithIds.entrySet()) {
            if (entry.getValue().equals(tekton)) {
                return entry.getKey(); // Return the ID if the Tekton matches
            }
        }
        return null; // Return null if the Tekton is not found
    }

    public void cutHyphae(String entomologistID, String insectID, String hyphaeID){
        Insect insect = insectsWithIds.get(insectID);
        Entomologist entomologist = entomologistWithIds.get(entomologistID);
        Hyphae hyphae = hyphaesWithIds.get(hyphaeID);

        if(insect == null){
            System.out.println("Invalid insect ID");
            return;
        } else if(entomologist == null){
            System.out.println("Invalid entomologist ID");
            return;
        } else if(hyphae == null){
            System.out.println("Invalid hyphae ID");
            return;
        } else {
            insect.cutHyphae(hyphae);
            System.out.println(insectID + " insect cut " + hyphaeID);
        }

    }

    public static void setMyceliumType(String mycologistID, String spore) {
        Mycologist mycologist = mycologistWithIds.get(mycologistID);

        if(mycologist == null){
            System.out.println("Invalid Mycologist ID");
            return;
        } 


        switch (spore) {
            case "clone":
            case "cloning":
            case "clonespore":
                mycologist.chooseSpore(5);
                System.out.println("Clone spore chosen");
                break;
            case "speed":
            case "speedboost":
            case "speedboostspore":
                mycologist.chooseSpore(3);
                System.out.println("Speed boost spore chosen");
                break;
            case "slowing":
            case "slowingspore":
                mycologist.chooseSpore(4);
                System.out.println("Slowing spore chosen");
                break;
            case "stun":
            case "stunning":
            case "stunspore":
                mycologist.chooseSpore(1);
                System.out.println("Stun spore chosen");
                break;
            case "defensive":
            case "defensivespore":
                mycologist.chooseSpore(2);
                System.out.println("Defensive spore chosen");
                break;
            default:
                System.out.println("Invalid spore type");
                break;
        }
    }

    public void eatInsect(String mycologistID, String insectID){
        Mycologist mycologist = mycologistWithIds.get(mycologistID);
        Insect insect = insectsWithIds.get(insectID);

        if(mycologist == null){
            System.out.println("Invalid Mycologist ID");
            return;
        } else if(insect == null){
            System.out.println("Invalid Insect ID");
            return;
        } else {
            mycologist.eatInsect(insect);
            System.out.println(mycologistID + " mycologist ate " + insectID + " insect.");
        }
    }

    public void putSporeToMycelium(String mycologistID, String myceliumID){
        Mycelium mycelium = myceliumsWithIds.get(myceliumID);
        Mycologist mycologist = mycologistWithIds.get(mycologistID);
        if(mycelium == null || mycologist == null){
            System.out.println("No mycelium or mycologist with this ID");
        } else {
            mycologist.growSpore(mycelium);
            System.out.println("Spore put in "+myceliumID + " mycelium");
        }
    }

    public void breakApart(String tektonID) {
        Tekton tekton = tektonsWithIds.get(tektonID);
        if (tekton == null) {
            System.out.println("Tekton with ID " + tektonID + " not found.");
            return;
        }
    
        // Break apart the Tekton
        List<Tekton> newtektons = gameController.getTektonManager().breakApart(tekton);
        if(newtektons == null){
            System.out.println("There are no breakable tektons.");
            return;
        }

        // Remove the Tekton from the map
        tektonsWithIds.remove(tektonID);
        boolean isfirst = true;
        System.out.println(tektonID + " has break apart.");
        for (Tekton newTekton : newtektons) {
            String newTektonId = generateId("T", tektonsWithIds.size());
            tektonsWithIds.put(newTektonId, newTekton);
            if(isfirst)System.out.print(newTektonId+ " and ");
            else System.out.print(newTektonId + " ");
        }
        System.out.println("born");
       
    }
}

