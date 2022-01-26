package com.zittla.blocksumo.commons.config.generic.adapter;

import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public interface ConfigurationAdapter {

  Path getConfigFile();

  void reload();

  <T> void set(String path, T value);

  void save();

  String getString(String path, String def);

  default String getString(String path) {
    return getString(path, "");
  }

  int getInteger(String path, int def);

  default int getInteger(String path) {
    return getInteger(path, 0);
  }

  boolean getBoolean(String path, boolean def);

  default boolean getBoolean(String path) {
    return getBoolean(path, false);
  }

  List<String> getStringList(String path, List<String> def);

  default List<String> getStringList(String path) {
    return getStringList(path, Collections.emptyList());
  }

  List<String> getKeys(String path, List<String> def);

  default List<String> getKeys(String path) {
    return getKeys(path, Collections.emptyList());
  }

  Map<String, String> getStringMap(String path, Map<String, String> def);

  default Map<String, String> getStringMap(String path) {
    return getStringMap(path, Collections.emptyMap());
  }

}
