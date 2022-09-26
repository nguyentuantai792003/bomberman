package com.game;

import com.badlogic.gdx.math.Rectangle;

public class Bomb{
    float time;

    Rectangle rectangle;

    Bomb(Rectangle rect, float time){
        rectangle = rect;
        this.time = time;
    }

}
