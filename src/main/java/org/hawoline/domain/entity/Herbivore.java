package org.hawoline.domain.entity;

import java.util.List;

import org.hawoline.domain.BreadFirstEntitySearch;
import org.hawoline.domain.Coordinates;
import org.hawoline.domain.EntitySearch;
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
