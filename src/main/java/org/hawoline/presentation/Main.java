package org.hawoline.presentation;

import org.hawoline.domain.GameMap;
import org.hawoline.domain.AddRandomEntitiesToMapAction;

import java.util.HashMap;

public class Main {
  public static void main(String[] args) {
    ConsoleGameMapRenderer consoleGameMapRenderer = new ConsoleGameMapRenderer();
    GameMap emptyMap = new GameMap(new HashMap<>());
    AddRandomEntitiesToMapAction addRandomEntitiesToMapAction = new AddRandomEntitiesToMapAction();
    GameMap gameMapWithEntities = addRandomEntitiesToMapAction.addEachTypeOfEntityWithRandomPosition(emptyMap, 5);

    consoleGameMapRenderer.render(gameMapWithEntities);
  }
}