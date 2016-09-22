package model;

public interface Player {

    public void play(BoardConfig config, PlayInfo info);
    public StoneType type();
    public String name();
    public boolean isHuman();
}
