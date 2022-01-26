package com.zittla.blocksumo.plugin.arena.map.codec;

import com.zittla.api.blocksumo.arena.IArena;
import com.zittla.api.blocksumo.arena.map.IArenaMap;
import com.zittla.api.blocksumo.exception.IErrorDetails;
import com.zittla.api.blocksumo.io.IObjectCodec;
import com.zittla.api.blocksumo.util.Vector;
import com.zittla.blocksumo.commons.config.generic.adapter.ConfigurationAdapter;
import com.zittla.blocksumo.commons.config.generic.adapter.configurate.HoconConfigurateConfigAdapter;
import com.zittla.blocksumo.plugin.BlockSumoPlugin;
import com.zittla.blocksumo.plugin.arena.Arena;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class DefaultArenaCodec implements IObjectCodec<IArena> {

  private BlockSumoPlugin plugin;
  private boolean enabled;

  @Override
  public void load(Object plugin) {
    if (plugin instanceof BlockSumoPlugin) {
      this.plugin = (BlockSumoPlugin) plugin;
      this.enabled = true;
    }
  }

  @Override
  public Collection<IArena> read(Path path, IErrorDetails errorDetails) {
    if (!this.enabled) {
      return Collections.emptySet();
    }
    ConfigurationAdapter config = new HoconConfigurateConfigAdapter(path);
    String id, name;
    id = config.getString("data.id");
    name = config.getString("data.name");
    int minPlayers, maxPlayers;
    minPlayers = config.getInteger("data.min-players");
    maxPlayers = config.getInteger("data.max-players");
    Set<Vector> spawns = new HashSet<>();
    Vector spectatorSpawn = new Vector(0, 100, 20, 10.1F, 20.0F), waitingSpawn = new Vector();
    IArenaMap arenaMap = this.plugin.getArenaMapProvider().provide(config.getStringMap("map"), errorDetails);
    if (arenaMap == null || errorDetails.errorCount() != 0) {
      return Collections.emptySet();
    }
    Arena arena = new Arena(id, name, minPlayers, maxPlayers, spawns, spectatorSpawn, waitingSpawn, arenaMap);
    return Collections.singleton(arena);
  }

  @Override
  public void write(Path path, Collection<IArena> objects, IErrorDetails errorDetails) {

  }

  @Override
  public String getId() {
    return "conf";
  }

}
