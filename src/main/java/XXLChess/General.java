package XXLChess;

import java.util.ArrayList;
import java.util.List;

public class General extends Piece {
    private String imgPath;

    public General(Color color, Square currentSquare, String imgPath) {
        super(PieceType.GENERAL, color, currentSquare, imgPath);
        this.imgPath = imgPath;
    }

    @Override
    public List<Square> calculateLegalMoves(Board board) {
        List<Square> legalMoves = new ArrayList<>();
        int currentX = this.getCurrentSquare().getX();
        int currentY = this.getCurrentSquare().getY();

        // The 8 possible king moves
        int[] kingPossibleX = { -1, -1, -1, 0, 0, 1, 1, 1 };
        int[] kingPossibleY = { -1, 0, 1, -1, 1, -1, 0, 1 };

        // The 8 possible knight moves
        int[] knightPossibleX = { -2, -1, 1, 2, -2, -1, 1, 2 };
        int[] knightPossibleY = { 1, 2, 2, 1, -1, -2, -2, -1 };

        for (int i = 0; i < 8; i++) {
            int newX = currentX + kingPossibleX[i];
            int newY = currentY + kingPossibleY[i];

            if (newX >= 0 && newX < 14 && newY >= 0 && newY < 14) {
                Square possibleSquare = board.getSquare(newX, newY);
                // If the square is empty or occupied by an enemy piece, it's a legal move
                if (possibleSquare.isEmpty() || !possibleSquare.getPiece().getColor().equals(this.getColor())) {
                    legalMoves.add(possibleSquare);
                }
            }

            newX = currentX + knightPossibleX[i];
            newY = currentY + knightPossibleY[i];

            if (newX >= 0 && newX < 14 && newY >= 0 && newY < 14) {
                Square possibleSquare = board.getSquare(newX, newY);
                // If the square is empty or occupied by an enemy piece, it's a legal move
                if (possibleSquare.isEmpty() || !possibleSquare.getPiece().getColor().equals(this.getColor())) {
                    legalMoves.add(possibleSquare);
                }
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
