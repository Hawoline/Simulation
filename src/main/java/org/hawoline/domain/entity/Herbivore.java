package org.hawoline.domain.entity;

import org.hawoline.domain.Coordinates;
import org.hawoline.domain.World;

public class Herbivore extends Creature {
    public Herbivore(int speed, int health) {
        super(speed, health);
    }

    @Override
    protected EntityType getTarget() {
        return EntityType.GRASS;
    }

    @Override
    protected void tryEat(World world, Coordinates foodCoordinates) {
        world.remove(foodCoordinates);
        changeHealth(5);
    }

    @Override
    public EntityType getType() {
        return EntityType.HERBIVORE;
    }
}
