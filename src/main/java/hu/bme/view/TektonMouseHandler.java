package hu.bme.view;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import hu.bme.managers.TektonManager;
import hu.bme.tekton.Tekton;

public class TektonMouseHandler implements MouseMotionListener {
    private TektonView tektonView;

    public TektonMouseHandler(TektonView tektonView) {
        this.tektonView = tektonView;
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

    @Override
    public void mouseDragged(MouseEvent e) {
        // Nem szükséges implementálni, de a MouseMotionListener miatt kell jelen lennie
    }
}