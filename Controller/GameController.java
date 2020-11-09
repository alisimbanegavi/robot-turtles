package Controller;

import Model.Turtle;
import Model.Card;

// import java.util.Scanner;

/** @author Ali Simbanegavi + Daniel Cumming
 * @version C3721.2.0
 * @since 2020-11-07
 *
 * Class of main game controller. This class initializes the game, uses the view to prompt players for moves
 * and executes moves if they are validated by the current model.
 */
public interface GameController {

    public boolean initializeGame();
    public void playGame();
    public void update();
    public void update(Turtle player);
    public void playerWon(Turtle player);
    public void newRound();
    public Card getMove (Turtle player);
    public void invalidMove(Turtle player);
}