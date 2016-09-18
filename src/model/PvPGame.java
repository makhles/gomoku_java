package model;

import java.util.ArrayList;
import java.util.List;

import view.UserInterface;

public class PvPGame implements Game {

    private int row;
    private int col;
    private int steps;
    private List<UserInterface> observers;

    public PvPGame() {
        observers = new ArrayList<>();
    }

    @Override
    public void play(int row, int col) {
        System.out.println("PvPGame::play()");
    }

    @Override
    public void addInterfaceObserver(UserInterface observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObservers() {
        for (UserInterface ui : observers) {
            ui.update();
        }
    }

    @Override
    public PlayInfo getPlayInfo() {
        return new PlayInfo(row, col, steps);
    }
}
