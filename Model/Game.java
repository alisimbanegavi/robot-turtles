package Model;

import java.util.ArrayDeque;
import java.util.List;

public class Game extends GameModel {
    public Game(List<Turtle> tMasters, List<Tile> jwls) {
        board = new BasicBoard (MAX_SIZE, tMasters, jwls);
        players = new ArrayDeque<Turtle>(tMasters);
        complete = false;
    }
}
