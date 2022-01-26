package com.zittla.blocksumo.commons.plugin.classpath;

import com.zittla.blocksumo.commons.loader.JarClassLoader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import org.jetbrains.annotations.NotNull;

public class JarClassPathAppender implements ClassPathAppender {

  private final JarClassLoader classLoader;

  public JarClassPathAppender(ClassLoader classLoader) {
    if (!(classLoader instanceof JarClassLoader)) {
      throw new IllegalArgumentException("Loader is not a JarClassLoader: " + classLoader.getClass().getName());
    }
    this.classLoader = (JarClassLoader) classLoader;
  }

  @Override
  public void addJarToClasspath(@NotNull Path file) {
    try {
      this.classLoader.addJarToClasspath(file.toUri().toURL());
    } catch (MalformedURLException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void close() {
    try {
      this.classLoader.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
