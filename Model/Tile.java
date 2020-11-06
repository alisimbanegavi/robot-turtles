package Model;

public abstract class Tile
{
    private Coordinate coord;

    public Tile(Coordinate c){
        coord = c;
    }

    public void setCoordinate(Coordinate newCoordinate) {
        coord = newCoordinate;
    }

    public Coordinate getCoordinate() {
        return coord;
    }
}