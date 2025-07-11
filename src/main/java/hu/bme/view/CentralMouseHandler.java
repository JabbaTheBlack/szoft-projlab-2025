package hu.bme.view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.DefaultListModel;

import hu.bme.fungi.Hyphae;
import hu.bme.fungi.Mycelium;
import hu.bme.fungi.Mycologist;
import hu.bme.insect.Entomologist;
import hu.bme.insect.Insect;
import hu.bme.managers.InsectManager;
import hu.bme.managers.MycologistManager;
import hu.bme.managers.TektonManager;
import hu.bme.tekton.Tekton;

//-------------------------------------------------------------------------------------------------------------
//              Ez a Központi egér eseménykezelős osztály, ha szét akarod szedni a kódot több osztályra, sok sikert.
//              Ha máshogy akarod megoldani akkor légyszíves jelezd hanyadik agyfaszt kapod. Eddigi agyfaszok száma: 4
//              ebbol egyet Praxi okozott Janinak. Falra maszok a breakaparttol.
//-------------------------------------------------------------------------------------------------------------
public class CentralMouseHandler extends MouseAdapter {
    private DefaultListModel<String> commandListModel;
    private Insect selectedInsect; // Az aktuálisan kiválasztott rovar
    private String selectedCommand; // Az aktuálisan kiválasztott parancs
    private Tekton selectedTekton; // Az aktuálisan kiválasztott tekton
    private TektonView tektonView;
    private MyceliumView myceliumView;
    private Object activePlayer; // Az aktuális játékos
    private Mycelium selectedMycelium;
    private Hyphae selectedHyphae;
    private SporeView sporeView;
    private Mycelium hoveredMycelium;

    //* Aktív player beállítása */
    public void setActivePlayer(Object activePlayer) {
        this.activePlayer = activePlayer;
    }

    //* Aktív player lekérdezése */
    public CentralMouseHandler(DefaultListModel<String> commandListModel, TektonView tektonView,
            MyceliumView myceliumView, SporeView sporeView, Object activePlayer) {
        this.tektonView = tektonView;
        this.myceliumView = myceliumView;
        this.sporeView = sporeView;
        this.commandListModel = commandListModel;
        selectedCommand = null;
        this.activePlayer = activePlayer;
        System.out.println("InsectMouseHandler inicializálva!");
    }

    // Kattintás eseménykezelő
    @Override
    public void mouseMoved(MouseEvent e) {
        TektonManager manager = TektonManager.getInstance();
        tektonView.setHoveredTekton(null); // Alapértelmezésben nincs kijelölt Tekton
        hoveredMycelium = null;

        for (Mycologist mycologist : MycologistManager.getInstance().getMycologists()) {
            for (Mycelium mycelium : mycologist.getMyceliums()) {
                int x = mycelium.getCurrentTekton().getX();
                int y = mycelium.getCurrentTekton().getY();
                int size = 21;
                if (e.getX() >= x - size / 2 && e.getX() <= x + size / 2 &&
                        e.getY() >= y - size / 2 && e.getY() <= y + size / 2) {

                    myceliumView.setHoverMycelium(mycelium);
                    break;
                }
            }

        }
        myceliumView.repaint(); // Újrarajzolás
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
            } else {
                tektonView.setHoveredTekton(null); // Ha nem, akkor töröljük a kijelölést
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

    public Mycelium getSelectedMycelium() {
        return selectedMycelium;
    }

    public Hyphae getSelectedHyphae() {
        return selectedHyphae;
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

        // Ellenőrizzük, hogy a kattintás egy hyphae-ra esett-e
        if (selectedCommand == null || selectedCommand.equals("cuthyphae")) {
            if (checkHyphaeSelection(mouseX, mouseY)) {
                return;
            }
        }

        if (MycologistManager.getInstance().getMycologists().contains(activePlayer)) {
            if (selectedCommand == null) {
                checkMyceliumSelection(mouseX, mouseY);
                checkHyphaeSelection(mouseX, mouseY);

            } else if (selectedCommand == null || selectedCommand.equals("GrowHyphae")
                    || selectedCommand.equals("GrowMycelium")) {
                checkHyphaeSelection(mouseX, mouseY);
                checkTektonSelection(mouseX, mouseY);

            } else if (selectedCommand == "EatInsect") {
                checkTektonSelection(mouseX, mouseY);
            }
        }

        // A tekton kijelölés megszüntetése
        // selectedTekton = null;
    }

    private double pointToSegmentDistance(int px, int py, int x1, int y1, int x2, int y2) {
        double dx = x2 - x1;
        double dy = y2 - y1;
        if (dx == 0 && dy == 0) {
            // It's a point, not a segment.
            dx = px - x1;
            dy = py - y1;
            return Math.sqrt(dx * dx + dy * dy);
        }

        // Calculate t that minimizes the distance
        double t = ((px - x1) * dx + (py - y1) * dy) / (dx * dx + dy * dy);
        t = Math.max(0, Math.min(1, t));
        double closestX = x1 + t * dx;
        double closestY = y1 + t * dy;
        dx = px - closestX;
        dy = py - closestY;
        return Math.sqrt(dx * dx + dy * dy);
    }

    private boolean checkHyphaeSelection(int mouseX, int mouseY) {
        List<Mycologist> mycologists = MycologistManager.getInstance().getMycologists();
        for (Mycologist mycologist : mycologists) {
            for (Hyphae hyphae : mycologist.getHyphaes()) {
                int x1 = (int) hyphae.getP1().getX();
                int y1 = (int) hyphae.getP1().getY();
                int x2 = (int) hyphae.getP2().getX();
                int y2 = (int) hyphae.getP2().getY();

                // Calculate the distance from the mouse point to the line segment
                double distance = pointToSegmentDistance(mouseX, mouseY, x1, y1, x2, y2);

                // Check if the distance is within a certain threshold (e.g., 3 pixels)
                if (distance <= 3) {
                     if (MycologistManager.getInstance().getMycologists().contains(activePlayer)) {
                    Mycologist activeMycologist = (Mycologist) activePlayer;
                    if (hyphae.getOwner() == activeMycologist) {
                        selectedHyphae = hyphae;
                        System.out.println("Saját hyphae-ra kattintottál: " + hyphae);
                        return true;
                    }
                }
                    // If the active player is an entomologist (insect player), allow selection of any hyphae
                    if (InsectManager.getInstance().geEntomologists().contains(activePlayer)) {
                        selectedHyphae = hyphae;
                        System.out.println("Hyphae-ra kattintottál (insect): " + hyphae);
                        return true;
                    }
                }
            }
        }

        return false;
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
                        if (selectedInsect == null) {
                            selectedInsect = insect;
                        }

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
                        if (selectedInsect != null) {

                            commandListModel.clear();
                            commandListModel.addElement("cuthyphae");
                            commandListModel.addElement("move");
                            commandListModel.addElement("eatspore");
                        }

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
                            System.out.println("Ez nem a te gombatested!");
                            // innen a return false maradhat amúgy, return false;
                        }
                        // Parancsok hozzáadása a listához
                        if (selectedMycelium != null) {

                            commandListModel.clear();
                            commandListModel.addElement("GrowHyphae");
                            commandListModel.addElement("spreadSpore");
                            commandListModel.addElement("upgradeMycelium");
                            commandListModel.addElement("GrowMycelium");
                            commandListModel.addElement("EatInsect");
                        }

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

    public String executeCommand() {
        String result = null;
        if (selectedCommand != null) { // selectedInsect != null kiszedve
            switch (selectedCommand) {
                case "move":
                    if (selectedTekton != null) {
                        System.out.println("Mozgatás: " + selectedInsect + " -> " + selectedTekton);
                        selectedInsect.move(selectedTekton);
                    } else {
                        result = "Nincs kiválasztott tekton!";
                    }
                    selectedTekton = null;

                    break;
                case "cuthyphae":
                    if (selectedInsect != null && selectedHyphae != null) {
                        selectedInsect.cutHyphae(selectedHyphae); // Feltételezve, hogy van ilyen metódus
                        System.out.println("Hyphae vágása: " + selectedInsect + " -> " + selectedHyphae);

                    } else {
                        result = "Nincs kiválasztott hyphae!";
                    }
                    break;
                case "eatspore":
                    if (selectedInsect != null) {
                        System.out.println("Spóra evése: " + selectedInsect);
                        if (selectedInsect.getCurrentTekton().getSporeCount() > 0
                                && selectedInsect.getCurrentTekton().equals(selectedInsect.getCurrentTekton())) {
                            selectedInsect.eatSpore(selectedInsect.getCurrentTekton().getSpores()
                                    .get(selectedInsect.getCurrentTekton().getSporeCount() - 1));
                        } else {
                            result = "Nincs elérhető spóra!";
                        }
                    } else {
                        result = "Nincs kiválasztott rovar!";
                    }
                    sporeView.repaint();
                    break;
                case "GrowHyphae":
                    if (selectedMycelium != null && selectedHyphae == null) {
                        // gombatestből saját tektonra
                        System.out.println("Hyphae növesztése: " + selectedMycelium + " -> " + selectedTekton);
                        Mycologist mycologist = MycologistManager.getInstance().getMycologists()
                                .get(MycologistManager.getInstance().getMycologists().indexOf(activePlayer));
                        mycologist.growHyphaeOnTekton(selectedMycelium, selectedMycelium.getCurrentTekton());

                    } else if (selectedHyphae != null && selectedTekton != null) {

                        Mycologist mycologist = MycologistManager.getInstance().getMycologists()
                                .get(MycologistManager.getInstance().getMycologists().indexOf(activePlayer));
                        if (selectedHyphae.getCurrentTekton().size() > 1 && selectedHyphae.getCurrentTekton()
                                .contains(selectedTekton)) {
                            // kooztes fonalbol tektonra
                            mycologist.growHyphaeOnTekton(selectedHyphae, selectedTekton);
                        } else {
                            // ket tekton közötti fonal
                            if (selectedHyphae != null) {
                                mycologist.growHyphaeToTekton(selectedHyphae, selectedTekton);
                            } else {
                                result = "Nincs kiválasztott hyphae!";
                            }
                        }
                        selectedHyphae = null;
                    } else {
                        result = "Nincs kiválasztott tekton!";
                    }
                    break;
                case "upgradeMycelium":
                    if (selectedMycelium != null) {
                        System.out.println("Mycelium fejlesztése: " + selectedMycelium);
                        Mycologist mycologist = MycologistManager.getInstance().getMycologists()
                                .get(MycologistManager.getInstance().getMycologists().indexOf(activePlayer));
                        if (!selectedMycelium.isUpgraded()) {

                            mycologist.upgradeMycelium(selectedMycelium);
                        } else {
                            result = "Ez a gombatest már fejlesztve van!";
                        }
                    } else {
                        result = "Nincs kiválasztott gombatest!";
                    }
                    break;

                case "spreadSpore":
                    if (selectedMycelium != null) {
                        System.out.println("Spóra terjesztése: " + selectedMycelium);
                        selectedMycelium.releaseSpores();
                        sporeView.repaint();
                    }
                    break;
                case "GrowMycelium":
                    if (selectedHyphae != null && selectedTekton != null) {
                        Mycologist mycologist = MycologistManager.getInstance().getMycologists()
                                .get(MycologistManager.getInstance().getMycologists().indexOf(activePlayer));
                        mycologist.growMycelium(selectedHyphae, selectedTekton);
                        System.out.println("Mycelium növesztése: " + selectedMycelium + " -> " + selectedTekton);

                    } else {
                        result = "Nincs kiválasztott tekton!";
                    }
                    break;
                case "EatInsect":
                    if (selectedMycelium != null && selectedTekton != null) {
                        for (Entomologist entomologist : InsectManager.getInstance().geEntomologists()) {
                            for (Insect insect : entomologist.getInsects()) {
                                if (insect.getCurrentTekton() == selectedTekton) {
                                    if (insect.isStunned()) {
                                        System.out.println("Rovar evése: " + selectedMycelium);
                                        Mycologist mycologist = MycologistManager.getInstance().getMycologists()
                                                .get(MycologistManager.getInstance().getMycologists()
                                                        .indexOf(activePlayer));
                                        mycologist.eatInsect(insect);
                                        return null;
                                    }

                                }
                            }
                        }

                    } else {
                        result = "Nincs kiválasztott tekton!";
                    }
                    break;
            }
        }
        return result;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // Nem szükséges implementálni, de a MouseMotionListener miatt kell jelen lennie
    }
}