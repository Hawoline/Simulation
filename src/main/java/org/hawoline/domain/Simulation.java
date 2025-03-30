package org.hawoline.domain;

import java.util.ArrayList;
import java.util.List;
import org.hawoline.presentation.ConsoleGameMapRenderer;

public class Simulation {
  private final GameMap map;
  private final int stepCounter;
  private final ConsoleGameMapRenderer fieldRenderer;
  private final List<Action> actions;

  private boolean isRunning;
  public Simulation(GameMap map, int stepCounter, ConsoleGameMapRenderer consoleFieldRenderer, ArrayList<Action> actions) {
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
