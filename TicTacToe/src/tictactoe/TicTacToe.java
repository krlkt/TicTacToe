package tictactoe;

public interface TicTacToe {

    /**
     *
     * @param userName user name
     * @param wantedSymbol user asks for this symbol, it can be race condition
     * @return selected Symbol
     * @throws GameException called when both symbols are already taken
     * @throws StatusException can only be called if games hasn't started yet.
     */

    TicTacToePiece pick(String userName, TicTacToePiece wantedSymbol)
            throws GameException, StatusException;

    /**
     *
     * @param piece to be placed on board
     * @param position
     * @return true if won, false otherwise
     * @throws GameException position outside board or position not empty
     * @throws StatusException not in status play
     */
    boolean set(TicTacToePiece piece, TicTacToeBoardPosition position)
            throws GameException, StatusException;
}
