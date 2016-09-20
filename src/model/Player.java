package model;

public interface Player {

    public void play(BoardConfig config, PlayInfo info);
    public boolean isHuman();
}
