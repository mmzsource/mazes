package com.mmz.mazes;

import java.util.*;

public class Distances {

    private Cell root;

    private Map<Location, Integer> distances = new HashMap<>();

    public Distances(Cell start) {
        this.root = start;
        int distance = 0;
        distances.put(start.getLocation(), distance);
        Set<Cell> frontier = start.getLinks();
        while (frontier.size() > 0) {
            distance++;
            Set<Cell> newFrontier = new HashSet<>();
            for (Cell frontierCell : frontier) {
                distances.put(frontierCell.getLocation(), distance);
                for (Cell cell : frontierCell.getLinks()){
                    if (!distances.containsKey(cell.getLocation())){
                        newFrontier.add(cell);
                    }
                }
            }
            frontier = newFrontier;
        }
    }

    public Integer getDistanceFromRoot(Location location) {
        return distances.get(location);
    }

    public LinkedList<Location> getShortestPathTo(Cell end){
        LinkedList<Location> locations = new LinkedList<>();
        locations.add(end.getLocation());
        int distance = distances.get(end.getLocation());
        Cell currentCell = end;
        while (distance > 0) {
            distance--;
            for (Cell linkedCell : currentCell.getLinks()) {
                if (distances.get(linkedCell.getLocation()).compareTo(distance) == 0){
                    locations.add(linkedCell.getLocation());
                    currentCell = linkedCell;
                    break;
                }
            }
        }
        return locations;
    }
}
