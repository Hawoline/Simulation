package org.hawoline.domain.entity;

import java.util.List;
import org.hawoline.domain.BreadFirstEntitySearch;
import org.hawoline.domain.Coordinates;
import org.hawoline.domain.EntitySearch;
import org.hawoline.domain.Field;

public class Herbivore extends Creature {
  public Herbivore(int speed, int health) {
    super(speed, health);
  }

  @Override public Field makeMove(final Field field, Coordinates coordinates) {
    EntitySearch grassSearch = new BreadFirstEntitySearch(field);
    boolean grassFound = grassSearch.search(coordinates, EntityType.GRASS);
    if (!grassFound) {
      return field;
    }
    List<Coordinates> path = grassSearch.getPath();
    if (path.size() == 2) {
      return field.remove(path.get(0)).put(coordinates, new Herbivore(getSpeed(), getHealth() + 5));
    }
    Coordinates nextCoordinates = getNextPosition(path);
    return field.remove(coordinates).put(nextCoordinates, new Herbivore(getSpeed(), getHealth() - 1));
  }

  @Override
  public EntityType getType() {
    return EntityType.HERBIVORE;
  }
}
