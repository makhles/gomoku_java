package model;

import java.util.ArrayList;
import java.util.List;

import view.UserInterface;

public class PvEGame implements Game {

    private int row;
    private int col;
    private int steps;
    private List<UserInterface> observers;

    public PvEGame() {
        observers = new ArrayList<>();
    }

    @Override
    public void play(int row, int col) {
        System.out.println("PvEGame::play()");
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
