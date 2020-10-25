public abstract class PlayerTile extends Tile {
    private Colour colour;

    PlayerTile (Coordinate coord, Colour colour) {
        super(coord);
        this.colour = colour;
    }

    public Colour getColour() { return colour;}
}
