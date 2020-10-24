public class Turtle extends Tile {
    private Direction dir;
    private Colour col;

    public Turtle(Coordinate c, Colour co, Direction d) {
        super(c);
        setDirection(d);
    }

    public void setDirection(Direction d)
    {
        dir = d;
    }

    public Direction getDir()
    {
        return dir;
    }

    public void setColour(Colour clr)
    {
        col = clr;
    }

    public Colour getColour()
    {
        return col;
    }
}
