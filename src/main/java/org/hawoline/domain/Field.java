package org.hawoline.domain;

import org.hawoline.domain.entity.*;

import java.util.HashMap;
import java.util.Map;

public record Field(Map<Coordinates, Entity> entities) {
  // Почему не в конструкторе? Потому что у нас нет цели создавать несколько симуляций одновременно с разными размерами карты.
  // Когда будет такая потребность - сделаю.
  public static final int WIDTH = 20;
  public static final int HEIGHT = 20;

  public Field(final Map<Coordinates, Entity> entities) {
    this.entities = new HashMap<>(entities);
  }

  @Override
  public Map<Coordinates, Entity> entities() {
    return new HashMap<>(entities);
  }

  public Entity getEntityIn(Coordinates coordinates) {
    return entities.get(coordinates);
  }

  public Field removeAll() {
    return new Field(new HashMap<>());
  }

  public Field put(Coordinates coordinates, Entity entity) {
    Map<Coordinates, Entity> result = new HashMap<>(entities);
    result.put(coordinates, entity);
    return new Field(result);
  }

  public Field remove(Coordinates coordinates) {
    Map<Coordinates, Entity> result = new HashMap<>(entities);
    result.remove(coordinates);
    return new Field(result);
  }

  public Field move(Coordinates from, Coordinates to) {
    Entity entity = entities.get(from);
    return remove(from).put(to, entity);
  }

  public boolean entityExits(Coordinates coordinates) {
    return entities.containsKey(coordinates);
  }
}