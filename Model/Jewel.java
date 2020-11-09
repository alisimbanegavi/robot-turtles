package Model;

public class Jewel extends Tile { // Class for Jewel objects
    private Colour col;

    public Jewel(Coordinate c, Colour co) {
        super(c, co);
    }

    public boolean compareJewel(Jewel j) {
        return ((j.getCoord() == this.getCoord()) && (j.getColour() == col));
    }
}