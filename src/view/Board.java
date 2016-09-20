package view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import model.Game;
import model.PlayInfo;
import model.StoneType;


public class Board extends JPanel implements UserInterface {

    private static final long serialVersionUID = 1L;
    
    private final int TILE_WIDTH = 50;
    private final int TILE_HEIGHT = 50;

    private int rows;
    private int cols;
    private Game game;

    private TileButton[][] tiles;

    private class TileButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            TileButton button;
            if (e.getSource() instanceof TileButton) {
                button = (TileButton) e.getSource();
                game.play(button.getRow(), button.getCol());
            }
        }
    }

    public Board(Game game) {
        this.game = game;
        rows = this.game.getBoardConfig().getGridRows();
        cols = this.game.getBoardConfig().getGridCols();
        setLayout(new GridLayout(rows, cols));
        setPreferredSize(new Dimension(cols * TILE_WIDTH, rows * TILE_HEIGHT));
        createTiles();
        game.addInterfaceObserver(this);
    }

    private void createTiles() {
        ActionListener tileButtonListener = new TileButtonListener();
        TileButton tile;
        tiles = new TileButton[rows][cols];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                tiles[row][col] = new TileButton(row, col, TILE_WIDTH, TILE_HEIGHT);
                tile = tiles[row][col];
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
        PlayInfo info = game.getPlayInfo();
        TileButton tile = tiles[info.getRow()][info.getColumn()];

        if (info.getStoneType().equals(StoneType.BLACK)) {
            tile.setBlackStoneIcon();
        } else {
            tile.setWhiteStoneIcon();
        }

        // Prevent tile from sending click signal again
        tile.setDisabledIcon(tile.getIcon());
        tile.setEnabled(false);
        revalidate();
    }
}
