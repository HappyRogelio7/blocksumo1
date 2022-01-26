package com.zittla.blocksumo.commons.plugin;

import com.zittla.api.blocksumo.arena.IArena;
import com.zittla.api.blocksumo.arena.IArenaLoader;
import com.zittla.api.blocksumo.arena.map.IArenaMapProvider;
import com.zittla.api.blocksumo.game.IGame;
import com.zittla.api.blocksumo.io.IObjectCodec;
import com.zittla.api.blocksumo.util.Registry;
import com.zittla.blocksumo.commons.plugin.bootstrap.IBlockSumoBootstrap;

public interface IBlockSumoPlugin {

  Registry<String, IGame> getGameRegistry();

  Registry<String, IArena> getArenaRegistry();

  Registry<String, IObjectCodec<IArena>> getArenaCodecRegistry();

  IArenaLoader getArenaLoader();

  IArenaMapProvider getArenaMapProvider();

  IBlockSumoBootstrap getBootstrap();

}
