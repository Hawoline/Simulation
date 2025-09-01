package org.hawoline.domain.entity;

import org.hawoline.domain.Coordinates;
import org.hawoline.domain.World;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

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
        final ConcurrentMap<Coordinates, Entity> entities = new ConcurrentHashMap<>();
        entities.put(new Coordinates(3, 2), grass);
        entities.put(herbivoreCoordinates, herbivore);
        entities.put(fasterHerbivoreCoordinates, faster);
        final World world = new World(entities, 20, 20);
        herbivore.makeMove(world, herbivoreCoordinates);
        assertTrue(world.entityExits(new Coordinates(3, 4)));

        faster.makeMove(world, fasterHerbivoreCoordinates);
        assertTrue(world.entityExits(new Coordinates(3, 4)));
        faster.makeMove(world, new Coordinates(3, 4));
        assertTrue(world.entityExits(new Coordinates(3, 3)));
    }

    @Test
    void testGrassNearby() {
        final ConcurrentMap<Coordinates, Entity> entities = new ConcurrentHashMap<>();
        entities.put(new Coordinates(3, 4), new Grass());
        entities.put(herbivoreCoordinates, herbivore);
        final World world = new World(entities, 20, 20);
        herbivore.makeMove(world, herbivoreCoordinates);
        assertTrue(world.entityExits(new Coordinates(3, 5)));
        assertEquals(1, world.entities().size());
    }


    @Test
    void testHungryHerbivoreDeath() {
        final ConcurrentMap<Coordinates, Entity> entities = new ConcurrentHashMap<>();
        Creature hungryHerbivore = new Herbivore(1, 1);
        entities.put(herbivoreCoordinates, hungryHerbivore);
        final World world = new World(entities, 20, 20);
        hungryHerbivore.makeMove(world, herbivoreCoordinates);
        assertTrue(world.entities().isEmpty());
    }
}