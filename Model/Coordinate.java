package Model;

public class Coordinate
{
    private int x, y;

    public Coordinate(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){ return x; }

    public int getY(){ return y; }

    @Override
    public boolean equals(Object other){
        if (this == other) return true;
        if (!(other instanceof Coordinate)) return false;

        Coordinate tmp = (Coordinate) other;
        return this.x == tmp.x && this.y == tmp.y;
    }

    // public Coordinate copy() {
    //     // Returns deep copy of coordinate
    //     return new Coordinate(x, y);
    // }

    public boolean outBounds(int MAX) {
        return x < 0 || x >= MAX || y < 0 || y >= MAX;
    }
}