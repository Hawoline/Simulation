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
  private final World world = new World(new HashMap<>(), 20, 20);
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
    world.put(rockCoordinates, rock);
    world.put(treeCoordinates, tree);
    world.put(grassCoordinates, grass);
    world.put(secondGrassCoordinates, grass);
    world.put(herbivoreCoordinates, herbivore);
    world.put(predatorCoordinates, predator);
/*
  G T - -
  - R - -
  - - - -
  - - H P
  - - - G
   */
    makeMoveAction.act(world);

    Coordinates herbivoreNextCoordinates = new Coordinates(2, 3);
    assertTrue(world.entityExits(herbivoreNextCoordinates));
    assertEquals(7, ((Creature) world.getEntity(herbivoreNextCoordinates)).getHealth());

    assertEquals(9, ((Creature) world.getEntity(new Coordinates(3, 3))).getHealth());
  }
}