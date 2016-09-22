package model;

import java.util.ArrayList;
import java.util.List;

import view.UserInterface;

public class PvEGame implements Game {

    private Player humanPlayer;
    private Player aiPlayer;
    private PlayInfo playInfo;
    private BoardConfig currentConfig;

    private List<UserInterface> observers;

    public PvEGame() {
        observers = new ArrayList<>();
        currentConfig = new BoardConfig();
        createPlayers();
    }

    /**
     * Create the human and the AI players.
     */
    private void createPlayers() {
        aiPlayer = new AIPlayer(StoneType.BLACK);
        humanPlayer = new HumanPlayer(StoneType.WHITE);
    }

    @Override
    public void startGame() {
        // AI starts playing - testing
        playInfo = new PlayInfo(7,7);
        AIplay(playInfo);
    }

    @Override
    public void play(int row, int col) {
        System.out.println("\n\n---------------------------------------------------");
        System.out.println("              HUMAN");
        System.out.println("---------------------------------------------------");
        playInfo = new PlayInfo(row, col);
        humanPlayer.play(currentConfig, playInfo);
        if (playerWins(humanPlayer)) {
            playInfo.setState(GameState.WIN);
        }
        notifyObservers();
        if (playInfo.getState().equals(GameState.RUNNING)) {
            System.out.println("\n\n---------------------------------------------------");
            System.out.println("              AI");
            System.out.println("---------------------------------------------------");
            AIplay(playInfo);
        }
    }

    /**
     * 
     * @param playInfo
     */
    private void AIplay(PlayInfo playInfo) {
        aiPlayer.play(currentConfig, playInfo);
        if (playerWins(aiPlayer)) {
            playInfo.setState(GameState.WIN);
        }
        notifyObservers();
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
        return playInfo;
    }

    @Override
    public boolean playerWins(Player player) {
        return currentConfig.playerWins(player);
    }

    @Override
    public BoardConfig getBoardConfig() {
        return currentConfig;
    }
}
