import java.util.ArrayList;
import java.util.ArrayDeque;

import java.util.Iterator;

public class GameModel {
    private GameState state = GameState.ONGOING;
    private ArrayList<Jewel>         jewels;
    private ArrayDeque<TurtleMaster> players;
    
    GameModel (String[] playerNames){
        if (playerNames.length > 0 && playerNames.length <= 4){
            jewels  = new ArrayList<Jewel>(playerNames.length);
            players = new ArrayDeque<TurtleMaster>(playerNames.length);
            switch (playerNames.length){
                case 4:
                    jewels.add (new Jewel(Tile.CENTER_TOP_RIGHT, Colour.GREEN));
                    players.addFirst(new TurtleMaster(Tile.TOP_RIGHT, Colour.GREEN, playerNames[3]));
                case 3:
                    jewels.add (new Jewel(Tile.CENTER_BOT_RIGHT, Colour.PURPLE));
                    players.addFirst(new TurtleMaster(Tile.BOT_RIGHT, Colour.PURPLE, playerNames[2]));
                case 2:
                    jewels.add (new Jewel(Tile.CENTER_BOT_LEFT, Colour.RED));
                    players.addFirst(new TurtleMaster(Tile.BOT_LEFT, Colour.RED, playerNames[1]));
                case 1:
                    jewels.add (new Jewel(Tile.CENTER_TOP_LEFT, Colour.BLUE));
                    players.addFirst(new TurtleMaster(Tile.TOP_LEFT, Colour.BLUE, playerNames[0]));
            }
        }
        else
            state = GameState.COMPLETE;
    }

    // Accessors
    public GameState    getGameState     () { return state;}
    public TurtleMaster getCurrentPlayer () { return players.peek();}
    public Jewel []            getJewels () { return jewels.toArray (new Jewel[jewels.size()]);}
    public TurtleMaster []     getPlayers() { return players.toArray(new TurtleMaster [players.size()]);}

    // Modifiers
    public void gameOver(){state = GameState.COMPLETE;}
    public boolean makeMove (Card card){
        TurtleMaster currentPlayer = players.peek();

        if (card == Card.FORWARD){
            Coordinate currCoord = currentPlayer.getCoord();
            Coordinate moveCoord;

            if      (currentPlayer.getDir() == Direction.NORTH) moveCoord = new Coordinate(currCoord.getX(), currCoord.getY()-1);   
            else if (currentPlayer.getDir() == Direction.SOUTH) moveCoord = new Coordinate(currCoord.getX(), currCoord.getY()+1);
            else if (currentPlayer.getDir() == Direction.EAST)  moveCoord = new Coordinate(currCoord.getX()+1, currCoord.getY());
            else                                                moveCoord = new Coordinate(currCoord.getX()-1, currCoord.getY()); //(currentPlayer.getDir() == Direction.WEST)  
                                         
            if (!moveCoord.outBounds()){
                // Check it does not have same coord as other players
                boolean collision = false;
                Iterator <TurtleMaster> playerItr = players.iterator();
                playerItr.next(); // Skips current player
                while (playerItr.hasNext()){
                    collision = playerItr.next().getCoord().equals(moveCoord);
                }
                
                if (collision) return false;
                else {
                    Iterator <Jewel> jewelItr = jewels.iterator();
                    while (jewelItr.hasNext())
                        if (jewelItr.next().getCoord().equals(moveCoord)){
                            currentPlayer.won();
                            jewelItr.remove();
                        } 
                    currentPlayer.setCoordinate(moveCoord);
                }
            }
            else return false;
        }
        else if (card == Card.LEFT_TURN) {
            if      (currentPlayer.getDir() == Direction.NORTH ) currentPlayer.setDirection(Direction.WEST);
            else if (currentPlayer.getDir() == Direction.WEST)   currentPlayer.setDirection(Direction.SOUTH);
            else if (currentPlayer.getDir() == Direction.SOUTH)  currentPlayer.setDirection(Direction.EAST);
            else                                                 currentPlayer.setDirection(Direction.NORTH);
        }
        else if (card == Card.RIGHT_TURN) {
            if      (currentPlayer.getDir() == Direction.NORTH ) currentPlayer.setDirection(Direction.EAST);
            else if (currentPlayer.getDir() == Direction.EAST)   currentPlayer.setDirection(Direction.SOUTH);
            else if (currentPlayer.getDir() == Direction.SOUTH)  currentPlayer.setDirection(Direction.WEST);
            else                                                 currentPlayer.setDirection(Direction.NORTH);
        }
        return true;        
    }
    public void undoMove (Card card, boolean wasVaildMove){
        TurtleMaster currentPlayer = players.peek();
        if (card == Card.FORWARD && wasVaildMove){
            Coordinate currCoord = currentPlayer.getCoord();
            if      (currentPlayer.getDir() == Direction.NORTH) currentPlayer.setCoordinate( new Coordinate(currCoord.getX(), currCoord.getY()+1));   
            else if (currentPlayer.getDir() == Direction.SOUTH) currentPlayer.setCoordinate( new Coordinate(currCoord.getX(), currCoord.getY()-1));
            else if (currentPlayer.getDir() == Direction.EAST)  currentPlayer.setCoordinate( new Coordinate(currCoord.getX()-1, currCoord.getY()));
            else                                                currentPlayer.setCoordinate( new Coordinate(currCoord.getX()+1, currCoord.getY()));  
        }
        else if (card == Card.LEFT_TURN)  makeMove(Card.RIGHT_TURN);
        else if (card == Card.RIGHT_TURN) makeMove(Card.LEFT_TURN);
    }
    public void finishTurn(){
        TurtleMaster currentPlayer = players.removeFirst();
        if (!currentPlayer.hasWon()) 
            players.add(currentPlayer);
        if (jewels.isEmpty())
            state = GameState.COMPLETE;
    }
}
