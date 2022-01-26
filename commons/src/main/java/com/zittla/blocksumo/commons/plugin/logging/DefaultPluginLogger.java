package com.zittla.blocksumo.commons.plugin.logging;

import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.jetbrains.annotations.NotNull;

public class DefaultPluginLogger implements PluginLogger {

  private static final String LOG_FORMAT = "%s \nmessage: %s \ncause: %s";
  private final ConsoleCommandSender console;

  public DefaultPluginLogger(ConsoleCommandSender console) {
    this.console = console;
  }

  @Override
  public void info(String s) {
    log("&f&linfo", s);
  }

  @Override
  public void warn(String s) {
    log("&6&lwarn", s);
  }

  @Override
  public void warn(String s, @NotNull Throwable t) {
    warn(String.format(LOG_FORMAT, s, t.getMessage(), t.getCause()));
  }

  @Override
  public void severe(String s) {
    log("&c&lsevere", s);
  }

  @Override
  public void severe(String s, @NotNull Throwable t) {
    severe(String.format(LOG_FORMAT, s, t.getMessage(), t.getCause()));
  }

  private void log(@NotNull String prefix, String message) {
    this.console.sendMessage(
        ChatColor.translateAlternateColorCodes('&',
            String.format("&a&lBlockSumo &8[%s&8] &r%s", prefix.toUpperCase(), message)));
  }

}
