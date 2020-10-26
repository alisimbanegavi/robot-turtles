package Model;

import Controller.*;
import java.util.*;

/**
 * TurtleMaster class
 */
public class TurtleMaster extends Turtle
{
    private String name;
    private PlayerState state;
    private Stack<Card> cardSequence;

    public TurtleMaster(Coordinate c, Colour colour, Direction d)
    {
        super(c, colour, d);
        state = PlayerState.PLAYING;
        cardSequence = new Stack<>();
    }

    public void setPlayerName(String n)
    {
        name = n;
    }

    public String getPlayerName() { return name;}

    public Boolean hasWon() { return state == PlayerState.WON;}

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

    public void won() {state = PlayerState.WON;}
}