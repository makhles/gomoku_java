package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class AIPlayer implements Player {

    private StoneType stoneType;
    private Random randomGenerator;

    public AIPlayer(StoneType stoneType) {
        this.stoneType = stoneType;
        randomGenerator = new Random();
    }

    @Override
    public void play(BoardConfig config, PlayInfo info) {
        // TODO
        Tile tile = new Tile(7, 7, stoneType);
        if (!config.getEmptyNeighbours().isEmpty()) {
            List<Tile> emptyNeighbours = new ArrayList<>(config.getEmptyNeighbours());
            int pos = randomGenerator.nextInt(emptyNeighbours.size());
            tile = emptyNeighbours.get(pos);
            System.out.println("Empty neighbours size: " + emptyNeighbours.size());
            System.out.println("Random number: " + pos);
        }
        if (tile != null) {
            info.setRow(tile.getRow());
            info.setColumn(tile.getCol());
            info.setSteps(30);
            info.setStoneType(stoneType);
            info.setPlayer(this);
            System.out.println("AI chose: [" + tile.getRow() + "," + tile.getCol() + "," + tile.getType() + "]");
            config.addStone(tile.getRow(), tile.getCol(), stoneType);
        } else {
            System.out.println("AIPlayer::play(): Tile is null.");
        }
    }

    @Override
    public boolean isHuman() {
        return false;
    }
}
