package org.hawoline.domain;

import org.hawoline.domain.entity.EntityType;

import java.util.*;

public class BreadFirstEntitySearch implements EntitySearch {
    private World world;
    private Set<Coordinates> visited = new HashSet<>();
    private Map<Coordinates, Coordinates> paths = new HashMap<>();
    private Queue<Coordinates> childs = new LinkedList<>();
    private Coordinates result;
    private List<Coordinates> path = new ArrayList<>();

    public BreadFirstEntitySearch(World world) {
        this.world = world;
    }

    @Override
    public boolean search(Coordinates startCoordinates, EntityType target) {
        childs.add(startCoordinates);
        while (!childs.isEmpty()) {
            Coordinates coordinates = childs.poll();
            if (world.entityExits(coordinates) && world.getEntity(coordinates).getType().equals(target)) {
                result = coordinates;
                fillPath(startCoordinates, result);
                return true;
            }
            visited.add(coordinates);
            for (Coordinates child : getNeighbourCoordinates(coordinates)) {
                if (world.entityExits(child) && !world.getEntity(child).getType().equals(target)) {
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
        Coordinates left = new Coordinates(coordinates.x() - 1, coordinates.y());
        Coordinates right = new Coordinates(coordinates.x() + 1, coordinates.y());
        Coordinates top = new Coordinates(coordinates.x(), coordinates.y() - 1);
        Coordinates bottom = new Coordinates(coordinates.x(), coordinates.y() + 1);

        if (world.coordinatesBounds(left)) {
            result.add(left);
        }
        if (world.coordinatesBounds(right)) {
            result.add(right);
        }
        if (world.coordinatesBounds(top)) {
            result.add(top);
        }
        if (world.coordinatesBounds(bottom)) {
            result.add(bottom);
        }

        return result;
    }


}
