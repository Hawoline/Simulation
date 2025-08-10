package org.hawoline.domain.entity;

import org.hawoline.domain.Coordinates;
import org.hawoline.domain.Field;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HerbivoreTest {
    private final Herbivore herbivore = new Herbivore(1, 10);
    private final Coordinates herbivoreCoordinates = new Coordinates(3, 5);

    private final Herbivore faster = new Herbivore(2, 10);
    private final Coordinates fasterHerbivoreCoordinates = new Coordinates(4, 5);

    private final Grass grass = new Grass();
    private final Coordinates grassCoordinates = new Coordinates(0, 1); // Надеюсь ты когда-нибудь пригодишься.

    @Test
    void testSearchGrass() {
        final Map<Coordinates, Entity> entities = new HashMap<>();
        entities.put(new Coordinates(3, 2), grass);
        entities.put(herbivoreCoordinates, herbivore);
        entities.put(fasterHerbivoreCoordinates, faster);
        final Field field = new Field(entities, 20, 20);
        final Field oneStepCloserToGrass = herbivore.makeMove(field, herbivoreCoordinates);
        assertTrue(oneStepCloserToGrass.entityExits(new Coordinates(3, 4)));

        final Field twoStepCloserToGrass = faster.makeMove(field, fasterHerbivoreCoordinates);
        assertTrue(twoStepCloserToGrass.entityExits(new Coordinates(3, 4)));
        final Field eatGrass = faster.makeMove(twoStepCloserToGrass, new Coordinates(3, 4));
        assertTrue(eatGrass.entityExits(new Coordinates(3, 3)));
    }

    @Test
    void testGrassNearby() {
        final Map<Coordinates, Entity> entities = new HashMap<>();
        entities.put(new Coordinates(3, 4), new Grass());
        entities.put(herbivoreCoordinates, herbivore);
        final Field field = new Field(entities, 20, 20);
        final Field grassAte = herbivore.makeMove(field, herbivoreCoordinates);
        assertTrue(grassAte.entityExits(new Coordinates(3, 5)));
        assertEquals(1, grassAte.entities().size());
    }
}