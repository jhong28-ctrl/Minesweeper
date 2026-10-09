package com.hong;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class GameBoard {
    private int[][] board;
    private int numBombs; //total number of bombs in the grid
    private int numFlags; //the number of flags remaining to be placed by the player
    public static final int BOMB = -1;  //constant helps with readability
    private GameplayScreen gameplayScreen; //reference link back to the gameplay screen

    private Texture emptyTile;
    private Texture emptyFloorTile;
    private Texture oneTile, twoTile, threeTile, fourTile, fiveTile, sixTile, sevenTile, eightTile;
    private Texture bombTile;
    private Texture flagTile;

    private final int TILE_SIZE = 25;
    private final int X_OFFSET = 50;
    private final int Y_OFFSET = 600;

    public GameBoard(GameplayScreen gameplayScreen) {
        this.gameplayScreen = gameplayScreen;
        board = new int[13][13];
        numBombs = 50;
        numFlags = numBombs;
        loadGraphics();
        placeAllBombs();

        //REMOVE LATER
        ArrayList<Location> arr = getNeighbors(new Location(0,1));
        for (int i = 0; i < arr.size(); i++) {
            System.out.println("Neighbor " + i + " at " + arr.get(i));
        }
    }

    public GameBoard(GameplayScreen gameplayScreen, int numRows, int numCols, int numBombs) {
        this.gameplayScreen = gameplayScreen;
        board = new int[numRows][numCols];
        this.numBombs = numBombs;
        numFlags = numBombs;
        loadGraphics();
        placeAllBombs();

    }

    public void loadGraphics() {
        emptyTile = new Texture("emptyTile.jpg");
        emptyFloorTile = new Texture("emptyFloorTile.jpg");
        oneTile = new Texture("oneTile.jpg");
        twoTile = new Texture("twoTile.jpg");
        threeTile = new Texture("threeTile.jpg");
        fourTile = new Texture("fourTile.jpg");
        fiveTile = new Texture("fiveTile.jpg");
        sixTile = new Texture("sixTile.jpg");
        sevenTile = new Texture("sevenTile.jpg");
        eightTile = new Texture("eightTile.jpg");
        bombTile = new Texture("bombTile.jpg");
        flagTile = new Texture("flagTile.jpg");
    }

    private void testBoard() {
        board[0][0] = 11; //numbers
        board[0][1] = 12;
        board[0][2] = 13;
        board[0][3] = 14;
        board[0][4] = 15;
        board[0][5] = 16;
        board[1][0] = 17;
        board[1][1] = 18;
        board[1][2] = 9; //bomb
        board[1][3] = 21; //flag
        board[1][4] = 10; //empty floor
    }

    public void draw(SpriteBatch spriteBatch) {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                int x = X_OFFSET + (col * TILE_SIZE);
                int y = Y_OFFSET - (row * TILE_SIZE);

                int value = board[row][col];

                if (value <= 8) spriteBatch.draw(emptyTile, x, y);
                else if (value == 9) spriteBatch.draw(bombTile, x, y);
                else if (value == 10) spriteBatch.draw(emptyFloorTile,x ,y);
                else if (value == 11) spriteBatch.draw(oneTile,x ,y);
                else if (value == 12) spriteBatch.draw(twoTile,x ,y);
                else if (value == 13) spriteBatch.draw(threeTile,x ,y);
                else if (value == 14) spriteBatch.draw(fourTile,x ,y);
                else if (value == 15) spriteBatch.draw(fiveTile,x ,y);
                else if (value == 16) spriteBatch.draw(sixTile,x ,y);
                else if (value == 17) spriteBatch.draw(sevenTile,x ,y);
                else if (value == 18) spriteBatch.draw(eightTile,x ,y);
                else if (value >= 19) spriteBatch.draw(flagTile, x, y);

                //temp draw code, show all bombs
                if (value == -1) spriteBatch.draw(bombTile, x, y);
            }

        }
    }

    private void placeAllBombs() {
        int currentBombs = 0;
        while (currentBombs < numBombs) {
            int row = (int) (Math.random() * board.length);
            int col = (int) (Math.random() * board[row].length);

            if (board[row][col] != -1) {
                board[row][col] = -1;
                currentBombs++;
            }
        }
    }

    //return true if loc is a valid location in the grid(check to make sure it is in bounds of the board)
    private boolean isValid(Location loc) {
        int row = loc.getRow();
        int col = loc.getCol();

        return (row >= 0 && row < board.length) && (col >= 0 && col < board[row].length);
    }

    //returns 3-8 locations that neighbor the given loc
    private ArrayList<Location> getNeighbors(Location loc) {
        ArrayList<Location> arr = new ArrayList<>();
        int row = loc.getRow();
        int col = loc.getCol();
        int[][] neighbors = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1,-1}, {1,0}, {1,1}};
        for (int i = 0; i < neighbors.length; i++) {
            Location neighbor = new Location(row + neighbors[i][0], col + neighbors[i][1]);
            if (isValid(neighbor)) {
                arr.add(neighbor);
            }
        }
        return arr;
    }
}
