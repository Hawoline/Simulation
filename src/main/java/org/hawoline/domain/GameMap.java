package org.hawoline.domain;

import org.hawoline.domain.entity.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GameMap {
  private final Map<Coordinates, Entity> entities;
  public static final int WIDTH = 20;
  public static final int HEIGHT = 20;
  
  public GameMap(final Map<Coordinates, Entity> entities) {
    this.entities = new HashMap<>(entities);
  }

  public Map<Coordinates, Entity> getEntities() {
    return new HashMap<>(entities);
  }

  public Entity getEntityIn(Coordinates coordinates) {
    return entities.get(coordinates);
  }

    public GameMap removeAll() {
      return new GameMap(new HashMap<>());
    }

  public GameMap add(Coordinates coordinates, Entity entity) {
    Map<Coordinates, Entity> result = new HashMap<>(entities);
    result.put(coordinates, entity);
    return new GameMap(result);
  }

  public GameMap remove(Coordinates coordinates) {
    Map<Coordinates, Entity> result = new HashMap<>(entities);
    result.remove(coordinates);
    return new GameMap(result);
  }

  public GameMap addRandomEntity() {
    Map<Coordinates, Entity> result = new HashMap<>(entities);
    Random random = new Random();
    EntityType randomEntityType = EntityType.values()[random.nextInt(EntityType.values().length)];
    Coordinates randomPosition;
    do {
      randomPosition = new Coordinates(random.nextInt(WIDTH), random.nextInt(HEIGHT));
    } while (result.containsKey(randomPosition));
    int randomHealth = random.nextInt(20) + 1;
    switch (randomEntityType) {
      case EntityType.GRASS:
        result.put(randomPosition, new Grass());
        break;
      case EntityType.HERBIVORE:
        result.put(randomPosition, new Herbivore(1, randomHealth));
        break;
      case EntityType.PREDATOR:
        result.put(randomPosition, new Predator(1, randomHealth, random.nextInt(5) + 1));
        break;
      case EntityType.TREE:
        result.put(randomPosition, new Tree());
        break;
      case EntityType.ROCK:
        result.put(randomPosition, new Rock());
        break;

    }
    return new GameMap(result);
  }

  public boolean entityExitsIn(Coordinates coordinates) {
    return entities.containsKey(coordinates);
  }
}
