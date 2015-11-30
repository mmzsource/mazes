package com.mmz.mazes;

public class Main {

    public static void main(String[] args) {
        Grid grid = new Grid(20, 16);
        RecursiveBackTracker.createMazeOn(grid);
        RouteAnalyser.setLongestPathOn(grid);
        System.out.println(grid.printLongestRoute());
        System.out.println(grid.printGrid());
//        System.out.println(grid.printDistances());
    }
}
