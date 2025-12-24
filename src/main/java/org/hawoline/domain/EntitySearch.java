package org.hawoline.domain;

import org.hawoline.domain.entity.EntityType;

import java.util.List;

public interface EntitySearch {
    boolean search(Coordinates startCoordinates, EntityType target);

    Coordinates getResult();

    List<Coordinates> getPath();
}
