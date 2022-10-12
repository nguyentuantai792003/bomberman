package com.game;

import com.badlogic.gdx.math.Rectangle;

public class Monster {
    Rectangle rectangle;
    int direction;

    float time;

    Monster(Rectangle rect, int dir) {
        rectangle = rect;
        direction = dir;
        time = 0;
    }
}
