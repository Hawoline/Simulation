package org.hawoline.domain;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameMapTest {
    private final GameMap emptyMap = new GameMap(new HashMap<>());
    private final int MAX_ENTITIES_COUNT = 6;

    @BeforeAll
    protected static void setUp() {
    }

    @Test
    protected void testEmptyMap() {
        assertTrue(emptyMap.getEntities().isEmpty());

    }

    @Test
    protected void testRandomMultiplyEntityAddition() {
        GameMap gameMapWithEntities = emptyMap.addEachTypeOfEntitiesWithRandomPositions(MAX_ENTITIES_COUNT);
        Map<Coordinates, Entity> entities = gameMapWithEntities.getEntities();
        assertTrue(entities.size() < EntityType.values().length * MAX_ENTITIES_COUNT);
        assertTrue(entities.size() > MAX_ENTITIES_COUNT);
        GameMap moreEntitiesOnMap = gameMapWithEntities.addEachTypeOfEntitiesWithRandomPositions(MAX_ENTITIES_COUNT);
        entities = moreEntitiesOnMap.getEntities();
        assertTrue(entities.size() < EntityType.values().length * MAX_ENTITIES_COUNT * 2);
        assertTrue(entities.size() > MAX_ENTITIES_COUNT * 2);
    }

    @Test
    protected void testRemoveEntities() {
        GameMap gameMapWithEntities = emptyMap.addEachTypeOfEntitiesWithRandomPositions(MAX_ENTITIES_COUNT);
        GameMap emptyMap = gameMapWithEntities.reset();
        assertTrue(emptyMap.getEntities().isEmpty());
        gameMapWithEntities = emptyMap.addEachTypeOfEntitiesWithRandomPositions(MAX_ENTITIES_COUNT);
        assertFalse(gameMapWithEntities.getEntities().isEmpty());
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }
}