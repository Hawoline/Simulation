package org.hawoline.domain;

import java.util.ArrayList;
import java.util.List;
import org.hawoline.presentation.FieldRenderer;

public class Simulation {
  private final GameMap map;
  private final int stepCounter;
  private final FieldRenderer fieldRenderer;
  private final List<Action> actions;

  private boolean isRunning;
  public Simulation(GameMap map, int stepCounter, FieldRenderer fieldRenderer, ArrayList<Action> actions) {
    this.map = map;
    this.stepCounter = stepCounter;
    this.fieldRenderer = fieldRenderer;
    this.actions = actions;
  }

  public void nextTurn() {
    //simulate();
  }

  public void startSimulation() {

  }

  public void endSimulation() {

  }
}
