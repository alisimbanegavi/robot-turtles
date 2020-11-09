package View;

import java.util.Scanner;

/**
 * @author Ali Simbanegavi + Daniel Cumming
 * @version C3721.2.0
 * @since 2020-11-09
 *
 * Turtle class representing player
 */
public abstract class GameView {
    private static final Scanner input = new Scanner(System.in);

    /**
     * Displays line of text in console
     * @param txt Text to displayer
     */
    public static void displayText(String txt){
        System.out.println(txt);
    }

    /**
     * Displays text prompt in console for requesting player input in same line
     * @param prompt Text of prompt to display
     */
    public static void displayPrompt(String prompt){
        System.out.print(prompt);
    }

    /**
     * Displays board configuration in console
     * @param jewels Jewels on board
     * @param players Players on board
     * @param size Board size
     */
    public static void displayBoard(int[][] jewels, int [][] players, int size){
        System.out.println();
        char [][] board = new char [size][size];
        for (int y = 0; y < size; y++)
            for (int x = 0; x < size; x++)
                board[y][x] = '.';

        for (int i = 0; i < jewels.length; i++){
            int x = jewels[i][0];
            int y = jewels[i][1];
            board[y][x] = 'j';
        }

        for (int i = 0; i < players.length; i++){
            int x = players[i][0];
            int y = players[i][1];
            // player num
            int playerNum = players[i][2] + 1; // based on colour
            board[y][x] = (char)(playerNum+'0');
        }

        StringBuilder str = new StringBuilder ();
        for (int y = 0; y < size; y++){
            for (int x = 0; x < size; x++){
                str.append(board[y][x]);
                str.append(' ');
            }
            str.append('\n');
        }

        str.append('\n');
        System.out.println(str.toString());
    }

    /**
     * Reads text input from command line
     * @return String Input from command line
     */
    public static String readText(){return input.next();}

    /**
     * Reads numeric input from command line
     * @return int Number input from command line
     */
    public static int readDigit(){return input.nextInt();
    }
}