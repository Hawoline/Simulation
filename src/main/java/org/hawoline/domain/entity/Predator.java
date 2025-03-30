package org.hawoline.domain.entity;

import org.hawoline.domain.Coordinates;

import java.util.Map;

public class Predator extends Creature {
  private final int attackPower;
  public Predator(int speed, int health, int attackPower) {
    super(speed, health);
    this.attackPower = attackPower;
  }

  @Override public void makeMove() {
    // Потратить ход на атаку травоядного или передвижение
  }

  @Override
  protected void move(Map<Coordinates, Entity> map) {

  }

  public void attack() {

  }
}
