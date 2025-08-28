package org.hawoline.domain;

import java.util.HashMap;

import org.hawoline.domain.entity.Herbivore;
import org.hawoline.domain.entity.Predator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WorldTest {
    private final World map = new World(new HashMap<>(), 20, 20);
    private final int MAX_ENTITY_COUNT = 6;
    private final AddRandomEntitiesAction
        addRandomEntitiesToFieldAction = new AddRandomEntitiesAction();

    @Test
    protected void testEmptyMap() {
        assertTrue(map.entities().isEmpty());
    }

    @Test
    protected void testConcreteEntityAdditionOnConcreteCoordinates() { //Да ну к черту тестить добавление
        map.put(new Coordinates(2, 3), new Predator(2, 2, 6));
        assertEquals(1, map.entities().size());
    }

    @Test
    protected void testRemoveEntities() {
        addRandomEntitiesToFieldAction.addEachTypeOfEntitiesWithRandomPosition(
            map, MAX_ENTITY_COUNT);
        map.removeAll();
        assertTrue(map.entities().isEmpty());
        addRandomEntitiesToFieldAction.addEachTypeOfEntitiesWithRandomPosition(map, MAX_ENTITY_COUNT);
        assertFalse(map.entities().isEmpty());
        map.removeAll();
        testSingleRemove();
    }
    private void testSingleRemove() {
        map.put(new Coordinates(2, 3), new Predator(2, 2, 6));
        map.remove(new Coordinates(2, 3));
        assertTrue(map.entities().isEmpty());
    }

    @Test
    public void testEntityExistsInSomeCoordinates() {
        map.put(new Coordinates(2, 3), new Predator(2, 2, 6));
        assertTrue(map.entityExits(new Coordinates(2, 3)));
        assertFalse(map.entityExits(new Coordinates(2, 4)));
    }

    @Test
    public void testMoveEntities() {
        map.put(new Coordinates(1, 1), new Herbivore(1, 12));
        map.move(new Coordinates(1, 1), new Coordinates(2, 2));
        assertEquals(1, map.entities().size());
        assertNull(map.getEntity(new Coordinates(1, 1)));
        assertNotNull(map.getEntity(new Coordinates(2, 2)));
    }
}