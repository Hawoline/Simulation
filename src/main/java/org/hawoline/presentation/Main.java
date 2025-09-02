package org.hawoline.presentation;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.hawoline.domain.*;

public class Main {
  public static void main(String[] args) {
    System.out.println("G - Grass");
    System.out.println("H - Herbivore");
    System.out.println("P - Predator");
    System.out.println("R - Rock");
    System.out.println("T - Tree");

    ConsoleFieldRenderer consoleFieldRenderer = new ConsoleFieldRenderer();
    World world = new World(new ConcurrentHashMap<>(), 20, 20);

    List<WorldAction> actions = new ArrayList<>();
    actions.add(new MakeMoveAction());
    actions.add(new AddGrassAction());
    Simulation simulation = new Simulation(world, consoleFieldRenderer, actions);
    WorldAction initAction = new AddRandomEntitiesAction(5);
    initAction.act(world);
    consoleFieldRenderer.render(world);
    Menu menu = new Menu(simulation);
    menu.inputSomething();
  }
}