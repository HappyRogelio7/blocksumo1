package com.zittla.blocksumo.commons.config.generic.adapter.configurate;

import com.zittla.blocksumo.commons.config.generic.adapter.configurate.serializer.Serializers;
import java.nio.file.Path;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.hocon.HoconConfigurationLoader;
import org.spongepowered.configurate.loader.ConfigurationLoader;

public class HoconConfigurateConfigAdapter extends ConfigurateConfigAdapter {

  public HoconConfigurateConfigAdapter(Path path) {
    super(path);
  }

  @Override
  protected ConfigurationLoader<? extends ConfigurationNode> createLoader(Path path) {
    return HoconConfigurationLoader.builder()
        .path(path)
        .defaultOptions(Serializers.options)
        .build();
  }
}
