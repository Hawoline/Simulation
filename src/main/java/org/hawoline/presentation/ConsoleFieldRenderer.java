package org.hawoline.presentation;

import org.hawoline.domain.Coordinates;
import org.hawoline.domain.World;
import org.hawoline.domain.entity.Entity;

public class ConsoleFieldRenderer {
    private static final char GRASS = 'G';
    private static final char ROCK = 'R';
    private static final char HERBIVORE = 'H';
    private static final char TREE = 'T';
    private static final char PREDATOR = 'P';
    private static final char EMPTY_SQUARE = '-';

    public void render(World world) {
        StringBuilder lines = new StringBuilder();
        for (int x = 0; x < world.width(); x++) {
            for (int y = 0; y < world.height(); y++) {
                if (world.entityExits(new Coordinates(x, y))) {
                    lines.append(getEntitySprite(world.getEntity(new Coordinates(x, y))));
                } else {
                    lines.append(getSpriteForEmptySquare());
                }
            }
            lines.append("\n");
        }

        System.out.println(lines);
    }

    private String getSpriteForEmptySquare() {
        return " "+ EMPTY_SQUARE + " ";
    }

    private String getEntitySprite(Entity entity) {
        return " " + selectSpriteFor(entity) + " ";
    }

    private char selectSpriteFor(Entity entity) {
        return switch (entity.getType()) {
            case GRASS -> GRASS;
            case ROCK -> ROCK;
            case HERBIVORE -> HERBIVORE;
            case TREE -> TREE;
            case PREDATOR -> PREDATOR;
        };
    }
}
