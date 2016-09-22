package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoardConfig {

    static final int GRID_ROWS = 15;
    static final int GRID_COLS = 15;

    private Map<String, Tile> stones;

    /**
     * List of {@link Tile}s that are not occupied by
     * any stones and are at a max distance of any of
     * the occupied tiles.
     */
    private List<Tile> emptyNeighbours; 

    public BoardConfig() {
        stones = new HashMap<>();
        emptyNeighbours = new ArrayList<>();
    }

    public BoardConfig(Map<String, Tile> stones, List<Tile> emptyNeighbours) {
        this.stones = stones;
        this.emptyNeighbours = emptyNeighbours;
    }

    /**
     * 
     * @param row
     * @param col
     * @param type
     */
    public void addStone(int row, int col, StoneType type) {
        stones.put(asKey(row, col), new Tile(row, col, type));
        updateEmptyNeighbours(row, col, type);
    }

    /**
     * Returns the string representation of the given row and column
     * to be used as key in a mapping.
     * @param row - the row.
     * @param col - the column.
     * @return - the string representation.
     */
    private String asKey(int row, int col) {
        return String.valueOf(row) + "/" + String.valueOf(col);
    }

    /**
     * 
     * @param row
     * @param col
     */
    private void updateEmptyNeighbours(int row, int col, StoneType type) {
        final int DISTANCE = 1;
        List<Tile> neighbours = new ArrayList<>();

        for (int x = 1; x <= DISTANCE; x++) {

            // Top
            if (row - x >= 0) {
                neighbours.add(new Tile(row - x, col));
            }
            // Bottom
            if (row + x <= GRID_ROWS - 1) {
                neighbours.add(new Tile(row + x, col));
            }
            // Left
            if (col - x >= 0) {
                neighbours.add(new Tile(row, col - x));
            }
            // Right
            if (col + x <= GRID_COLS - 1) {
                neighbours.add(new Tile(row, col + x));
            }   
            // Top left
            if (row - x >= 0 && col - x >= 0) {
                neighbours.add(new Tile(row - x, col - x));
            }
            // Top right
            if (row - x >= 0 && col + x <= GRID_COLS - 1) {
                neighbours.add(new Tile(row - x, col + x));
            }
            // Bottom left
            if (row + x <= GRID_ROWS - 1 && col - x >= 0) {
                neighbours.add(new Tile(row + x, col - x));
            }
            // Bottom right
            if (row + x <= GRID_ROWS - 1 && col + x <= GRID_COLS - 1) {
                neighbours.add(new Tile(row + x, col + x));
            }
        }

        // Print stones list for debugging
        System.out.println("\nBoard stones:");
        System.out.println("------------");
        for (Map.Entry<String, Tile> stone : stones.entrySet()) {
            System.out.println(stone.getValue());
        }
        System.out.println("------------\n");

        /**
         * Add the last stone's neighbours to the list of empty neighbours,
         * if it doesn't already have them and if they're not occupied by stones.
         * For that, the equals() and hashCode() methods must be implemented in Tile.
         */
        for (Tile neighbour : neighbours) {
            if (!stones.containsKey(asKey(neighbour.getRow(), neighbour.getCol())) &&
                    !emptyNeighbours.contains(neighbour)) {
                if (emptyNeighbours.add(neighbour)) {
                    System.out.println(neighbour + " added to list of empty neighbours.");
                } else {
                    System.out.println(neighbour + " was NOT added to list of empty neighbours.");
                }
            }
        }

        /**
         * Check if the last placed stone occupies a former
         * empty neighbour. If so, remove the tile from the list.
         */
        for (Tile tile : emptyNeighbours) {
            if (tile.getRow() == row && tile.getCol() == col) {
                emptyNeighbours.remove(tile);
                break;
            }
        }

        System.out.println("\nEmpty neighbours:");
        System.out.println("-----------------");
        for (Tile tile : emptyNeighbours) {
            System.out.println(tile);
        }
        System.out.println("-----------------");
    }

    /**
     * 
     * @return
     */
    public BoardConfig generateConfig(StoneType stoneType) {
        //TODO
        return null;
    }

    @Override
    public String toString() {
        String toReturn = "----------------\n";
        for (Map.Entry<String, Tile> stone : stones.entrySet()) {
            toReturn += stone.getValue().toString() + "\n";
        }
        return toReturn;
    }

    public Map<String, Tile> getStones() {
        return stones;
    }
    
    public int getGridRows() {
        return GRID_ROWS;
    }

    public int getGridCols() {
        return GRID_COLS;
    }

    public List<Tile> getEmptyNeighbours() {
        return emptyNeighbours;
    }
}
