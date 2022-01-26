package com.zittla.api.blocksumo.arena;

import java.nio.file.Path;

public interface IArenaLoader {

  void load();

  Path getDirectory();

}
