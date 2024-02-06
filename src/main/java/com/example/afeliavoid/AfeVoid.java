package com.example.voidafeliagen;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;

import java.util.Random;

public class VoidGenerator extends ChunkGenerator {

    @Override
    public ChunkGenerator.ChunkData generateChunkData(World world, Random random, int x, int z, ChunkGenerator.BiomeGrid biome) {
        ChunkGenerator.ChunkData chunkData = createChunkData(world);

        for (int y = 0; y < world.getMaxHeight(); y++) {
            for (int relativeX = 0; relativeX < 16; relativeX++) {
                for (int relativeZ = 0; relativeZ < 16; relativeZ++) {
                    chunkData.setBlock(relativeX, y, relativeZ, Material.AIR);
                }
            }
        }

        return chunkData;
    }
}
