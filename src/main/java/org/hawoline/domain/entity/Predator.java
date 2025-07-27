package org.hawoline.domain.entity;

import java.util.List;
import org.hawoline.domain.BreadFirstEntitySearch;
import org.hawoline.domain.Coordinates;
import org.hawoline.domain.EntitySearch;
import org.hawoline.domain.Field;

public class Predator extends Creature {
  private final int attackPower;
  public Predator(int speed, int health, int attackPower) {
    super(speed, health);
    this.attackPower = attackPower;
  }

  @Override public Field makeMove(final Field field, Coordinates coordinates) {
    EntitySearch grassSearch = new BreadFirstEntitySearch(field);
    boolean grassFound = grassSearch.search(coordinates, EntityType.HERBIVORE);
    if (grassFound) {
      List<Coordinates> path = grassSearch.getPath();
      Coordinates nextCoordinates = getNextPosition(path);
      if (path.size() == 2) {
        Creature creature = (Creature) field.getEntityIn(nextCoordinates);
        int herbivoreHealth = creature.getHealth() - getAttackPower();
        if (herbivoreHealth < 1) {
          return field.remove(path.get(0));
        }
        return field.add(path.get(0), new Herbivore(creature.getSpeed(), herbivoreHealth));
      }
      return field.move(coordinates, nextCoordinates);
    }
    return field;
  }

  public void attack() {

  }

  public int getAttackPower() {
    return attackPower;
  }

  @Override
  public EntityType getType() {
    return EntityType.PREDATOR;
  }
}
