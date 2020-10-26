package Controller;
import Model.*;
import java.util.*;

/**
 * Class for executing player instructions.
 */
public class Move
{
    private TurtleMaster player;
    private Turtle playerTile;
    private Card instruction;
    private Direction currDirection;
    private Board gBoard;

    private static final Map<Direction, Integer> moveDirs = Map.of(
            Direction.NORTH, -1,
            Direction.SOUTH, +1,
            Direction.EAST, -1,
            Direction.WEST, +1
    ); // Mapping directions to corresponding number for change to an index if moving in a particular direction

    public Move(TurtleMaster t, Card input, Board target)
    {
        player = t;
        playerTile = (Turtle) t;
        instruction = input;
        currDirection = t.getDir();
        gBoard = target;
    }

    public TurtleMaster getCurrPlayer()
    {
        return player;
    }

    public Card getCard()
    {
        return instruction;
    }

    public void execute()
    {
        // Executing move requested by player
        if (instruction == Card.FORWARD) {step();} // Step forward
        else if (instruction == Card.BUG) {bug();} // Bug card
        else {rotate(instruction);} // Turn left or right
    }

    public void step()
    {
        // Calculating new coordinate and performing shift
        Coordinate orig = player.getCoord();
        Coordinate next = gBoard.newCoord(orig, currDirection);
        if(checkForJewel(next)) {gBoard.markWinner(player);}
        gBoard.shiftTile(orig, next);
    }

    public void rotate(Card side)
    {
        // Assigning each direction a numerical value that will change based on turn direction
        Map<Direction, Integer> turnDirection = Map.of(
                Direction.NORTH, 1,
                Direction.EAST, 2,
                Direction.SOUTH, 3,
                Direction.WEST, 4
        );
        int n = turnDirection.get(currDirection); // Getting number that corresponds to current direction

        n = (side == Card.LEFT) ? (n-1) % 4: (n+1) % 4; // Decrement by 1 if left turn, increment by 1 otherwise
        for(Direction d: turnDirection.keySet())
        {
            if (n == turnDirection.get(d)) {
                playerTile.setDirection(d);
                currDirection = d;
                break;} // Update current direction by finding key corresponding to new value of direction facing
        }
    }

    public void bug()
    {
        // Undoes move performed by last card used by TurtleMaster
        if(player.cardSeq().size() != 0) // Reversal only executed if previous card sequence is not empty
        {
            Card buggy = player.removeFromSeq(); // Last card executed

            if (buggy.equals(Card.FORWARD)) {bugStep();}
            else {bugRotate(buggy);}
        }
    }

    public void bugRotate(Card side)
    {
        // Helper method for bug() if last card executed by player was turning left or turning right
        if (side == Card.LEFT) {rotate(Card.RIGHT);}
        else {rotate(Card.LEFT);}
    }

    public void bugStep()
    {
        // Helper method for bug() if last card executed by player was forward step
        Direction saveDir = currDirection;
        player.setDirection(reverseDirection());
        step(); // Turtle takes step in direction opposite from the one they are facing
        player.setDirection(saveDir); // Turtle faces original direction after step backwards
    }

    public Direction reverseDirection()
    {
        // Helper method for bugStep() to reverse direction movement of player movement
        if((currDirection == Direction.SOUTH) || (currDirection == Direction.NORTH))
        {
            return (currDirection == Direction.SOUTH) ? Direction.NORTH: Direction.SOUTH; // Returns south if facing north & vice versa
        }
        return (currDirection == Direction.EAST) ? Direction.WEST: Direction.EAST; // Returns east if facing west & vice versa
    }

    public boolean checkForJewel(Coordinate target)
    {
        return (gBoard.getTileAtPos(target) instanceof Jewel); // Helper method to check for jewel
    }
}
