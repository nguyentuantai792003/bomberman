package com.game;


import com.badlogic.gdx.utils.Array;

public class Pathfinding {

    Array<PathNode> grid;
    Array<PathNode> openList;
    Array<PathNode> closedList;

    public Pathfinding() {
        grid = new Array<>();
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                PathNode pathNode = new PathNode(i, j);

                if (GameScreen.map[i][j] != 0) {
                    pathNode.isWalkable = false;
                }
                grid.add(pathNode);
            }
        }
    }


    public Array<PathNode> FindPath(int startX, int startY, int endX, int endY) {
        PathNode startNode = getGrid(grid, startX, startY);
        PathNode endNode = getGrid(grid, endX, endY);
        openList = new Array<>();
        openList.add(startNode);
        closedList = new Array<>();

//        System.out.println(grid);
//        System.out.println(startNode);
//        System.out.println(openList);

        for (PathNode pathNode : grid) {
            pathNode.gCost = 99999999;
            pathNode.CalculateFCost();
            pathNode.cameFromNode = null;
        }

        startNode.gCost = 0;
        startNode.hCost = CalculateHcost(startNode, endNode);
        startNode.CalculateFCost();
        int count = 0;
        while (openList.size > 0) {
            count++;
            PathNode currentNode = getLowestNode(openList);

            System.out.println(count + " : " + currentNode);

            if (currentNode == endNode) {
                return CalculatePath(endNode);
            }

            openList.removeValue(currentNode, true);
            closedList.add(currentNode);

            for (PathNode neighbourNode : getNeighbour(currentNode)) {
                if (closedList.contains(neighbourNode, true)) continue;
                if (!neighbourNode.isWalkable) {
                    closedList.add(neighbourNode);
                    continue;
                }

                int tentativeCost = currentNode.gCost + CalculateHcost(currentNode, neighbourNode);

                System.out.println(neighbourNode + " : " + tentativeCost + ", " + neighbourNode.gCost);

                if (tentativeCost < neighbourNode.gCost) {
                    neighbourNode.cameFromNode = currentNode;
                    neighbourNode.gCost = tentativeCost;
                    neighbourNode.hCost = CalculateHcost(neighbourNode, endNode);
                    neighbourNode.CalculateFCost();
                    if (!openList.contains(neighbourNode, true)) {
                        openList.add(neighbourNode);
                    }
                }
            }
        }
        return null;
    }

    private PathNode getGrid(Array<PathNode> Grid, int x, int y) {
        for (PathNode pathNode : Grid) {
            if (pathNode.x == x && pathNode.y == y) {
                return pathNode;
            }
        }
        return null;
    }

    private Array<PathNode> CalculatePath(PathNode endNode) {
        Array<PathNode> path = new Array<>();
        path.add(endNode);
        PathNode currentNode = endNode;
        while (currentNode.cameFromNode != null) {
            path.add(currentNode.cameFromNode);
            currentNode = currentNode.cameFromNode;
        }
        path.reverse();
        return path;
    }

    private PathNode getLowestNode(Array<PathNode> nodeList) {
        PathNode lowest = nodeList.get(0);
        for (int i = 1; i < nodeList.size; i++) {
            if (nodeList.get(i).fCost < lowest.fCost) {
                lowest = nodeList.get(i);
            }
        }
        return lowest;
    }

    private int CalculateHcost(PathNode a, PathNode b) {
        int xDistance = Math.abs(a.x - b.x);
        int yDistance = Math.abs(a.y - b.y);
        return 10 * Math.abs(xDistance - yDistance) + 14 * Math.min(xDistance, yDistance);
    }

    private Array<PathNode> getNeighbour(PathNode currentNode) {
        Array<PathNode> list = new Array<>();
        if (currentNode.x - 1 >= 0) {
            list.add(getGrid(grid, currentNode.x - 1, currentNode.y));
            if (currentNode.y - 1 >= 0) list.add(getGrid(grid, currentNode.x - 1, currentNode.y - 1));
            if (currentNode.y + 1 < 20) list.add(getGrid(grid, currentNode.x - 1, currentNode.y + 1));
        }
        if (currentNode.x + 1 < 20) {
            list.add(getGrid(grid, currentNode.x + 1, currentNode.y));
            if (currentNode.y - 1 >= 0) list.add(getGrid(grid, currentNode.x + 1, currentNode.y - 1));
            if (currentNode.y + 1 < 20) list.add(getGrid(grid, currentNode.x + 1, currentNode.y + 1));
        }
        if (currentNode.y - 1 >= 0) {
            list.add(getGrid(grid, currentNode.x, currentNode.y - 1));
        }
        if (currentNode.y + 1 < 20) {
            list.add(getGrid(grid, currentNode.x, currentNode.y + 1));
        }
        return list;
    }
}
