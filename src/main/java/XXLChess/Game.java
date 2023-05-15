package XXLChess;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private Board board;
    private Color currentPlayer;
    private List<Piece> pieces;

    public Game() {
        board = new Board();
        currentPlayer = Color.WHITE; // White usually goes first in chess
        pieces = new ArrayList<>();
    }

    public Board getBoard() {
        return board;
    }

    public Color getCurrentPlayer() {
        return currentPlayer;
    }

    public void addPiece(Piece piece) {
        pieces.add(piece);
        Square square = piece.getCurrentSquare();
        board.getSquare(square.getX(), square.getY()).setPiece(piece);
    }

    public void playerMove(Piece piece, Square destination) {
        if (piece.getColor() != Color.WHITE) {
            throw new IllegalArgumentException("You can only move white pieces");
        }

        makeMove(piece, destination);
    }

    public void computerMove() {
        // For now, this is a placeholder
        // You'd need to add code to generate a random legal move for the black pieces
    }

    private void makeMove(Piece piece, Square destination) {
        // You could add more checks here - is the move valid for that type of piece? Is the destination square occupied?

        List<Square> legalMoves = piece.calculateLegalMoves(board);
        if (!legalMoves.contains(destination)) {
            throw new IllegalArgumentException("Illegal move");
        }

        // Move the piece
        Square currentSquare = piece.getCurrentSquare();
        currentSquare.setPiece(null);
        destination.setPiece(piece);
        piece.setCurrentSquare(destination);

        // Switch the current player
        //currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
    }

    public boolean isGameOver() {
        // This is a placeholder - you'd need to implement this based on the rules of chess
        return false;
    }
}