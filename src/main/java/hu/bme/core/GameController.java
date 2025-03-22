package hu.bme.core;

import hu.bme.managers.InsectManager;
import hu.bme.managers.MycologistManager;
import hu.bme.managers.TektonManager;

/**
 * This class controls the game flow by managing different game components.
 */
public class GameController {
    private TektonManager tektonManager;
    private InsectManager insectManager;
    private MycologistManager mycologistManager;
    
    /**
     * Initializes the game controller with necessary managers.
     */
    public GameController() {
        tektonManager = new TektonManager();
        //insectManager = new InsectManager();
        //mycologistManager = new MycologistManager();
    }

    /**
     * Contols the game loop by running the game components.
     */
    public void runGameLoop(){
        // TODO implement function, add javadoc
        throw new UnsupportedOperationException("Unimplemented method 'runGameLoop' in GameController class");
    }

    /**
     * Starts the game by initializing all game components.
     */
    public void startGame() {
        // TODO implement function
        throw new UnsupportedOperationException("Unimplemented method 'startGame' in GameController class");
    }

    /**
     * Ends the game by stopping all game components.
     */
    public void endGame() {
        // TODO implement function
        throw new UnsupportedOperationException("Unimplemented method 'endGame' in GameController class");
    }

    /**
     * Returns the number of players in the game.
     * @return the number of players in the game
     */
    public int getPlayerCount() {
        // TODO add javadoc
        return insectManager.getInsectCount() + mycologistManager.getMycologistCount();
    }
}
