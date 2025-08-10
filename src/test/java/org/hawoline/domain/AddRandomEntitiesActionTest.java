package org.hawoline.domain;

import org.hawoline.domain.entity.Entity;
import org.hawoline.domain.entity.EntityType;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class AddRandomEntitiesActionTest {
    private final int MAX_RANDOM_EACH_TYPE_ENTITY_COUNT = 6;
    private final Field emptyMap = new Field(new HashMap<>(), 20, 20);
    private final AddRandomEntitiesAction
        arrangeRandomEntitiesToMapAction = new AddRandomEntitiesAction();
    @Test
    protected void multiplyRandomEntityAddition() {
        for (int i = 0; i < 100; i++) {
            testRandomEntityAddition();
        }
    }

    @Test
    protected void testRandomEntityAddition() {
        Field fieldWithEntities = arrangeRandomEntitiesToMapAction.addEachTypeOfEntitiesWithRandomPosition(emptyMap, MAX_RANDOM_EACH_TYPE_ENTITY_COUNT);
        final Map<Coordinates, Entity> entities = fieldWithEntities.entities();
        testEntityCountIsInterval(entities.size(), MAX_RANDOM_EACH_TYPE_ENTITY_COUNT, EntityType.values().length * MAX_RANDOM_EACH_TYPE_ENTITY_COUNT);
        Field moreEntitiesOnMap = arrangeRandomEntitiesToMapAction.addEachTypeOfEntitiesWithRandomPosition(fieldWithEntities,
                MAX_RANDOM_EACH_TYPE_ENTITY_COUNT);
        final Map<Coordinates, Entity> moreEntities = moreEntitiesOnMap.entities();
        testEntityCountIsInterval(moreEntities.size(), MAX_RANDOM_EACH_TYPE_ENTITY_COUNT * 2,
            EntityType.values().length * MAX_RANDOM_EACH_TYPE_ENTITY_COUNT * 2);

        testTwoEntitiesNotEquals();
    }

    private void testTwoEntitiesNotEquals() {
        Field doubleEntityMap = arrangeRandomEntitiesToMapAction.addRandomEntity(arrangeRandomEntitiesToMapAction.addRandomEntity(emptyMap));
        Map<Coordinates, Entity> twoEntities = doubleEntityMap.entities();
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