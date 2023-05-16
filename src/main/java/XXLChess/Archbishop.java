package XXLChess;

import java.util.ArrayList;
import java.util.List;

public class Archbishop extends Piece {
    private String imgPath;

    public Archbishop(Color color, Square currentSquare, String imgPath) {
        super(PieceType.ARCHBISHOP, color, currentSquare, imgPath);
        this.imgPath = imgPath;
    }

    @Override
    public List<Square> calculateLegalMoves(Board board) {
        List<Square> legalMoves = new ArrayList<>();
        int currentX = this.getCurrentSquare().getX();
        int currentY = this.getCurrentSquare().getY();

        // Add Bishop's moves
        int[] bishopX = { -1, -1, 1, 1 };
        int[] bishopY = { -1, 1, -1, 1 };

        for (int i = 0; i < 4; i++) {
            for (int steps = 1; steps < 14; steps++) {
                int newX = currentX + bishopX[i] * steps;
                int newY = currentY + bishopY[i] * steps;

                if (newX >= 0 && newX < 14 && newY >= 0 && newY < 14) {
                    Square possibleSquare = board.getSquare(newX, newY);
                    if (possibleSquare.isEmpty()) {
                        legalMoves.add(possibleSquare);
                    } else {
                        // If the square is occupied by an enemy piece, it's a legal move, but we can't go beyond it
                        if (!possibleSquare.getPiece().getColor().equals(this.getColor())) {
                            legalMoves.add(possibleSquare);
                        }
                        break;
                    }
                } else {
                    break;
                }
            }
        }

        // Add Knight's moves
        int[] knightX = { -2, -1, 1, 2, -2, -1, 1, 2 };
        int[] knightY = { 1, 2, 2, 1, -1, -2, -2, -1 };

        for (int i = 0; i < 8; i++) {
            int newX = currentX + knightX[i];
            int newY = currentY + knightY[i];

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
