package XXLChess;

import java.util.ArrayList;
import java.util.List;

public class Amazon extends Piece {
    private String imgPath;

    public Amazon(Color color, Square currentSquare, String imgPath) {
        super(PieceType.AMAZON, color, currentSquare, imgPath);
        this.imgPath = imgPath;
    }

    @Override
    public List<Square> calculateLegalMoves(Board board) {
        List<Square> legalMoves = new ArrayList<>();
        int currentX = this.getCurrentSquare().getX();
        int currentY = this.getCurrentSquare().getY();

        // The 8 possible knight moves
        int[] knightPossibleX = { -2, -1, 1, 2, -2, -1, 1, 2 };
        int[] knightPossibleY = { 1, 2, 2, 1, -1, -2, -2, -1 };

        // The rook and bishop's moves
        int[] directions = {-1, 1};

        // Knight moves
        for (int i = 0; i < 8; i++) {
            int newX = currentX + knightPossibleX[i];
            int newY = currentY + knightPossibleY[i];

            if (newX >= 0 && newX < 14 && newY >= 0 && newY < 14) {
                Square possibleSquare = board.getSquare(newX, newY);
                // If the square is empty or occupied by an enemy piece, it's a legal move
                if (possibleSquare.isEmpty() || !possibleSquare.getPiece().getColor().equals(this.getColor())) {
                    legalMoves.add(possibleSquare);
                }
            }
        }

        // Rook and Bishop moves
        for (int direction : directions) {
            // Rook's horizontal and vertical moves
            for (int i = 0; i < 2; i++) {
                int newX = currentX;
                int newY = currentY;
                while (true) {
                    if (i == 0) {
                        newX += direction; // Horizontal movement
                    } else {
                        newY += direction; // Vertical movement
                    }

                    if (newX >= 0 && newX < 14 && newY >= 0 && newY < 14) {
                        Square possibleSquare = board.getSquare(newX, newY);
                        if (possibleSquare.isEmpty()) {
                            legalMoves.add(possibleSquare);
                        } else {
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

            // Bishop's diagonal moves
            for (int i = 0; i < 2; i++) {
                int newX = currentX;
                int newY = currentY;
                while (true) {
                    newX += direction;
                    newY += (i == 0) ? direction : -direction; // Diagonal movement

                    if (newX >= 0 && newX < 14 && newY >= 0 && newY < 14) {
                        Square possibleSquare = board.getSquare(newX, newY);
                        if (possibleSquare.isEmpty()) {
                            legalMoves.add(possibleSquare);
                        } else {
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
