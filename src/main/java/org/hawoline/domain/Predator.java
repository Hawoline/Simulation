package org.hawoline.domain;

public class Predator extends Creature {
  private final int attackPower;
  public Predator(int speed, int health, int attackPower) {
    super(speed, health);
    this.attackPower = attackPower;
  }

  @Override public void makeMove() {
    // Потратить ход на атаку травоядного или передвижение
  }

  public void attack() {

  }
}
