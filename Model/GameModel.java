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
        if (chg.getCard() == null) {return false;} // Move is automatically invalid if null
        Card shift = chg.getCard();

        if (shift == Card.BUG){return validBug(chg);} // Check for valid bug if bug card is inputted

        if((shift == Card.LEFT) || (shift == Card.RIGHT)) {return true;}
        // Move is automatically valid if move is a left turn or right turn

        Turtle currPlayer = chg.getCurrPlayer();
        Direction currDir = currPlayer.getDir();
        Coordinate test = board.coordAhead(currPlayer.getCoord(), currDir);
        // If new coordinate will

        return ((!test.outBounds(maxSize)) && (board.isEmpty(test) || (board.getTileAtPos(test) instanceof Jewel)));
        // Valid move if new coordinate is in bounds and space is empty
    }

    public boolean validBug(Move chg)
    {
        // Method to test is bug card move is valid based on index of turtle's position on board and direction of turtle
        if (chg.getCard() != Card.BUG) {return false;} // False if bug card is not being evaluated

        TurtleMaster toBug = chg.getCurrPlayer();
        if(toBug.cardSeq().peek() == Card.FORWARD) // Test if bug card is requesting move backwards
        {
            Coordinate test = board.coordBehind(toBug.getCoord(), toBug.getDir()); // Testing coordinate behind bug
            return (board.isEmpty(test) || !(board.getTileAtPos(test) instanceof Turtle)); // True if space is empty or not another turtle
        }
        return true; // Automatically true if bug card is reversing right or left turn
    }

    public boolean gameOver(){return complete;} //  Checking if game is over

    public ArrayDeque<TurtleMaster> getPlayers() {return players;} // Returns queue of players

    public Board getBoard() {return board;} // Returns game board

    public void updateBoard(Move chg) {
        if (validate(chg)) {
            chg.execute();
            if (chg.checkBoard().getTurtles().size() == 0) {complete = true;} // Mark game complete if all players have collected their jewels and board is empty
        }
    } // Updates board if valid move is executed

    public List<String> moveList() {return List.of("LEFT", "RIGHT", "FORWARD", "BUG");}
    // Helper method to return card instructions as strings; will help with parsing user input in GameController.promptMove()


    public int getMaxSize() {return maxSize;} // Returns maximum size of board according to model

}