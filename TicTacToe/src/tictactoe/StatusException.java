package tictactoe;

public class StatusException extends Exception {

    /**
     * Can only be called when the game hasn't started yet
     */
    public StatusException(){ super(); }
    public StatusException(String message){ super(message); }
    public StatusException(String message, Throwable t){ super(message, t); }
}
