public class Turtle extends PlayerTile {    
    private Direction dir = null;

    public Turtle(Coordinate coord, Colour colour) {
        super(coord, colour);
        if      (coord.equals(TOP_LEFT))  dir = Direction.SOUTH;
        else if (coord.equals(BOT_LEFT))  dir = Direction.EAST;
        else if (coord.equals(BOT_RIGHT)) dir = Direction.NORTH;
        else if (coord.equals(TOP_RIGHT)) dir = Direction.WEST;
        
    }
    
    public void setDirection(Direction new_dir) {
        dir = new_dir;
    }

    public Direction getDir() {
        return dir;
    }
}
