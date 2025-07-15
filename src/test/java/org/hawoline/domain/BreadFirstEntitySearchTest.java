package org.hawoline.domain;

import org.hawoline.domain.entity.Entity;
import org.hawoline.domain.entity.EntityType;
import org.hawoline.domain.entity.Grass;
import org.hawoline.domain.entity.Herbivore;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class BreadFirstEntitySearchTest {
    private Herbivore herbivore = new Herbivore(1, 10);

    @Test
    void testSearchGrass() {
        final Map<Coordinates, Entity> entities = new HashMap<>();
        entities.put(new Coordinates(0, 1), new Grass());
        entities.put(new Coordinates(3, 2), new Grass());
        Coordinates herbivoreCoordinates = new Coordinates(3, 5);
        entities.put(herbivoreCoordinates, herbivore);
        final Field field = new Field(entities);

        EntitySearch entitySearch = new BreadFirstEntitySearch(field);
        assertTrue(entitySearch.search(herbivoreCoordinates, EntityType.GRASS));
        assertEquals(new Coordinates(3, 2), entitySearch.getResult());
        Coordinates twoStepCloserToGrass = new Coordinates(3, 4);
        assertEquals(twoStepCloserToGrass, entitySearch.getPath().get(0));
    }
}