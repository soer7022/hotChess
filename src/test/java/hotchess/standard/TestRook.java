package hotchess.standard;

import hotchess.framework.Piece;
import hotchess.framework.Player;
import hotchess.framework.Position;
import hotchess.pieces.Rook;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class TestRook {
    private Piece blackRook;
    private Piece whiteRook;

    @Before
    public void setUp() throws Exception {
        this.blackRook = new Rook(Player.BLACK);
        this.whiteRook = new Rook(Player.WHITE);
    }

    @Test
    public void whiteRookShouldBeAbleToMoveOneStepForwards() {
        assertThat(whiteRook.isLegalMove(new Position(0, 0), new Position(1, 0), false), is(true));
    }

    @Test
    public void whiteRookShouldBeAbleToMoveOneStepBackwards() {
        assertThat(whiteRook.isLegalMove(new Position(1, 0), new Position(0, 0), false), is(true));
    }

    @Test
    public void whiteRookShouldNotBeAbleToStandStill() {
        assertThat(whiteRook.isLegalMove(new Position(0, 0), new Position(0, 0), false), is(false));
    }

    @Test
    public void whiteRookShouldNotBeAbleToMoveOutsideBoard() {
        assertThat(whiteRook.isLegalMove(new Position(0, 0), new Position(-1, 0), false), is(false));
        assertThat(whiteRook.isLegalMove(new Position(0, 0), new Position(0, -1), false), is(false));
        assertThat(whiteRook.isLegalMove(new Position(0, 0), new Position(0, 8), false), is(false));
        assertThat(whiteRook.isLegalMove(new Position(0, 0), new Position(8, 0), false), is(false));
    }

    @Test
    public void whiteRookShouldNotBeAbleToMoveDiagonally() {
        assertThat(whiteRook.isLegalMove(new Position(0,0), new Position(1,1),false), is(false));
    }
}
