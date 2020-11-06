import Controller.GameController;

/**
 * Tester class for game
 */
public class TestGame {
    public static void main(String[] args) {
        GameController control = new GameController();
        while (control.initializeGame()) control.playGame();
    }
}
