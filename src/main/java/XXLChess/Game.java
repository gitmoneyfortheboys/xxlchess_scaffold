package XXLChess;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
        //computerMove(); // ISSUE HERE
    }

    public void computerMove() {
        // Get a list of all black pieces
        List<Piece> blackPieces = new ArrayList<>();
        for (Piece piece : pieces) {
            if (piece.getColor() == Color.BLACK) {
                blackPieces.add(piece);
            }
        }
    
        // If no black pieces are available, return
        if (blackPieces.isEmpty()) {
            return;
        }
    
        // Initialize variables for the selected piece and its legal moves
        Piece selectedPiece;
        List<Square> legalMoves;
    
        // Select a random piece until one with legal moves is found
        do {
            // Select a random piece
            selectedPiece = blackPieces.get(new Random().nextInt(blackPieces.size()));
    
            // Get the legal moves for the selected piece
            legalMoves = selectedPiece.calculateLegalMoves(board);
        } while (legalMoves.isEmpty());
    
        // If the selected piece has legal moves, make a random legal move
        if (!legalMoves.isEmpty()) {
            Square destination = legalMoves.get(new Random().nextInt(legalMoves.size()));
            makeMove(selectedPiece, destination);
        }
    }
    

    private void makeMove(Piece piece, Square destination) {
        // You could add more checks here - is the move valid for that type of piece? Is the destination square occupied?
    
        List<Square> legalMoves = piece.calculateLegalMoves(board);
        if (!legalMoves.contains(destination)) {
            throw new IllegalArgumentException("Illegal move");
        }
    
        // Move the piece
        Square currentSquare = piece.getCurrentSquare();
    
        // If the destination square is occupied, remove the piece there
        if (!destination.isEmpty()) {
            Piece pieceToBeCaptured = destination.getPiece();
    
            // Remove the captured piece from the pieces list
            pieces.remove(pieceToBeCaptured);
            
            // You might also want to add a "capturedPieces" list to your game state to keep track of captured pieces
            // capturedPieces.add(pieceToBeCaptured);
        }
    
        currentSquare.setPiece(null);
        destination.setPiece(piece);
        piece.setCurrentSquare(destination);
    
        // Switch the current player
        currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
    
        // Computer move
    
            // Make the computer's move, if it's the computer's turn
        if (currentPlayer == Color.BLACK) {
            computerMove();
        }
    }
    

    public boolean isGameOver() {
        // This is a placeholder - you'd need to implement this based on the rules of chess
        return false;
    }
}