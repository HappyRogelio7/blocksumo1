package com.zittla.blocksumo.commons.util;

import com.zittla.api.blocksumo.util.Vector;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public final class VectorParser {

  @Contract("_ -> new")
  public static @NotNull Vector parseFromString(String raw) {
    if (raw == null || raw.isEmpty()) {
      return new Vector();
    }
    String[] args = raw.split(":");
    if (args.length < 1) {
      return new Vector();
    }
    Vector vector = new Vector();
    String block = args[0];
    if (block != null) {
      String[] blockArgs = block.split(",");
      if (blockArgs.length > 3) {
        vector.setX(Double.parseDouble(blockArgs[0]));
        vector.setY(Double.parseDouble(blockArgs[1]));
        vector.setZ(Double.parseDouble(blockArgs[2]));
      }
    }
    String vision = args[1];
    if (block != null) {
      String[] visionArgs = vision.split(",");
      if (visionArgs.length > 2) {
        vector.setYaw(Float.parseFloat(visionArgs[0]));
        vector.setPitch(Float.parseFloat(visionArgs[1]));
      }
    }
    return vector;
  }

  public static String parseToSting(Vector vector) {
    if (vector == null) {
      return "0.0,0.0,0.0:0.0,0.0";
    }
    return String.format("%s,%s,%s:%s,%s", vector.getX(), vector.getY(), vector.getY(), vector.getYaw(),
        vector.getPitch());
  }

}
