package tictactoe;

import org.junit.Assert;
import org.junit.Test;

public class UsageTests {
    public static final String ALICE = "Alice";
    public static final String BOB = "Bob";
    public static final String CLARA = "Clara";

    private TicTacToe getTicTacToe() {
        return new TicTacToeImpl();
    }

    @Test
    public void goodPickSymbol1() throws GameException, StatusException {
        TicTacToe ttt = this.getTicTacToe();
        TicTacToePiece aliceSymbol = ttt.pick(ALICE, TicTacToePiece.O);
        Assert.assertEquals(TicTacToePiece.O, aliceSymbol);
    }

    @Test
    public void goodPickSymbol2() throws GameException, StatusException {
        TicTacToe ttt = this.getTicTacToe();
        TicTacToePiece aliceSymbol = ttt.pick(ALICE, TicTacToePiece.O);
        TicTacToePiece bobSymbol = ttt.pick(BOB, TicTacToePiece.X);
        Assert.assertEquals(TicTacToePiece.O, aliceSymbol);
        Assert.assertEquals(TicTacToePiece.X, bobSymbol);
    }

    @Test
    public void goodPickSymbol3() throws GameException, StatusException {
        TicTacToe ttt = this.getTicTacToe();
        TicTacToePiece aliceSymbol = ttt.pick(ALICE, TicTacToePiece.O);
        TicTacToePiece bobSymbol = ttt.pick(BOB, TicTacToePiece.O);
        Assert.assertEquals(TicTacToePiece.O, aliceSymbol);
        Assert.assertEquals(TicTacToePiece.X, bobSymbol);
    }

    @Test
    public void goodPickSymbol4() throws GameException, StatusException {
        TicTacToe ttt = this.getTicTacToe();
        TicTacToePiece bobSymbol = ttt.pick(BOB, TicTacToePiece.O);
        TicTacToePiece aliceSymbol = ttt.pick(ALICE, TicTacToePiece.O);
        Assert.assertEquals(TicTacToePiece.X, aliceSymbol);
        Assert.assertEquals(TicTacToePiece.O, bobSymbol);
    }

    @Test(expected = GameException.class)
    public void failurePickSymbol3times() throws GameException, StatusException {
        TicTacToe ttt = this.getTicTacToe();
        ttt.pick(BOB, TicTacToePiece.O);
        ttt.pick(ALICE, TicTacToePiece.O);
        ttt.pick(CLARA, TicTacToePiece.O);
    }

    @Test
    public void goodPickSymbol5() throws GameException, StatusException {
        TicTacToe ttt = this.getTicTacToe();
        TicTacToePiece aliceSymbol = ttt.pick(ALICE, TicTacToePiece.O);
        //reconsidered
        aliceSymbol = ttt.pick(ALICE, TicTacToePiece.X);
        TicTacToePiece bobSymbol = ttt.pick(BOB, TicTacToePiece.O);
        Assert.assertEquals(TicTacToePiece.X, aliceSymbol);
        Assert.assertEquals(TicTacToePiece.O, bobSymbol);
    }

    @Test
    public void goodSet1() throws GameException, StatusException {
        TicTacToe ttt = this.getTicTacToe();
        TicTacToePiece aliceSymbol = ttt.pick(ALICE, TicTacToePiece.O);
        TicTacToePiece bobSymbol = ttt.pick(BOB, TicTacToePiece.X);

        TicTacToeBoardPosition position = new TicTacToeBoardPosition("A", 2);
        Assert.assertFalse(ttt.set(TicTacToePiece.O, position));
    }

    @Test(expected = GameException.class)
    public void failureSetOutSide1() throws GameException, StatusException {
        TicTacToe ttt = this.getTicTacToe();
        TicTacToePiece aliceSymbol = ttt.pick(ALICE, TicTacToePiece.O);
        TicTacToePiece bobSymbol = ttt.pick(BOB, TicTacToePiece.X);

        TicTacToeBoardPosition position = new TicTacToeBoardPosition("D", 2);
        ttt.set(TicTacToePiece.O, position);
    }

    @Test(expected = GameException.class)
    public void failureSetOutSide2() throws GameException, StatusException {
        TicTacToe ttt = this.getTicTacToe();
        TicTacToePiece aliceSymbol = ttt.pick(ALICE, TicTacToePiece.O);
        TicTacToePiece bobSymbol = ttt.pick(BOB, TicTacToePiece.X);

        TicTacToeBoardPosition position = new TicTacToeBoardPosition("C", 4);
        ttt.set(TicTacToePiece.O, position);
    }

    @Test
    public void marginSet1() throws GameException, StatusException {
        TicTacToe ttt = this.getTicTacToe();
        TicTacToePiece aliceSymbol = ttt.pick(ALICE, TicTacToePiece.O);
        TicTacToePiece bobSymbol = ttt.pick(BOB, TicTacToePiece.X);

        TicTacToeBoardPosition position = new TicTacToeBoardPosition("C", 2);
        Assert.assertFalse(ttt.set(TicTacToePiece.O, position));
        position = new TicTacToeBoardPosition("A", 0);
        Assert.assertFalse(ttt.set(TicTacToePiece.X, position));
        position = new TicTacToeBoardPosition("A", 2);
        Assert.assertFalse(ttt.set(TicTacToePiece.O, position));
    }

    @Test(expected = StatusException.class)
    public void failureStatus1() throws GameException, StatusException {
        TicTacToe ttt = this.getTicTacToe();
        TicTacToeBoardPosition position = new TicTacToeBoardPosition("C", 4);
        ttt.set(TicTacToePiece.O, position);
    }

    @Test(expected = StatusException.class)
    public void failureStatus2() throws GameException, StatusException {
        TicTacToe ttt = this.getTicTacToe();
        TicTacToePiece aliceSymbol = ttt.pick(ALICE, TicTacToePiece.O);
        TicTacToePiece bobSymbol = ttt.pick(BOB, TicTacToePiece.X);

        TicTacToeBoardPosition position = new TicTacToeBoardPosition("C", 4);
        ttt.set(TicTacToePiece.O, position);
        ttt.pick(BOB, TicTacToePiece.X);
    }

    @Test
    public void goodCompleteGame() throws GameException, StatusException {
        TicTacToe ttt = this.getTicTacToe();
        TicTacToePiece aliceSymbol = ttt.pick(ALICE, TicTacToePiece.O);
        TicTacToePiece bobSymbol = ttt.pick(BOB, TicTacToePiece.X);

        TicTacToeBoardPosition position = new TicTacToeBoardPosition("A", 0);
        Assert.assertFalse(ttt.set(TicTacToePiece.O, position));

        position = new TicTacToeBoardPosition("B", 0);
        Assert.assertFalse(ttt.set(TicTacToePiece.X, position));

        position = new TicTacToeBoardPosition("A", 1);
        Assert.assertFalse(ttt.set(TicTacToePiece.O, position));

        position = new TicTacToeBoardPosition("B", 1);
        Assert.assertFalse(ttt.set(TicTacToePiece.X, position));

        position = new TicTacToeBoardPosition("A", 2);
        Assert.assertTrue(ttt.set(TicTacToePiece.O, position));
    }

    @Test(expected = GameException.class)
    public void failureSetSamePosition() throws GameException, StatusException {
        TicTacToe ttt = this.getTicTacToe();
        TicTacToePiece aliceSymbol = ttt.pick(ALICE, TicTacToePiece.O);
        TicTacToePiece bobSymbol = ttt.pick(BOB, TicTacToePiece.X);

        TicTacToeBoardPosition position = new TicTacToeBoardPosition("C", 4);
        ttt.set(TicTacToePiece.O, position);
        ttt.set(TicTacToePiece.X, position);
    }
}
