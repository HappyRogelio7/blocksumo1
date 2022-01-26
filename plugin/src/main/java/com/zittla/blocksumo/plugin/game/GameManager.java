package com.zittla.blocksumo.plugin.game;

import com.zittla.api.blocksumo.arena.IArena;
import com.zittla.api.blocksumo.game.GameLocationData;
import com.zittla.blocksumo.commons.exception.ErrorDetails;
import com.zittla.blocksumo.plugin.BlockSumoPlugin;
import java.util.UUID;
import org.bukkit.World;
import org.jetbrains.annotations.NotNull;

public class GameManager {

  private final BlockSumoPlugin plugin;

  public GameManager(BlockSumoPlugin plugin) {
    this.plugin = plugin;
  }

  public void createAllGames() {
    this.plugin.getArenaRegistry().getAll().forEach(this::createGame);
  }

  public void createGame(@NotNull IArena arena) {
    String id = UUID.randomUUID().toString();
    ErrorDetails errorDetails = new ErrorDetails("Cannot create game '" + id + "' (" + arena.getId() + ")");
    World world = arena.getArenaMap().generateArena(id, errorDetails);
    if (world == null) {
      errorDetails.add("The game world can't be null");
    }
    Game game = new Game(id, arena.getId(), GameLocationData.parse(world, arena));
    if (errorDetails.errorCount() == 0) {
      this.plugin.getGameRegistry().register(game);
      return;
    }
    this.plugin.getBootstrap().getLogger().warn(errorDetails.format());
  }

}
