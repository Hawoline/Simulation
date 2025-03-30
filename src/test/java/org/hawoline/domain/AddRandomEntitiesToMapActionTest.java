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
    private final int MAX_ENTITY_COUNT = 6;
    private final GameMap emptyMap = new GameMap(new HashMap<>());
    private final AddRandomEntitiesToMapAction addRandomEntitiesToMapAction = new AddRandomEntitiesToMapAction();
    @Test
    protected void testRandomEntityAddition() {
        GameMap gameMapWithEntities = addRandomEntitiesToMapAction.addEachTypeOfEntityWithRandomPosition(emptyMap, MAX_ENTITY_COUNT);
        Map<Coordinates, Entity> entities = gameMapWithEntities.getEntities();
        assertTrue(entities.size() < EntityType.values().length * MAX_ENTITY_COUNT);
        assertTrue(entities.size() > MAX_ENTITY_COUNT);
        GameMap moreEntitiesOnMap = addRandomEntitiesToMapAction.addEachTypeOfEntityWithRandomPosition(emptyMap, MAX_ENTITY_COUNT);
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
}