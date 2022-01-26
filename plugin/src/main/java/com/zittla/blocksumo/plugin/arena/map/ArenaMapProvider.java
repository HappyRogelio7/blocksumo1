package com.zittla.blocksumo.plugin.arena.map;

import com.zittla.api.blocksumo.arena.map.IArenaMap;
import com.zittla.api.blocksumo.arena.map.IArenaMapProvider;
import com.zittla.api.blocksumo.arena.map.IArenaMapResolver;
import com.zittla.api.blocksumo.exception.IErrorDetails;
import com.zittla.api.blocksumo.util.Registry;
import com.zittla.blocksumo.commons.util.DefaultRegistry;
import java.util.Map;
import org.jetbrains.annotations.NotNull;

public class ArenaMapProvider implements IArenaMapProvider {

  private final Registry<String, IArenaMapResolver> arenaMapResolverRegistry;

  public ArenaMapProvider() {
    this.arenaMapResolverRegistry = new DefaultRegistry<>();
  }

  @Override
  public void load(Object plugin) {
    this.arenaMapResolverRegistry.getAll().forEach(resolver -> resolver.load(plugin));
  }

  @Override
  public void registerProvider(IArenaMapResolver arenaResolver) {
    this.arenaMapResolverRegistry.register(arenaResolver);
  }

  @Override
  public IArenaMap provide(@NotNull Map<String, String> data, IErrorDetails errorDetails) {
    String id = data.get("type");
    if (id == null) {
      return null;
    }
    data.remove("type");
    IArenaMapResolver mapResolver = this.arenaMapResolverRegistry.get(id);
    return mapResolver.resolve(data, errorDetails);
  }

}
