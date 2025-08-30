package org.hawoline.domain;

import java.util.Random;
import org.hawoline.domain.entity.Grass;

public class AddGrassAction implements WorldAction {
  @Override public void act(World world) {
    Random random = new Random();
    world.put(new Coordinates(random.nextInt(world.width()), random.nextInt(world.height())), new Grass());
  }
}
