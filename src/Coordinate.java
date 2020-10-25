public class Coordinate
{
    private int xPos;
    private int yPos;

    public Coordinate(int x, int y)
    {
        setX(x);
        setY(y);
    }

    public void setX(int x)
    {
        xPos = x;
    }

    public void setY(int y)
    {
        yPos = y;
    }

    public int getX()
    {
        return xPos;
    }

    public int getY()
    {
        return yPos;
    }

    @Override
    public boolean equals(Object other){
        if (this == other) return true;
        if (!(other instanceof Coordinate)) return false;
        
        Coordinate tmp = (Coordinate) other;
        return this.xPos == tmp.xPos && this.yPos == tmp.yPos;
    }

    public boolean outBounds (){
        return xPos < 0 || xPos > Tile.MAX_X || yPos < 0 || yPos > Tile.MAX_Y;
    }
}
