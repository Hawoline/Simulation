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
        for (int entity = 0; entity < random.nextInt(maxRandomEntityCount) + 1; entity++) {
            result.put(new Coordinates(random.nextInt(WIDTH), random.nextInt(HEIGHT)),
                    new Herbivore(1, random.nextInt(20) + 1));
        }
        for (int entity = 0; entity < random.nextInt(maxRandomEntityCount) + 1; entity++) {
            result.put(new Coordinates(random.nextInt(WIDTH), random.nextInt(HEIGHT)),
                    new Predator(1, random.nextInt(20) + 1, random.nextInt(5) + 1));
        }
        for (int entity = 0; entity < random.nextInt(maxRandomEntityCount) + 1; entity++) {
            result.put(new Coordinates(random.nextInt(WIDTH), random.nextInt(HEIGHT)),
                    new Grass());
        }
        for (int entity = 0; entity < random.nextInt(maxRandomEntityCount) + 1; entity++) {
            result.put(new Coordinates(random.nextInt(WIDTH), random.nextInt(HEIGHT)),
                    new Rock());
        }
        for (int entity = 0; entity < random.nextInt(maxRandomEntityCount) + 1; entity++) {
            result.put(new Coordinates(random.nextInt(WIDTH), random.nextInt(HEIGHT)),
                    new Tree());
        }
        return new GameMap(result);
    }
}
