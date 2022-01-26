package com.zittla.blocksumo.commons.config;

import com.zittla.blocksumo.commons.config.generic.KeyedConfiguration;
import com.zittla.blocksumo.commons.config.generic.key.ConfigKey;
import com.zittla.blocksumo.commons.config.generic.key.SimpleConfigKey;
import java.util.List;

public final class ConfigKeys {

  private ConfigKeys() {
  }

  private static final List<SimpleConfigKey<?>> KEYS = KeyedConfiguration.initialise(ConfigKeys.class);

  public static List<? extends ConfigKey<?>> getKeys() {
    return KEYS;
  }

}
