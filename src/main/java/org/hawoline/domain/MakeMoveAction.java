package org.hawoline.domain;

import java.util.Map;
import org.hawoline.domain.entity.Creature;
import org.hawoline.domain.entity.Entity;

public class MakeMoveAction implements WorldAction {
  @Override
  public void act(World world) {
    for (Map.Entry<Coordinates, Entity> entityEntry : world.entities().entrySet()) {
      Entity value = entityEntry.getValue();
      if (value instanceof Creature creature) {
        creature.makeMove(world, entityEntry.getKey());
      }
    }
  }
}
