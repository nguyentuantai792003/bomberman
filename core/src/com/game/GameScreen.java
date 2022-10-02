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
    //    int[][] map;
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
    private Array<Bomb> bombs;
    private Array<Explosion> exs;
    Monster monstest;
    Texture backgroundImg;

    public final int EMPTY = 0;
    public final int WALL = 1;
    public final int BRICK = 2;
    public final int MONSTER = 3;

    public int count = 0;


    int map[][] = {
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
    };

    public GameScreen(MyGame game) {
        this.game = game;

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

        player = new Rectangle(120, 120, gridSize, gridSize);


        Gdx.input.setInputProcessor(this);
        bombs = new Array<>();
        exs = new Array<>();

        monstest = new Monster(new Rectangle(80, 80, 40, 40), 1);
    }

    public boolean PlayerCollision(){
        if (player.x == monstest.rectangle.x && player.y == monstest.rectangle.y){
            return true;
        }
        for (Explosion ex : exs) {
            if (player.x == ex.rectangle.x && player.y == ex.rectangle.y && ex.time > 3) {
                return true;
            }
        }
        return false;
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
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                if (map[i][j] == WALL) {
                    game.batch.draw(wallImg, j * gridSize, (19 - i) * gridSize, gridSize, gridSize);
//                    game.batch.draw(wallImg, i * gridSize, j * gridSize, gridSize, gridSize);
                }
                if (map[i][j] == BRICK) {
                    game.batch.draw(brickImg, j * gridSize, (19 - i) * gridSize, gridSize, gridSize);
                }
            }
        }
        game.batch.draw(playerImg, player.x, player.y, gridSize, gridSize);
        game.batch.draw(monsterImg, monstest.rectangle.x, monstest.rectangle.y, gridSize, gridSize);
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
        monstest.time += Gdx.graphics.getDeltaTime();
        if (monstest.time > 0.2) {
            monstest.rectangle.x += monstest.direction * 40;
            monstest.time = 0;
        }

        if (PlayerCollision() == true){
            game.setScreen(new EndGameScreen(game));
            dispose();
        }

        if (map[19 - (int) (monstest.rectangle.y / gridSize)][(int) monstest.rectangle.x / gridSize] != 0) {
//        if(monstest.rectangle.x == 800 - gridSize || monstest.rectangle.x==0){
//            System.out.println(monstest.rectangle.x/gridSize);
            monstest.direction = -1 * monstest.direction;
            monstest.rectangle.x += monstest.direction * 40;
        }
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
            event.moveRight(player,map);
        }
        if (keycode == Input.Keys.LEFT) {
            event.moveLeft(player,map);
        }
        if (keycode == Input.Keys.UP) {
            event.moveUp(player,map);

        }
        if (keycode == Input.Keys.DOWN) {
            event.moveDown(player,map);
        }
        if (keycode == Input.Keys.A) {
            event.spawnBomb(player, bombs, exs);
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
