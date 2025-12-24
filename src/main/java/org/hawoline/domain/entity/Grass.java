package org.hawoline.domain.entity;

public class Grass extends Entity {
    public Grass() {
    }

    @Override
    public EntityType getType() {
        return EntityType.GRASS;
    }
}
