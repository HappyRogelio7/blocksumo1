package com.zittla.blocksumo.commons.config.generic.adapter.configurate;

import com.google.common.base.Splitter;
import com.zittla.blocksumo.commons.config.generic.adapter.ConfigurationAdapter;
import io.leangen.geantyref.TypeToken;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.spongepowered.configurate.ConfigurateException;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.loader.ConfigurationLoader;
import org.spongepowered.configurate.serialize.SerializationException;

public abstract class ConfigurateConfigAdapter implements ConfigurationAdapter {

  private final ConfigurationLoader<? extends ConfigurationNode> loader;
  private final Path path;
  private ConfigurationNode root;

  public ConfigurateConfigAdapter(Path path) {
    this.loader = createLoader(path);
    this.path = path;
    reload();
  }

  @Override
  public <T> void set(String path, T value) {
    try {
      resolvePath(path).set(value.getClass(), value);
    } catch (SerializationException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void save() {
    try {
      this.loader.save(this.root);
    } catch (ConfigurateException e) {
      e.printStackTrace();
    }
  }

  protected abstract ConfigurationLoader<? extends ConfigurationNode> createLoader(Path path);

  @Override
  public void reload() {
    if (!Files.exists(this.path)) {
      InputStream stream = getClass().getClassLoader().getResourceAsStream(this.path.getFileName().toString());
      if (stream != null) {
        try {
          Files.copy(stream, this.path);
        } catch (IOException ignored) {
        }
      }
    }
    try {
      this.root = this.loader.load();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private ConfigurationNode resolvePath(String path) {
    if (this.root == null) {
      throw new RuntimeException("Config is not loaded.");
    }
    return this.root.node(Splitter.on('.').splitToList(path).toArray());
  }

  @Override
  public String getString(String path, String def) {
    return resolvePath(path).getString(def);
  }

  @Override
  public int getInteger(String path, int def) {
    return resolvePath(path).getInt(def);
  }

  @Override
  public boolean getBoolean(String path, boolean def) {
    return resolvePath(path).getBoolean(def);
  }

  @Override
  public List<String> getStringList(String path, List<String> def) {
    ConfigurationNode node = resolvePath(path);
    if (node.virtual() || !node.isList()) {
      return def;
    }

    try {
      return node.getList(String.class);
    } catch (SerializationException e) {
      return def;
    }
  }

  @Override
  public List<String> getKeys(String path, List<String> def) {
    ConfigurationNode node = resolvePath(path);
    if (node.virtual() || !node.isMap()) {
      return def;
    }

    return node.childrenMap().keySet().stream().map(Object::toString).collect(Collectors.toList());
  }

  @Override
  public Map<String, String> getStringMap(String path, Map<String, String> def) {
    ConfigurationNode node = resolvePath(path);
    if (node.virtual()) {
      return def;
    }
    try {
      return node.get(new TypeToken<Map<String, String>>() {
      }, def);
    } catch (SerializationException e) {
      return def;
    }
  }

  public Path getConfigFile() {
    return this.path;
  }

}
