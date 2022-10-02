package com.game;

import com.badlogic.gdx.math.Rectangle;

public class Explosion {
    float time;
    Rectangle rectangle;
    Explosion(Rectangle rect){
        rectangle = rect;
        time =0;
    }
}
