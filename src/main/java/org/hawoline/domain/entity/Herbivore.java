package org.hawoline.domain.entity;

import org.hawoline.domain.Coordinates;

import java.util.Map;

public class Herbivore extends Creature {
  public Herbivore(int speed, int health) {
    super(speed, health);
  }

  @Override public void makeMove() {

  }

  @Override
  protected void move(Map<Coordinates, Entity> map) {

  }
}
