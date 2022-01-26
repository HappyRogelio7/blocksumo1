package com.zittla.blocksumo.commons.util;

import com.zittla.api.blocksumo.util.Registry;
import com.zittla.api.blocksumo.util.Savable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.jetbrains.annotations.NotNull;

public class DefaultRegistry<O extends Savable> implements Registry<String, O> {

  private final Map<String, O> cache;

  public DefaultRegistry() {
    this.cache = new HashMap<>();
  }

  @Override
  public Collection<O> getAll() {
    return cache.values();
  }

  @Override
  public O get(String id) {
    return cache.get(id);
  }

  @Override
  public void register(O object) {
    cache.put(object.getId(), object);
  }

  @Override
  public void registerAll(@NotNull Collection<O> objects) {
    objects.forEach(o -> this.cache.put(o.getId(), o));
  }

  @Override
  public boolean contains(String id) {
    return cache.containsKey(id);
  }

  @Override
  public void unload(String id) {
    cache.remove(id);
  }

}
