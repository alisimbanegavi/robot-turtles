/*
  @author Ali Simbanegavi + Daniel Cumming
 * @version C3721.2.0
 * @since 2020-11-07
 */

package Controller;
import Model.*;
import View.*;
import java.util.*;

/**
 * Class of main game controller. This class initializes the game, uses the view to prompt players for moves
 * and executes moves if they are validated by the current model.
 */
public class GameController {
    private final Scanner input;
    private GameModel model;

    /**
     * Constructor for controller
     */
    public GameController(){
        input = new Scanner (System.in);
    } // Instantiating Scanner object for console input

    /**
     * Begins game by calling new initializer object. First step of gameplay before players can execute moves
     * @return boolean Returns true if game is successfully initialized
     */
    public boolean initializeGame(){
        GameView.displayText("Welcome to ROBOT TURTLES!");
        GameView.displayPrompt("Game Menu\n1) Play a Game\n2) Exit\n\nEnter Choice: ");
        if (input.nextInt() == 1){
            GameView.displayPrompt("Enter the number of players: ");
            GameInitializer.start(input.nextInt());
            model = GameInitializer.newGame();
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
    public Action promptMove(Turtle player)
    {
        // Prompting player for move by requesting input
        GameView.displayPrompt(player.getPlayerName() + " - Enter move card [LEFT, RIGHT, FORWARD, BUG]: ");
        String response = input.nextLine().toUpperCase(); // Saving response and making text uppercase for processing
        Card choice = (model.moveList().contains(response)) ? Card.valueOf(response) : null; // Getting corresponding Card for requested move
        Action result = new Action(player, choice, model.getBoard()); // Creating Move for player to execute on board

        if(result.validate()) {return result;} // Move is returned if valid according to model
        else {System.out.println("Sorry, " + player.getPlayerName() + ", but that move is invalid." );}
        return promptMove(player); // Recursively prompt user for Move again if previous input was invalid
    }

    /**
     * Plays one player's turn by executing their requested Move
     * @param mov Instruction for current player to execute on board
     */
    public void playTurn(Action mov)
    {
        Turtle curr = mov.getCurrPlayer(); // Current player
        GameView.displayText(curr.getPlayerName() + " (Player "+ (curr.getColour().ordinal()+1) +") your " +
                curr.getColour().name() + " turtle is facing " +
                curr.getDir().name());
        model.update(mov); // Updating board and executing move through model

        if (mov.getCard().isTurn()) { // Sending view update if player is facing new direction after action
            GameView.displayText(curr.getPlayerName() + ", your " + curr.getColour().toString() + " turtle is now facing " + curr.getDir().toString() + ".\n");
        }

        if(curr.hasWon()){ // Updating view if a player's Move has led to a win
            GameView.displayText("Congratulations, "+ curr.getPlayerName() + "!\nYour "+curr.getColour().toString() +" Turtle has captured a Jewel. You have WON!!!");
        }
    }

    /**
     * Method to play a round by executing each player's turn
     */
    public void playRound()
    {
        System.out.println("New round beginning. Players, prepare your strategy.");
        ArrayDeque<Turtle> plyrSequence = model.players();

        for (Turtle curr : plyrSequence) { //Iterating through each player in game and executing move
            GameView.displayPrompt("\n" + curr.getPlayerName() + ", your " + curr.getColour().toString() + " turtle is facing " + curr.getDir().toString() + ".\n");
            playTurn(promptMove(curr)); // Playing one turn for current player
        }
        GameView.displayText("Round is complete. All players have moved their turtles.");
    }

    /**
     * Method to play a full game by executing as many rounds as necessary until all players have won
     */
    public void playGame()
    {
        int dimensions = model.getMaxSize(); // Dimensions of board according to model
        GameView.displayBoard(BoardConverter.parseJewels(model.jewels()), BoardConverter.parseTurtles(model.turtles()), dimensions); // Displaying board configuration in view
        while (!model.gameOver()){ // Playing rounds repeatedly while game status is not complete
            playRound();
            // Updating view and display board after each round has been played
            GameView.displayBoard(BoardConverter.parseJewels(model.jewels()), BoardConverter.parseTurtles(model.turtles()), dimensions);
        }
        GameView.displayText("Congratulations everyone has won!\n\n-----------------------"); // Game has ended and display message is shown
    }
}