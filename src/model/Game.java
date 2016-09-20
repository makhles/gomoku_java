package model;

import view.UserInterface;

public interface Game {

    public void startGame();
    public void play(int row, int col);
    public boolean playerWins(Player player);
    public void addInterfaceObserver(UserInterface observer);
    public void notifyObservers();
    public PlayInfo getPlayInfo();
    public BoardConfig getBoardConfig();
}
