package com.mmz.mazes;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class RouteAnalyser {

    public static void setLongestPathOn(Grid grid) {
        Cell randomCell = grid.getRandomCell();
        Distances distances = new Distances(randomCell);

        Location furthestBorderLocation = findBorderCellFurthestFromStartLocation(grid, distances, randomCell.getLocation());

        distances = new Distances(grid.getCell(furthestBorderLocation));

        Location furthestFromFurthest = findBorderCellFurthestFromStartLocation(grid, distances, furthestBorderLocation);

        LinkedList<Location> shortestPath = distances.getShortestPathTo(grid.getCell(furthestFromFurthest));

        Set<Location> locations = new HashSet<>();
        for (Location loc : shortestPath) {
            locations.add(loc);
        }

        grid.setDistances(distances);
        grid.setStartStop(furthestBorderLocation, furthestFromFurthest);
        grid.setLocationsOnPath(locations);
    }

    private static Location findBorderCellFurthestFromStartLocation(Grid grid, Distances distances, Location start) {
        Location furthestBorderLocationSoFar = start;
        Integer maxDistance = 0;
        for (Cell cell : grid.getCells()) {
            Location location = cell.getLocation();
            if (distances.getDistanceFromRoot(location).compareTo(maxDistance) > 0 && grid.isBorderLocation(location)) {
                furthestBorderLocationSoFar = location;
                maxDistance = distances.getDistanceFromRoot(location);
            }
        }
        return furthestBorderLocationSoFar;
    }
}
