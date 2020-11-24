package tictactoe;

public class TicTacToeBoardPosition {
    private final String sCoordinate;
    private final int iCoordinate;

    TicTacToeBoardPosition(String sCoordinate, int iCoordinate) {
        this.sCoordinate = sCoordinate;
        this.iCoordinate = iCoordinate;
    }

    public String getsCoordinate() {
        return sCoordinate;
    }

    public int getiCoordinate() {
        return iCoordinate;
    }
}
