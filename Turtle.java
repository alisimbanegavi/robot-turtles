package Model;

import java.util.Stack;

public class Turtle extends BasicTile
{
    private Direction dir;
    private String name;
    private Stack<Card> cardSequence;
    private boolean winner;
    private Colour turtleCol;

    public Turtle(Coordinate c, Colour colour, Direction d) {
        super(c, colour);
        setDir(d);
        cardSequence = new Stack<>();
        winner = false;
    }

    public void setDir(Direction new_dir) {
        dir = new_dir;
    }

    public Direction getDir() {
        return dir;
    }

    public void setPlayerName(String n)
    {
        name = n;
    }

    public String getPlayerName() { return name;}

    public boolean hasWon() { return winner;}

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