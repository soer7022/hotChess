package hotchess.framework;

public interface Piece {
    /**
     * @return The owner of the piece
     */
    Player getOwner();

    String getType();

    boolean isLegalMove(Position from, Position to, boolean isAttacking);
}
