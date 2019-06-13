package hotchess.framework;

public interface Game {
    /**
     * @return The player currently in turn
     */
    Player getCurrentPlayer();

    boolean movePiece(Position from, Position to);

    Piece getPieceAt(Position position);

    void removePieceAt(Position position);

    void placePieceAt(Position position, Piece piece);

    void endTurn();

}
