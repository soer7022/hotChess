package hotchess.standard;

import hotchess.framework.Game;
import hotchess.framework.GameConstants;
import hotchess.framework.Player;
import hotchess.framework.Position;
import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;


public class TestHotChess {
    private Game game;

    @Before
    public void setUp() throws Exception {
        this.game = new GameImpl();
    }

    @Test
    public void basicArithmeticTest() {
        assertThat(2 + 2, is(4));
    }

    @Test
    public void shouldBePondsAtRow1And6() {
        for (int i = 0; i < 7; i++) {
            assertThat(game.getPieceAt(new Position(6, i)).getType(), is(GameConstants.POND));
            assertThat(game.getPieceAt(new Position(1, i)).getType(), is(GameConstants.POND));
        }
    }

    @Test
    public void shouldBeRooksOnCorrectPositions() {
        assertThat(game.getPieceAt(new Position(0, 0)).getType(), is(GameConstants.ROOK));
        assertThat(game.getPieceAt(new Position(0, 7)).getType(), is(GameConstants.ROOK));
        assertThat(game.getPieceAt(new Position(7, 0)).getType(), is(GameConstants.ROOK));
        assertThat(game.getPieceAt(new Position(7, 7)).getType(), is(GameConstants.ROOK));
    }

    @Test
    public void whiteStartsTheGame() {
        assertThat(game.getCurrentPlayer(), is(Player.WHITE));
    }

    @Test
    public void endTurnCyclesPlayer() {
        game.endTurn();
        assertThat(game.getCurrentPlayer(), is(Player.BLACK));
        game.endTurn();
        assertThat(game.getCurrentPlayer(), is(Player.WHITE));
    }

    @Test
    public void canMoveWhitePondForwardsTwoStepsInOneTurn() {
        assertThat(game.movePiece(new Position(0,0), new Position(2,0)),is(true));
        assertThat(game.getPieceAt(new Position(2,0)).getType(), is(notNullValue()));
    }

    @Test
    public void canNotMoveBlackPondOnWhitesTurn() {
        assertThat(game.movePiece(new Position(6,0), new Position(5,0)), is(false));
    }
}
