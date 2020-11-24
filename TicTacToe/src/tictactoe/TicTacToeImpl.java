package tictactoe;

import java.util.HashMap;

public class TicTacToeImpl implements TicTacToe {
    private Status status = Status.START;
    HashMap<TicTacToePiece, String> player = new HashMap<>();

    @Override
    public TicTacToePiece pick(String userName, TicTacToePiece wantedSymbol)
            throws GameException, StatusException {
        if(this.status != Status.START && this.status != Status.ONE_PICKED){
            throw new StatusException("pick called but wrong status");
        }

        TicTacToePiece takenSymbol = null;
        //already taken a Symbol?
        takenSymbol = this.getTakenSymbol(userName, TicTacToePiece.O);
        if(takenSymbol == null) {
            takenSymbol = this.getTakenSymbol(userName, TicTacToePiece.X);
        }


        //if this user number 2+ ?
        if(takenSymbol == null && this.player.values().size() == 2){
            throw new GameException("both symbols taken but not from " + userName);
        }

        //user already got Symbol?
        if(takenSymbol != null) {  // yes - user got a symbol
            //wanted one?
            if (takenSymbol == wantedSymbol) return wantedSymbol;

            //had a change of heart - can it be changed?
            if (this.player.get(wantedSymbol) == null) { //yes - can change
                this.player.remove(takenSymbol);
                this.player.put(wantedSymbol, userName);
                return wantedSymbol;
            } else { //cannot change is already taken
                return takenSymbol;
            }
        } else { //no - no symbol taken yet
            //wanted symbol available?
            if (this.player.get(wantedSymbol) == null) { // yes - symbol available
                this.player.put(wantedSymbol, userName);
                this.changeStatusAfterPickedSymbol();
                return wantedSymbol;
            } else { //not - wanted symbol not available
                TicTacToePiece otherSymbol = wantedSymbol == TicTacToePiece.O ? TicTacToePiece.X : TicTacToePiece.O;
                this.player.put(otherSymbol, userName);
                this.changeStatusAfterPickedSymbol();
                return otherSymbol;
            }
        }
    }

    private void changeStatusAfterPickedSymbol() {
        this.status = this.status == Status.START ? Status.ONE_PICKED : Status.ACTIVE_O;
    }

    private TicTacToePiece board[][] = new TicTacToePiece[3][3]; //horizontal / vertical

    @Override
    public boolean set(TicTacToePiece piece, TicTacToeBoardPosition position)
            throws GameException, StatusException {
        if(this.status != Status.ACTIVE_O && this.status != Status.ACTIVE_X){
            throw new StatusException("set called but wrong status");
        }

        int horizontal = this.sCoordinate2Int(position.getsCoordinate());
        int vertical = this.checkIntCoordinate(position.getiCoordinate());

        if(board[horizontal][vertical] != null){
            throw new GameException("Position already occupied");
        }
        this.board[horizontal][vertical] = piece;
        return this.hasWon(piece);
    }

    private boolean hasWon(TicTacToePiece piece) {
        //Vertical row
        if(this.threeInARow(piece, 0, 0, 1 , 0)) {
            return true;
        }

        //horizontal row
        if(this.threeInARow(piece,0,0,0,1)){
            return true;
        }

        //diagonal row
        if(this.threeInARow(piece,0,0,1,1)){
            return true;
        }
        return false;
    }

    private int checkIntCoordinate(int i) throws GameException {
        if (i < 0 || i > 2) {
            throw new GameException("coordinate outside the board!");
        }
        return i;
    }

    private int sCoordinate2Int(String cCoordinate) throws GameException {
        switch (cCoordinate){
            case "A": return 0;
            case "B": return 1;
            case "C": return 2;
        }
        throw new GameException("coordinate outside the board");
    }

    private boolean threeInARow(TicTacToePiece piece, int horizontalStart, int verticalStart,
                                int horizontalIncrement, int verticalIncrement){

        if(this.board[horizontalStart][verticalStart] != piece) return false;

        int h = horizontalStart;
        for(int hRoundCounter = 0; hRoundCounter < 3; hRoundCounter++){
            int v = verticalStart;
            for(int vRoundCounter = 0; vRoundCounter < 3; vRoundCounter++){
                if(this.board[h][v] != piece) return  false;
                v += verticalIncrement;
            }
            h += horizontalIncrement;
        }

        return true; //line is not broken
    }

    private TicTacToePiece getTakenSymbol(String userName, TicTacToePiece piece){
        String name = this.player.get(piece);
        if(name != null && name.equalsIgnoreCase(userName)){
            return piece;
        }
        return null;
    }
}
