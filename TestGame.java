import Controller.GameController;

/**
 * Tester class for game
 */
public class TestGame {
    public static void main(String[] args) {
        GameController control = new GameController();
        if (control.initializeGame()) control.playGame();
    }
}
