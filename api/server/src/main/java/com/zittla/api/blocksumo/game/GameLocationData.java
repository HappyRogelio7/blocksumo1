package com.zittla.api.blocksumo.game;

import com.zittla.api.blocksumo.arena.IArena;
import com.zittla.api.blocksumo.util.Vector;
import java.util.Set;
import java.util.stream.Collectors;
import org.bukkit.Location;
import org.bukkit.World;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class GameLocationData {

  private final Set<Location> spawns;
  private final Location spectatorSpawn, waitingSpawn;

  private GameLocationData(World world, @NotNull Set<Vector> spawns, @NotNull Vector spectatorSpawn,
      @NotNull Vector waitingSpawn) {
    this.spawns = spawns.stream().map(vector -> vector.toLocation(world)).collect(Collectors.toSet());
    this.spectatorSpawn = spectatorSpawn.toLocation(world);
    this.waitingSpawn = waitingSpawn.toLocation(world);
  }

  @Contract("_, _ -> new")
  public static @NotNull GameLocationData parse(World world, @NotNull IArena arena) {
    return new GameLocationData(world, arena.getSpawns(), arena.getSpectatorSpawn(), arena.getWaitingSpawn());
  }

  public Set<Location> getSpawns() {
    return this.spawns;
  }

  public Location getSpectatorSpawn() {
    return this.spectatorSpawn;
  }

  public Location getWaitingSpawn() {
    return this.waitingSpawn;
  }

}
