package org.hawoline.domain;

import org.hawoline.domain.entity.*;

import java.util.HashMap;
import java.util.Map;

public record Field(Map<Coordinates, Entity> entities, int width, int height) {
  public Field(final Map<Coordinates, Entity> entities, final int width, final int height) {
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

  public Field removeAll() {
    return new Field(new HashMap<>(), width, height);
  }

  public Field put(Coordinates coordinates, Entity entity) {
    if (!isCoordinatesBounds(coordinates)) {
      return this;
    }
    Map<Coordinates, Entity> result = new HashMap<>(entities);
    result.put(coordinates, entity);
    return new Field(result, width, height);
  }

  public Field remove(Coordinates coordinates) {
    Map<Coordinates, Entity> result = new HashMap<>(entities);
    result.remove(coordinates);
    return new Field(result, width, height);
  }

  public Field move(Coordinates from, Coordinates to) {
    Entity entity = entities.get(from);
    return remove(from).put(to, entity);
  }

  public boolean isCoordinatesBounds(Coordinates coordinates) {
    return coordinates.getX() > -1
        && coordinates.getY() > -1
        && coordinates.getX() < width
        && coordinates.getY() < height;
  }

  public boolean entityExits(Coordinates coordinates) {
    return entities.containsKey(coordinates);
  }
}