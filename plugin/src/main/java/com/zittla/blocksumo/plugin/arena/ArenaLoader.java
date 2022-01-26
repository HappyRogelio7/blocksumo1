package com.zittla.blocksumo.plugin.arena;

import com.zittla.api.blocksumo.arena.IArena;
import com.zittla.api.blocksumo.arena.IArenaLoader;
import com.zittla.api.blocksumo.exception.IErrorDetails;
import com.zittla.api.blocksumo.io.IObjectCodec;
import com.zittla.blocksumo.commons.exception.ErrorDetails;
import com.zittla.blocksumo.commons.plugin.IBlockSumoPlugin;
import com.zittla.blocksumo.commons.util.MoreFiles;
import com.zittla.blocksumo.commons.util.Pair;
import java.io.File;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Set;
import org.jetbrains.annotations.NotNull;

public class ArenaLoader implements IArenaLoader {

  private final IBlockSumoPlugin plugin;
  private final Path directory;

  public ArenaLoader(@NotNull IBlockSumoPlugin plugin) {
    this.plugin = plugin;
    this.directory = plugin.getBootstrap().getDataDirectory().resolve("arenas");
    MoreFiles.createDirectoriesIfNotExists(this.directory);
  }

  @Override
  public void load() {
    Set<File> rawFiles = MoreFiles.getFiles(this.directory.toFile());
    for (File rawFile : rawFiles) {
      Pair<IErrorDetails, Collection<IArena>> pair = loadArena(rawFile);
      if (pair.getLeft().errorCount() != 0) {
        this.plugin.getBootstrap().getLogger().warn(pair.getLeft().format());
        continue;
      }
      this.plugin.getArenaRegistry().registerAll(pair.getRight());
    }
  }

  @Override
  public Path getDirectory() {
    return this.directory;
  }

  public Pair<IErrorDetails, Collection<IArena>> loadArena(@NotNull File file) {
    String[] args = file.getName().split("\\.");
    String extension = args[args.length - 1];
    IObjectCodec<IArena> codec = this.plugin.getArenaCodecRegistry().get(extension);
    IErrorDetails errorDetails = new ErrorDetails("Cannot load arena file: " + file);
    Pair<IErrorDetails, Collection<IArena>> pair = Pair.of(errorDetails, null);
    if (codec == null) {
      errorDetails.add("Unknown arena codec for '" + extension + "' extension.");
      return pair;
    }
    pair.setRight(codec.read(file.toPath(), errorDetails));
    return pair;
  }

}
