package Model;

import Controller.*;
import java.util.*;

/**
 * Class for game model used to check rules
 */
public class GameModel
{
    private ArrayDeque<TurtleMaster> players;
    private Board board;
    private int maxSize;
    private boolean complete;

    public GameModel (List<TurtleMaster> tMasters, Board b)
    {
        board = b;
        players = new ArrayDeque<>(tMasters);
        complete = false;
        maxSize = b.getSize();
    }

    public boolean validate(Move chg)
    {
        // Checking if move is valid based on index of turtle's position on board and direction of turtle
        if (chg == null) {return false;} // Move is automatically invalid if null
        Card shift = chg.getCard();
        if((shift == Card.LEFT) || ((shift == Card.RIGHT))) {return true;}
        // Move is automatically valid if it is a left turn or right turn

        Turtle currPlayer = chg.getCurrPlayer();
        Direction currDir = currPlayer.getDir();
        Coordinate test = board.nextCoord(currPlayer.getCoord(), currDir);

        return ((!test.outBounds(maxSize)) && (board.isEmpty(test)));
        // Valid move if new coordinate is in bounds and space is empty
    }

    public boolean gameOver(){return complete;} //  Checking if game is over

    public ArrayDeque<TurtleMaster> getPlayers() {return players;} // Returns queue of players

    public Board getBoard() {return board;} // Returns game board

    public void updateBoard(Move chg) {
        if (validate(chg)) {
            chg.execute();
        }
    } // Updates board if valid move is executed


    public List<String> moveList() {return List.of("LEFT", "RIGHT", "FORWARD", "BUG");}
    // Helper method to return card instructions as strings; will help with parsing user input in GameController.promptMove()


    public int getMaxSize() {return maxSize;} // Returns maximum size of board according to model

}