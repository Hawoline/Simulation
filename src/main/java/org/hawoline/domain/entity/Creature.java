package org.hawoline.domain.entity;

import java.util.List;
import org.hawoline.domain.BreadFirstEntitySearch;
import org.hawoline.domain.Coordinates;
import org.hawoline.domain.EntitySearch;
import org.hawoline.domain.World;

public abstract class Creature extends Entity {
  private int speed;
  private int health;

  public Creature(int speed, int health) {
    this.speed = speed;
    this.health = health;
  }

  public void makeMove(World world, Coordinates coordinates) {
    if (!world.entityExits(coordinates)) {
      world.remove(coordinates);
      return;
    }
    EntitySearch search = new BreadFirstEntitySearch(world);
    boolean foodFound = search.search(coordinates, getTarget());
    if (foodFound) {
      List<Coordinates> path = search.getPath();
      if (path.size() == 2) {
        tryEat(world, path.get(0));
        return;
      }
      Coordinates nextPosition = getNextPosition(path, world);
      world.move(coordinates, nextPosition);
    } else {
      changeHealth(-1);
      if (health < 1) {
        world.remove(coordinates);
        return;
      }
    }
  }

  protected abstract EntityType getTarget();

  protected abstract void tryEat(World world, Coordinates foodCoordinates);

  protected Coordinates getNextPosition(List<Coordinates> path, World world) {
    int nextPosition = path.size() - getSpeed() - 1;
    if (nextPosition < 1) {
      nextPosition = 1;
    }
    return path.get(nextPosition);
  }
  protected void changeHealth(int value) {
    health += value;
  }

  protected int getSpeed() {
    return speed;
  }

  public int getHealth() {
    return health;
  }
}
