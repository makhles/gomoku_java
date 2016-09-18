package view;


import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class TileButton extends JButton {

    private static final long serialVersionUID = 1L;
    private int row;
    private int col;

    static ImageIcon darkTileIcon = createImageIcon("/images/tile_dark.png", "");
    static ImageIcon lightTileIcon = createImageIcon("/images/tile_light.png", "");
    static ImageIcon blackStoneIcon = createImageIcon("/images/stone_black.png", "");
    static ImageIcon whiteStoneIcon = createImageIcon("/images/stone_white.png", "");

    
    public TileButton(int row, int col, int width, int height) {
        this.row = row;
        this.col = col;
        setPreferredSize(new Dimension(width, height));
        setBorder(BorderFactory.createEmptyBorder());
    }

    /**
     * Returns an ImageIcon, or null if the path was invalid.
     * 
     * @param path - Relative path to image.
     * @param description - The image description.
     * @return The created icon image.
     */
    static ImageIcon createImageIcon(String path, String description) {
        java.net.URL imgURL = TileButton.class.getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    public void setDarkTileIcon() {
        setIcon(darkTileIcon);
    }

    public void setLightTileIcon() {
        setIcon(lightTileIcon);
    }

    public void setBlackStoneIcon() {
        setIcon(blackStoneIcon);
    }

    public void setWhiteStoneIcon() {
        setIcon(whiteStoneIcon);
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
