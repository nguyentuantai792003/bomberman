package com.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.Iterator;

public class GameScreen implements Screen, InputProcessor {

    final MyGame game;
    public final int gridSize = 40;
    int[][] map;
    public int playerPosX;
    public int playerPosY;
    OrthographicCamera camera;
    public Rectangle player;

    Texture playerImg;
    Texture brickImg;
    Texture wallImg;
    Texture monsterImg;
    Texture explosionImg;
    Texture bombImg;

    private Rectangle bomb;

    private Array<Bomb> bombs;

    private Array<Explosion> exs;
    Texture backgroundImg;

    public GameScreen(MyGame game) {
        this.game = game;

        map = new int[20][20];
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                map[i][j] = 0;
            }
        }

        playerPosX = 0;
        playerPosY = 0;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 800);

        playerImg = new Texture(Gdx.files.internal("player.png"));
        brickImg = new Texture(Gdx.files.internal("brick.png"));
        monsterImg = new Texture(Gdx.files.internal("monster.png"));
        bombImg = new Texture(Gdx.files.internal("bomb.png"));
        explosionImg = new Texture(Gdx.files.internal("explosion.png"));
        wallImg = new Texture(Gdx.files.internal("wall.jpg"));
        backgroundImg = new Texture(Gdx.files.internal("map.jpg"));

        player = new Rectangle(0, 0, gridSize, gridSize);


        Gdx.input.setInputProcessor(this);
        bombs = new Array<>();
        exs = new Array<>();
    }

    @Override
    public void show() {

    }


    @Override
    public void render(float delta) {

        ScreenUtils.clear(0, 0, 0.2f, 1);

        // tell the camera to update its matrices.
        camera.update();

        // tell the SpriteBatch to render in the
        // coordinate system specified by the camera.
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.batch.draw(backgroundImg, 0, 0, 800, 800);
        for (Bomb bomb : bombs) {
            game.batch.draw(bombImg, bomb.rectangle.x, bomb.rectangle.y, gridSize, gridSize);
        }
        for (Explosion ex : exs) {
            if (ex.time > 3) {
                game.batch.draw(explosionImg, ex.rectangle.x, ex.rectangle.y, gridSize, gridSize);
            }
        }
        game.batch.draw(playerImg, player.x, player.y, gridSize, gridSize);
        game.batch.end();

        for (Iterator<Bomb> iter = bombs.iterator(); iter.hasNext(); ) {
            Bomb bomb = iter.next();
            bomb.time += Gdx.graphics.getDeltaTime();
            if (bomb.time > 3) {
                iter.remove();
            }
        }
        for (Iterator<Explosion> iter = exs.iterator(); iter.hasNext(); ) {
            Explosion ex = iter.next();
            ex.time += Gdx.graphics.getDeltaTime();
            if (ex.time > 5) {
                iter.remove();
            }
        }
    }


    private void spawnBomb() {
        Rectangle bom = new Rectangle(player.x, player.y, gridSize, gridSize);
        Bomb bomb = new Bomb(bom, 0);
        bombs.add(bomb);
        exs.add(new Explosion(new Rectangle(player.x, player.y, gridSize, gridSize)));
        exs.add(new Explosion(new Rectangle(player.x + gridSize, player.y, gridSize, gridSize)));
        exs.add(new Explosion(new Rectangle(player.x - gridSize, player.y, gridSize, gridSize)));
        exs.add(new Explosion(new Rectangle(player.x, player.y + gridSize, gridSize, gridSize)));
        exs.add(new Explosion(new Rectangle(player.x, player.y - gridSize, gridSize, gridSize)));

    }

    @Override
    public void resize(int width, int height) {

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

    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.RIGHT) {
            event.moveRight(player);
        }
        if (keycode == Input.Keys.LEFT) {
            event.moveLeft(player);
        }
        if (keycode == Input.Keys.UP) {
            event.moveUp(player);

        }
        if (keycode == Input.Keys.DOWN) {
            event.moveDown(player);
        }
        if (keycode == Input.Keys.A) {
            event.spawnBomb(player,bombs,exs);
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
