package hotchess.pieces;

import hotchess.framework.GameConstants;
import hotchess.framework.Piece;
import hotchess.framework.Player;
import hotchess.framework.Position;

public class Rook implements Piece {

    private final Player owner;

    public Rook(Player owner) {
        this.owner = owner;
    }

    @Override
    public Player getOwner() {
        return owner;
    }

    @Override
    public String getType() {
        return GameConstants.ROOK;
    }

    @Override
    public boolean isLegalMove(Position from, Position to, boolean isAttacking) {
        if (to.getRow() < 0 || to.getRow() > 7 || to.getColumn() < 0 || to.getColumn() > 7) return false;
            boolean isMovingHorizontal = from.getColumn() != to.getColumn();
        boolean isMovingVertical = from.getRow() != to.getRow();
        boolean isOnlyMovingInOneDirection = isMovingHorizontal ^ isMovingVertical; // XOR Operation
        return isOnlyMovingInOneDirection;
    }
}
