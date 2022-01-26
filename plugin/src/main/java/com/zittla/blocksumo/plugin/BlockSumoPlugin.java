package com.zittla.blocksumo.plugin;

import co.aikar.commands.PaperCommandManager;
import com.zittla.api.blocksumo.arena.IArena;
import com.zittla.api.blocksumo.arena.IArenaLoader;
import com.zittla.api.blocksumo.arena.map.IArenaMapProvider;
import com.zittla.api.blocksumo.game.IGame;
import com.zittla.api.blocksumo.io.IObjectCodec;
import com.zittla.api.blocksumo.util.Registry;
import com.zittla.blocksumo.commons.plugin.IBlockSumoPlugin;
import com.zittla.blocksumo.commons.plugin.bootstrap.IBlockSumoBootstrap;
import com.zittla.blocksumo.commons.util.DefaultRegistry;
import com.zittla.blocksumo.plugin.arena.ArenaLoader;
import com.zittla.blocksumo.plugin.arena.map.ArenaMapProvider;
import com.zittla.blocksumo.plugin.arena.map.codec.DefaultArenaCodec;
import com.zittla.blocksumo.plugin.arena.map.types.schematic.SchematicMapResolver;
import com.zittla.blocksumo.plugin.game.GameManager;

public class BlockSumoPlugin implements IBlockSumoPlugin {

  private final BlockSumoBootstrap bootstrap;

  private final Registry<String, IArena> arenaRegistry;
  private final Registry<String, IObjectCodec<IArena>> arenaCodecRegistry;
  private final ArenaLoader arenaLoader;
  private final IArenaMapProvider arenaMapProvider;

  private final GameManager gameManager;
  private final Registry<String, IGame> gameRegistry;

  private PaperCommandManager commandManager;

  public BlockSumoPlugin(BlockSumoBootstrap bootstrap) {
    this.bootstrap = bootstrap;

    this.gameManager = new GameManager(this);
    this.gameRegistry = new DefaultRegistry<>();

    this.arenaRegistry = new DefaultRegistry<>();
    this.arenaCodecRegistry = new DefaultRegistry<>();
    this.arenaLoader = new ArenaLoader(this);
    this.arenaMapProvider = new ArenaMapProvider();
  }

  public void load() {
    getArenaCodecRegistry().register(new DefaultArenaCodec());
    getArenaMapProvider().registerProvider(new SchematicMapResolver());

    getArenaMapProvider().load(this);
    getArenaCodecRegistry().getAll().forEach(codec -> codec.load(this));

    getArenaLoader().load();
  }

  public void enable() {
    this.gameManager.createAllGames();
    this.commandManager = new PaperCommandManager(getBootstrap().getLoader());
  }

  public void disable() {
  }

  public PaperCommandManager getCommandManager() {
    return this.commandManager;
  }

  @Override
  public Registry<String, IGame> getGameRegistry() {
    return this.gameRegistry;
  }

  @Override
  public Registry<String, IArena> getArenaRegistry() {
    return this.arenaRegistry;
  }

  @Override
  public Registry<String, IObjectCodec<IArena>> getArenaCodecRegistry() {
    return this.arenaCodecRegistry;
  }

  @Override
  public IArenaLoader getArenaLoader() {
    return this.arenaLoader;
  }

  @Override
  public IArenaMapProvider getArenaMapProvider() {
    return this.arenaMapProvider;
  }

  @Override
  public IBlockSumoBootstrap getBootstrap() {
    return this.bootstrap;
  }

}
