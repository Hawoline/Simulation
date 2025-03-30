package org.hawoline.domain;

import java.util.HashMap;

import org.hawoline.domain.entity.Predator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameMapTest {
    private final GameMap emptyMap = new GameMap(new HashMap<>());
    private final int MAX_ENTITY_COUNT = 6;

    @Test
    protected void testEmptyMap() {
        assertTrue(emptyMap.getEntities().isEmpty());
    }

    @Test
    protected void testConcreteEntityAdditionOnConcreteCoordinates() { //Да ну к черту тестить добавление, ааа
        GameMap mapWithOnePredator = emptyMap.add(new Coordinates(2, 3), new Predator(2, 2, 6));
        assertEquals(1, mapWithOnePredator.getEntities().size());
    }

    @Test
    protected void testRemoveEntities() {
        GameMap gameMapWithEntities = new AddRandomEntitiesToMapAction().addEachTypeOfEntityWithRandomPosition(emptyMap, MAX_ENTITY_COUNT);
        GameMap emptyMap = gameMapWithEntities.removeAll();
        assertTrue(emptyMap.getEntities().isEmpty());
        gameMapWithEntities = new AddRandomEntitiesToMapAction().addEachTypeOfEntityWithRandomPosition(emptyMap, MAX_ENTITY_COUNT);
        assertFalse(gameMapWithEntities.getEntities().isEmpty());
        testSingleRemove();
    }
    private void testSingleRemove() {
        GameMap mapWithOnePredator = emptyMap.add(new Coordinates(2, 3), new Predator(2, 2, 6));
        GameMap emptyMapAfterRemovingPredator = mapWithOnePredator.remove(new Coordinates(2, 3));
        assertTrue(emptyMapAfterRemovingPredator.getEntities().isEmpty());
    }

    @Test
    public void testEntityExistsInSomeCoordinates() {
        GameMap mapWithOnePredator = emptyMap.add(new Coordinates(2, 3), new Predator(2, 2, 6));
        assertTrue(mapWithOnePredator.entityExitsIn(new Coordinates(2, 3)));
        assertFalse(mapWithOnePredator.entityExitsIn(new Coordinates(2, 4)));
    }
}