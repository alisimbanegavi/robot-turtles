package Controller;

import java.util.*;
import Model.*;

/**
 * Class representing abstraction of board configuration
 */
public class Board
{
    private int size;
    private Tile[][] config;
    private List<Turtle> turtles;
    private List<Jewel> jewels;

    public Board(int n, List<TurtleMaster> players, List<Jewel> gems)
    {
        size = n;
        config = new Tile[n][n];
        turtles = new ArrayList<Turtle>();
        for(TurtleMaster m: players) { turtles.add(m);} // Getting piece for each player
        placeTurtles(turtles);
        jewels = gems;
        placeJewels(gems);
    }

    public void placeTurtles(List<Turtle> trtlMasters)
    {
        // Placing all turtles on board
        for (Tile t: trtlMasters) {setTileAtPos(t.getCoord(), t);}
    }

    public void placeJewels(List<Jewel> jwls)
    {
        // Placing all jewels on board
        for (Jewel j: jwls) {setTileAtPos(j.getCoord(), j);}
    }

    public Tile getTileAtPos(Coordinate target)
    {
        // Returns tile at specific coordinate
        return config[target.getX()][target.getY()];
    }

    public void setTileAtPos(Coordinate target, Tile place)
    {
        // Sets tile at specific coordinate to specific Tile
        if(place == null) { removeTile(target);} // Removes tile if null value in inputted
        else{
            place.setCoordinate(target); // Place tile on board and update tile's coordinate
            config[target.getX()][target.getY()] = place;
        }
    }

    public boolean isEmpty(Coordinate c)
    {
        Tile tester = config[c.getX()][c.getY()];
        return (!(tester instanceof Turtle) && !(tester instanceof Jewel)); // Tests if tile is empty
    }

    public void shiftTile(Coordinate orig, Coordinate dest)
    {
        // Moving tile in desired direction
        Tile toMove = getTileAtPos(orig);
        setTileAtPos(dest, toMove);
        removeTile(orig);
    }

    public void removeTile(Coordinate toClear)
    {
        config[toClear.getX()][toClear.getY()] = null;
    }

    public void markWinner(TurtleMaster winner)
    {
        // Method to mark TurtleMaster as winner
        winner.won();
        removeTile(winner.getCoord());
        turtles.remove((Turtle)winner); // Remove player from board
    }

    public Coordinate coordAhead(Coordinate orig, Direction di)
    {
        // Calculates coordinate ahead based on shift in particular direction
        Coordinate newC = orig.copy();
        if ((di == Direction.NORTH) || (di == Direction.SOUTH)) // Condition for vertical chg
        {
            newC.setY((di == Direction.NORTH) ? orig.getY() - 1: orig.getY() + 1); // Chg vertical position by -1 if facing north and +1 if facing south
        }else // Condition for horizontal change
        {
            newC.setX((di == Direction.EAST) ? orig.getX() + 1: orig.getX() - 1); // Chg horizontal position by -1 if facing west and +1 if facing east
        }
        return newC;
    }

    public Coordinate coordBehind(Coordinate orig, Direction di)
    {
        // Calculates coordinate behind based on shift opposite to particular direction
        Coordinate newC = orig.copy();
        if ((di == Direction.NORTH) || (di == Direction.SOUTH)) // Condition for vertical chg
        {
            newC.setY((di == Direction.NORTH) ? orig.getY() + 1: orig.getY() - 1); // Chg vertical position by +1 if facing north and -1 if facing south
        }else // Condition for horizontal change
        {
            newC.setX((di == Direction.EAST) ? orig.getX() - 1: orig.getX() + 1); // Chg horizontal position by +1 if facing west and -1 if facing east
        }
        return newC;
    }

    public List<Turtle> getTurtles() {return turtles;} // Returns list of turtle tiles

    public List<Jewel> getJewels() {return jewels;} // Returns list of jewel tiles

    public int getSize()
    {
        return size;
    }
}
