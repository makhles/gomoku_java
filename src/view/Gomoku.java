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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import model.Game;
import model.PvEGame;
import model.PvPGame;

public class Gomoku extends JFrame implements UserInterface {

    private static final long serialVersionUID = 1L;

    private JPanel mainPanel;
    private JPanel board;
    private JPanel scorePanel;
    private JTextArea console;

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
                mainPanel.removeAll();
                Game game = new PvPGame();
                board = new Board(game);
                board.setVisible(true);
                mainPanel.add(board);
                createScorePanel();
                game.startGame();
                revalidate();
            }
        });

        JMenuItem pveMenuItem = new JMenuItem("Play against the computer");
        pveMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPanel.removeAll();
                Game game = new PvEGame();
                board = new Board(game);
                game.addInterfaceObserver(Gomoku.this);
                board.setVisible(true);
                mainPanel.add(board);
                createScorePanel();
                game.startGame();
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

    /**
     * Creates the score panel at the bottom of the application window.
     */
    private void createScorePanel() {
        console = new JTextArea();
        scorePanel = new JPanel();
        scorePanel.add(new JScrollPane(console));
        scorePanel.setPreferredSize(new Dimension(750, 100));
        scorePanel.setVisible(true);
        mainPanel.add(scorePanel);
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
                gomoku.setLocationRelativeTo(null);  // Center the window on the screen
                gomoku.setVisible(true);
            }
        });
    }
}
