package com.zittla.blocksumo.commons.config.generic.key;

import com.zittla.blocksumo.commons.config.generic.adapter.ConfigurationAdapter;

public interface ConfigKey<T> {

    int ordinal();

    boolean reloadable();

    T get(ConfigurationAdapter adapter);

}
