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
import java.io.*;



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

        // Create Archbishop piece and add to the game

        Square archbishopSquare1 = game.getBoard().getSquare(3, 13);
        Archbishop archbishop1 = new Archbishop(Color.WHITE, archbishopSquare1, "src/main/resources/XXLChess/w-archbishop.png");
        game.addPiece(archbishop1);

        // Create two Camel pieces and add them to the game
        Square camelSquare1 = game.getBoard().getSquare(4, 13);
        Square camelSquare2 = game.getBoard().getSquare(9,13);
        Camel camel1 = new Camel(Color.WHITE, camelSquare1, "src/main/resources/XXLChess/w-camel.png");
        game.addPiece(camel1);
        Camel camel2 = new Camel(Color.WHITE, camelSquare2, "src/main/resources/XXLChess/w-camel.png");
        game.addPiece(camel2);

        // Create two General pieces and add them to the game
        Square generalSquare1 = game.getBoard().getSquare(5, 13);
        Square generalSquare2 = game.getBoard().getSquare(8,13);
        General general1 = new General(Color.WHITE, generalSquare1, "src/main/resources/XXLChess/w-knight-king.png");
        game.addPiece(general1);
        General general2 = new General(Color.WHITE, generalSquare2, "src/main/resources/XXLChess/w-knight-king.png");
        game.addPiece(general2);

       // Create a Chancellor piece and add to the game
        Square chancellorSquare = game.getBoard().getSquare(10, 13);
        Chancellor chancellor = new Chancellor(Color.WHITE, chancellorSquare, "src/main/resources/XXLChess/w-chancellor.png");
        game.addPiece(chancellor);

        // Create an Amazon pieces and add to the game
        Square amazonSquare = game.getBoard().getSquare(6, 13);
        Amazon amazon = new Amazon(Color.WHITE, amazonSquare, "src/main/resources/XXLChess/w-amazon.png");
        game.addPiece(amazon);

        
        // Create black pawns and add to the game
        for (int x = 0; x < 14; x++) {
            Square blackPawnSquare = game.getBoard().getSquare(x, 1);
            Pawn blackPawn = new Pawn(Color.BLACK, blackPawnSquare, "src/main/resources/XXLChess/b-pawn.png");
            game.addPiece(blackPawn);
        }

        //Create two black rook pieces and add them to the game
        Square BlackRookSquare1 = game.getBoard().getSquare(0,0);
        Square BlackRookSquare2 = game.getBoard().getSquare(13,0);
        Rook BlackRook1 = new Rook(Color.BLACK, BlackRookSquare1, "src/main/resources/XXLChess/b-rook.png");
        game.addPiece(BlackRook1);
        Rook BlackRook2 = new Rook(Color.BLACK, BlackRookSquare2, "src/main/resources/XXLChess/b-rook.png");
        game.addPiece(BlackRook2);

        // Create two black Bishop pieces and add them to the game
        Square blackBishopSquare1 = game.getBoard().getSquare(11, 0);
        Square blackBishopSquare2 = game.getBoard().getSquare(2, 0);
        Bishop blackBishop1 = new Bishop(Color.BLACK, blackBishopSquare1, "src/main/resources/XXLChess/b-bishop.png");
        game.addPiece(blackBishop1);
        Bishop blackBishop2 = new Bishop(Color.BLACK, blackBishopSquare2, "src/main/resources/XXLChess/b-bishop.png");
        game.addPiece(blackBishop2);

        // Create two black Knight pieces and add them to the game
        Square blackKnightSquare1 = game.getBoard().getSquare(1, 0);
        Square blackKnightSquare2 = game.getBoard().getSquare(12, 0);
        Knight blackKnight1 = new Knight(Color.BLACK, blackKnightSquare1, "src/main/resources/XXLChess/b-knight.png");
        game.addPiece(blackKnight1);
        Knight blackKnight2 = new Knight(Color.BLACK, blackKnightSquare2, "src/main/resources/XXLChess/b-knight.png");
        game.addPiece(blackKnight2);

        // Create a black Archbishop piece and add it to the game
        Square blackArchbishopSquare = game.getBoard().getSquare(3, 0);
        Archbishop blackArchbishop = new Archbishop(Color.BLACK, blackArchbishopSquare, "src/main/resources/XXLChess/b-archbishop.png");
        game.addPiece(blackArchbishop);

        // Create two black Camel pieces and add them to the game
        Square blackCamelSquare1 = game.getBoard().getSquare(4, 0);
        Square blackCamelSquare2 = game.getBoard().getSquare(9, 0);
        Camel blackCamel1 = new Camel(Color.BLACK, blackCamelSquare1, "src/main/resources/XXLChess/b-camel.png");
        game.addPiece(blackCamel1);
        Camel blackCamel2 = new Camel(Color.BLACK, blackCamelSquare2, "src/main/resources/XXLChess/b-camel.png");
        game.addPiece(blackCamel2);

        // Create two black General pieces and add them to the game

        Square blackGeneralSquare1 = game.getBoard().getSquare(5, 0);
        Square blackGeneralSquare2 = game.getBoard().getSquare(8, 0);
        General blackGeneral1 = new General(Color.BLACK, blackGeneralSquare1, "src/main/resources/XXLChess/b-knight-king.png");
        game.addPiece(blackGeneral1);
        General blackGeneral2 = new General(Color.BLACK, blackGeneralSquare2, "src/main/resources/XXLChess/b-knight-king.png");
        game.addPiece(blackGeneral2);

        // Create a black Chancellor piece and add to the game
        Square blackChancellorSquare = game.getBoard().getSquare(10, 0);
        Chancellor blackChancellor = new Chancellor(Color.BLACK, blackChancellorSquare, "src/main/resources/XXLChess/b-chancellor.png");
        game.addPiece(blackChancellor);

        // Create a black Amazon piece and add to the game

        Square blackAmazonSquare = game.getBoard().getSquare(6, 0);
        Amazon blackAmazon = new Amazon(Color.BLACK, blackAmazonSquare, "src/main/resources/XXLChess/b-amazon.png");
        game.addPiece(blackAmazon);

        // Create a black King piece and add to the game
        Square blackKingSquare = game.getBoard().getSquare(7, 0);
        King blackKing = new King(Color.BLACK, blackKingSquare, "src/main/resources/XXLChess/b-king.png");
        game.addPiece(blackKing);
        




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
                Square square = game.getBoard().getSquare(x, y);
    
                List<Square> legalMoves = null;
                // If a piece is selected, calculate its legal moves
                if (pieceSelected) {
                    legalMoves = selectedPiece.calculateLegalMoves(game.getBoard());
                }
                
                // If the square contains the selected piece, set the color to green
                if (pieceSelected && square.equals(selectedPiece.getCurrentSquare())) {
                    fill(0xFFCDD26A);
                }
                // If the square is a legal move for the selected piece, set the color to blue
                else if (pieceSelected && legalMoves.contains(square)) {
                    // If the square is occupied by an enemy piece, set the color to light red
                    if (!square.isEmpty() && square.getPiece().getColor() != selectedPiece.getColor()) {
                        fill(0xFFFFA466);
                    } else {
                        fill(0xFFC4E0E8);
                    }
                }
                // Otherwise, set the color based on the square's coordinates
                else if ((x + y) % 2 == 0) {
                    fill(WHITESQUARESCOLOUR);
                } else {
                    fill(BLACKSQUARESCOLOUR);
                }
    
                // Draw the square
                rect(x * CELLSIZE, y * CELLSIZE, CELLSIZE, CELLSIZE);
    
                // Draw piece if it exists on this square
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
