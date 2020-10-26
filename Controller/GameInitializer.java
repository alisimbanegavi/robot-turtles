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
    private Board initBoard;
    private List<TurtleMaster> initPlayers;
    private GameView view;
    private int nPlayers;
    private Scanner input;
    private BoardConverter converter;

    public GameInitializer(GameView mainView)
    {
        input = new Scanner(System.in);
        view = mainView;
        view.displayWelcome();
    }

    public boolean start()
    {
        view.displayPrompt ("Game Menu\n1) Play a Game\n2) Exit\n\nEnter Choice: ");
        if (input.nextInt() == 1) // Process of collecting input from players begins if user chooses 1
        {
            view.displayPrompt("Enter number of players (1-4): ");
            nPlayers = input.nextInt(); // TODO - constrain input (1-4)
            createPlayers(nPlayers); // Method to gather player info through system input and initialize TurtleMasters
            createBoard(nPlayers);// Instantiating board with jewels and player tiles
            GameModel model = newGame(); // Initializing game model
            converter = new BoardConverter(model.getBoard());
            return true;
        }

        view.displayGoodbye();
        return false;
    }

    public void createBoard(int numPlayers)
    {
        List<Jewel> jewls = createJewels(initPlayers);
        initBoard = new Board(SIZE, initPlayers, jewls);
    }

    public void createPlayers(int n)
    {
        // Creates list of n players and assigns each player's piece to a corner coordinate
        List<Coordinate> locations = cornerCoordinates();
        List<TurtleMaster> playerList = new ArrayList<TurtleMaster>();
        Colour[] colours = colorList();
        Direction currDir;

        // Assigning each player to a new Turtle and adding to list of players
        for(int i = 0; i < n; i++)
        {
            currDir = (locations.get(i).getX() == 0 ? Direction.SOUTH: Direction.NORTH);
            TurtleMaster newPlayer = new TurtleMaster(locations.get(i), colours[i], currDir);
            view.displayPrompt("Enter Player "+ (i+1) +"'s name: ");
            namePlayer(newPlayer, input.next()); // Prompting each player for their name
            playerList.add(newPlayer);
        }
        initPlayers = playerList;
    }

    public void namePlayer(TurtleMaster plyr, String name)
    {
        plyr.setPlayerName(name);
    }

    public List<Jewel> createJewels(List<TurtleMaster> players)
    {
        // List of jewels generated for each player to be assigned to center
        List<Jewel> jwls = new ArrayList<Jewel>();
        List<Coordinate> cntr = centerCoordinates();
        int count = 0;

        for(TurtleMaster p: players)
        {
            jwls.add(new Jewel(cntr.get(count), p.getColour()));
            count++;
        }
        return jwls;
    }

    public List<Coordinate> cornerCoordinates()
    {
        // Generating list of corner coordinates
        List<Coordinate> corners = new ArrayList<Coordinate>();
        corners.add(new Coordinate(0, 0)); // Northwest corner
        corners.add(new Coordinate(0, SIZE - 1)); // Northeast corner
        corners.add(new Coordinate(SIZE - 1, 0)); // Southest corner
        corners.add(new Coordinate(SIZE - 1, SIZE - 1)); // Southwest corner

        return corners;
    }

    public List<Coordinate> centerCoordinates()
    {
        List<Coordinate> center = new ArrayList<Coordinate>();
        center.add(new Coordinate((SIZE/2)-1, (SIZE/2)-1)); // Northwest center corner
        center.add(new Coordinate((SIZE/2)-1, (SIZE/2))); // Northeast center corner
        center.add(new Coordinate((SIZE/2), ((SIZE/2)-1))); // Southeast center corner
        center.add(new Coordinate((SIZE/2), (SIZE/2))); // Southwest center corner

        return center;
    }

    public Colour[] colorList()
    {
        // Generating list of possible colours
        Colour[] colours = Colour.values();
        return colours;
    }

    public GameModel newGame()
    {
        GameModel game = new GameModel(initPlayers, initBoard);
        return game;
    }
}
