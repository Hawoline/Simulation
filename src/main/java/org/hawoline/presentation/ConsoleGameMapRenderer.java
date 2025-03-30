package org.hawoline.presentation;

import org.hawoline.domain.Coordinates;
import org.hawoline.domain.GameMap;
import org.hawoline.domain.entity.Entity;

public class ConsoleGameMapRenderer {
    private static final char GRASS = 'G';
    private static final char ROCK = 'R';
    private static final char HERBIVORE = 'H';
    private static final char TREE = 'T';
    private static final char PREDATOR = 'P';
    private static final char EMPTY_SQUARE = '-';

    public void render(GameMap gameMap) {
        StringBuilder line = new StringBuilder();
        for (int x = 0; x < GameMap.WIDTH; x++) {
            for (int y = 0; y < GameMap.WIDTH; y++) {
                if (gameMap.entityExitsIn(new Coordinates(x, y))) {
                    line.append(getEntitySprite(gameMap.getEntityIn(new Coordinates(x, y))));
                } else {
                    line.append(getSpriteForEmptySquare());
                }
            }
            line.append("\n");
        }

        System.out.println(line);
    }

    private String getSpriteForEmptySquare() {
        return " "+ EMPTY_SQUARE + " ";
    }

    private String getEntitySprite(Entity entity) {
        return " " + selectSpriteFor(entity) + " ";
    }

    private char selectSpriteFor(Entity entity) {
        return switch (entity.getClass().getSimpleName()) {
            case "Grass" -> GRASS;
            case "Rock" -> ROCK;
            case "Herbivore" -> HERBIVORE;
            case "Tree" -> TREE;
            case "Predator" -> PREDATOR;
            default -> throw new IllegalArgumentException("Entity not found");
        };
    }
}
