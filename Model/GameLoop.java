package Model;

import java.util.ArrayDeque;
import Controller.GameController;

public abstract class GameLoop {
    private GameController controller;
    private GameModel game;

    public GameLoop(GameController controller){
        this.controller = controller;
    }

    public void start (GameModel newGame){
        game = newGame;
        controller.update();
        while (!game.gameOver()){ // Playing rounds repeatedly while game status is not complete
            playRound();
            // Updating view and display board after each round has been played
        }
    }

    /**
     * Method to play a round by executing each player's turn
     */
    private void playRound()
    {
        controller.newRound();
        ArrayDeque<Turtle> plyrSequence = game.players();
        for (Turtle curr : plyrSequence) { //Iterating through each player in game and executing move
            playTurn(move(curr)); // Playing one turn for current player
        }
    }

        /**
     * Prompting player for Move to execute on their Turtle. Repeatedly prompts player until a valid selection is made
     * @param player Player being prompted
     * @return Move Returns command if move has been validated by model
     */
    private Action move(Turtle player)
    {
        Card choice = controller.getMove(player);
        Action result = new PlayerAction(player, choice, game.getBoard()); // Creating Move for player to execute on board

        if(result.validate()) {return result;} // Move is returned if valid according to model
        else {controller.invalidMove(player);}
        return move(player); // Recursively prompt user for Move again if previous input was invalid
    }

    /**
     * Plays one player's turn by executing their requested Move
     * @param mov Instruction for current player to execute on board
     */
    private void playTurn(Action mov)
    {
        Turtle curr = mov.getCurrPlayer(); // Current player
        game.update(mov); // Updating board and executing move through model

        if (mov.getCard().isTurn()) { // Sending view update if player is facing new direction after action
            controller.update(curr);
        }

        // Updating view to show board after each player's turn
        controller.update();

        if(curr.hasWon()){ // Updating view if a player's Move has led to a win
            controller.playerWon(curr);
        }
    }
}
