package com.zittla.blocksumo.commons.config.generic.adapter.configurate.serializer;

import com.zittla.api.blocksumo.util.Vector;
import com.zittla.blocksumo.commons.util.VectorParser;
import java.lang.reflect.Type;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.serialize.SerializationException;
import org.spongepowered.configurate.serialize.TypeSerializer;

public class VectorSerializer implements TypeSerializer<Vector> {

  @Override
  public Vector deserialize(Type type, @NotNull ConfigurationNode node) {
    return VectorParser.parseFromString(node.getString(""));
  }

  @Override
  public void serialize(Type type, @Nullable Vector obj, @NotNull ConfigurationNode node)
      throws SerializationException {
    node.set(VectorParser.parseToSting(obj));
  }
}
