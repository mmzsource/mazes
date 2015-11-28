package com.mmz.mazes;

import java.util.List;
import java.util.Random;
import java.util.Stack;

public class RecursiveBackTracker {

    public static void createMazeOn(Grid grid) {
        Stack<Cell> stack = new Stack<>();
        Cell start = grid.getRandomCell();
        stack.push(start);
        Cell current = start;

        while (!stack.empty()) {
            List<Cell> unvisitedNeighbours = current.getUnvisitedNeighbours();
            if (unvisitedNeighbours.size() > 0) {
                Cell randomUnvisitedNeighbour = unvisitedNeighbours.get(new Random().nextInt(unvisitedNeighbours.size()));
                randomUnvisitedNeighbour.link(current);
                stack.push(randomUnvisitedNeighbour);
                current = randomUnvisitedNeighbour;
            } else {
                current = stack.pop();
            }
        }
    }
}
