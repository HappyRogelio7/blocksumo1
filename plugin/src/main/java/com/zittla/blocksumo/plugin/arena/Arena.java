package com.zittla.blocksumo.plugin.arena;

import com.zittla.api.blocksumo.arena.IArena;
import com.zittla.api.blocksumo.arena.map.IArenaMap;
import com.zittla.api.blocksumo.util.Vector;
import java.util.Set;

public class Arena implements IArena {

  private final String id, name;
  private final int minPlayers, maxPlayers;
  private final Set<Vector> spawns;
  private final Vector spectatorSpawn;
  private final Vector waitingSpawn;
  private final IArenaMap arenaMap;

  public Arena(String id, String name, int minPlayers, int maxPlayers, Set<Vector> spawns, Vector spectatorSpawn,
      Vector waitingSpawn, IArenaMap arenaMap) {
    this.id = id;
    this.name = name;
    this.minPlayers = minPlayers;
    this.maxPlayers = maxPlayers;
    this.spawns = spawns;
    this.spectatorSpawn = spectatorSpawn;
    this.waitingSpawn = waitingSpawn;
    this.arenaMap = arenaMap;
  }

  @Override
  public String getId() {
    return this.id;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public int getMinPlayers() {
    return this.minPlayers;
  }

  @Override
  public int getMaxPlayers() {
    return this.maxPlayers;
  }

  @Override
  public Set<Vector> getSpawns() {
    return this.spawns;
  }

  @Override
  public Vector getSpectatorSpawn() {
    return this.spectatorSpawn;
  }

  @Override
  public Vector getWaitingSpawn() {
    return this.waitingSpawn;
  }

  @Override
  public IArenaMap getArenaMap() {
    return this.arenaMap;
  }

}
