package org.hawoline.domain.entity;

import org.hawoline.domain.Coordinates;
import org.hawoline.domain.Field;

public class Herbivore extends Creature {
  public Herbivore(int speed, int health) {
    super(speed, health);
  }

  @Override public void makeMove(final Field field) {

  }

  @Override
  protected Coordinates move(Field field, Coordinates startCoordinates) {
    return null;
  }

  @Override
  public EntityType getType() {
    return EntityType.HERBIVORE;
  }
}
