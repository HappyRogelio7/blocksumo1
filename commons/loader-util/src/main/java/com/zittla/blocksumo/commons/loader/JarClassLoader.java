package com.zittla.blocksumo.commons.loader;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class JarClassLoader extends URLClassLoader {

  static {
    ClassLoader.registerAsParallelCapable();
  }

  public JarClassLoader(ClassLoader loaderClassLoader, String jarResourcePath) throws LoadingException {
    super(new URL[]{extractJar(loaderClassLoader, jarResourcePath)}, loaderClassLoader);
  }

  private static @NotNull URL extractJar(@NotNull ClassLoader loaderClassLoader, String jarResourcePath)
      throws LoadingException {
    URL jarInJar = loaderClassLoader.getResource(jarResourcePath);
    if (jarInJar == null) {
      throw new LoadingException("Could not locate jar");
    }
    Path path;
    try {
      path = Files.createTempFile("blocksumo-plugin", ".jar.tmp");
    } catch (IOException e) {
      throw new LoadingException("Unable to create a temporary file", e);
    }
    path.toFile().deleteOnExit();
    try (InputStream in = jarInJar.openStream()) {
      Files.copy(in, path, StandardCopyOption.REPLACE_EXISTING);
    } catch (IOException e) {
      throw new LoadingException("Unable to copy jar to temporary path", e);
    }
    try {
      return path.toUri().toURL();
    } catch (MalformedURLException e) {
      throw new LoadingException("Unable to get URL from path", e);
    }
  }

  public void addJarToClasspath(URL url) {
    addURL(url);
  }

  public LoaderBootstrap instantiatePlugin(String bootstrapClass, JavaPlugin loaderPlugin) throws LoadingException {
    Class<? extends LoaderBootstrap> plugin;
    try {
      plugin = loadClass(bootstrapClass).asSubclass(LoaderBootstrap.class);
    } catch (ReflectiveOperationException e) {
      throw new LoadingException("Unable to load bootstrap class", e);
    }

    Constructor<? extends LoaderBootstrap> constructor;
    try {
      constructor = plugin.getConstructor(JavaPlugin.class);
    } catch (ReflectiveOperationException e) {
      throw new LoadingException("Unable to get bootstrap constructor", e);
    }

    try {
      return constructor.newInstance(loaderPlugin);
    } catch (ReflectiveOperationException e) {
      throw new LoadingException("Unable to create bootstrap plugin instance", e);
    }
  }

}
