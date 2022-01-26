package com.zittla.blocksumo.commons.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import org.jetbrains.annotations.NotNull;

public class MoreFiles {

  public static @NotNull Set<File> getFiles(@NotNull File file) {
    Set<File> files = new HashSet<>();
    if (file.isDirectory()) {
      for (File f : Objects.requireNonNull(file.listFiles())) {
        files.addAll(getFiles(f));
      }
    } else {
      files.add(file);
    }
    return files;
  }

  public static Path createFileIfNotExists(Path path) {
    if (!Files.exists(path)) {
      try {
        Files.createFile(path);
      } catch (IOException ignored) {
      }
    }
    return path;
  }

  public static Path createDirectoryIfNotExists(Path path) {
    if (Files.exists(path) && (Files.isDirectory(path) || Files.isSymbolicLink(path))) {
      return path;
    }
    try {
      Files.createDirectory(path);
    } catch (IOException ignored) {
    }
    return path;
  }

  public static void createDirectoriesIfNotExists(Path path) {
    if (Files.exists(path) && (Files.isDirectory(path) || Files.isSymbolicLink(path))) {
      return;
    }
    try {
      Files.createDirectories(path);
    } catch (IOException ignored) {
    }
  }

  public static void deleteDirectory(Path path) {
    if (!Files.exists(path) || !Files.isDirectory(path)) {
      return;
    }
    try (DirectoryStream<Path> contents = Files.newDirectoryStream(path)) {
      for (Path file : contents) {
        if (Files.isDirectory(file)) {
          deleteDirectory(file);
        } else {
          Files.delete(file);
        }
      }
    } catch (IOException ignored) {
    }
    try {
      Files.delete(path);
    } catch (IOException ignored) {
    }
  }

}
