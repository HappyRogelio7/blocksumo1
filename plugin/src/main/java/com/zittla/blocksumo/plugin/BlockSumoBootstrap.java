package com.zittla.blocksumo.plugin;

import com.zittla.blocksumo.commons.loader.LoaderBootstrap;
import com.zittla.blocksumo.commons.plugin.bootstrap.IBlockSumoBootstrap;
import com.zittla.blocksumo.commons.plugin.classpath.ClassPathAppender;
import com.zittla.blocksumo.commons.plugin.classpath.JarClassPathAppender;
import com.zittla.blocksumo.commons.plugin.logging.DefaultPluginLogger;
import com.zittla.blocksumo.commons.plugin.logging.PluginLogger;
import java.nio.file.Path;
import java.util.concurrent.CountDownLatch;
import org.bukkit.plugin.java.JavaPlugin;

public class BlockSumoBootstrap implements LoaderBootstrap, IBlockSumoBootstrap {

  private final JavaPlugin loader;
  private final BlockSumoPlugin plugin;

  private final ClassPathAppender classPathAppender;
  private final PluginLogger logger;

  private final CountDownLatch loadLatch = new CountDownLatch(1);
  private final CountDownLatch enableLatch = new CountDownLatch(1);

  public BlockSumoBootstrap(JavaPlugin loader) {
    this.loader = loader;
    this.plugin = new BlockSumoPlugin(this);
    this.classPathAppender = new JarClassPathAppender(getClass().getClassLoader());
    this.logger = new DefaultPluginLogger(getLoader().getServer().getConsoleSender());
  }

  @Override
  public void onLoad() {
    try {
      this.plugin.load();
    } finally {
      this.loadLatch.countDown();
    }
  }

  @Override
  public void onEnable() {
    try {
      this.plugin.enable();
    } finally {
      this.enableLatch.countDown();
    }
  }

  @Override
  public void onDisable() {
    this.plugin.disable();
  }

  @Override
  public ClassPathAppender getClassPathAppender() {
    return this.classPathAppender;
  }

  @Override
  public JavaPlugin getLoader() {
    return this.loader;
  }

  @Override
  public Path getDataDirectory() {
    return getLoader().getDataFolder().toPath();
  }

  @Override
  public PluginLogger getLogger() {
    return this.logger;
  }

}
