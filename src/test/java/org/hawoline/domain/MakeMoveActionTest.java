package org.hawoline.domain;

import java.util.HashMap;
import org.hawoline.domain.entity.Creature;
import org.hawoline.domain.entity.Grass;
import org.hawoline.domain.entity.Herbivore;
import org.hawoline.domain.entity.Predator;
import org.hawoline.domain.entity.Rock;
import org.hawoline.domain.entity.Tree;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MakeMoveActionTest {
  private final Field emptyField = new Field(new HashMap<>(), 20, 20);
  /*
  G T - -
  - R - -
  - - H -
  - - - P
  - - - G
   */
  private final Rock rock = new Rock();
  private final Coordinates rockCoordinates = new Coordinates(1, 1);

  private final Tree tree = new Tree();
  private final Coordinates treeCoordinates = new Coordinates(1, 0);

  private final Grass grass = new Grass();
  private final Coordinates grassCoordinates = new Coordinates(0, 0);
  private final Coordinates secondGrassCoordinates = new Coordinates(3, 4);

  private final Herbivore herbivore = new Herbivore(1, 10);
  private final Coordinates herbivoreCoordinates = new Coordinates(2, 2);

  private final Predator predator = new Predator(2, 10, 2);
  private final Coordinates predatorCoordinates = new Coordinates(3, 3);

  @Test
  void fillField() {
    final MakeMoveAction makeMoveAction = new MakeMoveAction();
    final Field field = emptyField
        .put(rockCoordinates, rock)
        .put(treeCoordinates, tree)
        .put(grassCoordinates, grass)
        .put(secondGrassCoordinates, grass)
        .put(herbivoreCoordinates, herbivore)
        .put(predatorCoordinates, predator);
/*
  G T - -
  - R - -
  - - - -
  - - H P
  - - - G
   */
    final Field moved = makeMoveAction.act(field);

    Coordinates herbivoreNextCoordinates = new Coordinates(2, 3);
    assertTrue(moved.entityExits(herbivoreNextCoordinates));
    assertEquals(7, ((Creature) moved.getEntity(herbivoreNextCoordinates)).getHealth());

    assertEquals(9, ((Creature) moved.getEntity(new Coordinates(3, 3))).getHealth());
  }
}