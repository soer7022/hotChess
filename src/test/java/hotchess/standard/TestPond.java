package hotchess.standard;

import hotchess.pieces.Pond;
import hotchess.framework.Piece;
import hotchess.framework.Player;
import hotchess.framework.Position;
import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;


public class TestPond {
    private Piece blackPond;
    private Piece whitePond;

    @Before
    public void setUp() throws Exception {
        this.blackPond = new Pond(Player.BLACK);
        this.whitePond = new Pond(Player.WHITE);
    }

    @Test
    public void whitePondShouldBeAbleToMoveOneStepForwards() {
        assertThat(whitePond.isLegalMove(new Position(0, 0), new Position(1, 0), false), is(true));
    }

    @Test
    public void blackPondShouldBeAbleToMoveOneStepForwards() {
        assertThat(blackPond.isLegalMove(new Position(7, 0), new Position(6, 0), false), is(true));
    }

    @Test
    public void blackPondShouldBeAbleToMoveTwoOnItsFirstTurn() {
        assertThat(blackPond.isLegalMove(new Position(7, 0), new Position(5, 0), false), is(true));
    }

    @Test
    public void blackPondShouldNotBeAbleToMoveTwoOnItsSecondTurn() {
        assertThat(blackPond.isLegalMove(new Position(7, 0), new Position(6, 0), false), is(true));
        assertThat(blackPond.isLegalMove(new Position(6, 0), new Position(4, 0), false), is(false));
    }

    @Test
    public void blackPondShouldNotBeAbleToMoveDiagonallyIfNotAttacking() {
        assertThat(blackPond.isLegalMove(new Position(7, 0), new Position(6, 1), false), is(false));
    }

    @Test
    public void blackPondShouldBeAbleToMoveDiagonallyIfAttacking() {
        assertThat(blackPond.isLegalMove(new Position(7, 0), new Position(6, 1), true), is(true));
    }

    @Test
    public void blackPondShouldNotBeAbleToMoveOutsideBoard() {
        assertThat(blackPond.isLegalMove(new Position(0, 0), new Position(-1, 0), false), is(false));
    }

    @Test
    public void whitePondShouldNotBeAbleToMoveOutsideBoard() {
        assertThat(whitePond.isLegalMove(new Position(7, 0), new Position(8, 0), false), is(false));
    }

    @Test
    public void whitePondShouldNotBeAbleToAttackOutsideLeftSideBoard() {
        assertThat(whitePond.isLegalMove(new Position(6, 0), new Position(7, -1), true), is(false));
    }

    @Test
    public void whitePondShouldNotBeAbleToAttackOutsideRightSideBoard() {
        assertThat(whitePond.isLegalMove(new Position(6, 7), new Position(7, 8), true), is(false));
    }

    @Test
    public void whitePondShouldNotBeAbleToMoveBackwards() {
        assertThat(whitePond.isLegalMove(new Position(6, 0), new Position(5, 0), false), is(false));
    }

    @Test
    public void whitePondShouldNotBeAbleToStayInPlace() {
        assertThat(whitePond.isLegalMove(new Position(5, 0), new Position(5, 0), false), is(false));
    }

    @Test
    public void blackPondShouldNotBeAbleToMoveBackwards() {
        assertThat(blackPond.isLegalMove(new Position(5, 0), new Position(6, 0), false), is(false));
    }

    @Test
    public void blackPondShouldNotBeAbleToStayInPlace() {
        assertThat(blackPond.isLegalMove(new Position(5, 0), new Position(5, 0), false), is(false));
    }

    @Test
    public void blackPondShouldOnlyMoveDiagonallyOneStepDuringAttack() {
        assertThat(blackPond.isLegalMove(new Position(5,0), new Position(5,1) ,true), is(false));
        assertThat(blackPond.isLegalMove(new Position(5,0), new Position(5,2) ,true), is(false));
        assertThat(blackPond.isLegalMove(new Position(5,0), new Position(3,2) ,true), is(false));
    }
}
