package view;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class Gomoku extends JFrame {

    private static final long serialVersionUID = 1L;

    public Gomoku() {
        initUI();
    }

    private void initUI() {
        setTitle("Gomoku");
        setSize(600, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Gomoku gomoku = new Gomoku();
                gomoku.setVisible(true);
            }
        });
    }
}
