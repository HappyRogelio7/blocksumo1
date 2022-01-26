package com.zittla.blocksumo.plugin.util;

import com.boydti.fawe.FaweAPI;
import com.boydti.fawe.object.schematic.Schematic;
import com.sk89q.worldedit.extent.clipboard.ClipboardFormats;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import com.zittla.api.blocksumo.exception.IErrorDetails;
import com.zittla.api.blocksumo.util.Vector;
import java.io.File;
import java.io.IOException;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class Schematics {

  public static ClipboardFormat getFormatFromFile(File schematic) {
    if (schematic == null || !schematic.exists()) {
      return null;
    }
    return ClipboardFormats.findByFile(schematic);
  }

  public static @Nullable Schematic getSchematic(@NotNull ClipboardFormat format, File schematicFile, IErrorDetails errorDetails) {
    try {
      return format.load(schematicFile);
    } catch (IOException e) {
      errorDetails.add(e);
      return null;
    }
  }

  public static boolean pasteSchematic(@NotNull Schematic schematic, String worldName, Vector vector) {
    schematic.paste(FaweAPI.getWorld(worldName), parseVector(vector));
    return true;
  }

  @Contract("_ -> new")
  public static com.sk89q.worldedit.@NotNull Vector parseVector(@NotNull Vector vector) {
    return new com.sk89q.worldedit.Vector(vector.getX(), vector.getY(), vector.getZ());
  }

}
