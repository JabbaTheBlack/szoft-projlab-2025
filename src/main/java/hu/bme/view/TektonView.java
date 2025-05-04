package hu.bme.view;

import java.awt.Graphics2D;

import hu.bme.managers.TektonManager;
import hu.bme.tekton.Tekton;

public class TektonView {

    public void render(TektonManager manager, Graphics2D g) {
        for(Tekton tekton : manager.getTektons()) {
            
        }
    }
}
