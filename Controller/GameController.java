/**
 * @author Ali Simbanegavi + Daniel Cumming
 * @version C3721.2.0
 * @since 2020-11-07
 */

package Controller;
import View.GameView;
import Model.Board;
import Model.GameModel;
import Model.Turtle;
import Model.Card;

import java.util.Scanner;

/**
 * Class of main game controller. This class initializes the game, uses the view to prompt players for moves
 * and executes moves if they are validated by the current model.
 */
public class GameController {
    private Scanner input;
    private GameModel game;

    /**
     * Constructor for controller
     */
    public GameController(){
        input = new Scanner (System.in); // Instantiating Scanner object for console input
    }

    /**
     * Begins game by calling new initializer object. First step of gameplay before players can execute moves
     * @return boolean Returns true if game is successfully initialized
     */
    public boolean initializeGame(){
        GameView.displayPrompt("Game Menu\n1) Play a Game\n2) Exit\n\nEnter Choice: ");
        if (input.nextInt() == 1){
            game = GameInitializer.createGame();
            return true;
        }
        GameView.displayText("Goodbye!");
        return false;
    }

    /**
     * Prompting player for Move to execute on their Turtle. Repeatedly prompts player until a valid selection is made
     * @param player Player being prompted
     * @return Move Returns command if move has been validated by model
     */
    private Card promptMove(Turtle player) {
        // Prompting player for move by requesting input
        Card choice = null;
        while (choice == null){
            GameView.displayPrompt(player.getName() + " - Enter move card [LEFT, RIGHT, FORWARD, BUG]: ");
            String response = input.next(); 
            if      (response.equalsIgnoreCase("left"))     choice = Card.LEFT;
            else if (response.equalsIgnoreCase("right"))    choice = Card.RIGHT;
            else if (response.equalsIgnoreCase("forward"))  choice = Card.FORWARD;
            else if (response.equalsIgnoreCase("bug"))      choice = Card.BUG;
        }
        return choice;
    }

    /**
     * Plays one player's turn by executing their requested move
     */
    private void playTurn(){
        Turtle currentPlayer = game.getCurrentPlayer();
        GameView.displayText (  currentPlayer.getName() + " (Player "+ (currentPlayer.getColour().ordinal()+1) +") your " + 
                                currentPlayer.getColour().name() + " turtle is facing " + 
                                currentPlayer.getDir().name());

        Card move = promptMove(currentPlayer);
        if (!game.move(move))
            playTurn();
        
        if (currentPlayer.hasWon()){
            GameView.displayText("Congratulations, "+ currentPlayer.getName() + 
                                 "!\nYour "+currentPlayer.getColour().name() +" Turtle has captured a Jewel. You have WON!!!");
        }
    }

    /**
     * Method to play a full game by executing as many rounds as necessary until all players have won
     */
    public void playGame(){
        GameView.displayBoard(BoardConverter.parseJewels(game.getJewels()), BoardConverter.parseTurtles(game.getTurtles()), Board.DIMENSIONS);
        while (!game.isOver()){ // Each player continues playing their turn until all have won
            playTurn();
            GameView.displayBoard(BoardConverter.parseJewels(game.getJewels()), BoardConverter.parseTurtles(game.getTurtles()), Board.DIMENSIONS);
        }
        GameView.displayText("Congratulations everyone has won!\n\n-----------------------"); // Game has ended and display message is shown
    }
}