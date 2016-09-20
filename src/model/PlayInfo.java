package model;

public class PlayInfo {

    private int row;         // The row where the stone was placed
    private int column;      // The column where the stone was placed
    private int steps;       // The number of steps used by the AI
    private GameState state;
    private StoneType stoneType;
    private Player player;

    public PlayInfo() {
        state = GameState.RUNNING;
    }

    public PlayInfo(int row, int column) {
        this.row = row;
        this.column = column;
        state = GameState.RUNNING;
    }

    public PlayInfo(int row, int column, int steps) {
        this.row = row;
        this.column = column;
        this.steps = steps;
        state = GameState.RUNNING;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }

    public GameState getState() {
        return state;
    }

    public void setState(GameState state) {
        this.state = state;
    }

    public StoneType getStoneType() {
        return stoneType;
    }

    public void setStoneType(StoneType stoneType) {
        this.stoneType = stoneType;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
