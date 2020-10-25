public class ModelConverter {
    private GameModel model;

    ModelConverter(GameModel reference){
        model = reference;
    }

    public int [][] parseJewels(){
        Jewel [] tiles = model.getJewels();
        int [][] results = new int [tiles.length][3];
        for (int i = 0; i < tiles.length; i++){
            Coordinate coord = tiles[i].getCoord();
            results[i][0] = coord.getX();
            results[i][1] = coord.getY();
            results[i][2] = tiles[i].getColour().ordinal();
        }
        return results;
    }
    
    public int [][] parsePlayers(){
        TurtleMaster [] tiles = model.getPlayers();
        int [][] results = new int [tiles.length][4];
        for (int i = 0; i < tiles.length; i++){
            Coordinate coord = tiles[i].getCoord();
            results[i][0] = coord.getX();
            results[i][1] = coord.getY();
            results[i][2] = tiles[i].getColour().ordinal();
            results[i][3] = tiles[i].getDir().ordinal();
        }
        return results;
    }

}
