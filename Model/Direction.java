package Model;

import java.util.Map;

public enum Direction {
    NORTH,
    SOUTH,
    EAST,
    WEST;

    public Direction reverse() {
        // Helper method for bugStep() to reverse direction movement of player movement
        if ((this == Direction.SOUTH) || (this == Direction.NORTH)) {
            return (this == Direction.SOUTH) ? Direction.NORTH : Direction.SOUTH; // Returns south if facing north & vice versa
        }
        return (this == Direction.EAST) ? Direction.WEST : Direction.EAST; // Returns east if facing west & vice versa
    }

    public Direction turnDir(String side) {
        /*
        // Assigning each direction a numerical value that will change based on turn direction
        if (!side.toUpperCase().equals("LEFT") || !side.toUpperCase().equals("RIGHT")) {
            return null;} // Nothing happens if left or right is not input string
        */
        Map<Direction, Integer> turnDirection = Map.of(
                Direction.NORTH, 0,
                Direction.EAST, 1,
                Direction.SOUTH, 2,
                Direction.WEST, 3
        );
        int n = turnDirection.get(this); // Getting number that corresponds to current direction

        n = (side.toUpperCase().equals("LEFT")) ? ((n - 1) + 4) % 4 : ((n + 1) + 4) % 4;
        // Decrement value by 1 if left turn, increment by 1 if right turn - mod 4 calculation added for wraparound from north to west via left turn
        Direction result = this;

        for (Direction d : turnDirection.keySet()) // Searching for value corresponding to key
        {
            if (n == turnDirection.get(d)) {
                result = d;
                break;
            }
        }// Return updated direction

        return result;
    }
}