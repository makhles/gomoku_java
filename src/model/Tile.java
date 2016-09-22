package model;

import java.util.ArrayList;
import java.util.List;

public class Tile {

    private int row;
    private int col;
    private StoneType type;
    private List<Direction> visited;

    public Tile(int row, int col) {
        this.row = row;
        this.col = col;
        this.type = null;
        visited = new ArrayList<>();
    }

    public Tile(int row, int col, StoneType type) {
        this.row = row;
        this.col = col;
        this.type = type;
        visited = new ArrayList<>();
    }

    /**
     * Checks if this tile has been visited in the specified direction.
     * @param direction - the direction.
     * @return True if it has.
     */
    public boolean visited(Direction direction) {
        return visited.contains(direction);
    }

    /**
     * Clears the list of visited directions.
     */
    public void clearVisited() {
        visited.clear();
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public StoneType getType() {
        return type;
    }

    public void setType(StoneType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Stone[" + row + "," + col + "," + type + "]";
    }

    /**
     * The StoneType is not being taken into account!
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + col;
        result = prime * result + row;
        return result;
    }

    /**
     * The StoneType is not being taken into account!
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Tile other = (Tile) obj;
        if (col != other.col)
            return false;
        if (row != other.row)
            return false;
        return true;
    }
}
