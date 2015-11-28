package com.mmz.mazes;

import java.util.*;

public class Grid {

    private final int rows;

    private final int cols;

    private Distances distances;

    private Set<Location> locationsOnPath;

    private Location start;

    private Location stop;

    private Map<Location, Cell> grid = new HashMap<>();

    public Grid(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        constructCells();
        configureCells();
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

    public void setDistances(Distances distances) {
        this.distances = distances;
    }

    public void setStartStop(Location start, Location stop) {
        this.start = start;
        this.stop = stop;
    }

    public void setLocationsOnPath(Set<Location> locationsOnPath) {
        this.locationsOnPath = locationsOnPath;
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("\n").append(topBorder());
        for (int i = 0; i < rows; i++) {
            result.append("|");
            for (int j = 0; j < cols; j++) {
                Cell cell = grid.get(new Location(i, j));
                if (locationsOnPath != null) {
                    result.append(pathBodyOf(cell));
                } else if (distances != null) {
                    result.append(distanceBodyOf(cell));
                } else {
                    result.append(bodyOf(cell));
                }
                if (cell.isLinked(cell.getEast())) {
                    result.append(" ");
                } else {
                    result.append("|");
                }
            }
            result.append("\n").append("+");
            for (int j = 0; j < cols; j++) {
                result.append(bottomBorderOf(grid.get(new Location(i, j))));
            }
            result.append("\n");
        }
        return result.toString();
    }

    private String topBorder() {
        StringBuilder result = new StringBuilder();
        result.append("+");
        for (int i = 0; i < cols; i++) {
            result.append("-----+");
        }
        result.append("\n");
        return result.toString();
    }

    public String bodyOf(Cell cell) {
        if (cell.getLocation().equals(start) || cell.getLocation().equals(stop)) {
            return "  @  ";
        }
        return "     ";
    }

    public String distanceBodyOf(Cell cell) {
        if (distances == null) {
            return bodyOf(cell);
        }
        StringBuilder result = new StringBuilder();
        String distance = distances.getDistanceFromRoot(cell.getLocation()).toString();
        String str = "";
        if (distance.length() < 3) {
            char[] charArray = new char[3 - distance.length()];
            Arrays.fill(charArray, ' ');
            str = new String(charArray);
        }
        result.append(" ").append(distance).append(str).append(" ");
        return result.toString();
    }

    public String pathBodyOf(Cell cell) {
        if (locationsOnPath == null) {
            return bodyOf(cell);
        }

        if (cell.getLocation().equals(start) || cell.getLocation().equals(stop)) {
            return "  @  ";
        }

        if (locationsOnPath.contains(cell.getLocation())) {
            return "  .  ";
        }

        return bodyOf(cell);
    }

    private String bottomBorderOf(Cell cell) {
        StringBuilder result = new StringBuilder();
        if (cell.isLinked(cell.getSouth())) {
            result.append("     +");
        } else {
            result.append("-----+");
        }
        return result.toString();
    }

    public Cell getRandomCell() {
        int randomRowNr = new Random().nextInt(rows);
        int randomColumnNr = new Random().nextInt(cols);
        return grid.get(new Location(randomRowNr, randomColumnNr));
    }

    public boolean isBorderLocation(Location location) {
        return location.getRow() == 0 ||
                location.getRow() == rows - 1 ||
                location.getCol() == 0 ||
                location.getCol() == cols - 1;
    }

    public String printLongestRoute() {
        Distances tmpDistances = distances;
        distances = null;
        String result = toString();
        distances = tmpDistances;
        return result;
    }

    public String printDistances() {
        Set<Location> tmpLocations = locationsOnPath;
        locationsOnPath = null;
        String result = toString();
        locationsOnPath = tmpLocations;
        return result;
    }

    public String printGrid() {
        Distances tmpDistances = distances;
        Set<Location> tmpLocations = locationsOnPath;
        distances = null;
        locationsOnPath = null;
        String result = toString();
        distances = tmpDistances;
        locationsOnPath = tmpLocations;
        return result;
    }
}
