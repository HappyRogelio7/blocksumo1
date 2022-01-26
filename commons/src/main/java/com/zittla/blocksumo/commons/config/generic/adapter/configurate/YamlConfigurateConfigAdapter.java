package com.zittla.blocksumo.commons.config.generic.adapter.configurate;

import java.nio.file.Path;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.loader.ConfigurationLoader;
import org.spongepowered.configurate.yaml.NodeStyle;
import org.spongepowered.configurate.yaml.YamlConfigurationLoader;

public class YamlConfigurateConfigAdapter extends ConfigurateConfigAdapter {

  public YamlConfigurateConfigAdapter(Path path) {
    super(path);
  }

  @Override
  protected ConfigurationLoader<? extends ConfigurationNode> createLoader(Path path) {
    return YamlConfigurationLoader.builder()
        .path(path)
        .nodeStyle(NodeStyle.BLOCK)
        .indent(2)
        .build();
  }

}
