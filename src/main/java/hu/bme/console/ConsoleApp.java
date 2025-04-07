package hu.bme.console;

import java.nio.channels.MulticastChannel;
import java.util.ArrayList;
import java.util.Collections;
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



    private Integer getTektonId(Tekton tekton) {
        for (HashMap.Entry<Integer, Tekton> entry : tektonsWithIds.entrySet()) {
            if (entry.getValue().equals(tekton)) {
                return entry.getKey(); // Return the ID if the Tekton matches
            }
        }
        return null; // Return null if the Tekton is not found
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
    
    private void printMap(){
        gameController.getTektonManager().getTektons().forEach(tekton -> {
            System.out.println(tekton);
            tekton.getConnectedNeighbours().forEach(neighbour -> {
                System.out.println("  " + neighbour);
            });
        }); 
    }

    private void listSpore(String tektronid) {
        int id = Integer.parseInt(tektronid);
        Tekton tekton = tektonManager.getTektons().get(id);
        System.out.println(tekton.getSporeCount());
        tekton.getSpores().forEach(spore -> {
            System.out.println(spore);
        });
    }

    private void growHyphaeFromHyphae(String tektronid, String sporeid, String mycologistid, String hyphaeId) {
        int id = Integer.parseInt(tektronid);
    
        Tekton tekton = tektonManager.getTektons().get(id);
        Mycologist mycologist = mycologistWithIds.get(Integer.parseInt(mycologistid));
        Hyphae hyphae = tekton.getHyphaes().get(Integer.parseInt(hyphaeId));
        mycologist.growHyphaeToTekton(hyphae, tekton);
    }

    //map-hoz hozzaadni
    private void growHyphaefromMycelium(String tektronid, String sporeid, String mycologistid, String myceliumId) { 
        int id = Integer.parseInt(tektronid);
        Mycologist mycologist = mycologistWithIds.get(Integer.parseInt(mycologistid));
        Mycelium mycelium = mycologist.getMyceliums().get(Integer.parseInt(myceliumId));
        mycologist.growHyphaeOnTekton(mycelium);
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

    private void addTekton(String type){
        Tekton tekton = null;
        switch(type){
            case "absorbing":
                 tekton = new AbsrobingTekton();
                break;
            case "multitype":
                 tekton = new MultiTypeTekton();
                break;
            case "singletype":
                tekton = new SingleTypeTekton();
                break;
            case "keeper":
                tekton = new KeeperTekton();
                break;
            case "myceliumfree":
                tekton = new MyceliumFreeTekton();
                break;
            default:
                System.out.println("Invalid type");
                return;
        }
        Integer newid = Collections.max(tektonsWithIds.keySet()) + 1;
    
        tektonsWithIds.put(newid, tekton);
        tektonManager.addTekton(tekton);
        System.out.println(newid + " "+ type +" tekton added");
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
                default:
                    break;
            }
               
        } while (!inputStrings[0].equals("Exit"));
    }
}
