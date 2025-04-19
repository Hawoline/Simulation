package org.hawoline.domain.entity;

import org.hawoline.domain.Coordinates;
import org.hawoline.domain.Field;

public abstract class Creature extends Entity {
  private final int speed;
  private final int health;

  public Creature(final int speed, final int health) {
    this.speed = speed;
    this.health = health;
  }

  public abstract void makeMove(final Field field);

  protected abstract Coordinates move(final Field field, final Coordinates startCoordinates);
}
