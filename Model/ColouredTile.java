package Model;

import Model.Colour;

public abstract class ColouredTile extends Tile
{
    private Colour col;

    public ColouredTile(Coordinate c, Colour colour) {
        super(c);
        col = colour;
    }

    public Colour getColour()
    {
        return col;
    }
}
