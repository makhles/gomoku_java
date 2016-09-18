package model;

import view.UserInterface;

public interface Game {

    public void play(int row, int col);
    public void addInterfaceObserver(UserInterface observer);
    public void notifyObservers();
    public PlayInfo getPlayInfo(); 
}
