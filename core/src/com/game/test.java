package com.game;

import com.badlogic.gdx.utils.Array;

public class test {


    public static void main(String[] args) {
        Pathfinding pathfinding = new Pathfinding();
        Array<PathNode> path = pathfinding.FindPath(0,0,18,18);
//        for(PathNode p : pathfinding.grid){
//            System.out.println(p);
//        }

        for (PathNode p:path){
            System.out.println(p.x + "," +p.y);
        }
    }
}
