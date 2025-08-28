package org.hawoline.domain;

import org.hawoline.domain.entity.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class AddRandomEntitiesAction {
    private static final int WIDTH = 20;
    private static final int HEIGHT = 20;
    public World addEachTypeOfEntityWithRandomPosition(World world) {
        Random random = new Random();
        Map<Coordinates, Entity> result = new HashMap<>(world.entities());
        result.put(new Coordinates(random.nextInt(WIDTH), random.nextInt(HEIGHT)),
                new Herbivore(1, random.nextInt(20) + 1));
        result.put(new Coordinates(random.nextInt(WIDTH), random.nextInt(HEIGHT)),
                new Predator(1, random.nextInt(20) + 1, random.nextInt(5) + 1));
        result.put(new Coordinates(random.nextInt(WIDTH), random.nextInt(HEIGHT)),
                new Grass());
        result.put(new Coordinates(random.nextInt(WIDTH), random.nextInt(HEIGHT)),
                new Rock());
        result.put(new Coordinates(random.nextInt(WIDTH), random.nextInt(HEIGHT)),
                new Tree());
        return new World(result, WIDTH, HEIGHT);
    }
    public void addEachTypeOfEntitiesWithRandomPosition(World world, int maxRandomConcreteEntityTypeCount) {
        Map<Coordinates, Entity> result = new HashMap<>(world.entities());
        for (int entity = 0; entity < maxRandomConcreteEntityTypeCount; entity++) {
            result.putAll(addEachTypeOfEntityWithRandomPosition(world).entities());
        }
        world.putAll(result);
    }

    public void addRandomEntity(World world) {
        Random random = new Random();
        final EntityType randomEntityType = EntityType.values()[random.nextInt(EntityType.values().length)];
        Coordinates randomPosition;
        do {
            randomPosition = new Coordinates(random.nextInt(world.width()), random.nextInt(world.height()));
        } while (world.entityExits(randomPosition));
        int randomHealth = random.nextInt(20) + 1;
        switch (randomEntityType) {
            case GRASS:
                world.put(randomPosition, new Grass());
                break;
            case HERBIVORE:
                world.put(randomPosition, new Herbivore(1, randomHealth));
                break;
            case PREDATOR:
                world.put(randomPosition, new Predator(1, randomHealth, random.nextInt(5) + 1));
                break;
            case TREE:
                world.put(randomPosition, new Tree());
                break;
            case ROCK:
                world.put(randomPosition, new Rock());
                break;
        }
    }
}
