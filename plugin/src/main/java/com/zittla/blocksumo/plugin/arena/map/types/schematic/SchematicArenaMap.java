package com.zittla.blocksumo.plugin.arena.map.types.schematic;

import com.boydti.fawe.object.schematic.Schematic;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import com.zittla.api.blocksumo.arena.map.IArenaMap;
import com.zittla.api.blocksumo.exception.IErrorDetails;
import com.zittla.api.blocksumo.util.Vector;
import com.zittla.blocksumo.plugin.util.Schematics;
import com.zittla.blocksumo.plugin.util.Worlds;
import java.io.File;
import org.bukkit.World;

public class SchematicArenaMap implements IArenaMap {

  private static final String SCHEMATIC_ARENA_MAP_TYPE = "schematic";

  private final Vector waitingLocation;
  private final File mapSchematic, waitingSchematic;
  private final int height;

  public SchematicArenaMap(File mapSchematic, File waitingSchematic, int height, Vector waitingLocation) {
    this.mapSchematic = mapSchematic;
    this.waitingSchematic = waitingSchematic;
    this.height = height;
    this.waitingLocation = waitingLocation;
  }

  @Override
  public World generateArena(String id, IErrorDetails errorDetails) {
    World world = Worlds.createWorld(id);
    paste(this.mapSchematic, id, new Vector(0, this.height, 0), errorDetails);
    return world;
  }

  public static void paste(File schematicFile, String worldName, Vector vector, IErrorDetails errorDetails) {
    ClipboardFormat format = Schematics.getFormatFromFile(schematicFile);
    if (format == null) {
      errorDetails.add("Unknown schematic format: " + schematicFile);
      return;
    }
    Schematic schematic = Schematics.getSchematic(format, schematicFile, errorDetails);
    if (schematic == null) {
      return;
    }
    if (!Schematics.pasteSchematic(schematic, worldName, vector)) {
      errorDetails.add("Failed to paste schematic: " + schematicFile);
    }
  }

  @Override
  public String getType() {
    return SchematicArenaMap.SCHEMATIC_ARENA_MAP_TYPE;
  }

}
