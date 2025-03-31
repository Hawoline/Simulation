package org.hawoline.domain;

import org.hawoline.domain.entity.Entity;
import org.hawoline.domain.entity.EntityType;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class AddRandomEntitiesToMapActionTest {
    private final int MAX_RANDOM_ENTITY_COUNT = 6;
    private final GameMap emptyMap = new GameMap(new HashMap<>());
    private final AddRandomEntitiesToMapAction addRandomEntitiesToMapAction = new AddRandomEntitiesToMapAction();

    @Test
    protected void multiplyRandomEntityAddition() {
        for (int i = 0; i < 100; i++) {
            testRandomEntityAddition();
        }
    }
    @Test
    protected void testRandomEntityAddition() {
        GameMap gameMapWithEntities = addRandomEntitiesToMapAction.addEachTypeOfEntityWithRandomPosition(emptyMap,
            MAX_RANDOM_ENTITY_COUNT);
        final Map<Coordinates, Entity> entities = gameMapWithEntities.getEntities();
        testEntityCountIsInterval(entities.size(), MAX_RANDOM_ENTITY_COUNT, EntityType.values().length * MAX_RANDOM_ENTITY_COUNT);
        GameMap moreEntitiesOnMap = addRandomEntitiesToMapAction.addEachTypeOfEntityWithRandomPosition(gameMapWithEntities,
            MAX_RANDOM_ENTITY_COUNT);
        final Map<Coordinates, Entity> moreEntities = moreEntitiesOnMap.getEntities();
        testEntityCountIsInterval(moreEntities.size(), MAX_RANDOM_ENTITY_COUNT * 2,
            EntityType.values().length * MAX_RANDOM_ENTITY_COUNT * 2);

        testTwoEntitiesNotEquals();
    }

    private void testTwoEntitiesNotEquals() {
        GameMap doubleEntityMap = new AddRandomEntitiesToMapAction().addRandomEntity(new AddRandomEntitiesToMapAction().addRandomEntity(emptyMap));
        Map<Coordinates, Entity> twoEntities = doubleEntityMap.getEntities();
        Set<Coordinates> twoEntityCoordinates = twoEntities.keySet();
        Iterator<Coordinates> coordinatesIterator = twoEntityCoordinates.iterator();
        Coordinates first = coordinatesIterator.next();
        Coordinates second = coordinatesIterator.next();
        assertNotEquals(twoEntities.get(first), twoEntities.get(second));
    }

    private void testEntityCountIsInterval(int size, int minEntitiesCount, int maxEntitiesCount) {
        assertTrue(size > minEntitiesCount);
        assertTrue(size < maxEntitiesCount);
    }
}