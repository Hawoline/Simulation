package org.hawoline.domain.entity;

import org.hawoline.domain.Coordinates;
import org.hawoline.domain.Field;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class HerbivoreTest {
    private Herbivore herbivore = new Herbivore(1, 10);
    
    @Test
    void testSearchGrass() {
        final Map<Coordinates, Entity> entities = new HashMap<>();
        entities.put(new Coordinates(0, 1), new Grass());
        Coordinates herbivoreCoordinates = new Coordinates(3, 5);
        entities.put(herbivoreCoordinates, herbivore);
        final Field field = new Field(entities);
        final Coordinates oneStepCloserToGrass = herbivore.move(field, herbivoreCoordinates);
        assertEquals(new Coordinates(3, 4), oneStepCloserToGrass);
        assertEquals(new Coordinates(2, 5), oneStepCloserToGrass);
    }
}