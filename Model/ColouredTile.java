package Model;

public abstract class ColouredTile extends Tile{
    Colour colour;
    ColouredTile(Coordinate coord, Colour colour){
        super(coord);
        this.colour = colour;
    }
    
    public Colour getColour () { return colour;}
}
