package Model;

public class Turtle extends ColouredTile {
    private Direction dir = null;

    public Turtle(Coordinate c, Colour colour, Direction d) {
        super(c, colour);
        setDirection(d);
    }

    public void setDirection(Direction new_dir) {
        dir = new_dir;
    }

    public Direction getDir() {
        return dir;
    }
}