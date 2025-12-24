package org.hawoline.domain;

import java.util.concurrent.ConcurrentMap;

import org.hawoline.domain.entity.Creature;
import org.hawoline.domain.entity.Entity;

public class MakeMoveAction implements WorldAction {

    @Override
    public void act(World world) {
        for (ConcurrentMap.Entry<Coordinates, Entity> entityEntry : world.entities().entrySet()) {
            Entity value = entityEntry.getValue();
            if (value instanceof Creature creature && !creature.isMoved) {
                creature.makeMove(world, entityEntry.getKey());
            }
        }
        for (ConcurrentMap.Entry<Coordinates, Entity> entityEntry : world.entities().entrySet()) {
            Entity value = entityEntry.getValue();
            if (value instanceof Creature creature) {
                creature.isMoved = false;
            }
        }

    }
}
