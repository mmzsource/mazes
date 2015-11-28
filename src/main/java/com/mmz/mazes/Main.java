package com.mmz.mazes;

public class Main {

    public static void main(String[] args) {
        Grid grid = new Grid(20,20);
        RecursiveBackTracker.createMazeOn(grid);
        Distances distances = new Distances(grid.getRandomCell());
        System.out.println(grid.toString());
        grid.setDistances(distances);
//        BinaryTree bt = new BinaryTree();
//        bt.createMazeOn(grid);
        System.out.println(grid.toString());
    }
}
