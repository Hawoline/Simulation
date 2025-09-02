package org.hawoline.presentation;

import org.hawoline.domain.Simulation;

import java.util.Scanner;

public class Menu {
    private Scanner scanner = new Scanner(System.in);
    private Simulation simulation;

    public Menu(Simulation simulation) {
        this.simulation = simulation;
    }

    public void inputSomething() {
        System.out.println("Введите любой символ, чтобы начать симуляцию");
        scanner.next();
        simulation.startSimulation();
    }
}
