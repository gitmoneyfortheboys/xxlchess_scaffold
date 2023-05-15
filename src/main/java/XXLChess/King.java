package XXLChess;

import java.util.ArrayList;
import java.util.List;
import XXLChess.Board;
import java.util.*;

public class King extends Piece {
    private String imgPath;

    public King(Color color, Square currentSquare, String imgPath) {
        super(PieceType.KING, color, currentSquare, imgPath);
        this.imgPath = imgPath;
    }

    @Override
    public List<Square> calculateLegalMoves(Board board) {
        List<Square> legalMoves = new ArrayList<>();
        int currentX = this.getCurrentSquare().getX();
        int currentY = this.getCurrentSquare().getY();

        // King can move in all 8 directions, one step at a time
        int[] possibleX = { -1, -1, -1, 0, 0, 1, 1, 1 };
        int[] possibleY = { -1, 0, 1, -1, 1, -1, 0, 1 };

        for (int i = 0; i < 8; i++) {
            int newX = currentX + possibleX[i];
            int newY = currentY + possibleY[i];

            if (newX >= 0 && newX < 14 && newY >= 0 && newY < 14) {
                Square possibleSquare = board.getSquare(newX, newY);
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
