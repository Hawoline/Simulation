package org.hawoline.domain;

import org.hawoline.domain.entity.Entity;
import org.hawoline.domain.entity.EntityType;
import org.hawoline.domain.entity.Grass;
import org.hawoline.domain.entity.Herbivore;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import static org.junit.jupiter.api.Assertions.*;

class BreadFirstEntitySearchTest {
    private Herbivore herbivore = new Herbivore(1, 10);

    @Test
    void testSearchGrass() {
        final ConcurrentMap<Coordinates, Entity> entities = new ConcurrentHashMap<>();
        entities.put(new Coordinates(0, 1), new Grass());
        entities.put(new Coordinates(3, 2), new Grass());
        Coordinates herbivoreCoordinates = new Coordinates(3, 5);
        entities.put(herbivoreCoordinates, herbivore);
        final World world = new World(entities, 20, 20);

        EntitySearch entitySearch = new BreadFirstEntitySearch(world);
        assertTrue(entitySearch.search(herbivoreCoordinates, EntityType.GRASS));
        assertEquals(new Coordinates(3, 2), entitySearch.getResult());
    }
}