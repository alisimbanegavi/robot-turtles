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
        if (control.initializeGame(control.getView()))
        {
            control.playGame();
        }
        System.out.println("Program Ended");
    }
}
