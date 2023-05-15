package XXLChess;

public class Board {
    private Square[][] squares;

    public Board() {
        squares = new Square[14][14];
        for (int i = 0; i < 14; i++) {
            for (int j = 0; j < 14; j++) {
                squares[i][j] = new Square(i, j);
            }
        }
    }

    public Square getSquare(int x, int y) {
        if (x < 0 || x >= 14 || y < 0 || y >= 14) {
            throw new IllegalArgumentException("Invalid coordinates");
        }
        return squares[x][y];
    }
}
