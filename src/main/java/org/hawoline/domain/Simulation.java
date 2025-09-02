package org.hawoline.domain;

import java.util.List;

public class Simulation {
  private final World world;
  private Renderer renderer;
  private List<WorldAction> actions;
  private int stepCounter = 0;

  private boolean isRunning;
  public Simulation(World world, Renderer renderer, List<WorldAction> actions) {
    this.world = world;
    this.renderer = renderer;
    this.actions = actions;
  }

  public void simulate() {
    while (isRunning) {
      nextTurn();
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
  }

  public void nextTurn() {
    for (WorldAction action: actions) {
      action.act(world);
    }
    renderer.drawStepCounter(stepCounter);
    renderer.render(world);
    stepCounter++;
  }

  public void startSimulation() {
    isRunning = true;
    simulate();
  }

  public void endSimulation() {
    isRunning = false;
  }
}
