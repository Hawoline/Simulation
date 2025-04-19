package org.hawoline.domain;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class InitActionTest {

    @Test
    protected void testActAction() {
        Field emptyField = new Field(new HashMap<>());
        InitAction<Field> addEntitiesWithRandomCoordinatesToMapAction = new AddRandomEntitiesToFieldAction(emptyField);
        assertEquals(addEntitiesWithRandomCoordinatesToMapAction.act(), addEntitiesWithRandomCoordinatesToMapAction.act());
    }
}