package org.hawoline.domain.entity;

import org.hawoline.domain.Coordinates;
import org.hawoline.domain.Field;

public class Predator extends Creature {
  private final int attackPower;
  public Predator(int speed, int health, int attackPower) {
    super(speed, health);
    this.attackPower = attackPower;
  }

  @Override public Field makeMove(final Field field, Coordinates coordinates) {
    // Потратить ход на атаку травоядного или передвижение
    return field;
  }

  public void attack() {

  }

  @Override
  public EntityType getType() {
    return EntityType.PREDATOR;
  }
}
