package com.game;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class event {
    static int gridSize = 40;

    public static void moveRight(Rectangle player) {
        if (player.x != 760) {
            player.x += gridSize;
            System.out.println("RIGHT");
        }
    }

    public static void moveLeft(Rectangle player) {
        if (player.x != 0) {
            player.x -= gridSize;
            System.out.println("LEFT");
        }
    }

    public static void moveUp(Rectangle player) {
        if (player.y != 760) {
            player.y += gridSize;
            System.out.println("UP");
        }
    }

    public static void moveDown(Rectangle player) {
        if (player.y != 0) {
            player.y -= gridSize;
            System.out.println("DOWN");
        }
    }

    public static void spawnBomb(Rectangle player, Array<Bomb> bombs, Array<Explosion> exs) {
        Rectangle bom = new Rectangle(player.x, player.y, gridSize, gridSize);
        Bomb bomb = new Bomb(bom, 0);
        bombs.add(bomb);
        exs.add(new Explosion(new Rectangle(player.x, player.y, gridSize, gridSize)));
        exs.add(new Explosion(new Rectangle(player.x + gridSize, player.y, gridSize, gridSize)));
        exs.add(new Explosion(new Rectangle(player.x - gridSize, player.y, gridSize, gridSize)));
        exs.add(new Explosion(new Rectangle(player.x, player.y + gridSize, gridSize, gridSize)));
        exs.add(new Explosion(new Rectangle(player.x, player.y - gridSize, gridSize, gridSize)));
    }
}
