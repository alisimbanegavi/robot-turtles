package View;

/**
 * GameView class for display
 */
public class GameView {
    public void displayText(String txt){
        System.out.println(txt);
    }

    public void displayPrompt (String prompt){
        System.out.print(prompt);
    }

    public void displayBoard (int[][] jewels, int [][] players, int size){
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
}