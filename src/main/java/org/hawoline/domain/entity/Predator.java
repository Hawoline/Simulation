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
    if (!grassFound) {
      return field;
    }
    List<Coordinates> path = grassSearch.getPath();
    Coordinates nextCoordinates = getNextPosition(path);
    if (path.size() == 2) {
      return attack(field, nextCoordinates, path.get(0));
    }
    return field.remove(coordinates).put(nextCoordinates, new Predator(getSpeed(), getHealth() - 1, attackPower));
  }

  private Field attack(Field field, Coordinates coordinates,
      Coordinates herbivoreCoordinates) {
    Creature creature = (Creature) field.getEntity(herbivoreCoordinates);
    int herbivoreHealth = creature.getHealth() - attackPower;
    if (herbivoreHealth < 1) {
      return field.remove(herbivoreCoordinates)
          .put(coordinates, new Predator(getSpeed(), getHealth() + 10, attackPower));
    }
    return field.put(herbivoreCoordinates, new Herbivore(creature.getSpeed(), herbivoreHealth)).put(coordinates, new Predator(getSpeed(), getHealth() - 1, attackPower));
  }

  @Override
  public EntityType getType() {
    return EntityType.PREDATOR;
  }
}
