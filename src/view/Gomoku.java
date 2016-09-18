package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import model.Game;
import model.PvEGame;
import model.PvPGame;

public class Gomoku extends JFrame implements UserInterface {

    private static final long serialVersionUID = 1L;

    private JPanel mainPanel;
    private JPanel board;
    private JPanel scorePanel;

    public Gomoku() {
        initUI();
    }

    private void initUI() {
        createMenuBar();
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(25, 33, 100));
        mainPanel.setVisible(true);
        mainPanel.setPreferredSize(new Dimension(750, 850));
        add(mainPanel);
        setTitle("Gomoku");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /**
     * Creates the main menu bar.
     */
    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);

        JMenuItem pvpMenuItem = new JMenuItem("Play against player");
        pvpMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Game game = new PvPGame();
                board = new Board(game);
                board.setVisible(true);
                scorePanel = new JPanel();
                scorePanel.setLayout(new GridLayout(2, 2));
                scorePanel.setPreferredSize(new Dimension(750, 100));
                scorePanel.setVisible(true);
                mainPanel.add(board);
                mainPanel.add(scorePanel);
                revalidate();
            }
        });

        JMenuItem pveMenuItem = new JMenuItem("Play against the computer");
        pveMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Game game = new PvEGame();
                board = new Board(game);
                board.setVisible(true);
                scorePanel = new JPanel(new GridLayout(2, 2));
                scorePanel.setVisible(true);
                mainPanel.add(board);
                mainPanel.add(scorePanel);
                revalidate();
            }
        });

        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        fileMenu.add(pvpMenuItem);
        fileMenu.add(pveMenuItem);
        fileMenu.add(exitMenuItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
    }

    public static void main(String args[]) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Gomoku gomoku = new Gomoku();
                gomoku.pack();
                gomoku.setVisible(true);
            }
        });
    }
}
