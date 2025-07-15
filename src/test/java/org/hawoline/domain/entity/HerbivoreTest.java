package org.hawoline.domain.entity;

import org.hawoline.domain.Coordinates;
import org.hawoline.domain.Field;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HerbivoreTest {
    private Herbivore herbivore = new Herbivore(1, 10);
    private Coordinates herbivoreCoordinates = new Coordinates(3, 5);
    private Herbivore faster = new Herbivore(2, 10);
    private Coordinates fasterHerbivoreCoordinates = new Coordinates(4, 5);

    @Test
    void testSearchGrass() {
        final Map<Coordinates, Entity> entities = new HashMap<>();
        entities.put(new Coordinates(0, 1), new Grass());
        entities.put(herbivoreCoordinates, herbivore);
        entities.put(fasterHerbivoreCoordinates, faster);
        final Field field = new Field(entities);
        final Field oneStepCloserToGrass = herbivore.makeMove(field, herbivoreCoordinates);
        assertTrue(oneStepCloserToGrass.entityExits(new Coordinates(2, 5)));
        final Field twoStepCloserToGrass = faster.makeMove(field, fasterHerbivoreCoordinates);
        assertTrue(twoStepCloserToGrass.entityExits(new Coordinates(3, 4)));

    }

    @Test
    void testGrassNearby() {
        final Map<Coordinates, Entity> entities = new HashMap<>();
        entities.put(new Coordinates(3, 4), new Grass());
        Coordinates herbivoreCoordinates = new Coordinates(3, 5);
        entities.put(herbivoreCoordinates, herbivore);
        final Field field = new Field(entities);
        final Field notChanges = herbivore.makeMove(field, herbivoreCoordinates);
        assertTrue(notChanges.entityExits(new Coordinates(3, 5)));
        assertEquals(1, notChanges.entities().size());
    }
}