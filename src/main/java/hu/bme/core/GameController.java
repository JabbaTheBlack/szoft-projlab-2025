package hu.bme.core;

import hu.bme.managers.InsectManager;
import hu.bme.managers.MycologistManager;
import hu.bme.managers.TektonManager;

public class GameController {
    private TektonManager tektonManager;
    private InsectManager insectManager;
    private MycologistManager mycologistManager;

    public GameController() {
        tektonManager = new TektonManager();
    }

    public void runGameLoop(){
        // TODO implement function, add javadoc
        throw new UnsupportedOperationException("Unimplemented method 'runGameLoop' in GameController class");
    }

    public void startGame() {
        // TODO implement function, add javadoc
        throw new UnsupportedOperationException("Unimplemented method 'startGame' in GameController class");
    }

    public void endGame() {
        // TODO implement function, add javadoc
        throw new UnsupportedOperationException("Unimplemented method 'endGame' in GameController class");
    }

    public int getPlayerCount() {
        // TODO add javadoc
        return insectManager.getInsectCount() + mycologistManager.getMycologistCount();
    }
}
