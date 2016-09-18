package model;

public class PvEGame implements Game {

    @Override
    public void play(int row, int col) {
        System.out.println("PvEGame::play()");
    }
}
