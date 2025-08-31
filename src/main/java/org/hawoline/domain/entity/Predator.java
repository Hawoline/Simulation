package org.hawoline.domain.entity;

import org.hawoline.domain.Coordinates;
import org.hawoline.domain.World;

public class Predator extends Creature {
  private int attackPower;
  public Predator(int speed, int health, int attackPower) {
    super(speed, health);
    this.attackPower = attackPower;
  }

  @Override
  protected EntityType getTarget() {
    return EntityType.HERBIVORE;
  }

  @Override protected void tryEat(World world, Coordinates foodCoordinates) {
    Creature creature = (Creature) world.getEntity(foodCoordinates);
    creature.changeHealth(-attackPower);
    System.out.println("Attack: " + attackPower);
    if (creature.getHealth() < 1) {
      changeHealth(10);
      world.remove(foodCoordinates);
    }
  }

  @Override
  public EntityType getType() {
    return EntityType.PREDATOR;
  }
}
