package org.hawoline.domain;

public interface Renderer {
    void render(World world);

    void drawStepCounter(int stepCounter);
}
