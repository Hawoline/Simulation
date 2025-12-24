package org.hawoline.domain.entity;

public class Rock extends Entity {
    public Rock() {
    }

    @Override
    public EntityType getType() {
        return EntityType.ROCK;
    }
}
