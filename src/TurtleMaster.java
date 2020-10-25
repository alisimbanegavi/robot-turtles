public class TurtleMaster extends Turtle {
    private String name;
    private PlayerState state = PlayerState.PLAYING;

    TurtleMaster (Coordinate coord, Colour colour, String name){
        super(coord, colour);
        this.name = name;
    }

    // Accessors
    public String getName () { return name;}
    public Boolean hasWon () { return state == PlayerState.WON;}
    
    // Modifiers
    public void won () {state = PlayerState.WON;}
}
