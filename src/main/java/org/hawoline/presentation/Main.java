package org.hawoline.presentation;

import org.hawoline.domain.Simulation;
import org.hawoline.domain.World;
import org.hawoline.domain.AddRandomEntitiesAction;

import java.util.HashMap;
import org.hawoline.domain.entity.Entity;
import org.hawoline.domain.entity.Predator;

public class Main {
  public static void main(String[] args) {
    System.out.println("G - Grass");
    System.out.println("H - Herbivore");
    System.out.println("P - Predator");
    System.out.println("R - Rock");
    System.out.println("T - Tree");

    ConsoleFieldRenderer consoleFieldRenderer = new ConsoleFieldRenderer();
    World world = new World(new HashMap<>(), 20, 20);

    Simulation simulation = new Simulation(world, consoleFieldRenderer);
    simulation.startSimulation();
  }
}