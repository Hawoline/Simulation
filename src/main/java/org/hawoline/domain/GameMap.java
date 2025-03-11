package org.hawoline.domain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class GameMap {
  private final Map<Coordinates, Entity> entityCoordinates;
  private static final int MAX_ENTITY_COUNT = 5;
  private static final int WIDTH = 100;
  private static final int HEIGHT = 100;
  
  public GameMap(final Map<Coordinates, Entity> entityCoordinates) {
    this.entityCoordinates = new HashMap<>(entityCoordinates);
  }

  public GameMap setUpEntities() {
    Random random = new Random();
    Map<Coordinates, Entity> result = new HashMap<>(entityCoordinates);
    for (int entity = 0; entity < random.nextInt(MAX_ENTITY_COUNT) + 1; entity++) {
      result.put(new Coordinates(random.nextInt(WIDTH), random.nextInt(HEIGHT)),
          new Herbivore(1, random.nextInt(20) + 1));
    }
    for (int entity = 0; entity < random.nextInt(MAX_ENTITY_COUNT) + 1; entity++) {
      result.put(new Coordinates(random.nextInt(WIDTH), random.nextInt(HEIGHT)),
          new Predator(1, random.nextInt(20) + 1, random.nextInt(5) + 1));
    }
    for (int entity = 0; entity < random.nextInt(MAX_ENTITY_COUNT) + 1; entity++) {
      result.put(new Coordinates(random.nextInt(WIDTH), random.nextInt(HEIGHT)),
          new Grass());
    }
    for (int entity = 0; entity < random.nextInt(MAX_ENTITY_COUNT) + 1; entity++) {
      result.put(new Coordinates(random.nextInt(WIDTH), random.nextInt(HEIGHT)),
          new Rock());
    }
    for (int entity = 0; entity < random.nextInt(MAX_ENTITY_COUNT) + 1; entity++) {
      result.put(new Coordinates(random.nextInt(WIDTH), random.nextInt(HEIGHT)),
          new Tree());
    }
    return new GameMap(result);
  }

  public Map<Coordinates, Entity> getEntities() {
    return new HashMap<>(entityCoordinates);
  }
  // TODO посмотреть какие коллекции подойдут лучше, чем список списков или двумерного массива
}
