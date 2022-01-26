package com.zittla.blocksumo.commons.util;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class Pair<L, R> {

  private L left;
  private R right;

  private Pair(L left, R right) {
    this.left = left;
    this.right = right;
  }

  @Contract(value = "_, _ -> new", pure = true)
  public static <L, R> @NotNull Pair<L, R> of(L left, R right) {
    return new Pair<>(left, right);
  }

  public L getLeft() {
    return this.left;
  }

  public void setLeft(L left) {
    this.left = left;
  }

  public R getRight() {
    return this.right;
  }

  public void setRight(R right) {
    this.right = right;
  }

}
