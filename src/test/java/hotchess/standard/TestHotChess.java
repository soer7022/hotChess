package hotchess.standard;

import hotchess.framework.Game;
import hotchess.framework.GameConstants;
import hotchess.framework.Player;
import hotchess.framework.Position;
import hotchess.pieces.Pond;
import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;


public class TestHotChess {
    private Game game;

    @Before
    public void setUp() {
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
        assertThat(game.movePiece(new Position(1, 0), new Position(3, 0)), is(true));
        System.out.println("Moved piece");
        assertThat(game.getPieceAt(new Position(3, 0)), is(notNullValue()));
    }

    @Test
    public void canNotMoveBlackPondOnWhitesTurn() {
        assertThat(game.movePiece(new Position(6, 0), new Position(5, 0)), is(false));
    }

    @Test
    public void whitePondCanAttackAndKillBlackPond() {
        game.placePieceAt(new Position(2, 1), new Pond(Player.BLACK));
        assertThat(game.getPieceAt(new Position(2, 1)).getOwner(), is(Player.BLACK));
        game.movePiece(new Position(1, 0), new Position(2, 1));
        assertThat(game.getPieceAt(new Position(2, 1)).getOwner(), is(Player.WHITE));
    }
}
