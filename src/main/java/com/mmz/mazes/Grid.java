package com.mmz.mazes;

import java.util.*;

public class Grid {

    private final int rows;

    private final int cols;

    private Distances distances;

    private Map<Location, Cell> grid = new HashMap<>();

    public Grid(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        constructCells();
        configureCells();
    }

    public void setDistances(Distances distances){
        this.distances = distances;
    }

    public Collection<Cell> getCells() {
        return Collections.unmodifiableCollection(grid.values());
    }

    public Cell getCell(Location location) {
        return grid.get(location);
    }

    private void constructCells() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Location location = new Location(i, j);
                grid.put(location, new Cell(location));
            }
        }
    }

    private void configureCells() {
        grid.values().forEach(this::setNeighbours);
    }

    private void setNeighbours(Cell cell) {
        Location location = cell.getLocation();
        cell.setNorth(grid.get(location.getNorth()));
        cell.setEast(grid.get(location.getEast()));
        cell.setSouth(grid.get(location.getSouth()));
        cell.setWest(grid.get(location.getWest()));
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("\n").append(topBorder());
        for (int i = 0; i < rows; i++) {
            result.append("|");
            for (int j = 0; j < cols; j++) {
                result.append(bodyOf(grid.get(new Location(i, j))));
            }
            result.append("\n").append("+");
            for (int j = 0; j < cols; j++) {
                result.append(bottomBorderOf(grid.get(new Location(i,j))));
            }
            result.append("\n");
        }
        return result.toString();
    }

    private String topBorder() {
        StringBuilder result = new StringBuilder();
        result.append("+");
        for (int i = 0; i < cols; i++) {
            result.append("---+");
        }
        result.append("\n");
        return result.toString();
    }

    private String bodyOf(Cell cell) {
        StringBuilder result = new StringBuilder();
        if (distances != null){
            String distance = distances.getDistanceFromRoot(cell.getLocation()).toString();
            String str = "";
            if (distance.length() < 3){
                char[] charArray = new char[3 - distance.length()];
                Arrays.fill(charArray, ' ');
                str = new String(charArray);
            }
            result.append(distance).append(str);
        } else {
            result.append("   ");
        }
        if (cell.isLinked(cell.getEast())){
            result.append(" ");
        } else {
            result.append("|");
        }
        return result.toString();
    }

    private String bottomBorderOf(Cell cell) {
        StringBuilder result  = new StringBuilder();
        if (cell.isLinked(cell.getSouth())){
            result.append("   +");
        } else {
            result.append("---+");
        }
        return result.toString();
    }

    public Cell getRandomCell() {
        int randomRowNr = new Random().nextInt(rows);
        int randomColumnNr = new Random().nextInt(cols);
        return grid.get(new Location(randomRowNr, randomColumnNr));
    }
}
