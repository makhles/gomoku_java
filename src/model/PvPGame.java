package model;

public class PvPGame implements Game {

    @Override
    public void play(int row, int col) {
        System.out.println("PvPGame::play()");
    }
}
