/**
 * @author Ali Simbanegavi + Daniel Cumming
 * @version C3721.2.0
 * @since 2020-11-07
 */

package Controller;
import Model.ColouredTile;
import Model.Coordinate;
import Model.Turtle;

/**
 * Class for parsing board configuration to game view. This class allows for the configuration of the Board, which is contained
 * in the model, to be passed onto the view without contaminating the view by having it access the model directly.
 */
public abstract class BoardConverter {
    public static int [][] parseJewels(ColouredTile[] jewels)
    {
        int [][] results = new int [jewels.length][3];
        for (int i = 0; i < jewels.length; i++){
            Coordinate coord = jewels[i].getCoordinate();
            results[i][0] = coord.getX();
            results[i][1] = coord.getY();
            results[i][2] = jewels[i].getColour().ordinal();
        }
        return results;
    }

    public static int [][] parseTurtles(Turtle[] turtles){
        int [][] results = new int [turtles.length][4];
        for (int i = 0; i < turtles.length; i++){
            Coordinate coord = turtles[i].getCoordinate();
            results[i][0] = coord.getX();
            results[i][1] = coord.getY();
            results[i][2] = turtles[i].getColour().ordinal();
            results[i][3] = turtles[i].getDir().ordinal();
        }
        return results;
    }

}