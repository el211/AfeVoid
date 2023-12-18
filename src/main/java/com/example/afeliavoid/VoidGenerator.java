package com.example.AfeliaVoid;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;

import java.util.Random;

public class VoidGenerator extends ChunkGenerator {

    @Override
    public ChunkGenerator.ChunkData generateChunkData(World world, Random random, int x, int z, ChunkGenerator.BiomeGrid biome) {
        ChunkGenerator.ChunkData chunkData = createChunkData(world);
        for (int y = 0; y < world.getMaxHeight(); y++) {
            chunkData.setBlock(x, y, z, Material.AIR);
        }
        return chunkData;
    }
}
