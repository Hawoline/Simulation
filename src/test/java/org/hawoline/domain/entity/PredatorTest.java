package org.hawoline.domain.entity;

import java.util.HashMap;
import java.util.Map;
import org.hawoline.domain.Coordinates;
import org.hawoline.domain.Field;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PredatorTest {
  private Predator predator = new Predator(1, 10, 2);
  private Coordinates predatorCoordinates = new Coordinates(3, 5);
  private Coordinates herbivoreCoordinates = new Coordinates(1, 5);
  private Herbivore herbivore = new Herbivore(1, 10);
  private Map<Coordinates, Entity> entities = new HashMap<>();
  private Field field;

  @BeforeEach
  void initField() {
    entities.put(herbivoreCoordinates, herbivore);
    entities.put(predatorCoordinates, predator);
    field = new Field(entities, 20, 20);
  }

  @Test
  void testAttackHerbivore() {
    final Field oneStepCloserToGrass = predator.makeMove(field, predatorCoordinates);
    final Coordinates nextPredatorCoordinates = new Coordinates(2, 5);
    assertTrue(oneStepCloserToGrass.entityExits(nextPredatorCoordinates));

    final Field damagedHerbivoreAndPredator = predator.makeMove(oneStepCloserToGrass, nextPredatorCoordinates);
    assertEquals(8, ((Creature) (damagedHerbivoreAndPredator.getEntity(herbivoreCoordinates))).getHealth());
    assertEquals(9, ((Creature) (damagedHerbivoreAndPredator.getEntity(nextPredatorCoordinates))).getHealth());
  }

  @Test
  void testHerbivoreDeath() {
    final Field oneStepCloserToGrass = predator.makeMove(field, predatorCoordinates);
    final Coordinates nextPredatorCoordinates = new Coordinates(2, 5);
    Field deathHerbivore = predator.makeMove(oneStepCloserToGrass, nextPredatorCoordinates);
    for (int i = 0; i < 4; i++) {
      deathHerbivore = predator.makeMove(deathHerbivore, nextPredatorCoordinates);
    }
    assertFalse(deathHerbivore.entityExits(herbivoreCoordinates));
    assertEquals(1, deathHerbivore.entities().size());
  }
}