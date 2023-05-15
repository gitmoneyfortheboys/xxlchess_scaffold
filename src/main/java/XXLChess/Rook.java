package XXLChess;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {
    private String imgPath;

    public Rook(Color color, Square currentSquare, String imgPath) {
        super(PieceType.ROOK, color, currentSquare, imgPath);
        this.imgPath = imgPath;
    }

    @Override
    public List<Square> calculateLegalMoves(Board board) {
        List<Square> legalMoves = new ArrayList<>();
        int currentX = this.getCurrentSquare().getX();
        int currentY = this.getCurrentSquare().getY();

        // Rook can move in all 4 straight directions, any number of steps at a time
        int[] possibleX = { 0, 0, -1, 1 };
        int[] possibleY = { -1, 1, 0, 0 };

        for (int i = 0; i < 4; i++) {
            for (int steps = 1; steps < 14; steps++) {
                int newX = currentX + possibleX[i] * steps;
                int newY = currentY + possibleY[i] * steps;

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

        return legalMoves;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
}
