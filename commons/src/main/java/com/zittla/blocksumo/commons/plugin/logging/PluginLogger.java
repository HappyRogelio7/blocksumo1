package com.zittla.blocksumo.commons.plugin.logging;

public interface PluginLogger {

  void info(String message);

  void warn(String message);

  void warn(String message, Throwable throwable);

  void severe(String message);

  void severe(String message, Throwable throwable);

}
