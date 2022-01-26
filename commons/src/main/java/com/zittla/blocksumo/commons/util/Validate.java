package com.zittla.blocksumo.commons.util;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public final class Validate {

  private Validate() {
    throw new UnsupportedOperationException();
  }

  @Contract(value = "null, _ -> fail; !null, _ -> param1", pure = true)
  public static <T> @NotNull T notNull(T object, String name) {
    if (object == null) {
      throw new NullPointerException(name);
    }
    return object;
  }

  @Contract("null -> fail")
  public static @NotNull String notEmpty(String string) {
    if (string == null || string.isEmpty()) {
      throw new IllegalArgumentException("Provided argument is null or empty!");
    }
    return string;
  }

  @Contract("_ -> param1")
  public static String @NotNull [] eachNotEmpty(String @NotNull [] array) {
    if (array.length == 0) {
      throw new IllegalArgumentException("Provided array is empty");
    }
    for (int i = 0; i < array.length; i++) {
      String string = array[i];
      if (string == null || string.isEmpty()) {
        throw new IllegalArgumentException("Element at index " + i + " in the"
            + " provided array is null or an empty string");
      }
    }
    return array;
  }

}
