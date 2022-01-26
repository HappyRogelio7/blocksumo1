package com.zittla.api.blocksumo.arena.map;

import com.zittla.api.blocksumo.exception.IErrorDetails;
import java.util.Map;

public interface IArenaMapProvider {

  void registerProvider(IArenaMapResolver arenaResolver);

  IArenaMap provide(Map<String, String> data, IErrorDetails errorDetails);

  void load(Object plugin);

}
