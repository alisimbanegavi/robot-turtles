package Model;

public abstract class BasicTile
{
    private Coordinate coord;

    public BasicTile(Coordinate c) {
        setCoord(c);
    }

    public void setCoord(Coordinate co) {coord = co;}

    public Coordinate getCoord() {return coord;}
}