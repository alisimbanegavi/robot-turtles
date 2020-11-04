package Model;
import java.util.*;

/**
 * Class for executing player instructions.
 */
public class Move
{
    private Turtle player;
    private Card instruction;
    private Board gBoard;
    private boolean bugCard;


    public Move(Turtle t, Card input, Board target)
    {
        player = t;
        instruction = input;
        gBoard = target;
        bugCard = (input == Card.BUG);
    }

    public Turtle getCurrPlayer()
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
        if(!bugCard) {player.addToCardSeq(instruction);} // Adding to sequence of player's executed cards if not bug
        if (instruction == Card.FORWARD) {step();} // Step forward
        else if (instruction == Card.BUG) {bug();} // Bug card
        else {rotate(instruction);} // Turn left or right
    }

    public void step()
    {
        // Calculating new coordinate and performing shift
        Coordinate next = gBoard.coordAhead(player.getCoord(), player.getDir());
        gBoard.shiftTurtle(player, next);
    }

    public void rotate(Card side)
    {
        // Assigning each direction a numerical value that will change based on turn direction
        Map<Direction, Integer> turnDirection = Map.of(
                Direction.NORTH, 0,
                Direction.EAST, 1,
                Direction.SOUTH, 2,
                Direction.WEST, 3
        );
        int n = turnDirection.get(player.getDir()); // Getting number that corresponds to current direction

        n = (side == Card.LEFT) ? (n-1) % 4: (n+1) % 4; // Decrement value by 1 if left turn, increment by 1 if right turn
        for(Direction d: turnDirection.keySet())
        {
            if (n == turnDirection.get(d)) {
                player.setDir(d);
                break;} // Update current direction by finding key corresponding to new value of direction player is facing
        }
    }

    public void bug()
    {
        // Undoes move performed by last card used by Turtle
        if(player.cardSeq().size() > 0) // Reversal only executed if previous card sequence is not empty
        {
            Card buggy = player.removeFromSeq(); // Last card executed

            if (buggy == Card.FORWARD) {bugStep();}
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
        Direction saveDir = player.getDir();
        player.setDir(reverseDirection(saveDir)); // Getting opposite direction of last step forward
        step(); // Turtle takes step in direction opposite from the one they are facing
        player.setDir(saveDir); // Turtle faces original direction after step backwards
    }

    public Direction reverseDirection(Direction currDir)
    {
        // Helper method for bugStep() to reverse direction movement of player movement
        if((currDir == Direction.SOUTH) || (currDir == Direction.NORTH))
        {
            return (currDir == Direction.SOUTH) ? Direction.NORTH: Direction.SOUTH; // Returns south if facing north & vice versa
        }
        return (currDir == Direction.EAST) ? Direction.WEST: Direction.EAST; // Returns east if facing west & vice versa
    }

    public Board checkBoard() {return gBoard;} // Returns copy of board move is enacted on
}
