/**
 * @author Ali Simbanegavi + Daniel Cumming
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
    private Scanner input;
    private GameView view;
    private GameModel model;
    private BoardConverter converter;

    /**
     * Constructor for controller
     */
    public GameController(){
        view = new GameView(); // Instantiating view
        input = new Scanner (System.in); // Instantiating Scanner object for console input
    }

    /**
     * Begins game by calling new initializer object. First step of gameplay before players can execute moves
     * @return boolean Returns true if game is successfully initialized
     */
    public boolean initializeGame(){
        // Method to call initializer
        GameInitializer initGame = new GameInitializer(view); // Assigns players to turtles, creates jewels, and populates board with all tiles
        if (!initGame.start()) return false; // Returns false if initializer does not start game as per user input
        model = initGame.newGame(); // Creating model for game based on initializer specifications
        converter = new BoardConverter(model.getBoard()); // Creating converter that will be used to update view
        return true;
    }

    /**
     * Prompting player for Move to execute on their Turtle. Repeatedly prompts player until a valid selection is made
     * @param player Player being prompted
     * @return Move Returns command if move has been validated by model
     */
    public Move promptMove(Turtle player)
    {
        // Prompting player for move by requesting input
        view.displayPrompt(player.getPlayerName() + " - Enter move card [LEFT, RIGHT, FORWARD, BUG]: ");
        String response = input.nextLine().toUpperCase(); // Saving response and making text uppercase for processing
        Card choice = (model.moveList().contains(response)) ? Card.valueOf(response) : null; // Getting corresponding Card for requested move
        Move result = new Move(player, choice, model.getBoard()); // Creating Move for player to execute on board

        if(model.validate(result)) {return result;} // Move is returned if valid according to model
        else {System.out.println("Sorry, " + player.getPlayerName() + ", but that move is invalid." );}
        return promptMove(player); // Recursively prompt user for Move again if previous input was invalid
    }

    /**
     * Plays one player's turn by executing their requested Move
     * @param mov Instruction for current player to execute on board
     */
    public void playTurn(Move mov)
    {
        // Executing move chosen by player
        model.updateBoard(mov); // Updating board by executing move
        Turtle curr = mov.getCurrPlayer(); // Current player
        if ((mov.getCard() == Card.LEFT) || (mov.getCard() == Card.RIGHT)) {
            view.displayText(curr.getPlayerName() + ", your " + curr.getCol().toString() + " turtle is now facing " + curr.getDir().toString() + ".\n");
        } // Sending view update if direction player is facing has changed
        if(curr.hasWon()) // Updating view if a player's Move has led to a win
        {
            view.displayText("Congratulations, "+ curr.getPlayerName() + "!\nYour "+curr.getCol().toString() +" Turtle has captured a Jewel. You have WON!!!");
        }
    }

    /**
     * Method to play a round by executing each player's turn
     */
    public void playRound()
    {
        System.out.println("New round beginning. Players, prepare your strategy.");
        ArrayDeque<Turtle> plyrSequence = model.getPlayers();

        for (Turtle curr : plyrSequence) { //Iterating through each player in game and executing move
            view.displayPrompt("\n" + curr.getPlayerName() + ", your " + curr.getCol().toString() + " turtle is facing " + curr.getDir().toString() + ".\n");
            playTurn(promptMove(curr)); // Playing one turn for current player
        }
        view.displayText("Round is complete. All players have moved their turtles.");
    }

    /**
     * Method to play a full game by executing as many rounds as necessary until all players have won
     */
    public void playGame()
    {
        int dimensions = model.getMaxSize(); // Dimensions of board according to model
        view.displayBoard(converter.parseJewels(), converter.parseTurtles(), dimensions); // Displaying board configuration in view
        while (!model.gameOver()){ // Playing rounds repeatedly while game status is not complete
            playRound();
            // Updating view and display board after each round has been played
            view.displayBoard(converter.parseJewels(), converter.parseTurtles(), model.getMaxSize());
        }
        view.displayText("Congratulations everyone has won!\n\n-----------------------"); // Game has ended and display message is shown
    }
}