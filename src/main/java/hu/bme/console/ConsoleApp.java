package hu.bme.console;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.spi.AbstractResourceBundleProvider;

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
        /*gameController.getTektonManager().getTektons().forEach(tekton -> {
            System.out.println(tekton);
            tekton.getConnectedNeighbours().forEach(neighbour -> {
                System.out.println("  " + neighbour);
            });
        }); */
        
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

    public void run(){
        System.out.println("Baszodj meg");
        String[] inputStrings;
        do {
            inputStrings = getInput().split(" ");
            for(int i = 1; i < inputStrings.length; i++) {
                inputStrings[i] = inputStrings[i].toLowerCase();
            }
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
                case "addTekton":
                    addTekton(inputStrings[1]);
                    break;
                default:
                    break;
            }
               
        } while (!inputStrings[0].equals("Exit"));
    }
}
