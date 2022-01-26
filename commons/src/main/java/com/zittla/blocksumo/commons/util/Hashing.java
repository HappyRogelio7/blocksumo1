package com.zittla.blocksumo.commons.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.jetbrains.annotations.NotNull;

public class Hashing {

  private static MessageDigest MESSAGE_DIGEST;

  static {
    try {
      MESSAGE_DIGEST = MessageDigest.getInstance("SHA-256");
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
  }

  public static @NotNull String hash(byte[] data) {
    BigInteger bigInt = new BigInteger(1, MESSAGE_DIGEST.digest(data));
    StringBuilder hashed = new StringBuilder(bigInt.toString(16));
    while (hashed.length() < 32) {
      hashed.insert(0, "0");
    }
    return hashed.toString();
  }

}
