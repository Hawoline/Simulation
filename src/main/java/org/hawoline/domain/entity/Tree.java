package org.hawoline.domain.entity;

public class Tree extends Entity {
    public Tree() {
    }

    @Override
    public EntityType getType() {
        return EntityType.TREE;
    }
}
