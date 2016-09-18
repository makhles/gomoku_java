package view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import model.Game;


public class Board extends JPanel implements UserInterface {

    private static final long serialVersionUID = 1L;
    
    private final int GRID_ROWS = 15;
    private final int GRID_COLS = 15;
    private final int TILE_WIDTH = 50;
    private final int TILE_HEIGHT = 50;

    private Game game;

    private TileButton[][] tiles;

    private class TileButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            TileButton button;
            if (e.getSource() instanceof TileButton) {
                button = (TileButton) e.getSource();
                game.play(button.getRow(), button.getCol());
                button.setBlackStoneIcon();
                button.setDisabledIcon(button.getIcon());
                button.setEnabled(false);
            }
        }
    }

    public Board(Game game) {
        this.game = game;
        setLayout(new GridLayout(GRID_ROWS, GRID_COLS));
        setPreferredSize(new Dimension(GRID_COLS * TILE_WIDTH, GRID_ROWS * TILE_HEIGHT));
        createTiles();
    }

    private void createTiles() {
        ActionListener tileButtonListener = new TileButtonListener();
        TileButton tile;
        tiles = new TileButton[GRID_ROWS][GRID_COLS];
        for (int row = 0; row < GRID_ROWS; row++) {
            for (int col = 0; col < GRID_ROWS; col++) {
                tile = tiles[row][col];
                tile = new TileButton(row, col, TILE_WIDTH, TILE_HEIGHT);
                if ((row == 7 && col == 7) || (row == 3 && col == 3) || (row == 3 && col == 11) ||
                        (row == 11 && col == 3) || (row == 11 && col == 11)) {
                    tile.setDarkTileIcon();;
                } else {
                    tile.setLightTileIcon();
                }
                tile.addActionListener(tileButtonListener);
                tile.setVisible(true);
                add(tile);
            }
        }
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
    }
}
