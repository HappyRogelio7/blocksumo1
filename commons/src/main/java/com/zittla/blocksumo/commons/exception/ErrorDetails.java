package com.zittla.blocksumo.commons.exception;

import com.zittla.api.blocksumo.exception.IErrorDetails;
import com.zittla.blocksumo.commons.util.Errors;
import com.zittla.blocksumo.commons.util.Validate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.StringJoiner;

public final class ErrorDetails implements IErrorDetails {

  private final String heading;
  private final List<String> messages;

  public ErrorDetails(String heading, Collection<String> messages) {
    Validate.notNull(messages, "messages");
    this.heading = Validate.notNull(heading, "heading");
    this.messages = new ArrayList<>(messages);
  }

  public ErrorDetails(String heading) {
    this.heading = Validate.notNull(heading, "heading");
    this.messages = new ArrayList<>();
  }

  @Override
  public IErrorDetails merge(IErrorDetails details) {
    Validate.notNull(details, "details");
    if (details.errorCount() == 0) {
      return this;
    }
    this.messages.addAll(((ErrorDetails) details).messages);
    return this;
  }

  @Override
  public synchronized ErrorDetails add(Throwable error) {
    return add(Errors.getStackTrace(error));
  }

  @Override
  public synchronized ErrorDetails add(String message) {
    this.messages.add(Validate.notEmpty(message));
    return this;
  }

  @Override
  public synchronized int errorCount() {
    return this.messages.size();
  }

  @Override
  public synchronized String format() {
    StringJoiner joiner = new StringJoiner("\n");
    joiner.add(heading);
    for (int i = 0; i < this.messages.size(); i++) {
      int number = i + 1;
      String message = this.messages.get(i);
      joiner.add(number + ") " + message);
    }
    return joiner.toString();
  }

}
