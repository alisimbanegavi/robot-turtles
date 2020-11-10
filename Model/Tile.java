package Model;

public abstract class Tile {
    private Coordinate coord;
    private Colour col;

    public Tile(Coordinate c, Colour clr) {
        setCoord(c);
        setColour(clr);
    }

    public void setCoord(Coordinate co) {
        coord = co;
    }

    public Coordinate getCoord() {
        return coord;
    }

    public Colour getColour() {
        return col;
    }

    public void setColour(Colour c) {
        col = c;
    }
}