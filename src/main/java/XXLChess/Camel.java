package XXLChess;

import java.util.ArrayList;
import java.util.List;

public class Camel extends Piece {
    private String imgPath;

    public Camel(Color color, Square currentSquare, String imgPath) {
        super(PieceType.CAMEL, color, currentSquare, imgPath);
        this.imgPath = imgPath;
    }

    @Override
    public List<Square> calculateLegalMoves(Board board) {
        List<Square> legalMoves = new ArrayList<>();
        int currentX = this.getCurrentSquare().getX();
        int currentY = this.getCurrentSquare().getY();

        // The 8 possible moves for a camel
        int[] possibleX = { -3, -1, 1, 3, -3, -1, 1, 3 };
        int[] possibleY = { 1, 3, 3, 1, -1, -3, -3, -1 };

        for (int i = 0; i < 8; i++) {
            int newX = currentX + possibleX[i];
            int newY = currentY + possibleY[i];

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
