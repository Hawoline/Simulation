package org.hawoline.domain;

import java.util.HashMap;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class GameMapTest {
  private GameMap emptyMap = new GameMap(new HashMap<>());

  @BeforeAll
  protected static void setUp() {
  }

  @Test
  public void testInitEntities() {
    GameMap gameMapWithEntities = emptyMap.setUpEntities();
    assertTrue(gameMapWithEntities.getEntities().size() < 25);
    assertTrue(gameMapWithEntities.getEntities().size() > 5);
  }
  @org.junit.jupiter.api.AfterEach
  void tearDown() {
  }
}