package hu.bme.view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.DefaultListModel;

import hu.bme.fungi.Mycelium;
import hu.bme.fungi.Mycologist;
import hu.bme.insect.Entomologist;
import hu.bme.insect.Insect;
import hu.bme.managers.FungalManager;
import hu.bme.managers.InsectManager;
import hu.bme.managers.MycologistManager;
import hu.bme.managers.TektonManager;
import hu.bme.tekton.Tekton;

//-------------------------------------------------------------------------------------------------------------
//              Ez a Központi egér eseménykezelős osztály, ha szét akarod szedni a kódot több osztályra, sok sikert.
//              Ha máshogy akarod megoldani akkor légyszíves jelezd hanyadik agyfaszt kapod. Eddigi agyfaszok száma: 2
//-------------------------------------------------------------------------------------------------------------
public class CentralMouseHandler extends MouseAdapter {
    private DefaultListModel<String> commandListModel;
    private Insect selectedInsect; // Az aktuálisan kiválasztott rovar
    private String selectedCommand; // Az aktuálisan kiválasztott parancs
    private Tekton selectedTekton; // Az aktuálisan kiválasztott tekton
    private TektonView tektonView;
    private Object activePlayer; // Az aktuális játékos
    private Mycelium selectedMycelium;

    public void setActivePlayer(Object activePlayer) {
        this.activePlayer = activePlayer;
    }

    public CentralMouseHandler(DefaultListModel<String> commandListModel, TektonView tektonView, Object activePlayer) {
        this.tektonView = tektonView;
        this.commandListModel = commandListModel;
        selectedCommand = null;
        this.activePlayer = activePlayer;
        System.out.println("InsectMouseHandler inicializálva!");
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        TektonManager manager = TektonManager.getInstance();
        tektonView.setHoveredTekton(null); // Alapértelmezésben nincs kijelölt Tekton

        // Ellenőrizzük, hogy az egér egy Tekton fölött van-e
        for (Tekton tekton : manager.getTektons()) {
            int x = tekton.getX();
            int y = tekton.getY();
            int radius = 50;

            // Ha az egér a Tekton körén belül van
            if (e.getX() >= x - radius / 2 && e.getX() <= x + radius / 2 &&
                    e.getY() >= y - radius / 2 && e.getY() <= y + radius / 2) {
                tektonView.setHoveredTekton(tekton);
                break;
            }
        }

        tektonView.repaint(); // Újrarajzolás
    }

    public Tekton getSelectedTekton() {
        return selectedTekton;
    }

    public Insect getSelectedInsect() {
        return selectedInsect;
    }

    public String getSelectedCommand() {
        return selectedCommand;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();
        System.out.println("parancs: " + selectedCommand);
        // Ellenőrizzük, hogy a kattintás egy rovarra esett-e
        if (InsectManager.getInstance().geEntomologists().contains(activePlayer)) {

            if (selectedCommand == null || selectedCommand.equals("move")) {
                if (checkInsectSelection(mouseX, mouseY)) {
                    return;
                }
            }
        }

        // Ellenőrizzük, hogy a kattintás egy tektont érintett-e
        if (selectedCommand != null && selectedCommand.equals("move")) {
            if (checkTektonSelection(mouseX, mouseY)) {
                return;
            }
        }
        if (MycologistManager.getInstance().getMycologists().contains(activePlayer)) {
            if (selectedCommand == null || selectedCommand.equals("GrowHyphae")) {
                if (checkMyceliumSelection(mouseX, mouseY)) {
                    return;
                }
            }
        }
    }

    private boolean checkInsectSelection(int mouseX, int mouseY) {
        System.out.println(activePlayer + " " + InsectManager.getInstance().geEntomologists()
                .get(InsectManager.getInstance().geEntomologists().indexOf(activePlayer)).getInsectCount());
        if (InsectManager.getInstance().geEntomologists().contains(activePlayer)) {

            List<Entomologist> entomologists = InsectManager.getInstance().geEntomologists();
            for (Entomologist entomologist : entomologists) {
                for (Insect insect : entomologist.getInsects()) {
                    int x = insect.getCurrentTekton().getX();
                    int y = insect.getCurrentTekton().getY();
                    int size = 21; // Rovar mérete

                    if (mouseX >= x - size / 2 && mouseX <= x + size / 2 &&
                            mouseY >= y - size / 2 && mouseY <= y + size / 2) {
                        System.out.println("Rovarra kattintottál: " + insect);

                        // Megjegyezzük a kiválasztott rovart

                        selectedInsect = insect;
                        if (!InsectManager.getInstance().geEntomologists()
                                .get(InsectManager.getInstance().geEntomologists().indexOf(activePlayer)).getInsects()
                                .contains(selectedInsect)) {
                            selectedInsect = null;
                            System.out.println("Ez nem a te rovarod!");
                            // innen a return false azért lett kivéve, mert lehet egy rovar egy másik
                            // rovaron és ha a listában előtte van a másik rovar akkor soha nem fog tudni rá
                            // kattintani
                        }
                        // Parancsok hozzáadása a listához
                        commandListModel.clear();
                        commandListModel.addElement("cuthyphae");
                        commandListModel.addElement("move");
                        commandListModel.addElement("eatspore");

                        // Állítsuk vissza a parancsot

                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean checkMyceliumSelection(int mouseX, int mouseY) {
        System.out.println(activePlayer + " " + MycologistManager.getInstance().getMycologists()
                .get(MycologistManager.getInstance().getMycologists().indexOf(activePlayer)).getMyceliums().size());
        if (MycologistManager.getInstance().getMycologists().contains(activePlayer)) {
            List<Mycologist> mycologists = MycologistManager.getInstance().getMycologists();
            for (Mycologist mycologist : mycologists) {
                for (Mycelium mycelium : mycologist.getMyceliums()) {
                    int x = mycelium.getCurrentTekton().getX();
                    int y = mycelium.getCurrentTekton().getY();
                    int size = 21; // Rovar mérete

                    if (mouseX >= x - size / 2 && mouseX <= x + size / 2 &&
                            mouseY >= y - size / 2 && mouseY <= y + size / 2) {
                        System.out.println("Gombára kattintottál: " + mycelium);

                        // Megjegyezzük a kiválasztott rovart

                        selectedMycelium = mycelium;
                        if (!MycologistManager.getInstance().getMycologists()
                                .get(MycologistManager.getInstance().getMycologists().indexOf(activePlayer))
                                .getMyceliums()
                                .contains(selectedMycelium)) {
                            selectedMycelium = null;
                            System.out.println("Ez nem a te gombád!");
                            // innen a return false maradhat amúgy, return false;
                        }
                        // Parancsok hozzáadása a listához
                        commandListModel.clear();
                        commandListModel.addElement("GrowHyphae");
                        commandListModel.addElement("spreadSpore");
                        commandListModel.addElement("upgradeMycelium");

                        // Állítsuk vissza a parancsot

                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean checkTektonSelection(int mouseX, int mouseY) {
        List<Tekton> tektons = TektonManager.getInstance().getTektons();
        for (Tekton tekton : tektons) {
            int x = tekton.getX();
            int y = tekton.getY();
            int size = 30; // Tekton mérete

            if (mouseX >= x - size / 2 && mouseX <= x + size / 2 &&
                    mouseY >= y - size / 2 && mouseY <= y + size / 2) {
                System.out.println("Tektonra kattintottál: " + tekton);

                // Megjegyezzük a kiválasztott tektont
                selectedTekton = tekton;
                tektonView.repaint(); // Újrarajzolás
                return true;
            }
        }
        return false;
    }

    public void setSelectedCommand(String command) {
        this.selectedCommand = command;
        System.out.println("Kiválasztott parancs: " + command);
        if (selectedCommand == null) {
            System.out.println("Parancs törölve!");
        }
    }

    public void executeCommand() {
        if (selectedInsect != null && selectedCommand != null) {
            switch (selectedCommand) {
                case "move":
                    if (selectedTekton != null) {
                        System.out.println("Mozgatás: " + selectedInsect + " -> " + selectedTekton);
                        selectedInsect.move(selectedTekton);

                    }
                    break;
                case "cuthyphae":
                    System.out.println("Hyphae vágása: " + selectedInsect);
                    // selectedInsect.cutHyphae(); // Feltételezve, hogy van ilyen metódus
                    break;
                case "eatspore":
                    System.out.println("Spóra evése: " + selectedInsect);
                    // selectedInsect.eatSpore(); // Feltételezve, hogy van ilyen metódus
                    break;
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // Nem szükséges implementálni, de a MouseMotionListener miatt kell jelen lennie
    }
}