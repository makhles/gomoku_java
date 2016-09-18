package model;

public class PlayInfo {

    int row;     // The row where the stone was placed
    int column;  // The column where the stone was placed
    int steps;   // The number of steps used by the AI
    
    public PlayInfo(int row, int column, int steps) {
        this.row = row;
        this.column = column;
        this.steps = steps;
    }
}
