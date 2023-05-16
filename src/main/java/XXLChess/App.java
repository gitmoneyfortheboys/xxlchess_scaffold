package XXLChess;

//import org.reflections.Reflections;
//import org.reflections.scanners.Scanners;
//import processing.core.PApplet;
//import processing.core.PImage;
//import processing.data.JSONObject;
//import processing.data.JSONArray;
//import processing.core.PFont;
//import processing.event.MouseEvent;

//import java.util.concurrent.ConcurrentHashMap;
//import java.util.concurrent.TimeUnit;
//import java.awt.Font;
//import java.io.*;
//mport java.util.*;

import processing.core.PApplet;
import processing.core.PImage;
import processing.event.MouseEvent;
import XXLChess.*;
import java.util.*;



public class App extends PApplet {

    public static final int SPRITESIZE = 480;
    public static final int CELLSIZE = 48;
    public static final int SIDEBAR = 120;
    public static final int BOARD_WIDTH = 14;

    public static int WIDTH = CELLSIZE*BOARD_WIDTH+SIDEBAR;
    public static int HEIGHT = BOARD_WIDTH*CELLSIZE;

    public static final int FPS = 60;

    public static final int WHITESQUARESCOLOUR = 0xFFF0D9B5;
    public static final int BLACKSQUARESCOLOUR = 0xFFB58863; 
	
    public String configPath;

   // private Board board;
    private Game game;
    private Piece selectedPiece = null;
    private boolean pieceSelected = false;


    public App() {
        this.configPath = "config.json";
    }

    /**
     * Initialise the setting of the window size.
    */
    public void settings() {
        size(WIDTH, HEIGHT);
    }

    /**
     * Load all resources such as images. Initialise the elements such as the player, enemies and map elements.
    */
    public void setup() {
        frameRate(FPS);
            
        // Initialize the game instance variable
        this.game = new Game();
            
        // Create a new King piece and add it to the game
        Square kingSquare = game.getBoard().getSquare(7, 13);
        King king = new King(Color.WHITE, kingSquare, "src/main/resources/XXLChess/w-king.png");
        game.addPiece(king);

        // Create new Pawn pieces and add them to the game
        for (int x = 0; x < 14; x++) {
            Square pawnSquare = game.getBoard().getSquare(x, 12);
            Pawn pawn = new Pawn(Color.WHITE, pawnSquare, "src/main/resources/XXLChess/w-pawn.png");
            game.addPiece(pawn);
        }

        // Create two Bishop pieces and add them to the game
        Square bishopSquare1 = game.getBoard().getSquare(11, 13);
        Square bishopSquare2 = game.getBoard().getSquare(2,13);
        Bishop bishop1 = new Bishop(Color.WHITE, bishopSquare1, "src/main/resources/XXLChess/w-bishop.png");
        game.addPiece(bishop1);
        Bishop bishop2 = new Bishop(Color.WHITE, bishopSquare2, "src/main/resources/XXLChess/w-bishop.png");
        game.addPiece(bishop2);

        //Create two rook pieces and add them to the game
        Square rookSquare1 = game.getBoard().getSquare(0,13);
        Square rookSquare2 = game.getBoard().getSquare(13,13);
        Rook rook1 = new Rook(Color.WHITE, rookSquare1, "src/main/resources/XXLChess/w-rook.png");
        game.addPiece(rook1);
        Rook rook2 = new Rook(Color.WHITE, rookSquare2, "src/main/resources/XXLChess/w-rook.png");
        game.addPiece(rook2);

        // Create two Knight pieces and add them to the game
        Square knightSquare1 = game.getBoard().getSquare(1, 13);
        Square knightSquare2 = game.getBoard().getSquare(12,13);
        Knight knight1 = new Knight(Color.WHITE, knightSquare1, "src/main/resources/XXLChess/w-knight.png");
        game.addPiece(knight1);
        Knight knight2 = new Knight(Color.WHITE, knightSquare2, "src/main/resources/XXLChess/w-knight.png");
        game.addPiece(knight2);

    
       // board = new Board();
    }
    

    /**
     * Receive key pressed signal from the keyboard.
    */
    public void keyPressed(){


    }
    
    /**
     * Receive key released signal from the keyboard.
    */
    public void keyReleased(){

    }

    @Override
    public void mousePressed() {
        int x = mouseX / CELLSIZE;
        int y = mouseY / CELLSIZE;
        Square clickedSquare = game.getBoard().getSquare(x, y);
    
        // If clicked on a non-empty square with a piece of the current player
        if (!clickedSquare.isEmpty() && clickedSquare.getPiece().getColor() == game.getCurrentPlayer()) {
            // Select the piece
            selectedPiece = clickedSquare.getPiece();
            pieceSelected = true;
            System.out.println("Piece selected: " + selectedPiece);
        }
        else if (pieceSelected) {
            Square destinationSquare = clickedSquare;
            
            // If the destination square is empty or contains an enemy piece
            if (destinationSquare.isEmpty() || !destinationSquare.getPiece().getColor().equals(selectedPiece.getColor())) {
                List<Square> legalMoves = selectedPiece.calculateLegalMoves(game.getBoard());
                
                // If the destination square is a legal move
                if (legalMoves.contains(destinationSquare)) {
                    // Move the piece
                    game.playerMove(selectedPiece, destinationSquare);
                    // Deselect the piece
                    selectedPiece = null;
                    pieceSelected = false;
                }
            }
        }
    }
    
    

    /**
     * Draw all elements in the game by current frame. 
    */
    public void draw() {
        // Draw each square on the board
        for (int x = 0; x < BOARD_WIDTH; x++) {
            for (int y = 0; y < BOARD_WIDTH; y++) {
                // Set the color based on the square's coordinates
                if ((x + y) % 2 == 0) {
                    fill(WHITESQUARESCOLOUR);
                } else {
                    fill(BLACKSQUARESCOLOUR);
                }
            // Draw the square
            rect(x * CELLSIZE, y * CELLSIZE, CELLSIZE, CELLSIZE);

            // Draw piece if it exists on this square
            Square square = game.getBoard().getSquare(x, y);
            if (!square.isEmpty()) {
                Piece piece = square.getPiece();
                PImage img = loadImage(piece.getImgPath());
                image(img, x * CELLSIZE, y * CELLSIZE, CELLSIZE, CELLSIZE);
                }
            }
        }
    }
	

    public static void main(String[] args) {
        try {
            PApplet.main("XXLChess.App");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

}
