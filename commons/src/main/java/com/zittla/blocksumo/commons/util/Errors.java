package com.zittla.blocksumo.commons.util;

import java.io.PrintWriter;
import java.io.StringWriter;

public final class Errors {

  private Errors() {
    throw new UnsupportedOperationException();
  }

  public static String getStackTrace(Throwable throwable) {
    Validate.notNull(throwable, "throwable");
    StringWriter writer = new StringWriter();
    PrintWriter printWriter = new PrintWriter(writer);
    throwable.printStackTrace(printWriter);
    String stackTrace = writer.toString();
    printWriter.flush();
    printWriter.close();
    return stackTrace;
  }

}
