package com.zittla.blocksumo.plugin.arena.map.types.schematic;

import com.zittla.api.blocksumo.arena.map.IArenaMap;
import com.zittla.api.blocksumo.arena.map.IArenaMapResolver;
import com.zittla.api.blocksumo.exception.IErrorDetails;
import com.zittla.api.blocksumo.util.Vector;
import com.zittla.blocksumo.commons.util.MoreFiles;
import com.zittla.blocksumo.commons.util.VectorParser;
import com.zittla.blocksumo.plugin.BlockSumoPlugin;
import java.io.File;
import java.nio.file.Path;
import java.util.Map;

public class SchematicMapResolver implements IArenaMapResolver {

  public static String LOG_FORMAT = "The %s file not found.";

  private boolean enabled;
  private Path folder;

  @Override
  public void load(Object plugin) {
    if (plugin instanceof BlockSumoPlugin) {
      this.enabled = true;
      this.folder = ((BlockSumoPlugin) plugin).getBootstrap().getDataDirectory().resolve("schematics");
      MoreFiles.createDirectoriesIfNotExists(this.folder);
    }
  }

  @Override
  public IArenaMap resolve(Map<String, String> data, IErrorDetails errorDetails) {
    if (!this.enabled) {
      return null;
    }
    File mapSchematic = this.folder.resolve(data.get("map-schematic")).toFile();
    File waitingSchematic = this.folder.resolve(data.get("waiting-clear-schematic")).toFile();
    if (!mapSchematic.exists()) {
      errorDetails.add(String.format(LOG_FORMAT, mapSchematic.getName()));
    }
    if (!waitingSchematic.exists()) {
      errorDetails.add(String.format(LOG_FORMAT, waitingSchematic.getName()));
    }
    int height = Integer.parseInt(data.getOrDefault("height", "80"));
    Vector waitingLocation = VectorParser.parseFromString(data.get("waiting-location"));
    return new SchematicArenaMap(mapSchematic, waitingSchematic, height, waitingLocation);
  }

  @Override
  public String getId() {
    return "schematic";
  }

}
