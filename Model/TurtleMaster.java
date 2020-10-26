package Model;

/**
 * TurtleMaster class
 */
public class TurtleMaster extends Turtle
{
    private String name;
    private PlayerState state;

    public TurtleMaster(Coordinate c, Colour colour, Direction d)
    {
        super(c, colour, d);
        state = PlayerState.PLAYING;
    }

    public void setPlayerName(String n)
    {
        name = n;
    }
    // Accessors
    public String getName() { return name;}
    public Boolean hasWon() { return state == PlayerState.WON;}

    // Modifiers
    public void won() {state = PlayerState.WON;}
}