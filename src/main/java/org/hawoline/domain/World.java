package org.hawoline.domain;

import java.util.concurrent.ConcurrentMap;
import org.hawoline.domain.entity.*;

public record World(ConcurrentMap<Coordinates, Entity> entities, int width, int height) {

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

  public void putAll(ConcurrentMap<Coordinates, Entity> entities) {
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