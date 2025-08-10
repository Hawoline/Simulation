package org.hawoline.domain;

import java.util.Map;
import org.hawoline.domain.entity.Creature;
import org.hawoline.domain.entity.Entity;
import org.hawoline.domain.entity.EntityType;

public class MakeMoveAction implements Action<Field, Field> {
  @Override
  public Field act(final Field field) {
    Field result = field;
    for (Map.Entry<Coordinates, Entity> entityEntry : result.entities().entrySet()) {
      Entity value = entityEntry.getValue();
      if (value instanceof Creature creature) {
        result = creature.makeMove(result, entityEntry.getKey());
      }
    }

    return result;
  }
}
