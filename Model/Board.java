package Model;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Class representing abstraction of board configuration
 */
public class Board
{
    private int size;
    private List<Turtle> turtles;
    private List<Jewel> jewels;
    private Object[][] config;

    public Board(int n, List<Turtle> players, List<Jewel> jwls)
    {
        size = n;
        config = new BasicTile[n][n];
        turtles = new ArrayList<>(players);
        jewels = new ArrayList<>(jwls);
        placeTiles(); // Collecting all players and jewels into 1 list);
    }

    public void placeTiles() {
        // Adding turtles and jewels to list of all tiles
        List<BasicTile> toPlace = Stream.of(turtles, jewels)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        // Setting each tile at position
        for(BasicTile b: toPlace) {
            setTileAtPos(b.getCoord(), b);}
    }

    /**
     * Returns tile at coordinate specified
     * @param target Target coordinate
     * @return BasicTile A tile or null if coordinate is unoccupied
     */
    public BasicTile getTileAtPos(Coordinate target) {
        // Returns tile at specific coordinate
        return (BasicTile) config[target.getX()][target.getY()];
    }

    /**
     * Setting tile at particular coordinate on board
     * @param target Location where tile will be placed
     * @param toPlace Tile to put on coordinate
     */
    public void setTileAtPos(Coordinate target, BasicTile toPlace) {
        if((toPlace != null) && (!toPlace.getCoord().equals(target))) {// Changing coordinate of tile if it doesn't match with new position
            toPlace.setCoord(target);}

        config[target.getX()][target.getY()] = toPlace; // Saving to list of occupiedTiles coordinates
    }

    /**
     * Tests if coordinate is empty by checking array of tiles
     * @param c Coordinate to check
     * @return boolean True if Coordinate is empty
     */
    public boolean isClear(Coordinate c) {return (getTileAtPos(c) == null);}

    public void clear(Coordinate c) {
        config[c.getX()][c.getY()] = null;
    }

    /**
     * Moves a player's turtle while simultaeneously checking for Jewel. Turtle moves if no jewel is present or is marked winner and picks up jewels
     * @param toMove Turtle to be moved
     * @param dest New coordinate where Turtle will move
     */
    public void moveTurtle(Turtle toMove, Coordinate dest) {
        // Board checking if player Turtle is about to be moved onto jewel
        if(checkForJewel(dest)) {
            pickJewel(toMove, (Jewel) getTileAtPos(dest));} // Mark player as winner if they are about to pick up jewel
        else{
            Coordinate orig = toMove.getCoord();
            setTileAtPos(dest, toMove);
            clear(orig);} // Moving player to new tile if no jewel is found
    }

    public void pickJewel(Turtle winner, Jewel toPick) {
        winner.won(); // Setting player as winner
        turtles.remove(winner);
        clear(winner.getCoord()); // Removing Turtle tile
        jewels.remove(toPick);
        clear(toPick.getCoord()); // Removing Jewel tile
    }

    public boolean checkForJewel(Coordinate target) {return (getTileAtPos(target) instanceof Jewel);}
        // Helper method to check if coordinate is Jewel

    public List<Turtle> getTurtles() {return turtles;} // Returns list of turtle tiles

    public List<Jewel> getJewels() {return jewels;}
    //----------------------------------------------------------------------
    public int getSize(){return size;}
}