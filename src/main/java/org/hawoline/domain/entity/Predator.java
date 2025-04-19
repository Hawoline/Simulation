package org.hawoline.domain.entity;

import org.hawoline.domain.Coordinates;
import org.hawoline.domain.Field;

public class Predator extends Creature {
  private final int attackPower;
  public Predator(int speed, int health, int attackPower) {
    super(speed, health);
    this.attackPower = attackPower;
  }

  @Override public void makeMove(final Field field) {
    // Потратить ход на атаку травоядного или передвижение
  }

  @Override
  protected Coordinates move(Field field, Coordinates startCoordinates) {
    return null;
  }

  public void attack() {

  }

  @Override
  public EntityType getType() {
    return EntityType.PREDATOR;
  }
}
