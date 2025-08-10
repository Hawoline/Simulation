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
            if (field.entityExits(coordinates) && field.getEntity(coordinates).getType().equals(target)) {
                result = coordinates;
                fillPath(startCoordinates, result);
                return true;
            }
            visited.add(coordinates);
            for (Coordinates child: getNeighbourCoordinates(coordinates)) {
                if (field.entityExits(child) && !field.getEntity(child).getType().equals(target)) {
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

        if (field.isCoordinatesBounds(left)) {
            result.add(left);
        }
        if (field.isCoordinatesBounds(right)) {
            result.add(right);
        }
        if (field.isCoordinatesBounds(top)) {
            result.add(top);
        }
        if (field.isCoordinatesBounds(bottom)) {
            result.add(bottom);
        }

        return result;
    }


}
