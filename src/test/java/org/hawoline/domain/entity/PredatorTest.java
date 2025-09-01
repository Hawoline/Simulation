package org.hawoline.domain.entity;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.hawoline.domain.Coordinates;
import org.hawoline.domain.World;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PredatorTest {
  private final Predator predator = new Predator(1, 10, 2);
  private final Coordinates predatorCoordinates = new Coordinates(3, 5);
  private final Coordinates herbivoreCoordinates = new Coordinates(1, 5);
  private final Herbivore herbivore = new Herbivore(1, 10);
  private final ConcurrentMap<Coordinates, Entity> entities = new ConcurrentHashMap<>();
  private World world;

  @BeforeEach
  void initField() {
    entities.put(herbivoreCoordinates, herbivore);
    entities.put(predatorCoordinates, predator);
    world = new World(entities, 20, 20);
  }

  @Test
  void testAttackHerbivore() {
    predator.makeMove(world, predatorCoordinates);
    final Coordinates nextPredatorCoordinates = new Coordinates(2, 5);
    assertTrue(world.entityExits(nextPredatorCoordinates));

    predator.makeMove(world, nextPredatorCoordinates);
    assertEquals(8, ((Creature) (world.getEntity(herbivoreCoordinates))).getHealth());
    assertEquals(8, ((Creature) (world.getEntity(nextPredatorCoordinates))).getHealth());
  }

  @Test
  void testHerbivoreDeath() {
    predator.makeMove(world, predatorCoordinates);
    final Coordinates nextPredatorCoordinates = new Coordinates(2, 5);
    predator.makeMove(world, nextPredatorCoordinates);
    for (int i = 0; i < 4; i++) {
      predator.makeMove(world, nextPredatorCoordinates);
    }
    assertFalse(world.entityExits(herbivoreCoordinates));
    assertEquals(1, world.entities().size());
  }

  @Test
  void testPredatorDeath() {
    final Predator oneShotPredator = new Predator(1, 1, 1);
    world.put(predatorCoordinates, oneShotPredator);
    oneShotPredator.makeMove(world, predatorCoordinates);
    assertEquals(1, world.entities().size());
  }
}