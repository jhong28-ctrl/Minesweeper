package com.hong;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameplayScreen implements Screen {

    private static final float WORLD_WIDTH = 1280;
    private static final float WORLD_HEIGHT = 720;

    //Object that draws all our sprite graphics: jpgs, pngs, etc
    private SpriteBatch spriteBatch;

    //Object that draws shapes: rectangles, ovals, lines, etc
    private ShapeRenderer shapeRenderer;

    //Camera to view the virtual world
    private Camera camera;

    //Controls how the camera views the world (zoom in/out, keep everything scaled)
    private Viewport viewport;

    //GAME VARIABLES
    private GameBoard board;

    /*
        runs one time, at the very beginning
        all setup should happen here
     */
    @Override
    public void show() {
        //OrthographicCamera is a 2D camera
        camera = new OrthographicCamera();
        //Sets the camera position to the middle of the window
        camera.position.set(WORLD_WIDTH/2, WORLD_HEIGHT/2, 0);
        //Required to save and update the camera
        camera.update();

        //Freezes the view to 1280x720, no matter the resolution of the window (camera will always show the same amount of the world)
        viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);

        //Objects that will draw graphics for us
        spriteBatch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();

        //???? Solution to annoying problem
        shapeRenderer.setAutoShapeType(true);


        board = new GameBoard(this);
    }

    /*
        this method runs as fast as it can (or to a set FPS)
        repeatedly, constantly looped
        Things to include in this method:
            (1) Process user input
            (2) A.I.
            (3) Draw all graphics
     */
    @Override
    public void render(float v) {
        clearScreen();

        //User Input

        //A.I.

        //All drawing of shapes must go between begin/end
        shapeRenderer.begin();

        shapeRenderer.end();

        //All drawing of graphics must go between begin/end
        spriteBatch.begin();

        board.draw(spriteBatch);
        spriteBatch.end();
    }

    public void clearScreen() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        //to prevent memory leaks
        shapeRenderer.dispose();
        spriteBatch.dispose();
    }
}
