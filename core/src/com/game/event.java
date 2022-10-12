package com.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.game.GameScreen.camera;

public class event {
    static int gridSize = 40;

    static int posX(Rectangle a) {
        return 19 - (int) a.y / gridSize;
    }

    static int posY(Rectangle a) {
        return (int) a.x / gridSize;
    }

    public static void moveRight(Rectangle player, int map[][]) {
        if (map[19 - (int)(player.y / 40) - 1][(int)(player.y / 40) ] == 0) {
            player.x += 1;
            camera.translate(1,0);

//            System.out.println("RIGHT");
        }
    }

    public static void moveLeft(Rectangle player, int map[][]) {
        if (map[19 - (int)(player.y / 40) - 1][(int)(player.y / 40) ] == 0) {
            player.x -= 1;
            camera.translate(-1,0);

//            System.out.println("LEFT");
        }
    }

    public static void moveUp(Rectangle player, int map[][]) {
        if (map[19 - (int)(player.y / 40) - 1][(int)(player.y / 40) ] == 0) {
            player.y += 1;
//            System.out.println("UP");
            camera.translate(0,1);
        }
    }

    public static void moveDown(Rectangle player, int map[][]) {
        if (map[19 - (int)(player.y / 40) - 1][(int)(player.y / 40) ] == 0) {
            player.y -= 1;
//            System.out.println("DOWN");
            camera.translate(0,-1);
        }
    }

    public static void spawnBomb(Rectangle player, Array<Bomb> bombs, Array<Explosion> exs, final int map[][]) {
        final Rectangle bom = new Rectangle(player.x, player.y, gridSize, gridSize);
        Bomb bomb = new Bomb(bom, 0);
        bombs.add(bomb);
        exs.add(new Explosion(new Rectangle(player.x, player.y, gridSize, gridSize)));
        exs.add(new Explosion(new Rectangle(player.x + gridSize, player.y, gridSize, gridSize)));
        exs.add(new Explosion(new Rectangle(player.x - gridSize, player.y, gridSize, gridSize)));
        exs.add(new Explosion(new Rectangle(player.x, player.y + gridSize, gridSize, gridSize)));
        exs.add(new Explosion(new Rectangle(player.x, player.y - gridSize, gridSize, gridSize)));

//        Timer timer = new Timer(3000, new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent arg0) {
//                // Code to be executed
//                if (map[posX(bom)][posY(bom)] == 2) {
//                    map[posX(bom)][posY(bom)] = 0;
//                }
//                if (map[posX(bom) - 1][posY(bom)] == 2) {
//                    map[posX(bom) - 1][posY(bom)] = 0;
//                }
//                if (map[posX(bom) + 1][posY(bom)] == 2) {
//                    map[posX(bom) + 1][posY(bom)] = 0;
//                }
//                if (map[posX(bom)][posY(bom) - 1] == 2) {
//                    map[posX(bom)][posY(bom) - 1] = 0;
//                }
//                if (map[posX(bom)][posY(bom) + 1] == 2) {
//                    map[posX(bom)][posY(bom) + 1] = 0;
//                }
//            }
//        });
//        timer.setRepeats(false); // Only execute once
//        timer.start(); // Go go go!
    }


}
