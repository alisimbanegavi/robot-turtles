package Model;

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

    public Board(int n, List<Turtle> players, List<Jewel> gems)
    {
        size = n;
        config = new Tile[n][n];
        turtles = players;
        placeTurtles(turtles);
        jewels = gems;
        placeJewels(gems);
    }

    public void placeTurtles(List<Turtle> trtls)
    {
        // Placing all turtles on board
        for (Tile t: trtls) {setTileAtPos(t.getCoord(), t);}
    }

    public void placeJewels(List<Jewel> jwls)
    {
        // Placing all jewels on board
        for (Jewel j: jwls) {setTileAtPos(j.getCoord(), j);}
    }

    public boolean checkForJewel(Coordinate target)
    {
        return (getTileAtPos(target) instanceof Jewel); // Helper method to check for jewel
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

    public void shiftTurtle(Turtle toMove, Coordinate dest)
    {
        // Moving tile in desired direction
        Coordinate orig = toMove.getCoord();
        if(checkForJewel(dest)) { // Checking if player Turtle is about to be moved onto jewel
            markWinner(toMove); // Mark player as winner if they are picking up jewel and remove corresponding turtle from board
            jewels.remove(getTileAtPos(dest)); // Removing from Board's list of current Jewels
            removeTile(dest);}
        else {
            setTileAtPos(dest, toMove);
            removeTile(orig);} // If player is not moving to jewel, Set destination tile to Turtle and clear previous tile
    }

    public void removeTile(Coordinate toClear)
    {
        config[toClear.getY()][toClear.getY()] = null; // Clearing space where target was
    }

    public void markWinner(Turtle winner)
    {
        // Method to mark Turtle as winner
        winner.won();
        removeTile(winner.getCoord());
        turtles.remove(winner); // Remove player from board
        //TODO: FIX THIS, WIN NOT TAKING JEWEL OFF BOARD
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
