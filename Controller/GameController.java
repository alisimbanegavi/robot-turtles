package Controller;
import Model.GameModel;
import Model.Turtle;
import Model.Action;
import Model.Card;
import View.GameView;
import java.util.ArrayDeque;
import java.util.Scanner;

/** @author Ali Simbanegavi + Daniel Cumming
 * @version C3721.2.0
 * @since 2020-11-07
 *
 * Class of main game controller. This class initializes the game, uses the view to prompt players for moves
 * and executes moves if they are validated by the current model.
 */
public class GameController {
    private GameModel model;

    /**
     * Constructor for controller
     */
    public GameController(){}

    /**
     * Begins game by calling new initializer object. First step of gameplay before players can execute moves
     * @return boolean Returns true if game is successfully initialized
     */
    public boolean initializeGame(){
        GameView.displayText("- - - - - - - - - - WELCOME to ROBOT TURTLES - - - - - - - - - - ");
        GameView.displayPrompt("GAME MENU:\n1) PLAY GAME\n2) EXIT\n\nENTER CHOICE: ");
        if (GameView.readDigit() == 1){
            GameView.displayPrompt("\nENTER NUMBER OF PLAYERS [1-4]: ");
            GameInitializer.start(GameView.readDigit());
            model = GameInitializer.newGame();
            return true;
        }
        GameView.displayText("GOODBYE!");
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
        GameView.displayText(player.getPlayerName() + " (PLAYER " + (player.getColour().ordinal() + 1) + ") - ENTER ACTION..............");

        StringBuilder sb = new StringBuilder(); // Building string for prompt
        sb.append("[LEFT (" + player.getDir().turnDir("LEFT").toString() + ")"); // Direction player will turn in left is chosen
        sb.append(", RIGHT (" + player.getDir().turnDir("RIGHT").toString() + ")"); // Direction if right is chosen
        sb.append(", FORWARD (" + player.getDir().toString() + ")"); // Direction if forward step is chosen
        sb.append(", BUG]: ");

        GameView.displayPrompt(sb.toString());
        String response = GameView.readText().toUpperCase(); // Saving response and making text uppercase for processing
        Card choice = (model.moveList().contains(response)) ? Card.valueOf(response) : null; // Getting corresponding Card for requested move
        Action result = new Action(player, choice, model.getBoard()); // Creating Move for player to execute on board

        if(result.validate()) {return result;} // Move is returned if valid according to model
        else {GameView.displayText("SORRY, " + player.getPlayerName() + " (PLAYER " + (player.getColour().ordinal() + 1) + "), THAT MOVE IS INVALID.");}
        return promptMove(player); // Recursively prompt user for Move again if previous input was invalid
    }

    /**
     * Plays one player's turn by executing their requested Move
     * @param mov Instruction for current player to execute on board
     */
    public void playTurn(Action mov)
    {
        Turtle curr = mov.getCurrPlayer(); // Current player
        model.update(mov); // Updating board and executing move through model

        if (mov.getCard().isTurn()) { // Sending view update if player is facing new direction after action
            GameView.displayText(curr.getPlayerName() + " (PLAYER "+ (curr.getColour().ordinal()+1) +") - YOUR " + curr.getColour().toString() + " TURTLE IS NOW FACING " + curr.getDir().toString() + ".\n");
        }

        // Updating view to show board after each player's turn
        GameView.displayBoard(BoardConverter.parseJewels(model.jewels()), BoardConverter.parseTurtles(model.turtles()), model.getMaxSize());

        if(curr.hasWon()){ // Updating view if a player's Move has led to a win
            GameView.displayText("CONGRATULATIONS, "+ curr.getPlayerName() +  " (PLAYER  " + (curr.getColour().ordinal()+1) +
                    ")! \nYOUR "+curr.getColour().toString() +" TURTLE HAS CAPTURED A JEWEL. YOU HAVE WON!!!");
        }
    }

    /**
     * Method to play a round by executing each player's turn
     */
    public void playRound()
    {
        GameView.displayText("- - - - - - NEW ROUND BEGINNING | PREPARE YOUR STRATEGY - - - - - -\n");
        ArrayDeque<Turtle> plyrSequence = model.players();

        for (Turtle curr : plyrSequence) { //Iterating through each player in game and executing move
            playTurn(promptMove(curr)); // Playing one turn for current player
        }
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
        }
        GameView.displayText("Congratulations everyone has won!\n\n-----------------------"); // Game has ended and display message is shown
    }
}