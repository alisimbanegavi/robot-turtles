package Controller;
import Model.*;
import View.*;
import java.util.*;

/**
 * Class of main game controller
 */
public class GameController {
    private Scanner input;
    private GameView view;
    private GameModel model;
    private BoardConverter converter;

    public GameController(){
        view = new GameView();
        input = new Scanner (System.in);
    }

    public boolean initializeGame(){
        // Method to call initializer to assign players to turtles and populate board with tiles
        GameInitializer initGame = new GameInitializer(view);
        if (!initGame.start())
            return false;
        model = initGame.newGame();
        converter = new BoardConverter(model.getBoard());
        return true;
    }

    public Move promptMove(TurtleMaster player)
    {
        // Prompting player for move by requesting input
        // Scanner sc = new Scanner(System.in);
        view.displayPrompt(player.getPlayerName() + " - Enter move card...\n [LEFT, RIGHT, FORWARD, BUG]: ");
        String response = input.nextLine().toUpperCase();
        Card choice = (model.moveList().contains(response)) ? Card.valueOf(response) : null;
        Move result = new Move(player, choice, model.getBoard());

        if(model.validate(result)) {return result;} // Move is executed on board if valid according to model
        else {System.out.println("Sorry, " + player.getPlayerName() + ", but that move is invalid." );}
        return promptMove(player);
    }

    public void playTurn(Move mov)
    {
        // Executing move chosen by player
        TurtleMaster curr = mov.getCurrPlayer();
        model.updateBoard(mov);
        if ((mov.getCard() == Card.LEFT) ||(mov.getCard() == Card.RIGHT)) {
            view.displayPrompt(curr.getPlayerName() + ", your " + curr.getColour().toString() + " turtle is now facing " + curr.getDir().toString() + ".\n\n");
        } // Sending view about update if direction player is facing has changed.
        if(mov.getCurrPlayer().hasWon())
        {
            view.displayWinner(curr.getColour().toString(), curr.getPlayerName()); // Displaying player win
        }
    }

    public void playRound()
    {
        System.out.println("New round beginning. Players, prepare your strategy.");
        ArrayDeque<TurtleMaster> plyrSequence = model.getPlayers();

        for (TurtleMaster curr : plyrSequence) {
            view.displayPrompt("\n" + curr.getPlayerName() + ", your " + curr.getColour().toString() + " turtle is facing " + curr.getDir().toString() + ".\n");
            playTurn(promptMove(curr));
        }
        //Iterating through each player and executing move

        System.out.println("Round is complete. All players have moved their turtles.");
    }

    public void playGame()
    {
        int dimensions = model.getMaxSize();
        view.displayBoard(converter.parseJewels(), converter.parseTurtles(), dimensions);
        while (!model.gameOver()){ // Play while game status is not complete
            playRound();
            view.displayBoard(converter.parseJewels(), converter.parseTurtles(), dimensions);
        }
        view.displayGameOver(); // Game has ended and display message is shown
    }
}