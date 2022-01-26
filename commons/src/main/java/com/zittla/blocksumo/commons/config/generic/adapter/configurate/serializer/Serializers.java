package com.zittla.blocksumo.commons.config.generic.adapter.configurate.serializer;

import com.zittla.api.blocksumo.util.Vector;
import io.leangen.geantyref.TypeToken;
import org.spongepowered.configurate.ConfigurationOptions;
import org.spongepowered.configurate.serialize.TypeSerializerCollection;

public final class Serializers {

  public static final ConfigurationOptions options;

  static {
    TypeSerializerCollection.Builder serializers = TypeSerializerCollection.defaults().childBuilder();
    serializers.register(TypeToken.get(Vector.class), new VectorSerializer());
    options = ConfigurationOptions.defaults().serializers(serializers.build());
  }

}
