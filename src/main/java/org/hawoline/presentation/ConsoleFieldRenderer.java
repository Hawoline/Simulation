package org.hawoline.presentation;

import org.hawoline.domain.Coordinates;
import org.hawoline.domain.Field;
import org.hawoline.domain.entity.Entity;
import org.hawoline.domain.entity.EntityType;

public class ConsoleFieldRenderer {
    private static final char GRASS = 'G';
    private static final char ROCK = 'R';
    private static final char HERBIVORE = 'H';
    private static final char TREE = 'T';
    private static final char PREDATOR = 'P';
    private static final char EMPTY_SQUARE = '-';

    public void render(Field field) {
        StringBuilder lines = new StringBuilder();
        for (int x = 0; x < Field.WIDTH; x++) {
            for (int y = 0; y < Field.HEIGHT; y++) {
                if (field.entityExits(new Coordinates(x, y))) {
                    lines.append(getEntitySprite(field.getEntityIn(new Coordinates(x, y))));
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
