\public class Turtle extends Tile {
    private Direction dir;
    private Colour col;
    private String name;
    private PlayerState state = PlayerState.PLAYING;

    public Turtle(Coordinate c, Colour co, Direction d, String name) {
        super(c);
        col = co;
        dir = d;
        // setDirection(d);
    }

    public void won() { state = PlayerState.WON;}
    public void setDirection(Direction d)
    {
        dir = d;
    }

    public Direction getDir()
    {
        return dir;
    }

    public PlayerState getState () { return state;}
    // public void setColour(Colour clr)
    // {
    //     col = clr;
    // }

    public Colour getColour()
    {
        return col;
    }
}
