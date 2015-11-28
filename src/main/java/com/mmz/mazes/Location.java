package com.mmz.mazes;

import java.util.Objects;

public class Location {

    private final int row;

    private final int col;

    public Location(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public Location getNorth() {
        return new Location(row - 1, col);
    }

    public Location getEast() {
        return new Location(row, col + 1);
    }

    public Location getSouth() {
        return new Location(row + 1, col);
    }

    public Location getWest() {
        return new Location(row, col - 1);
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final Location other = (Location) obj;
        return Objects.equals(this.row, other.row) && Objects.equals(this.col, other.col);
    }

    @Override
    public String toString() {
        return "(" + row + ", " + col + ")";
    }
}
