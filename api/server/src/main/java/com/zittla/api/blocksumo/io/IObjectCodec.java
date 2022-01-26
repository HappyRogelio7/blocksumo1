package com.zittla.api.blocksumo.io;

import com.zittla.api.blocksumo.exception.IErrorDetails;
import com.zittla.api.blocksumo.util.Savable;
import java.nio.file.Path;
import java.util.Collection;

public interface IObjectCodec<T> extends Savable {

  void load(Object plugin);

  Collection<T> read(Path path, IErrorDetails errorDetails);

  void write(Path path, Collection<T> objects, IErrorDetails errorDetails);

}
