package Model;
import Controller.*;
import java.util.*;

/**
 * Class for parsing board configuration to game view
 */
public class BoardConverter {
    private Board gameBoard;

    public BoardConverter(Board reference){
        gameBoard = reference;
    }

    public int [][] parseJewels()
    {
        List<Jewel> jwls = gameBoard.getJewels();
        Jewel[] tiles = new Jewel[jwls.size()];
        tiles = jwls.toArray(tiles);
        int [][] results = new int [tiles.length][3];
        for (int i = 0; i < tiles.length; i++){
            Coordinate coord = tiles[i].getCoord();
            results[i][0] = coord.getX();
            results[i][1] = coord.getY();
            results[i][2] = tiles[i].getColour().ordinal();
        }
        return results;
    }

    public int [][] parseTurtles(){
        List<Turtle> trtls = gameBoard.getTurtles();
        Turtle[] tiles = new Turtle[trtls.size()];
        tiles = trtls.toArray(tiles);
        int [][] results = new int [tiles.length][4];
        for (int i = 0; i < tiles.length; i++){
            Coordinate coord = tiles[i].getCoord();
            results[i][0] = coord.getX();
            results[i][1] = coord.getY();
            results[i][2] = tiles[i].getColour().ordinal();
            results[i][3] = tiles[i].getDir().ordinal();
        }
        return results;
    }

}