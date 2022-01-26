package com.zittla.blocksumo.plugin.game;

import com.zittla.api.blocksumo.game.GameLocationData;
import com.zittla.api.blocksumo.game.IGame;
import com.zittla.api.blocksumo.game.player.IGamePlayer;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Game implements IGame {

  private final Map<UUID, IGamePlayer> players;
  private final String id, arenaId;
  private final GameLocationData locationData;

  public Game(String id, String arenaId, GameLocationData locationData) {
    this.id = id;
    this.arenaId = arenaId;
    this.locationData = locationData;
    this.players = new HashMap<>();
  }

  @Override
  public String getId() {
    return this.id;
  }
}
