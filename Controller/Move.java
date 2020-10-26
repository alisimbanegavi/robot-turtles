package Controller;
import Model.*;
import java.util.*;

/**
 * Class for executing player instructions.
 */
public class Move
{
    private Turtle player;
    private Card instruction;
    private Direction currDirection;
    private Board gBoard;

    private static final Map<Direction, Integer> moveDirs = Map.of(
            Direction.NORTH, -1,
            Direction.SOUTH, +1,
            Direction.EAST, -1,
            Direction.WEST, +1
    ); // Mapping directions to corresponding number for change to an index if moving in a particular direction

    public Move(Turtle t, Card input, Board target)
    {
        player = t;
        instruction = input;
        currDirection = t.getDir();
    }

    public Turtle getCurrPlayer()
    {
        return player;
    }

    public Card getMove()
    {
        return instruction;
    }

    public void execute()
    {
        if (instruction == Card.FORWARD) {forward();}
        else {rotate();}
    }

    public void forward()
    {
        // Calculating new coordinate and performing shift
        Coordinate orig = player.getCoord();
        Coordinate next = gBoard.newCoord(orig, currDirection);
        gBoard.shiftTile(orig, next);
    }

    public void rotate()
    {
        // Assigning each direction a numerical value that will change based on turn direction
        Map<Direction, Integer> turnDirection = Map.of(
                Direction.NORTH, 1,
                Direction.SOUTH, 2,
                Direction.EAST, 3,
                Direction.WEST, 4
        );
        int n = turnDirection.get(currDirection); // Getting number that corresponds to current direction

        n = (instruction == Card.LEFT_TURN) ? (n-1) % 4: (n+1) % 4; // Decrement by 1 if left turn, increment by 1 otherwise
        for(Direction d: turnDirection.keySet())
        {
            if (n == turnDirection.get(n)) {
                player.setDirection(d);
                currDirection = d;
                break;} // Update current direction by finding key corresponding to new value of direction facing
        }
    }

    public boolean checkForJewel(Coordinate target)
    {
        return (gBoard.getTileAtPos(target) instanceof Jewel); // Helper method to check for jewel
    }

    //TODO: BUG
}
