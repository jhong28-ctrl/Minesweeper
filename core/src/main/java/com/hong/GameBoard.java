package com.hong;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

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

    public GameBoard(GameplayScreen gameplayScreen) {
        this.gameplayScreen = gameplayScreen;
        board = new int[16][30];
        numBombs = 50;
        numFlags = numBombs;
        loadGraphics();
    }

    public GameBoard(GameplayScreen gameplayScreen, int numRows, int numCols, int numBombs) {
        this.gameplayScreen = gameplayScreen;
        board = new int[numRows][numCols];
        this.numBombs = numBombs;
        numFlags = numBombs;
        loadGraphics();
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

    public void draw(SpriteBatch spriteBatch) {
        spriteBatch.draw(emptyTile, 50, 650);
    }
}
