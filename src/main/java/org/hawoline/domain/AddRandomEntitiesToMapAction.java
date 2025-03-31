package org.hawoline.domain;

import org.hawoline.domain.entity.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static org.hawoline.domain.GameMap.HEIGHT;
import static org.hawoline.domain.GameMap.WIDTH;

public class AddRandomEntitiesToMapAction {

    public GameMap addEachTypeOfEntityWithRandomPosition(GameMap gameMap, int maxRandomEntityCount) {
        Random random = new Random();
        Map<Coordinates, Entity> result = new HashMap<>(gameMap.getEntities());
        // [1, maxRandomEntityCount]
        for (int entity = 0; entity < random.nextInt(maxRandomEntityCount) + 1; entity++) {
            result.put(new Coordinates(random.nextInt(WIDTH), random.nextInt(HEIGHT)),
                    new Herbivore(1, random.nextInt(20) + 1));
        }
        // [1, maxRandomEntityCount]
        for (int entity = 0; entity < random.nextInt(maxRandomEntityCount) + 1; entity++) {
            result.put(new Coordinates(random.nextInt(WIDTH), random.nextInt(HEIGHT)),
                    new Predator(1, random.nextInt(20) + 1, random.nextInt(5) + 1));
        }
        // [1, maxRandomEntityCount]
        for (int entity = 0; entity < random.nextInt(maxRandomEntityCount) + 1; entity++) {
            result.put(new Coordinates(random.nextInt(WIDTH), random.nextInt(HEIGHT)),
                    new Grass());
        }
        // [1, maxRandomEntityCount]
        for (int entity = 0; entity < random.nextInt(maxRandomEntityCount) + 1; entity++) {
            result.put(new Coordinates(random.nextInt(WIDTH), random.nextInt(HEIGHT)),
                    new Rock());
        }
        // [1, maxRandomEntityCount]
        for (int entity = 0; entity < random.nextInt(maxRandomEntityCount) + 1; entity++) {
            result.put(new Coordinates(random.nextInt(WIDTH), random.nextInt(HEIGHT)),
                    new Tree());
        }
        return new GameMap(result);
    }

    public GameMap addRandomEntity(GameMap gameMap) {
        Map<Coordinates, Entity> result = new HashMap<>(gameMap.getEntities());
        Random random = new Random();
        EntityType randomEntityType = EntityType.values()[random.nextInt(EntityType.values().length)];
        Coordinates randomPosition;
        do {
            randomPosition = new Coordinates(random.nextInt(WIDTH), random.nextInt(HEIGHT));
        } while (result.containsKey(randomPosition));
        int randomHealth = random.nextInt(20) + 1;
        switch (randomEntityType) {
            case GRASS:
                result.put(randomPosition, new Grass());
                break;
            case HERBIVORE:
                result.put(randomPosition, new Herbivore(1, randomHealth));
                break;
            case PREDATOR:
                result.put(randomPosition, new Predator(1, randomHealth, random.nextInt(5) + 1));
                break;
            case TREE:
                result.put(randomPosition, new Tree());
                break;
            case ROCK:
                result.put(randomPosition, new Rock());
                break;
        }
        return new GameMap(result);
    }
}
