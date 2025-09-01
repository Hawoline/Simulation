package org.hawoline.domain;

import org.hawoline.domain.entity.Entity;
import org.hawoline.domain.entity.EntityType;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.jupiter.api.Assertions.*;

class AddRandomEntitiesWorldActionTest {
    private final int MAX_RANDOM_EACH_TYPE_ENTITY_COUNT = 6;
    private final World world = new World(new ConcurrentHashMap<>(), 20, 20);
    private final AddRandomEntitiesAction
        arrangeRandomEntitiesToMapAction = new AddRandomEntitiesAction(MAX_RANDOM_EACH_TYPE_ENTITY_COUNT);
    @Test
    protected void multiplyRandomEntityAddition() {
        for (int i = 0; i < 100; i++) {
            testRandomEntityAddition();
        }
    }

    @Test
    protected void testRandomEntityAddition() {
        final World world = new World(new ConcurrentHashMap<>(), 20, 20);
        arrangeRandomEntitiesToMapAction.act(
            world);
        final Map<Coordinates, Entity> entities = world.entities();
        testEntityCountIsInterval(entities.size(), MAX_RANDOM_EACH_TYPE_ENTITY_COUNT, EntityType.values().length * MAX_RANDOM_EACH_TYPE_ENTITY_COUNT);
        arrangeRandomEntitiesToMapAction.act(
            world);
        final Map<Coordinates, Entity> moreEntities = world.entities();
        testEntityCountIsInterval(moreEntities.size(), MAX_RANDOM_EACH_TYPE_ENTITY_COUNT * 2,
            EntityType.values().length * MAX_RANDOM_EACH_TYPE_ENTITY_COUNT * 2);

        testTwoEntitiesNotEquals();
    }

    private void testTwoEntitiesNotEquals() {
        arrangeRandomEntitiesToMapAction.addRandomEntity(world);
        arrangeRandomEntitiesToMapAction.addRandomEntity(world);
        Map<Coordinates, Entity> twoEntities = world.entities();
        Set<Coordinates> twoEntityCoordinates = twoEntities.keySet();
        Iterator<Coordinates> coordinatesIterator = twoEntityCoordinates.iterator();
        Coordinates first = coordinatesIterator.next();
        Coordinates second = coordinatesIterator.next();
        assertNotEquals(twoEntities.get(first), twoEntities.get(second));
    }

    private void testEntityCountIsInterval(int size, int minEntitiesCount, int maxEntitiesCount) {
        assertTrue(size >= minEntitiesCount);
        assertTrue(size <= maxEntitiesCount);
    }

}