package Controller;

import Model.*;
import View.*;
import java.util.*;

/**
 * Class to initialize game by assigning players and populating board
 */
public class GameInitializer
{
    private static final int SIZE = 8;
    private List<Turtle> initPlayers;
    private Board initBoard;
    private GameView view;
    private int nPlayers;
    private Scanner input;

    public GameInitializer(GameView mainView)
    {
        input = new Scanner(System.in);
        view = mainView;
    }

    public boolean start()
    {
        view.displayText("Welcome to ROBOT TURTLES!");
        view.displayPrompt ("GAME MENU:\n1) Play a Game\n2) Exit\n\nEnter Choice: ");
        try {
            if (input.nextInt() == 1) // Process of collecting input from players begins if user chooses 1
            {
                nPlayers = 0;
                while (nPlayers < 1 || nPlayers > 4) { // Prompting repeatedly until number of players inputted is between 1 and 4
                    view.displayPrompt("Enter number of players (1-4): ");
                    nPlayers = input.nextInt();
                }
                createBoard();// Instantiating board with jewels and player tiles
                return true;
            }
        }catch (InputMismatchException in) // Handling exception if input is not numeric
        {
            view.displayText("Sorry but your input must be a number! Please try again.");
        }
        view.displayText("Goodbye!");
        return false;
    }

    public void createBoard()
    {
        createPlayers();
        initBoard = new Board(SIZE, initPlayers, createJewels(initPlayers));
    }

    public void createPlayers()
    {
        // Creates list of n players and assigns each player's piece to a corner coordinate
        List<Coordinate> locations = cornerCoordinates();
        List<Turtle> playerList = new ArrayList<>();
        Direction currDir;

        // Assigning each player to a new Turtle and adding to list of players
        for(int i = 0; i < nPlayers; i++)
        {
            // Gathering player info through system input and initialize Turtles
            currDir = (locations.get(i).getY() == 0 ? Direction.SOUTH: Direction.NORTH);
            Turtle newPlayer = new Turtle(locations.get(i), Colour.values()[i], currDir);
            view.displayPrompt("Enter Player "+ (i+1) +"'s name: ");
            newPlayer.setPlayerName(input.next()); // Prompting each player for their name and setting
            playerList.add(newPlayer);
        }
        initPlayers = playerList;
    }

    public List<Jewel> createJewels(List<Turtle> players)
    {
        // List of jewels generated for each player with coordinates assigned to center
        List<Jewel> jwls = new ArrayList<>();
        List<Coordinate> cntr = centerCoordinates();
        int count = 0;

        for(Turtle p: players)
        {
            jwls.add(new Jewel(cntr.get(count), p.getCol()));
            count++;
        }
        return jwls;
    }

    public List<Coordinate> cornerCoordinates()
    {
        // Generating list of corner coordinates
        List<Coordinate> corners = new ArrayList<>();
        corners.add(new Coordinate(0, 0)); // Northwest corner
        corners.add(new Coordinate(0, SIZE - 1)); // Northeast corner
        corners.add(new Coordinate(SIZE - 1, 0)); // Southest corner
        corners.add(new Coordinate(SIZE - 1, SIZE - 1)); // Southwest corner

        return corners;
    }

    public List<Coordinate> centerCoordinates()
    {
        List<Coordinate> center = new ArrayList<>();
        center.add(new Coordinate((SIZE/2)-1, (SIZE/2)-1)); // Northwest center corner
        center.add(new Coordinate((SIZE/2)-1, (SIZE/2))); // Northeast center corner
        center.add(new Coordinate((SIZE/2), ((SIZE/2)-1))); // Southeast center corner
        center.add(new Coordinate((SIZE/2), (SIZE/2))); // Southwest center corner

        return center;
    }

    public GameModel newGame()
    {
        return new GameModel(initPlayers, initBoard);
    }
}