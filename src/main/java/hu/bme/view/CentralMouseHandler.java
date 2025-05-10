package hu.bme.view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.DefaultListModel;

import hu.bme.insect.Entomologist;
import hu.bme.insect.Insect;
import hu.bme.managers.InsectManager;
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

    public CentralMouseHandler(DefaultListModel<String> commandListModel, TektonView tektonView) {
        this.tektonView = tektonView;
        this.commandListModel = commandListModel;
        selectedCommand = null;

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
        if (selectedCommand == null || selectedCommand.equals("move")) {
            if (checkInsectSelection(mouseX, mouseY)) {
                return;
            }
        }

        // Ellenőrizzük, hogy a kattintás egy tektont érintett-e
        if (selectedCommand != null && selectedCommand.equals("move")) {
            if (checkTektonSelection(mouseX, mouseY)) {
                return;
            }
        }
    }

    private boolean checkInsectSelection(int mouseX, int mouseY) {
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