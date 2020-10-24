import java.util.Scanner;

public class GameController {
    // private GameView view;
    private GameModel model;
    private Scanner in;

    public GameController(){
        in = new Scanner(System.in);
        
        System.out.print("Enter the number of players (1-4): ");     // TODO - GO TO VIEWER
        int nPlayers = in.nextInt();
        // model = new GameModel(nPlayers);
        // view = new GameView();
    }

    public void playTurn(){
        
    }

    public void playGame(){
        GameState currentState = model.getCurrentState ();
        while (currentState == GameState.ONGOING){
            playTurn();
            currentState = model.getCurrentState ();
            // view.displayBoard(model.getBoard)
        }
        System.out.println("THANKS FOR PLAYING!"); // TODO - VIEWER
    }

    public static void main(String[] args) {
        GameController controller = new GameController();
        controller.playGame();
    }
}
