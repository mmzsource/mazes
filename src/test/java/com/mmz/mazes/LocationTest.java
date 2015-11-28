package com.mmz.mazes;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class LocationTest {

    Location location = new Location(1, 1);

    @Test
    public void calculatesNorthCorrectly() {
        assertThat(location.getNorth(), is(new Location(0, 1)));
    }

    @Test
    public void calculatesEastCorrectly() {
        assertThat(location.getEast(), is(new Location(1, 2)));
    }

    @Test
    public void calculatesSouthCorrectly() {
        assertThat(location.getSouth(), is(new Location(2, 1)));
    }

    @Test
    public void calculatesWestCorrectly() {
        assertThat(location.getWest(), is(new Location(1, 0)));
    }
}
