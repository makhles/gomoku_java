package model;

public class HumanPlayer implements Player {

    private StoneType stoneType;

    public HumanPlayer(StoneType stoneType) {
        this.stoneType = stoneType;
    }

    @Override
    public void play(BoardConfig config, PlayInfo info) {
        System.out.println("Human chose: [" + info.getRow() + "," + info.getColumn() + "," + info.getStoneType() + "]");
        config.addStone(info.getRow(), info.getColumn(), stoneType);
        info.setSteps(1);
        info.setStoneType(stoneType);
        info.setPlayer(this);
    }

    @Override
    public boolean isHuman() {
        return true;
    }

}
