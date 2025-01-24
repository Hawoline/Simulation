package org.hawoline.domain;

public abstract class Creature extends Entity {
  private final int speed;
  private final int health;

  public Creature(final int speed, int health) {
    this.speed = speed;
    this.health = health;
  }

  public abstract void move();
}
