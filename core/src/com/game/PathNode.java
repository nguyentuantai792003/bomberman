package com.game;

import com.badlogic.gdx.utils.Array;

public class PathNode {
    int x;
    int y;

    int gCost;
    int hCost;
    int fCost;

    boolean isWalkable;
    public PathNode cameFromNode;

    public PathNode (int x, int y){
        this.x = x;
        this.y =y;
        isWalkable = true;
    }


    public void CalculateFCost() {
        fCost = gCost+hCost;
    }

    @Override
    public String toString() {
        return x+" "+y;
    }
}
