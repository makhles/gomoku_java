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
        Map<Direction, Tile> neighbours = findNeighbours(row, col, type);
        Tile stone = new Tile(row, col, type, neighbours);
        stones.put(asKey(row, col), stone);
        updateOtherStonesNeighbours(stone);
        updateEmptyNeighbours(row, col, type);
    }

    /**
     * Find neighbouring stones of the same type.
     * @param row - the stone row.
     * @param col - the stone column.
     * @param type - the stone type.
     * @return The map of neighbours.
     */
    private Map<Direction, Tile> findNeighbours(int row, int col, StoneType type) {
        Map<Direction, Tile> neighbours = new HashMap<>();
        Tile stone;
        
        stone = stones.get(asKey(row, col + 1));
        if (stone != null && stone.getType().equals(type)) {
            neighbours.put(Direction.HORIZONTAL, stone);
        }
        
        stone = stones.get(asKey(row + 1, col + 1));
        if (stone != null && stone.getType().equals(type)) {
            neighbours.put(Direction.MAIN_DIAGONAL, stone);
        }
        
        stone = stones.get(asKey(row + 1, col));
        if (stone != null && stone.getType().equals(type)) {
            neighbours.put(Direction.VERTICAL, stone);
        }
        
        stone = stones.get(asKey(row + 1, col - 1));
        if (stone != null && stone.getType().equals(type)) {
            neighbours.put(Direction.COUNTER_DIAGONAL, stone);
        }
        return neighbours;
    }

    /**
     * Updates the references to this stone on its top, top-left, top-right and left neighbours. 
     * @param stone - the stone.
     */
    private void updateOtherStonesNeighbours(Tile stone) {
        int row = stone.getRow();
        int col = stone.getCol();
        Tile neighbour;

        // Top right
        neighbour = stones.get(asKey(row - 1, col + 1));
        if (neighbour != null && neighbour.getType().equals(stone.getType())) {
            neighbour.addNeighbour(Direction.COUNTER_DIAGONAL, stone);
        }

        // Top
        neighbour = stones.get(asKey(row - 1, col));
        if (neighbour != null && neighbour.getType().equals(stone.getType())) {
            neighbour.addNeighbour(Direction.VERTICAL, stone);
        }

        // Top left
        neighbour = stones.get(asKey(row - 1, col - 1));
        if (neighbour != null && neighbour.getType().equals(stone.getType())) {
            neighbour.addNeighbour(Direction.MAIN_DIAGONAL, stone);
        }

        // Left
        neighbour = stones.get(asKey(row, col - 1));
        if (neighbour != null && neighbour.getType().equals(stone.getType())) {
            neighbour.addNeighbour(Direction.HORIZONTAL, stone);
        }
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
//                    System.out.println(neighbour + " added to list of empty neighbours.");
                } else {
//                    System.out.println(neighbour + " was NOT added to list of empty neighbours.");
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

//        System.out.println("\nEmpty neighbours:");
//        System.out.println("-----------------");
//        for (Tile tile : emptyNeighbours) {
//            System.out.println(tile);
//        }
//        System.out.println("-----------------");
    }

    /**
     * Checks if the specified player has won the game.
     * @param player - the player.
     * @return True if it has.
     */
    public boolean playerWins(Player player) {
        System.out.println("\n-----------------");
        System.out.println("Checking if player has won...");
        Tile stone;

        for (int row = 0; row <= GRID_ROWS; row++) {
            for (int col = 0; col <= GRID_COLS; col++) {
                stone = stones.get(asKey(row, col));
                if (stone != null && stone.getType().equals(player.type())) {
                    System.out.println("\nStarting at " + stone);
                    for (Direction direction : Direction.values()) {
                        System.out.print("\n- " + direction + ": ");
                        if (stone.visited(direction)) {
                            System.out.print("already visited.");
                        } else if (fullSequence(stone, direction, player.type(), 0)) return true;
                    }
                }
            }
        }
        clearVisitedStones();
        return false;
    }

    /**
     * Visits a tile checking for a sequence of the stones of the same type.
     * @param tile - the tile to be visited.
     * @param direction - the direction of the visit.
     * @param type - the type of the tile.
     * @param sequence - the number of stones in the sequence. 
     * @return True if there are 5 stones in the sequence.
     */
    private boolean fullSequence(Tile tile, Direction direction, StoneType type, int sequence) {
        tile.visit(direction);
        sequence++;
        System.out.print(sequence + " ");
        if (sequence == 5) return true;
        Tile neighbour = tile.getNeighbour(direction); // Neighbours of equal type 
        if (neighbour != null) {
            if (neighbour.visited(direction)) {
                System.out.print("already visited.");
            } else {
                return fullSequence(neighbour, direction, type, sequence);
            }
        }
        return false;
    }
    
    /**
     * Clears all stones' visited lists. 
     */
    private void clearVisitedStones() {
        for (Map.Entry<String, Tile> stone : stones.entrySet()) {
            stone.getValue().clearVisited();
        }
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
