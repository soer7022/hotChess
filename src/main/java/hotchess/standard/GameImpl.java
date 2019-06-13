package hotchess.standard;

import hotchess.pieces.Pond;
import hotchess.framework.Game;
import hotchess.framework.Piece;
import hotchess.framework.Player;
import hotchess.framework.Position;
import hotchess.pieces.Rook;

import java.util.HashMap;

public class GameImpl implements Game {
    private Player currentPlayer;
    private HashMap<Position, Piece> pieceMap;

    public GameImpl() {
        // White always starts
        currentPlayer = Player.WHITE;
        pieceMap = new HashMap<>();
        buildBoard();
    }

    private void buildBoard() {
        // Put ponds
        for (int i = 0; i < 7; i++) {
            pieceMap.put(new Position(1, i), new Pond(Player.WHITE));
            pieceMap.put(new Position(6, i), new Pond(Player.BLACK));
        }
        // Put rooks
        pieceMap.put(new Position(0, 0), new Rook(Player.WHITE));
        pieceMap.put(new Position(0, 7), new Rook(Player.WHITE));
        pieceMap.put(new Position(7, 0), new Rook(Player.BLACK));
        pieceMap.put(new Position(7, 7), new Rook(Player.BLACK));
    }

    @Override
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    @Override
    public boolean movePiece(Position from, Position to) {
        boolean isAttacking = false;
        // Store the piece to be move so that we can move it later
        Piece pieceToMove = getPieceAt(from);
        boolean isMovingOnTopOfOtherPiece = getPieceAt(to) != null;
        if (isMovingOnTopOfOtherPiece) {
            if (getPieceAt(to).getOwner() == currentPlayer) {
                return false;
            } else {
                isAttacking = true;
            }
        }
        // Check if the current player is the owner
        if (pieceToMove.getOwner() != getCurrentPlayer()) return false;
        // Check if the move is legal
        boolean pieceCanBeLegallyMoved = getPieceAt(from).isLegalMove(from, to, isAttacking);
        if (pieceCanBeLegallyMoved) {
            // Move the piece
            removePieceAt(from);
            placePieceAt(to, pieceToMove);
            return true;
        }
        return false;
    }

    @Override
    public Piece getPieceAt(Position position) {
        return pieceMap.get(position);
    }

    @Override
    public void removePieceAt(Position position) {
        pieceMap.remove(position);
    }

    @Override
    public void placePieceAt(Position position, Piece piece) {
        pieceMap.put(position, piece);
    }

    @Override
    public void endTurn() {
        currentPlayer = currentPlayer == Player.BLACK ? Player.WHITE : Player.BLACK;
    }
}
