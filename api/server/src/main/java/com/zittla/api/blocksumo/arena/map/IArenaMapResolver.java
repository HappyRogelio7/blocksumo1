package com.zittla.api.blocksumo.arena.map;

import com.zittla.api.blocksumo.exception.IErrorDetails;
import com.zittla.api.blocksumo.util.Savable;
import java.util.Map;

public interface IArenaMapResolver extends Savable {

  void load(Object plugin);

  IArenaMap resolve(Map<String, String> data, IErrorDetails errorDetails);

}
