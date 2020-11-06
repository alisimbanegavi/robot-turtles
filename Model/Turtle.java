package Model;

import java.util.Stack;

public abstract class Turtle extends ColouredTile {
    private String name;
    private Stack<Card> moves;
    private Direction direction;
    private boolean capturedJewel = false;

    public Turtle(Coordinate coord, Colour colour, Direction dir, String name) {
        super(coord, colour);
        this.direction = dir;
        this.name = name;
        this.moves = new Stack<>();
    }

    // Accessors
    public Boolean      hasWon   () { return capturedJewel; }
    public Direction    getDir   () { return direction; }
    public String       getName  () { return name; }
    public Stack<Card>  getMoves () { return moves; }

    // Modifiers
    public void setDir(Direction newDirection) {
        direction = newDirection;
    }

    public void addMove (Card move){
        moves.push(move);
    }

    public void won() { capturedJewel = true; }
}