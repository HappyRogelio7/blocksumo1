package com.zittla.api.blocksumo.exception;

public interface IErrorDetails {

  IErrorDetails merge(IErrorDetails details);

  IErrorDetails add(Throwable error);

  IErrorDetails add(String message);

  int errorCount();

  String format();

}
