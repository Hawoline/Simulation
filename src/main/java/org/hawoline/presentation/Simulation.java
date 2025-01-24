package org.hawoline.presentation;

import org.hawoline.domain.GameMap;

public class Simulation {
  private final GameMap gameMap;
  private final int stepCounter;
  private final FieldRenderer fieldRenderer;
  private final Actions actions;

  public Simulation(GameMap gameMap, int stepCounter) {
    this.gameMap = gameMap;
    this.stepCounter = stepCounter;
  }

  public void nextTurn() {
    simulate();
    render();
  }

  public void startSimulation() {

  }

  public void endSimulation() {

  }
}
