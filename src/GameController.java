import java.util.Scanner;

public class GameController {
    private Scanner input;
    private GameView view;
    private GameModel model;
    private ModelConverter converter;

    public GameController(){
        view = new GameView();
        input = new Scanner (System.in);
        view.displayWelcome();
    }

    public void playTurn(){
        // model.gameOver();
        
        TurtleMaster currentPlayer = model.getCurrentPlayer();
        Card givenCard = null;
        char card;
        while (givenCard == null){
            view.displayPrompt(currentPlayer.getColour().name() + " TurtleMaster enter a card [f, l, r]: ");
            card = input.next().charAt(0);
            if      (card == 'f') givenCard = Card.FORWARD;
            else if (card == 'l') givenCard = Card.LEFT_TURN;
            else if (card == 'r') givenCard = Card.RIGHT_TURN;
        }
        boolean validMove = model.makeMove(givenCard);
        view.displayBoard(converter.parseJewels(), converter.parsePlayers(), Tile.MAX_X, Tile.MAX_Y);

        if (currentPlayer.hasWon()){
            view.displayWinner(currentPlayer.getName(), currentPlayer.getColour().name());
            model.finishTurn();
        }
        else {
            view.displayPrompt("Do you wish to submit a bug card [b or anything else]: ");
            card = input.next().charAt(0);
            if (card == 'b'){
                model.undoMove(givenCard, validMove);
                view.displayBoard(converter.parseJewels(), converter.parsePlayers(), Tile.MAX_X, Tile.MAX_Y);
                playTurn();
            }
            else model.finishTurn();
        }      
    }

    public void playGame(){
        view.displayBoard(converter.parseJewels(), converter.parsePlayers(), Tile.MAX_X, Tile.MAX_Y);

        GameState currentState = model.getGameState();
        while (currentState == GameState.ONGOING){
            playTurn();
            currentState = model.getGameState();
            view.displayBoard(converter.parseJewels(), converter.parsePlayers(), Tile.MAX_X, Tile.MAX_Y);  
        }
        view.displayGameOver();   
    }

    public boolean initializeGame(){
        view.displayPrompt ("Game Menu\n1) Play a Game\n2) Exit\n\nEnter Choice: ");
        if (input.nextInt() == 1){ 
            view.displayPrompt("Enter number of players (1-4): ");
            int nPlayers = input.nextInt(); // TODO - constrain input (1-4)
            String players[] = new String [nPlayers];
            
            for (int i = 0; i < nPlayers; i++){
                view.displayPrompt("Enter Player "+ (i+1) +"'s name: "); 
                players[i] = input.next();
            }
            model = new GameModel(players);
            converter = new ModelConverter(model);
            return true;
        }
        else {
            view.displayGoodbye();
            return false;   
        }        
    }

    public static void main(String[] args) {
        GameController controller = new GameController();
        while (controller.initializeGame()){
            controller.playGame();
        }
        System.out.println("Program Ended");
    }
}
