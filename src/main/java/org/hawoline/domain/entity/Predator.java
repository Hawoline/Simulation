package org.hawoline.domain.entity;

import java.util.List;
import org.hawoline.domain.BreadFirstEntitySearch;
import org.hawoline.domain.Coordinates;
import org.hawoline.domain.EntitySearch;
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
    int herbivoreHealth = creature.getHealth() - attackPower;
    if (herbivoreHealth < 1) {
      changeHealth(10);
      world.remove(foodCoordinates);
      return;
    }
    changeHealth(-1);
    world.put(foodCoordinates, new Herbivore(creature.getSpeed(), herbivoreHealth));
  }

  @Override
  public EntityType getType() {
    return EntityType.PREDATOR;
  }
}
