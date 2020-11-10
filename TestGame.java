// import Controller.BasicGameController;
import Controller.GameController;

/**
 * Tester class for game
 */
public class TestGame {
    public static void main(String[] args) {
        GameController control = new GameController();
        // GameController control = new BasicGameController();
        if (control.initializeGame()) control.playGame();
    }
}
