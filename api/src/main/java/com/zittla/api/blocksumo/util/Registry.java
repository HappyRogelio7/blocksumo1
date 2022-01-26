package com.zittla.api.blocksumo.util;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Function;

public interface Registry<T, O extends Savable> {

  Collection<O> getAll();

  O get(T id);

  default Optional<O> getIfLoaded(T id) {
    return Optional.ofNullable(get(id));
  }

  void register(O objects);

  void registerAll(Collection<O> object);

  boolean contains(T id);

  void unload(T id);

}
