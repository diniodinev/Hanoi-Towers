package main.java.bg.fmi.hanoi;

public class GameState {
    public Graphic gameDrawing;

    public int moves;

    public String playerName;

    public GameState() {
        gameDrawing = null;
        moves = 0;
        playerName = "Player 1";
    }
}
