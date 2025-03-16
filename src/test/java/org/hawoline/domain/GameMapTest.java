package org.hawoline.domain;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameMapTest {
    private final GameMap emptyMap = new GameMap(new HashMap<>());
    private final int MAX_ENTITY_COUNT = 6;

    @BeforeAll
    protected static void setUp() {
    }

    @Test
    protected void testEmptyMap() {
        assertTrue(emptyMap.getEntities().isEmpty());
    }

    @Test
    protected void testRandomEntityAddition() {
        GameMap gameMapWithEntities = emptyMap.addEachTypeOfEntityWithRandomPosition(MAX_ENTITY_COUNT);
        Map<Coordinates, Entity> entities = gameMapWithEntities.getEntities();
        assertTrue(entities.size() < EntityType.values().length * MAX_ENTITY_COUNT);
        assertTrue(entities.size() > MAX_ENTITY_COUNT);
        GameMap moreEntitiesOnMap = gameMapWithEntities.addEachTypeOfEntityWithRandomPosition(MAX_ENTITY_COUNT);
        entities = moreEntitiesOnMap.getEntities();
        assertTrue(entities.size() < EntityType.values().length * MAX_ENTITY_COUNT * 2);
        assertTrue(entities.size() > MAX_ENTITY_COUNT * 2);

        GameMap doubleEntityMap = emptyMap.addRandomEntity().addRandomEntity();
        Map<Coordinates, Entity> twoEntities = doubleEntityMap.getEntities();
        Set<Coordinates> twoEntityCoordinates = twoEntities.keySet();
        Iterator<Coordinates> coordinatesIterator = twoEntityCoordinates.iterator();
        Coordinates first = coordinatesIterator.next();
        Coordinates second = coordinatesIterator.next();
        assertNotEquals(twoEntities.get(first), twoEntities.get(second));
    }
    @Test
    protected void testConcreteEntityAdditionOnConcreteCoordinates() { //Да ну к черту тестить добавление, ааа
        GameMap mapWithOnePredator = emptyMap.add(new Coordinates(2, 3), new Predator(2, 2, 6));
        assertEquals(1, mapWithOnePredator.getEntities().size());
    }

    @Test
    protected void testRemoveEntities() {
        GameMap gameMapWithEntities = emptyMap.addEachTypeOfEntityWithRandomPosition(MAX_ENTITY_COUNT);
        GameMap emptyMap = gameMapWithEntities.removeAll();
        assertTrue(emptyMap.getEntities().isEmpty());
        gameMapWithEntities = emptyMap.addEachTypeOfEntityWithRandomPosition(MAX_ENTITY_COUNT);
        assertFalse(gameMapWithEntities.getEntities().isEmpty());
        testSingleRemove();
    }
    private void testSingleRemove() {
        GameMap mapWithOnePredator = emptyMap.add(new Coordinates(2, 3), new Predator(2, 2, 6));
        GameMap emptyMapAfterRemovingPredator = mapWithOnePredator.remove(new Coordinates(2, 3));
        assertTrue(emptyMapAfterRemovingPredator.getEntities().isEmpty());
    }

    @AfterAll
    protected static void tearDown() {
    }
}