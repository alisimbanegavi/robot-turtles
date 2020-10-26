import Model.*;
import View.*;
import Controller.*;
import java.util.*;

/**
 * Tester class for game
 */
public class TestGame {
    public static void main(String[] args) {
        GameController control = new GameController();
        control.initializeGame();
        control.playGame();

        System.out.println("Program Ended");
    }
}
