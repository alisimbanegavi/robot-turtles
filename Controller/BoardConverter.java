/**
 * @author Ali Simbanegavi + Daniel Cumming
 * @version C3721.2.0
 * @since 2020-11-07
 */

package Controller;
import Model.Board;
import Model.Coordinate;
import Model.Jewel;
import Model.Turtle;

import java.util.*;

/**
 * Class for parsing board configuration to game view. This class allows for the configuration of the Board, which is contained
 * in the model, to be passed onto the view without contaminating the view by having it access the model directly.
 */
public class BoardConverter {
    private Board gameBoard;

    public BoardConverter(Board reference){
        gameBoard = reference;
    }

    public int [][] parseJewels()
    {
        List<Jewel> jwls = gameBoard.getJewels();
        int [][] results = new int [jwls.size()][3];
        for (int i = 0; i < jwls.size(); i++){
            Coordinate coord = jwls.get(i).getCoord();
            results[i][0] = coord.getX();
            results[i][1] = coord.getY();
            results[i][2] = jwls.get(i).getCol().ordinal();
        }
        return results;
    }

    public int [][] parseTurtles(){
        List<Turtle> trtls = gameBoard.getTurtles();
        int [][] results = new int [trtls.size()][4];
        for (int i = 0; i < trtls.size(); i++){
            Coordinate coord = trtls.get(i).getCoord();
            results[i][0] = coord.getX();
            results[i][1] = coord.getY();
            results[i][2] = trtls.get(i).getCol().ordinal();
            results[i][3] = trtls.get(i).getDir().ordinal();
        }
        return results;
    }

}