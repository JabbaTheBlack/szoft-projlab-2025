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
    private Ticker ticker;
    private final GamePanel gamePanel;

    
    /**
     * Initializes the game controller with necessary managers.
     */
    public GameController() {
        tektonManager = TektonManager.getInstance();
        insectManager = InsectManager.getInstance();
        mycologistManager = MycologistManager.getInstance();
        ticker = Ticker.getInstance();
        ticker.addObserver(mycologistManager);
        ticker.addObserver(insectManager);
        gamePanel = new GamePanel();
    }

    /**
     * Returns the tekton manager instance.
     * @return the tekton manager instance
     */
    public TektonManager getTektonManager() {
        return tektonManager;   }

    /**
     * Contols the game loop by running the game components.
     */
    public void runGameLoop(){
        startGame();
        while(true){
            ticker.tick();
        }       
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
        return insectManager.getEntomologistCount() + mycologistManager.getMycologistCount();
    }
}
