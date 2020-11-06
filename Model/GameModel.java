package Model;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Class for game model used to check rules
 */
public abstract class GameModel
{
    public static final int DIMENSIONS = 8;

    private ArrayDeque<Turtle> players;
    private ArrayList <ColouredTile> jewels;
    private boolean finished = false;

    public GameModel(ArrayDeque<Turtle> players, ArrayList<ColouredTile> jewels) {
        this.players = players;
        this.jewels = jewels;
    }

    // Accessors
    public Boolean          isOver           () { return finished;}
    public Turtle           getCurrentPlayer () { return players.peek();}
    public ColouredTile[]   getJewels        () { return jewels.toArray  (new ColouredTile [jewels.size()]);}
    public Turtle []        getTurtles       () { return players.toArray (new Turtle [players.size()]);}
 
    // Modifiers
    public void move (Card choice){
        if (choice == Card.BUG)         undo();
        else {
            if (choice == Card.FORWARD) forward();
            else                        rotate(choice);

            Turtle currentPlayer = players.peek();
            currentPlayer.addMove(choice);
        }
    }

    private void forward (){
        Turtle currentPlayer = players.peek();
        Coordinate currCoord = currentPlayer.getCoordinate();
        Coordinate moveCoord;

        if      (currentPlayer.getDir() == Direction.NORTH) moveCoord = new Coordinate(currCoord.getX(), currCoord.getY()-1);   
        else if (currentPlayer.getDir() == Direction.SOUTH) moveCoord = new Coordinate(currCoord.getX(), currCoord.getY()+1);
        else if (currentPlayer.getDir() == Direction.EAST)  moveCoord = new Coordinate(currCoord.getX()+1, currCoord.getY());
        else                                                moveCoord = new Coordinate(currCoord.getX()-1, currCoord.getY()); 
                                        
        if (!moveCoord.outBounds(DIMENSIONS)){  
            boolean collision = false;
            Iterator <Turtle> playerItr = players.iterator();
            playerItr.next(); // Skips current player
            while (playerItr.hasNext() & !collision) // Check it does not have same coord as other players     
                collision = playerItr.next().getCoordinate().equals(moveCoord);
            
            if(!collision){
                Iterator <ColouredTile> jewelItr = jewels.iterator();
                while (jewelItr.hasNext())
                    if (jewelItr.next().getCoordinate().equals(moveCoord)){
                        currentPlayer.won();
                        jewelItr.remove();
                    } 
                currentPlayer.setCoordinate(moveCoord);
            }
        }
    }

    private void rotate (Card card){
        Turtle currentPlayer = players.peek();
        if (card == Card.LEFT){
            if      (currentPlayer.getDir() == Direction.NORTH ) currentPlayer.setDir(Direction.WEST);
            else if (currentPlayer.getDir() == Direction.WEST)   currentPlayer.setDir(Direction.SOUTH);
            else if (currentPlayer.getDir() == Direction.SOUTH)  currentPlayer.setDir(Direction.EAST);
            else                                                 currentPlayer.setDir(Direction.NORTH);
        }
        else if (card == Card.RIGHT){
            if      (currentPlayer.getDir() == Direction.NORTH ) currentPlayer.setDir(Direction.EAST);
            else if (currentPlayer.getDir() == Direction.EAST)   currentPlayer.setDir(Direction.SOUTH);
            else if (currentPlayer.getDir() == Direction.SOUTH)  currentPlayer.setDir(Direction.WEST);
            else                                                 currentPlayer.setDir(Direction.NORTH);
        }
    }

    private void undo (){
        Turtle currentPlayer = players.peek();
        Card previousMove = currentPlayer.getMove();
        if (previousMove == Card.FORWARD){
            Coordinate currCoord = currentPlayer.getCoordinate();
            if      (currentPlayer.getDir() == Direction.NORTH) currentPlayer.setCoordinate( new Coordinate(currCoord.getX(), currCoord.getY()+1));   
            else if (currentPlayer.getDir() == Direction.SOUTH) currentPlayer.setCoordinate( new Coordinate(currCoord.getX(), currCoord.getY()-1));
            else if (currentPlayer.getDir() == Direction.EAST)  currentPlayer.setCoordinate( new Coordinate(currCoord.getX()-1, currCoord.getY()));
            else                                                currentPlayer.setCoordinate( new Coordinate(currCoord.getX()+1, currCoord.getY()));  
        }
        else if (previousMove == Card.LEFT)  rotate(Card.RIGHT);
        else if (previousMove == Card.RIGHT) rotate(Card.LEFT);
    }

}