package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Tile {

    private int row;
    private int col;
    private StoneType type;
    private List<Direction> visited;
    Map<Direction, Tile> neighbours;

    public Tile(int row, int col) {
        this.row = row;
        this.col = col;
        this.type = null;
        visited = new ArrayList<>();
    }

    public Tile(int row, int col, StoneType type, Map<Direction, Tile> neighbours) {
        this.row = row;
        this.col = col;
        this.type = type;
        this.neighbours = neighbours;
        visited = new ArrayList<>();
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
    
    @Override
    public String toString() {
        return "Stone[" + row + "," + col + "," + type + "]";
    }

    /**
     * Add the specified direction to the list of visited directions.
     * @param direction - the direction.
     */
    public void visit(Direction direction) {
        visited.add(direction);
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

    /**
     * Add a new stone to its neighbours.
     * @param direction - the direction on which the stone is.
     * @param stone - the stone.
     */
    public void addNeighbour(Direction direction, Tile stone) {
        neighbours.put(direction, stone);
    }

    /**
     * Returns the neighbour in the specified direction.
     * @param direction - the direction.
     * @return The neighbour if it has one, null otherwise.
     */
    public Tile getNeighbour(Direction direction) {
        return neighbours.get(direction);
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

    public Map<Direction, Tile> getNeighbours() {
        return neighbours;
    }
}
