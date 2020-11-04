package Model;

public abstract class Tile
{
    private Coordinate coord;
    private Colour col;

    public Tile(Coordinate c)
    {
        setCoordinate(c);
        setCol(Colour.BLANK);
    }

    public Colour getCol() {
        return col;
    }

    public void setCol(Colour col) {
        this.col = col;
    }

    public void setCoordinate(Coordinate co)
    {
        coord = co;
    }

    public Coordinate getCoord()
    {
        return coord;
    }
}