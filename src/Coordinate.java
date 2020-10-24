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
}
