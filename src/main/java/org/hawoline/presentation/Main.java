package org.hawoline.presentation;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import org.hawoline.domain.AddGrassAction;
import org.hawoline.domain.MakeMoveAction;
import org.hawoline.domain.Simulation;
import org.hawoline.domain.World;

import org.hawoline.domain.WorldAction;

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
    simulation.startSimulation();
  }
}