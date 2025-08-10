package org.hawoline.presentation;

import org.hawoline.domain.Field;
import org.hawoline.domain.AddRandomEntitiesAction;

import java.util.HashMap;

public class Main {
  public static void main(String[] args) {
    ConsoleFieldRenderer consoleFieldRenderer = new ConsoleFieldRenderer();
    Field emptyMap = new Field(new HashMap<>(), 20, 20);
    AddRandomEntitiesAction addRandomEntitiesAction = new AddRandomEntitiesAction();
    Field fieldWithEntities = addRandomEntitiesAction.addEachTypeOfEntitiesWithRandomPosition(emptyMap, 10);

    consoleFieldRenderer.render(fieldWithEntities);
    System.out.println("G - Grass");
    System.out.println("H - Herbivore");
    System.out.println("P - Predator");
    System.out.println("R - Rock");
    System.out.println("T - Tree");
  }
}