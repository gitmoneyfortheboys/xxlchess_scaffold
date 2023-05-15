package XXLChess;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {
    private String imgPath;

    public Pawn(Color color, Square currentSquare, String imgPath) {
        super(PieceType.PAWN, color, currentSquare, imgPath);
        this.imgPath = imgPath;
    }

    @Override
    public List<Square> calculateLegalMoves(Board board) {
        List<Square> legalMoves = new ArrayList<>();
        int currentX = this.getCurrentSquare().getX();
        int currentY = this.getCurrentSquare().getY();

        // Pawns can move forward one space if unoccupied, or diagonally forward to capture an opponent's piece.
        // Pawns can move two spaces forward on their first move only if both squares are unoccupied.
        // Note: This code assumes that the board is oriented such that the white pieces are at the bottom.

        int direction = (this.getColor() == Color.WHITE) ? -1 : 1;
        int startingRank = (this.getColor() == Color.WHITE) ? 6 : 1;

        // Move forward one space
        if (currentY + direction >= 0 && currentY + direction < 14) {
            Square possibleSquare = board.getSquare(currentX, currentY + direction);
            if (possibleSquare.isEmpty()) {
                legalMoves.add(possibleSquare);
            }
        }

        // Capture diagonally
        if (currentX + 1 < 14 && currentY + direction >= 0 && currentY + direction < 14) {
            Square possibleSquare = board.getSquare(currentX + 1, currentY + direction);
            if (!possibleSquare.isEmpty() && !possibleSquare.getPiece().getColor().equals(this.getColor())) {
                legalMoves.add(possibleSquare);
            }
        }
        if (currentX - 1 >= 0 && currentY + direction >= 0 && currentY + direction < 14) {
            Square possibleSquare = board.getSquare(currentX - 1, currentY + direction);
            if (!possibleSquare.isEmpty() && !possibleSquare.getPiece().getColor().equals(this.getColor())) {
                legalMoves.add(possibleSquare);
            }
        }

        // Move forward two spaces on first move
        if (currentY == startingRank) {
            Square possibleSquare1 = board.getSquare(currentX, currentY + direction);
            Square possibleSquare2 = board.getSquare(currentX, currentY + 2*direction);
            if (possibleSquare1.isEmpty() && possibleSquare2.isEmpty()) {
                legalMoves.add(possibleSquare2);
            }
        }

        return legalMoves;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
}
