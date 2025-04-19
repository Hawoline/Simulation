package org.hawoline.domain;

import java.util.HashMap;

import org.hawoline.domain.entity.Herbivore;
import org.hawoline.domain.entity.Predator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FieldTest {
    private final Field emptyMap = new Field(new HashMap<>());
    private final int MAX_ENTITY_COUNT = 6;

    @Test
    protected void testEmptyMap() {
        assertTrue(emptyMap.entities().isEmpty());
    }

    @Test
    protected void testConcreteEntityAdditionOnConcreteCoordinates() { //Да ну к черту тестить добавление
        Field mapWithOnePredator = emptyMap.add(new Coordinates(2, 3), new Predator(2, 2, 6));
        assertEquals(1, mapWithOnePredator.entities().size());
    }

    @Test
    protected void testRemoveEntities() {
        Field fieldWithEntities = new AddRandomEntitiesToFieldAction(emptyMap).addEachTypeOfEntitiesWithRandomPosition(MAX_ENTITY_COUNT);
        Field emptyMap = fieldWithEntities.removeAll();
        assertTrue(emptyMap.entities().isEmpty());
        fieldWithEntities = new AddRandomEntitiesToFieldAction(emptyMap).addEachTypeOfEntitiesWithRandomPosition(MAX_ENTITY_COUNT);
        assertFalse(fieldWithEntities.entities().isEmpty());
        testSingleRemove();
    }
    private void testSingleRemove() {
        Field mapWithOnePredator = emptyMap.add(new Coordinates(2, 3), new Predator(2, 2, 6));
        Field emptyMapAfterRemovingPredator = mapWithOnePredator.remove(new Coordinates(2, 3));
        assertTrue(emptyMapAfterRemovingPredator.entities().isEmpty());
    }

    @Test
    public void testEntityExistsInSomeCoordinates() {
        Field mapWithOnePredator = emptyMap.add(new Coordinates(2, 3), new Predator(2, 2, 6));
        assertTrue(mapWithOnePredator.entityExits(new Coordinates(2, 3)));
        assertFalse(mapWithOnePredator.entityExits(new Coordinates(2, 4)));
    }

    @Test
    public void testMoveEntities() {
        Field fieldWithHerbivore = emptyMap.add(new Coordinates(1, 1), new Herbivore(1, 12));
        Field herbivoreMovedMap = fieldWithHerbivore.move(new Coordinates(1, 1), new Coordinates(2, 2));
        assertEquals(1, herbivoreMovedMap.entities().size());
        assertNull(herbivoreMovedMap.getEntityIn(new Coordinates(1, 1)));
        assertNotNull(herbivoreMovedMap.getEntityIn(new Coordinates(2, 2)));
    }
}