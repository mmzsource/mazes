package com.mmz.mazes;

import java.util.*;

public class Cell {

    private final Location location;

    private Cell north;

    private Cell east;

    private Cell south;

    private Cell west;

    private Set<Cell> links = new HashSet<>();

    public Cell(Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }

    public void link(Cell cell) {
        if (links.contains(cell)) {
            return;
        }
        links.add(cell);
        cell.link(this);
    }

    public boolean isLinked(Cell cell) {
        return links.contains(cell);
    }

    public Set<Cell> getLinks() {
        return Collections.unmodifiableSet(links);
    }

    public void setNorth(Cell cell) {
        this.north = cell;
    }

    public Cell getNorth() {
        return north;
    }

    public boolean hasNorth() {
        return north != null;
    }

    public void setEast(Cell cell) {
        this.east = cell;
    }

    public Cell getEast() {
        return east;
    }

    public boolean hasEast() {
        return east != null;
    }

    public void setSouth(Cell cell) {
        this.south = cell;
    }

    public Cell getSouth() {
        return south;
    }

    public boolean hasSouth() {
        return south != null;
    }

    public void setWest(Cell cell) {
        this.west = cell;
    }

    public Cell getWest() {
        return west;
    }

    public boolean hasWest() {
        return west != null;
    }

    public List<Cell> getUnvisitedNeighbours() {
        List<Cell> unvisitedNeighbours = new ArrayList<>();
        if (hasNorth() && north.getLinks().size() == 0) unvisitedNeighbours.add(north);
        if (hasEast() && east.getLinks().size() == 0) unvisitedNeighbours.add(east);
        if (hasSouth() && south.getLinks().size() == 0) unvisitedNeighbours.add(south);
        if (hasWest() && west.getLinks().size() == 0) unvisitedNeighbours.add(west);
        return unvisitedNeighbours;
    }
}
