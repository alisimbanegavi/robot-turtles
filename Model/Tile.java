package Model;

public abstract class Tile
{
    private Coordinate coord;

    public Tile(Coordinate c)
    {
        setCoordinate(c);
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