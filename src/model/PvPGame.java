package model;

import java.util.ArrayList;
import java.util.List;

import view.UserInterface;

public class PvPGame implements Game {

    private int row;
    private int col;
    private int steps;
    private Player turnPlayer;
    private PlayInfo playInfo;
    private BoardConfig currentConfig;

    private List<Player> players;
    private List<UserInterface> observers;

    public PvPGame() {
        observers = new ArrayList<>();
        createPlayers();
    }

    /**
     * Create the human players.
     */
    private void createPlayers() {
        players = new ArrayList<>();
        players.add(new HumanPlayer(StoneType.BLACK));
        players.add(new HumanPlayer(StoneType.WHITE));
        turnPlayer = players.get(0);
    }
    
    @Override
    public void startGame() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void play(int row, int col) {
        System.out.println("PvPGame::play()");
        turnPlayer.play(currentConfig, playInfo);
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

    @Override
    public boolean playerWins(Player player) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public BoardConfig getBoardConfig() {
        return currentConfig;
    }
}
