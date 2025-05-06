package hu.bme.view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.DefaultListModel;

import hu.bme.insect.Entomologist;
import hu.bme.insect.Insect;
import hu.bme.managers.InsectManager;

public class InsectMouseHandler extends MouseAdapter {
    private DefaultListModel<String> commandListModel;

    public InsectMouseHandler(DefaultListModel<String> commandListModel) {
        this.commandListModel = commandListModel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        handleMouseClick(e.getX(), e.getY());
    }

    private void handleMouseClick(int mouseX, int mouseY) {
        // Ellenőrizzük, hogy a kattintás egy rovarra esett-e
        List<Entomologist> entomologists = InsectManager.getInstance().geEntomologists();
        for (Entomologist entomologist : entomologists) {
            for (Insect insect : entomologist.getInsects()) {
                int x = insect.getCurrentTekton().getX();
                int y = insect.getCurrentTekton().getY();
                int size = 21; // Rovar mérete

                // Ellenőrizzük, hogy a kattintás a rovar területére esett-e
                if (mouseX >= x - size / 2 && mouseX <= x + size / 2 &&
                        mouseY >= y - size / 2 && mouseY <= y + size / 2) {
                    System.out.println("Rovarra kattintottál: " + insect);

                    // Parancsok hozzáadása a listához
                    commandListModel.clear();
                    commandListModel.addElement("cuthyphae");
                    commandListModel.addElement("move");
                    commandListModel.addElement("eatspore");
                    return;
                }
            }
        }
    }
}