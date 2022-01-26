package com.zittla.blocksumo.plugin.loader;

import com.zittla.blocksumo.commons.loader.JarClassLoader;
import com.zittla.blocksumo.commons.loader.LoaderBootstrap;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("unused")
public class BlockSumoPluginLoader extends JavaPlugin {

  private final LoaderBootstrap bootstrap;

  public BlockSumoPluginLoader() {
    JarClassLoader classLoader = new JarClassLoader(getClass().getClassLoader(), "blocksumo.plugin");
    this.bootstrap = classLoader.instantiatePlugin("com.zittla.blocksumo.plugin.BlockSumoBootstrap", this);
  }

  @Override
  public void onLoad() {
    bootstrap.onLoad();
  }

  @Override
  public void onEnable() {
    bootstrap.onEnable();
  }

  @Override
  public void onDisable() {
    bootstrap.onDisable();
  }

}
