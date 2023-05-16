package XXLChess;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;



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
        Piece selectedPiece = null;
        List<Square> legalMoves = new ArrayList<>();
    
        // Check if the black king is in check
        boolean kingInCheck = isKingInCheck(Color.BLACK);
    
        if (kingInCheck) {
            // If the king is in check, find a piece with a legal move that resolves the check
            for (Piece piece : blackPieces) {
                List<Square> pieceLegalMoves = piece.calculateLegalMoves(board);
    
                final Piece finalPiece = piece;
                pieceLegalMoves.removeIf(move -> {
                    // Make a hypothetical move
                    Square originalSquare = finalPiece.getCurrentSquare();
                    Piece capturedPiece = move.getPiece();
                    originalSquare.setPiece(null);
                    move.setPiece(finalPiece);
                    finalPiece.setCurrentSquare(move);
    
                    // Check if the king would still be in check
                    boolean stillInCheck = isKingInCheck(Color.BLACK);
    
                    // Undo the hypothetical move
                    finalPiece.setCurrentSquare(originalSquare);
                    move.setPiece(capturedPiece);
                    originalSquare.setPiece(finalPiece);
    
                    // If the king would still be in check, the move is not legal
                    return stillInCheck;
                });
    
                // If there are legal moves that can resolve the check, select this piece and its moves
                if (!pieceLegalMoves.isEmpty()) {
                    selectedPiece = piece;
                    legalMoves = pieceLegalMoves;
                    break;
                }
            }
    
            // If there are no legal moves and the king is in check, it's checkmate
            if (legalMoves.isEmpty()) {
                System.out.println("You won by checkmate");
                // Terminate the game, assuming a method game.terminate() exists.
                //this.terminate();
                return;
            }
        } else {
            // If the king is not in check, compile a list of all pieces that have legal moves
            List<Piece> piecesWithLegalMoves = new ArrayList<>();
            for (Piece piece : blackPieces) {
                List<Square> pieceLegalMoves = piece.calculateLegalMoves(board);
                if (!pieceLegalMoves.isEmpty()) {
                    piecesWithLegalMoves.add(piece);
                }
            }
    
            // Select a random piece from the list and get its legal moves
            if (!piecesWithLegalMoves.isEmpty()) {
                selectedPiece = piecesWithLegalMoves.get(new Random().nextInt(piecesWithLegalMoves.size()));
                legalMoves = selectedPiece.calculateLegalMoves(board);
            }
        }
    
        // If the selected piece has legal moves, make a random legal move
        if (selectedPiece != null && !legalMoves.isEmpty()) {
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

        // Check if the opponent's King (black) is in Check
        if (isKingInCheck(Color.BLACK)) {
            System.out.println("The black king is in check!");
        }
    
        // Switch the current player
        currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
    
        // Make the computer's move, if it's the computer's turn
        if (currentPlayer == Color.BLACK) {
            computerMove();
        }
    }

    public List<Square> getAllLegalMoves(Color color) {
        List<Square> legalMoves = new ArrayList<>();
        for (Piece piece : pieces) {
            if (piece.getColor() == color) {
                legalMoves.addAll(piece.calculateLegalMoves(board));
            }
        }
        return legalMoves;
    }

    public boolean isKingInCheck(Color kingColor) {
        // Get the position of the king
        Square kingSquare = null;
        for (Piece piece : pieces) {
            if (piece.getType() == PieceType.KING && piece.getColor() == kingColor) {
                kingSquare = piece.getCurrentSquare();
                break;
            }
        }
    
        // If we didn't find the king (which shouldn't happen), return false
        if (kingSquare == null) {
            return false;
        }
    
        // Get the color of the opponent
        Color opponentColor = (kingColor == Color.WHITE) ? Color.BLACK : Color.WHITE;
    
        // Get all legal moves of the opponent
        List<Square> opponentMoves = getAllLegalMoves(opponentColor);
    
        // If any of the opponent's legal moves can reach the king's square, the king is in check
        return opponentMoves.contains(kingSquare);
    }
    

    public boolean isGameOver() {
        // This is a placeholder - you'd need to implement this based on the rules of chess
        return false;
    }
}