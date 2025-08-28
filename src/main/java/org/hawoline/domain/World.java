package org.hawoline.domain;

import org.hawoline.domain.entity.*;

import java.util.HashMap;
import java.util.Map;

public record World(Map<Coordinates, Entity> entities, int width, int height) {
  public World(Map<Coordinates, Entity> entities, int width, int height) {
    this.entities = new HashMap<>(entities);
    this.width = width;
    this.height = height;
  }

  @Override
  public Map<Coordinates, Entity> entities() {
    return new HashMap<>(entities);
  }

  public Entity getEntity(Coordinates coordinates) {
    return entities.get(coordinates);
  }

  public void removeAll() {
    entities.clear();
  }

  public void put(Coordinates coordinates, Entity entity) {
    if (!coordinatesBounds(coordinates)) {
      return;
    }
    entities.put(coordinates, entity);
  }

  public void putAll(Map<Coordinates, Entity> entities) {
    this.entities.putAll(entities);
  }

  public void remove(Coordinates coordinates) {
    entities.remove(coordinates);
  }

  public void move(Coordinates from, Coordinates to) {
    entities.put(to, entities.remove(from));
  }

  public boolean coordinatesBounds(Coordinates coordinates) {
    return coordinates.x() > -1
        && coordinates.y() > -1
        && coordinates.x() < width
        && coordinates.y() < height;
  }

  public boolean entityExits(Coordinates coordinates) {
    return entities.containsKey(coordinates);
  }
}