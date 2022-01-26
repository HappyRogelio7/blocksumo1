package com.zittla.api.blocksumo.arena;

import com.zittla.api.blocksumo.arena.map.IArenaMap;
import com.zittla.api.blocksumo.util.Savable;
import com.zittla.api.blocksumo.util.Vector;
import java.util.Set;

public interface IArena extends Savable {

  String getName();

  int getMinPlayers();

  int getMaxPlayers();

  Set<Vector> getSpawns();

  Vector getSpectatorSpawn();

  Vector getWaitingSpawn();

  IArenaMap getArenaMap();

}
