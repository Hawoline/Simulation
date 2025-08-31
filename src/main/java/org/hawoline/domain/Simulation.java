package org.hawoline.domain;

public class Simulation {
  private final World world;
  private Renderer renderer;
  private WorldAction makeMoveAction;
  private WorldAction addGrassAction;
  private int stepCounter = 0;

  private boolean isRunning;
  public Simulation(World world, Renderer renderer) {
    this.world = world;
    this.renderer = renderer;
    makeMoveAction = new MakeMoveAction();
    addGrassAction = new AddGrassAction();
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
    makeMoveAction.act(world);
    addGrassAction.act(world);
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
