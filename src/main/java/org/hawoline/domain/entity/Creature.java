package org.hawoline.domain.entity;

import org.hawoline.domain.Coordinates;

import java.util.Map;

public abstract class Creature extends Entity {
  private final int speed;
  private final int health;

  public Creature(final int speed, final int health) {
    this.speed = speed;
    this.health = health;
  }

  public abstract void makeMove();

  protected abstract void move(final Map<Coordinates, Entity> map); //TODO what return?
}
