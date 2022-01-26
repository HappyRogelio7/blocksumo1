package com.zittla.blocksumo.commons.plugin.bootstrap;

import com.zittla.blocksumo.commons.plugin.classpath.ClassPathAppender;
import com.zittla.blocksumo.commons.plugin.logging.PluginLogger;
import java.nio.file.Path;
import org.bukkit.plugin.java.JavaPlugin;

public interface IBlockSumoBootstrap {

  ClassPathAppender getClassPathAppender();

  JavaPlugin getLoader();

  Path getDataDirectory();

  PluginLogger getLogger();

}
