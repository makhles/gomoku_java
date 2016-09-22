package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class AIPlayer implements Player {

    private final int DEPTH = 10;
    private StoneType stoneType;
    private Random randomGenerator;

    public AIPlayer(StoneType stoneType) {
        this.stoneType = stoneType;
        randomGenerator = new Random();
    }

    @Override
    public void play(BoardConfig config, PlayInfo info) {
        // TODO
//        applyMinMaxAlphaBeta(config, DEPTH, -Integer.MAX_VALUE, Integer.MAX_VALUE, true);

        Tile tile = null;
        if (!config.getEmptyNeighbours().isEmpty()) {
            List<Tile> emptyNeighbours = config.getEmptyNeighbours();
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
            info.setRow(7);
            info.setColumn(7);
            info.setSteps(30);
            info.setStoneType(stoneType);
            info.setPlayer(this);
            System.out.println("AI chose: [7,7," + stoneType + "]");
            config.addStone(7, 7, stoneType);
        }
    }

    private int applyMinMaxAlphaBeta(BoardConfig config, int depth, int alpha, int beta, boolean max) {
        BoardConfig child;
        int value, tmp = 0;
        if (depth == 0) return evaluateHeuristic(config);
        if (max) {
            value = - Integer.MAX_VALUE;
            while ((child = config.generateConfig(StoneType.WHITE)) != null) {
                tmp = applyMinMaxAlphaBeta(child, depth - 1, alpha, beta, false);
                if (tmp > value) value = tmp;
                if (value > alpha) alpha = value;
                if (beta <= alpha) break;  // Beta pruning
            }
        } else {
            value = Integer.MAX_VALUE;
            while ((child = config.generateConfig(StoneType.WHITE)) != null) {
                tmp = applyMinMaxAlphaBeta(child, depth - 1, alpha, beta, true);
                if (tmp < value) value = tmp;
                if (value < beta) beta = value;
                if (beta <= alpha) break;  // Alpha pruning
            }
        }
        return value;
    }

    private int evaluateHeuristic(BoardConfig config) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean isHuman() {
        return false;
    }

    @Override
    public StoneType type() {
        return stoneType;
    }

    @Override
    public String name() {
        return "AI player";
    }
}
