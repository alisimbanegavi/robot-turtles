package View;

/**
 * GameView class for display
 */
public class GameView {
    public void displayWelcome (){
        System.out.println("Welcome to Robot Turtles");
    }

    public void displayGoodbye() {
        System.out.println("Thanks for playing!");
    }

    public void displayGameOver () {
        System.out.println("Congratulations everyone has won!\n\n-----------------------");
    }

    public void displayWinner (String name, String turtleColour){
        System.out.println ("Congratulations "+name+"!\nYour "+turtleColour+" Model.Turtle has captured a Model.Jewel. You hav WON!!!");
    }

    public void displayPrompt (String prompt){
        System.out.print(prompt);
    }

    public void displayBoard (int[][] jewels, int [][] players, int size){
        char [][] board = new char [size+1][size+1];
        for (int y = 0; y <= size; y++)
            for (int x = 0; x <= size; x++)
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

            // // or player colour
            // int playerColour = players[i][2];
            // if      (playerColour == 0) board[y][x] = 'b';
            // else if (playerColour == 1) board[y][x] = 'r';
            // else if (playerColour == 2) board[y][x] = 'p';
            // else if (playerColour == 3) board[y][x] = 'g';

            // Test - player dir
            // int playerDir = players[i][3];
            // if      (playerDir == 0) board[y][x] = 'n';
            // else if (playerDir == 1) board[y][x] = 's';
            // else if (playerDir == 2) board[y][x] = 'e';
            // else if (playerDir == 3) board[y][x] = 'w';
        }

        StringBuilder str = new StringBuilder ();
        for (int y = 0; y <= size; y++){
            for (int x = 0; x <= size; x++){
                str.append(board[y][x]);
                str.append(' ');
            }
            str.append('\n');
        }

        str.append('\n');
        System.out.println(str.toString());
    }
}