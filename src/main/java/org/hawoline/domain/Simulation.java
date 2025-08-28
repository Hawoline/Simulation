package org.hawoline.domain;

import java.util.ArrayList;
import java.util.List;
import org.hawoline.presentation.ConsoleFieldRenderer;

public class Simulation {
  private final World map;
  private final int stepCounter;
  private final ConsoleFieldRenderer fieldRenderer;
  private final List<Action> actions;

  private boolean isRunning;
  public Simulation(World map, int stepCounter, ConsoleFieldRenderer consoleFieldRenderer, ArrayList<Action> actions) {
    this.map = map;
    this.stepCounter = stepCounter;
    this.fieldRenderer = consoleFieldRenderer;
    this.actions = actions;
  }

  public void nextTurn() {
    //simulate();
    //render
  }

  public void startSimulation() {

  }

  public void endSimulation() {

  }
}
