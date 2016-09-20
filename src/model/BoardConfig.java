package model;

import java.util.ArrayList;
import java.util.List;

public class BoardConfig {

    static final int GRID_ROWS = 15;
    static final int GRID_COLS = 15;

    private List<Tile> stones;

    /**
     * List of {@link Tile}s that are not occupied by
     * any stones and are at a max distance of any of
     * the occupied tiles.
     */
    private List<Tile> emptyNeighbours; 

    public BoardConfig() {
        stones = new ArrayList<>();
        emptyNeighbours = new ArrayList<>();
    }

    public BoardConfig(List<Tile> stones, List<Tile> emptyNeighbours) {
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
        stones.add(new Tile(row, col, type));
        updateEmptyNeighbours(row, col, type);
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
        for (Tile stone : stones) {
            System.out.println(stone);
        }
        System.out.println("------------\n");

        /**
         * Add the last stone's neighbours to the list of empty neighbours,
         * if it doesn't already have them and if they're not occupied by stones.
         * For that, the equals() and hashCode() methods must be implemented in Tile.
         */
        for (Tile neighbour : neighbours) {
            if (!stones.contains(new Tile(neighbour.getRow(), neighbour.getCol(), type)) &&
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
//        BoardConfig config = new BoardConfig(stones, emptyNeighbours);
//        List<Tile> nearbyStones = getNearbyStones();
//        int pos = ThreadLocalRandom.current().nextInt(nearbyStones.size());
//        config.addStone(nearbyStones.get(pos).getRow(),
//                        nearbyStones.get(pos).getCol(),
//                        stoneType);
//        return config;
        return null;
    }

//    /**
//     * 
//     * @return
//     */
//    private List<Tile> getNearbyStones() {
//        List<Tile> nearbyStones = new ArrayList<>();
//        for (Tile stone : stones) {
//            nearbyStones.addAll(getNearbyStones(nearbyStones, stone, 3));
//        }
//        return nearbyStones;
//    }


//    private List<Tile> getNearbyStones(List<Tile> nearbyStones, Tile stone, int distance) {
//        
//        return null;
//    }

    public List<Tile> getStones() {
        return stones;
    }
    
    @Override
    public String toString() {
        String toReturn = "----------------\n";
        for (Tile stone : stones) {
            toReturn += stone.toString() + "\n";
        }
        return toReturn;
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
