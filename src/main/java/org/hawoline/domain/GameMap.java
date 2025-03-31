package org.hawoline.domain;

import org.hawoline.domain.entity.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Сделал этот класс иммутабельным, остальные мутабельные
 */
public class GameMap{
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

  public GameMap move(Coordinates from, Coordinates to) {
    Entity entity = entities.get(from);
    return remove(from).add(to, entity);
  }

  public boolean entityExitsIn(Coordinates coordinates) {
    return entities.containsKey(coordinates);
  }
}