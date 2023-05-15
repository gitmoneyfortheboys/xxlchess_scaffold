package XXLChess;

import java.util.List;

public abstract class Piece {
    private PieceType type;
    private Color color;
    private Square currentSquare;
    private String imgPath;

    public Piece(PieceType type, Color color, Square currentSquare, String imgPath) {
        this.type = type;
        this.color = color;
        this.currentSquare = currentSquare;
        this.imgPath = imgPath;
    }

    public PieceType getType() {
        return type;
    }

    public Color getColor() {
        return color;
    }

    public Square getCurrentSquare() {
        return currentSquare;
    }

    public void setCurrentSquare(Square square) {
        this.currentSquare = square;
    }

    // Getter for imgPath
    public String getImgPath() {
        return imgPath;
    }

    // This method will be overridden by each specific piece
    public abstract List<Square> calculateLegalMoves(Board board);
}
