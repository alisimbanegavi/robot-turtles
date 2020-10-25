public abstract class Tile {
    private Coordinate coord;
    public static final int MAX_X = 8-1;
    public static final int MAX_Y = 8-1;
    public static final Coordinate TOP_LEFT  = new Coordinate(0, 0);
    public static final Coordinate TOP_RIGHT = new Coordinate(MAX_X, 0);
    public static final Coordinate BOT_LEFT  = new Coordinate(0, MAX_Y);
    public static final Coordinate BOT_RIGHT = new Coordinate(MAX_X, MAX_Y);

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
