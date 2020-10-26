package Model;

import Controller.*;
import java.util.*;

/**
 * Class for game model used to check rules
 */
public class GameModel
{
    private GameState state;
    private ArrayDeque<TurtleMaster> players;
    private Board board;
    private int maxSize;

    public GameModel (List<TurtleMaster> tMasters, Board b)
    {
        board = b;
        players = new ArrayDeque<>(tMasters);
        state = GameState.ONGOING;
        maxSize = b.getSize();
    }

    public boolean validate(Move chg)
    {
        // Checking if move is valid based on index of turtle's position on board and direction of turtle
        Card shift = chg.getMove();
        if((shift == Card.LEFT_TURN) || ((shift == Card.RIGHT_TURN))) {return true;}
        // Controller.Move is valid if it is a left turn or right turn

        Turtle currPlayer = chg.getCurrPlayer();
        Direction currDir = currPlayer.getDir();
        Coordinate test = board.newCoord(currPlayer.getCoord(), currDir);

        return ((!test.outBounds(maxSize)) && (board.isEmpty(test)));
        // Valid move if new coordinate is in bounds and space is empty

        // TODO: BUG
    }

    public GameState getGameState(){ return state;}

    public boolean gameOver(){return (state == GameState.COMPLETE);} //  Checking if game is over

    public ArrayDeque<TurtleMaster> getPlayers() {return players;} // Returns queue of players

    public Board getBoard() {return board;} // Returns game board

    public void updateBoard(Move chg) {if (validate(chg)) {chg.execute();}} // Updates board if valid move is executed

    public int getMaxSize() {return maxSize;} // Returns maximum size of board according to model

    public void markWinner(TurtleMaster winner)
    {
        // Method to mark TurtleMaster as winner
        winner.won();
        players.remove(winner); // Remove player from queue
    }
}