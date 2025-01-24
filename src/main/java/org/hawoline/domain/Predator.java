package org.hawoline.domain;

public class Predator extends Creature {
  private final int attackPower;
  public Predator(int speed, int health, int attackPower) {
    super(speed, health);
    this.attackPower = attackPower;
  }

  @Override public void move() {

  }

  public int attack(int herbivoreHealth) {
    return herbivoreHealth - attackPower;
  }
}
