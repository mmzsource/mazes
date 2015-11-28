package com.mmz.mazes;

import org.junit.Test;

import java.util.Collection;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNull;

public class Grid3x3Test {

    Grid grid = new Grid(3,3);

    @Test
    public void containsNineCells(){
        Collection<Cell> cells = grid.getCells();
        assertThat(cells.size(), is(9));
    }

    @Test
    public void containsCorrectlyConfiguredNorthWestCorner(){
        Location location = new Location(0, 0);
        Cell cell = grid.getCell(location);
        assertNull(cell.getNorth());
        assertThat(cell.getEast().getLocation(), is(new Location(0,1)));
        assertThat(cell.getSouth().getLocation(), is(new Location(1,0)));
        assertNull(cell.getWest());
    }
}
