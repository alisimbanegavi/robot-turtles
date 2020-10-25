import java.util.Scanner;

public class GameView {
    private Scanner input = new Scanner (System.in);

    public int displayGameMenu (){
        System.out.print("Welcome to Robot Turtles\nDo you wish to: \n1) Play a Game\n2) Exit\n\nEnter Choice: ");
        return input.nextInt();
    }

    public String[] promptPlayers(){
        System.out.print("Enter number of players(1-4): ");
        int nPlayers = input.nextInt();
        String players[] = new String [nPlayers];
        
        for (int i = 0; i < nPlayers; i++){
            System.out.print("Enter Player "+ (i+1) +"'s name: "); 
            players[i] = input.next();
        }
        return players;
    }

    

    public void displayBoard (){

    }


}
