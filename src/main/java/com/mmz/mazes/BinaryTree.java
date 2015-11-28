package com.mmz.mazes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BinaryTree {

    public void createMazeOn(Grid grid){
        for (Cell cell : grid.getCells()){
            List<Cell> neighbours = createListOfNeighbours(cell);
            Cell neighbour = pickRandomNeighbourFrom(neighbours);
            if (neighbour != null) {
                cell.link(neighbour);
            }
        }
    }

    /* In the BinaryTree algorithm, only north and east neighbours are relevant */
    private List<Cell> createListOfNeighbours(Cell cell) {
        List<Cell> neighbours = new ArrayList<>();
        if (cell.hasNorth()){
            neighbours.add(cell.getNorth());
        }
        if (cell.hasEast()){
            neighbours.add(cell.getEast());
        }
        return neighbours;
    }

    private Cell pickRandomNeighbourFrom(List<Cell> neighbours) {
        if (neighbours.size() == 0){
            return null;
        }
        return neighbours.get(new Random().nextInt(neighbours.size()));
    }
}
