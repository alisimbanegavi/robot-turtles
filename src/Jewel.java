public class Jewel extends Tile {
    private Colour col;

    public Jewel(Coordinate c, Colour co) {
        super(c);
        setColour(co);
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
