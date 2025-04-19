package org.hawoline.domain;

import org.hawoline.domain.entity.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static org.hawoline.domain.Field.HEIGHT;
import static org.hawoline.domain.Field.WIDTH;

public class AddRandomEntitiesToFieldAction extends InitAction<Field> {
    private final Field field;
    private final int maxRandomConcreteEntityTypeCount;

    public AddRandomEntitiesToFieldAction(Field field) {
        this(field, 1);
    }

    public AddRandomEntitiesToFieldAction(Field field, int maxRandomConcreteEntityTypeCount) {
        this.field = field;
        this.maxRandomConcreteEntityTypeCount = maxRandomConcreteEntityTypeCount;
    }

    public Field addEachTypeOfEntityWithRandomPosition() {
        Random random = new Random();
        Map<Coordinates, Entity> result = new HashMap<>(field.entities());
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
        return new Field(result);
    }

    public Field addEachTypeOfEntitiesWithRandomPosition(int maxRandomConcreteEntityTypeCount) {
        return addEachTypeOfEntitiesWithRandomPosition(field, maxRandomConcreteEntityTypeCount);
    }
    public Field addEachTypeOfEntitiesWithRandomPosition(Field field, int maxRandomConcreteEntityTypeCount) {
        Random random = new Random();
        Map<Coordinates, Entity> result = new HashMap<>(field.entities());
        // [1, maxRandomEntityCount] - interval
        for (int entity = 0; entity < random.nextInt(maxRandomConcreteEntityTypeCount) + 1; entity++) {
            result.putAll(addEachTypeOfEntityWithRandomPosition().entities());
        }
        return new Field(result);
    }

    public Field addRandomEntity() {
        return addRandomEntity(field);
    }

    public Field addRandomEntity(Field field) {
        Map<Coordinates, Entity> result = new HashMap<>(field.entities());
        Random random = new Random();
        final EntityType randomEntityType = EntityType.values()[random.nextInt(EntityType.values().length)];
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
        return new Field(result);
    }

    @Override
    protected Field init() {
        return addEachTypeOfEntitiesWithRandomPosition(field, maxRandomConcreteEntityTypeCount);
    }
}
