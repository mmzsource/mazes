package com.mmz.mazes;

import java.util.*;

public class Distances {

    private final Cell root;

    private Map<Location, Integer> distances = new HashMap<>();

    public Distances(Cell root) {
        this.root = root;
        int distance = 0;
        distances.put(root.getLocation(), distance);
        Set<Cell> frontier = root.getLinks();
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

    public Cell getRoot() {
        return root;
    }

    public Integer getDistanceFromRoot(Location location) {
        return distances.get(location);
    }
}
