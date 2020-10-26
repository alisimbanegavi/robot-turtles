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
        view.displayWelcome();
    }

    public void initializeGame(){
        // Method to call initializer to assign players to turtles and populate board with tiles
        GameInitializer initGame = new GameInitializer(view);
        initGame.start();
        model = initGame.newGame();
        converter = new BoardConverter(model.getBoard());
    }

    public Move promptMove(TurtleMaster player)
    {
        // Prompting player for move by requesting input
        Scanner sc = new Scanner(System.in);
        System.out.println(player.getPlayerName() + " (" + player.getColour() + ") - Enter move card...\n [LEFT, RIGHT, FORWARD]:");
        String response = sc.nextLine().toUpperCase();
        Card input = (model.moveList().contains(response)) ? Card.valueOf(response) : null;
        Move result = new Move(player, input, model.getBoard());

        if(model.validate(result)) {return result;} // Controller.Move is executed on board if valid according to model
        else {System.out.println("Sorry, " + player.getPlayerName() + ", but that move is invalid." );}
        return promptMove(player);
    }

    public void playTurn(Move mov)
    {
        // Executing move chosen by player
        model.updateBoard(mov);
    }

    public void playRound()
    {
        System.out.println("New round beginning. Players, prepare your strategy.");
        ArrayDeque<TurtleMaster> plyrSequence = model.getPlayers();
        Iterator seq = plyrSequence.iterator();

        while(seq.hasNext()) {playTurn(promptMove((TurtleMaster) seq.next()));}
        //Iterating through each player and executing move

        System.out.println("Round is complete. All players have moved their turtles.");
    }

    public void playGame()
    {
        int dimensions = model.getMaxSize();
        view.displayBoard(converter.parseJewels(), converter.parseTurtles(), dimensions);
        while (!model.gameOver()){
            playRound();
            view.displayBoard(converter.parseJewels(), converter.parseTurtles(), dimensions);
        }
        view.displayGameOver();
    }

    public GameView getView() {return view;}
}