package org.hawoline.domain.entity;

import java.util.List;
import org.hawoline.domain.Coordinates;
import org.hawoline.domain.Field;

public abstract class Creature extends Entity {
  private final int speed;
  private final int health;

  public Creature(final int speed, final int health) {
    this.speed = speed;
    this.health = health;
  }

  public abstract Field makeMove(final Field field, Coordinates coordinates);

  protected Coordinates getNextPosition(List<Coordinates> path) {
    int nextPosition = path.size() - getSpeed() - 1;
    if (nextPosition < 1) {
      nextPosition = 1;
    }
    return path.get(nextPosition);
  }

  public int getSpeed() {
    return speed;
  }

  public int getHealth() {
    return health;
  }
}
