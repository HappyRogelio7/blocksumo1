package com.zittla.api.blocksumo.arena.map;

import com.zittla.api.blocksumo.exception.IErrorDetails;
import org.bukkit.World;

public interface IArenaMap {

  World generateArena(String id, IErrorDetails errorDetails);

  String getType();

}
