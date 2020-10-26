package Model;

import Controller.*;
import java.util.*;

/**
 * TurtleMaster class
 */
public class TurtleMaster extends Turtle
{
    private String name;
    private Stack<Card> cardSequence;
    private boolean winner;

    public TurtleMaster(Coordinate c, Colour colour, Direction d)
    {
        super(c, colour, d);
        cardSequence = new Stack<>();
        winner = false;
    }

    public void setPlayerName(String n)
    {
        name = n;
    }

    public String getPlayerName() { return name;}

    public Boolean hasWon() { return winner;}

    public Stack<Card> cardSeq()
    {
        // Returns sequence of cards executed by player
        return cardSequence;
    }
    
    public void addToCardSeq(Card toAdd)
    {
        cardSequence.push(toAdd);
    }

    public Card removeFromSeq()
    {
        // Getting last card move executed by player
        if (cardSequence.size() > 0) {return cardSequence.pop();}
        return null;
    }

    public void won() {winner = true;}
}