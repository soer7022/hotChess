package hotchess.pieces;

import hotchess.framework.GameConstants;
import hotchess.framework.Piece;
import hotchess.framework.Player;
import hotchess.framework.Position;

public class Pond implements Piece {

    private final Player owner;
    private boolean isInitialMove;

    public Pond(Player owner) {
        this.owner = owner;
        this.isInitialMove = true;
    }

    @Override
    public Player getOwner() {
        return owner;
    }

    @Override
    public String getType() {
        // Type is always the same
        return GameConstants.POND;
    }

    @Override
    public boolean isLegalMove(Position from, Position to, boolean isAttacking) {
        // Cant move outside board
        if(to.getRow() < 0 ||to.getRow() > 7 || to.getColumn() < 0 || to.getColumn() > 7) return false;
        // Ponds can only move in the same column if they're not attacking
        if (!isAttacking && from.getColumn() != to.getColumn()) return false;
        // Ponds can must move diagonal by one if they want to attack
        if (isAttacking && Math.abs(from.getColumn() - to.getColumn()) != 1) return false;
        int rowsToMove;
        // Black ponds must move down
        if (getOwner() == Player.BLACK) {
            rowsToMove = from.getRow() - to.getRow();

        } else {
            rowsToMove = to.getRow() - from.getRow();
        }
        // Ponds can move two steps on the first turn
        if (isInitialMove && rowsToMove <= 2 && rowsToMove > 0) {
            if (!isAttacking) {
                isInitialMove = false;
                return true;
            }
            if (rowsToMove == 1) {
                isInitialMove = false;
                return true;
            } else {
                return false;
            }
        }
        return !isInitialMove && rowsToMove == 1;
    }
}
