package XXLChess;

public class PieceMove {
    private Piece piece;
    private Square destination;

    public PieceMove(Piece piece, Square destination) {
        this.piece = piece;
        this.destination = destination;
    }

    public Piece getPiece() {
        return piece;
    }

    public Square getDestination() {
        return destination;
    }
}

