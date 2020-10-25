public class GameController {
    private GameView view;
    private GameModel model = null;

    public GameController(){
        view = new GameView();
    }

    public void playTurn(){
        TurtleMaster currentPlayer = model.getCurrentPlayer();
        
        // Colour red = Colour.RED;
        // if(red.name().equalsIgnoreCase("red"))
        //     System.out.println("COLOUR IS CORRECT");
        // else System.out.println("DIDN'T WORK");
        // String colour;
        // switch (currentPlayer.getColour()){
        //     case RED:
        //         colour = "Red";
        //         break;
        //     case BLUE:
        //         colour = "Blue";
        //         break;
        //     case PURPLE:
        //         colour = "Purple";
        //         break;
        //     case GREEN:
        //         colour = "Green";
        //         break;
        //     // default:
        // }
        // System.out.print(colour + " Turtlemaster enter a card [f,l,r]: ");
        // String card = in.nextLine();
    }

    public void playGame(){
        GameState currentState = model.getGameState();
        while (currentState == GameState.ONGOING){
            playTurn();
            currentState = model.getGameState();
            // view.displayBoard(model.getBoard())  // TODO - Add parser & send to viewer
        }
        System.out.println("THANKS FOR PLAYING!\n\n--------------------------------"); // TODO - VIEWER
    }

    public boolean initializeGame(){
        if (view.displayGameMenu() == 1){
            model = new GameModel(view.promptPlayers());
            return true;
        }
        else return false;
    }

    public static void main(String[] args) {
        GameController controller = new GameController();
        while (controller.initializeGame()){
            controller.playGame();
        }
        System.out.println("Program Ended");
    }
}
