package com.zittla.blocksumo.plugin.util;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import org.bukkit.Difficulty;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.WorldCreator;
import org.bukkit.block.Biome;
import org.bukkit.entity.Entity;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

public final class Worlds {

  private static final ChunkGenerator CHUNK_GENERATOR = new VoidChunkGenerator();

  public static @NotNull World createWorld(String name) {
    World world = WorldCreator.name(name)
        .environment(Environment.NORMAL)
        .generator(CHUNK_GENERATOR)
        .generateStructures(false)
        .createWorld();

    world.setGameRuleValue("randomTickSpeed", "0");
    world.setGameRuleValue("mobGriefing", "true");
    world.setGameRuleValue("doFireTick", "false");
    world.setThundering(false);
    world.setGameRuleValue("showDeathMessages", "false");
    world.getWorldBorder().reset();
    world.setDifficulty(Difficulty.NORMAL);
    world.setStorm(false);
    world.setAutoSave(false);

    world.getEntities().forEach(Entity::remove);
    return world;
  }

  private static class VoidChunkGenerator extends ChunkGenerator {

    @Contract(pure = true)
    @Override
    public @NotNull @Unmodifiable List<BlockPopulator> getDefaultPopulators(World world) {
      return Collections.emptyList();
    }

    @Override
    public boolean canSpawn(World world, int x, int z) {
      return true;
    }

    @Override
    public ChunkData generateChunkData(World world, Random random, int cx, int cz, BiomeGrid biome) {
      ChunkData data = createChunkData(world);
      for (int x = 0; x <= 15; x++) {
        for (int z = 0; z <= 15; z++) {
          biome.setBiome(x, z, Biome.PLAINS);
        }
      }
      return data;
    }

    @Contract(value = "_, _ -> new", pure = true)
    @Override
    public @NotNull Location getFixedSpawnLocation(World world, Random random) {
      return new Location(world, 0, 150, 0);
    }
  }

}
