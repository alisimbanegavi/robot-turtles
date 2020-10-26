package Model;

public class Jewel extends ColouredTile {
    private Colour col;

    public Jewel(Coordinate c, Colour co) {
        super(c, co);
    }

    public void setColour(Colour clr) {
        col = clr;
    }
}