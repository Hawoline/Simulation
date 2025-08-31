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
        Thread.sleep(2000);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
  }

  public void nextTurn() {
    for (WorldAction action: actions) {
      action.act(world);
    }
    renderer.render(world);
    renderer.drawStepCounter(stepCounter);
    stepCounter++;
  }

  public void startSimulation() {
    WorldAction initAction = new AddRandomEntitiesAction(5);
    initAction.act(world);
    renderer.render(world);
    isRunning = true;
    simulate();
  }

  public void endSimulation() {
    isRunning = false;
  }
}
