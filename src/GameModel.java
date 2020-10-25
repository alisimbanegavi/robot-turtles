import java.util.ArrayList;
import java.util.Iterator;
import java.util.ArrayDeque;

public class GameModel {
    private GameState state = GameState.ONGOING;
    private ArrayList<Jewel>         jewels;
    private ArrayDeque<TurtleMaster> players;
    
    GameModel (String[] players){
        
    }

    public Tile[] getBoard () {
        Iterator<Jewel>         jewel_it  = jewels.iterator();
        Iterator<TurtleMaster>  turtle_it = players.iterator();
        int i = 0;
        Tile [] board = new Tile [jewels.size() + players.size()];
        while (jewel_it.hasNext()){
            board[i++] = jewel_it.next();
        }
        while (turtle_it.hasNext()){
            board[i++] = turtle_it.next();
        }
        return board;
    }

    public GameState    getGameState ()     { return state;}
    public TurtleMaster getCurrentPlayer () { return players.peek();}
    
}
