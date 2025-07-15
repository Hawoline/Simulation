package org.hawoline.domain;

import org.hawoline.domain.entity.EntityType;

import java.util.*;

public class BreadFirstEntitySearch implements EntitySearch {
    private final Field field;
    private final Set<Coordinates> visited = new HashSet<>();
    private final Map<Coordinates, Coordinates> paths = new HashMap<>();
    private final Queue<Coordinates> childs = new LinkedList<>();
    private Coordinates result;
    private final List<Coordinates> path = new ArrayList<>();

    public BreadFirstEntitySearch(Field field) {
        this.field = field;
    }

    @Override
    public boolean search(Coordinates startCoordinates, EntityType target) {
        childs.add(startCoordinates);
        while (!childs.isEmpty()) {
            Coordinates coordinates = childs.poll();
            if (field.entityExits(coordinates) && field.getEntityIn(coordinates).getType().equals(target)) {
                result = coordinates;
                fillPath(startCoordinates, result);
                return true;
            }
            visited.add(coordinates);
            for (Coordinates child: getNeighbourCoordinates(coordinates)) {
                if (field.entityExits(child) && !field.getEntityIn(child).getType().equals(target)) {
                    continue;
                }
                if (!visited.contains(child)) {
                    childs.add(child);
                    paths.put(child, coordinates);
                    visited.add(child);
                }
            }
        }
        return false;
    }

    @Override
    public Coordinates getResult() {
        return result;
    }

    @Override
    public List<Coordinates> getPath() {
        return path;
    }

    private void fillPath(Coordinates start, Coordinates end) {
        path.add(end);
        Coordinates current = end;
        do {
            current = paths.get(current);
            path.add(current);
        } while (!current.equals(start));
    }

    private List<Coordinates> getNeighbourCoordinates(Coordinates coordinates) {
        List<Coordinates> result = new ArrayList<>();
        Coordinates left = new Coordinates(coordinates.getX() - 1, coordinates.getY());
        Coordinates right = new Coordinates(coordinates.getX() + 1, coordinates.getY());
        Coordinates top = new Coordinates(coordinates.getX(), coordinates.getY() - 1);
        Coordinates bottom = new Coordinates(coordinates.getX(), coordinates.getY() + 1);

        if (isCoordinatesAndBoundsInField(left)) {
            result.add(left);
        }
        if (isCoordinatesAndBoundsInField(right)) {
            result.add(right);
        }
        if (isCoordinatesAndBoundsInField(top)) {
            result.add(top);
        }
        if (isCoordinatesAndBoundsInField(bottom)) {
            result.add(bottom);
        }

        return result;
    }

    private boolean isCoordinatesAndBoundsInField(Coordinates coordinates) {
        return coordinates.getX() >= 0 && coordinates.getY() >= 0 && coordinates.getX() < Field.WIDTH && coordinates.getY() < Field.HEIGHT;
    }
}
