package Controller;

import View.GameView;

import Model.Board;
import Model.Colour;
import Model.ColouredTile;
import Model.Coordinate;
import Model.Direction;
import Model.Jewel;
import Model.Player;
import Model.Turtle;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Scanner;



/**
 * Class to initialize game by assigning players and populating board
 */
public abstract class GameInitializer {
    
    public static Board createGame(){
        Scanner input = new Scanner (System.in);
        int nPlayers = 0;
        while (nPlayers < 1 || nPlayers > 4) { // Prompt until valid answer is given
            GameView.displayPrompt("Enter number of players (1-4): ");
            nPlayers = input.nextInt();
        }
        input.close();
        return new Board (createTurtles(nPlayers), createJewels(nPlayers));
    }

    private static ArrayDeque<Turtle> createTurtles(int nPlayers) {
        Scanner input = new Scanner (System.in);
        
        // Creates list of n players and assigns each player's piece to a corner coordinate
        Colour[] colours = Colour.values();
        Direction[] directions = Direction.values();
        Coordinate[] locations = cornerCoordinates();
        ArrayDeque<Turtle> playerList = new ArrayDeque<Turtle>();

        // Assigning each player to a new Turtle and adding to list of players
        String name;
        for(int i = 0; i < nPlayers; i++) {
            GameView.displayPrompt("Enter Player "+(i+1)+"'s name: ");
            name = input.next();            
            playerList.add(new Player(locations[i], colours[i], directions[i], name));
        }
        input.close();
        return playerList;
    }

    private static ArrayList<ColouredTile> createJewels(int nPlayers)
    {
        // List of jewels generated for each player with coordinates assigned to center
        Colour[] colours = Colour.values();
        Coordinate[] locations = centerCoordinates();
        ArrayList<ColouredTile> jewels = new ArrayList<>();
        
        for(int i = 0; i < nPlayers; i++)
            jewels.add(new Jewel(locations[i], colours[i]));
        return jewels;
    }

    private static Coordinate [] cornerCoordinates() {
        // Generating list of corner coordinates
        Coordinate[] corners = { new Coordinate(0, 0),                                       // Northwest corner
                                 new Coordinate(0, Board.DIMENSIONS - 1),                    // Southwest corner
                                 new Coordinate(Board.DIMENSIONS - 1, Board.DIMENSIONS - 1), // Southeast corner
                                 new Coordinate(Board.DIMENSIONS - 1, 0)};                   // Northeast corner
        return corners;
    }

    private static Coordinate[] centerCoordinates() {
        Coordinate[] center = { new Coordinate((Board.DIMENSIONS/2)-1,   (Board.DIMENSIONS/2)-1),    // Northwest center corner
                                new Coordinate((Board.DIMENSIONS/2)-1,   (Board.DIMENSIONS/2)),      // Southwest center corner
                                new Coordinate((Board.DIMENSIONS/2),     (Board.DIMENSIONS/2)),      // Southeast center corner
                                new Coordinate((Board.DIMENSIONS/2),     (Board.DIMENSIONS/2)-1)};   // Northeast center corner
        return center;
    }
}