package net.camtech.fopmremastered.worlds;

import java.util.Random;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;

public class FOPMR_FlatGenerator extends ChunkGenerator
{

    @Override
    public boolean canSpawn(World world, int x, int z)
    {
        return true;
    }

    public int xyzToByte(int x, int y, int z)
    {
        return (x * 16 + z) * 128 + y;
    }
    
    @Override
    public ChunkData generateChunkData(World world, Random random, int chunkx, int chunkz, BiomeGrid biome)
    {
        ChunkData result = createChunkData(world);
        int y = 0;
        for (int x = 0; x < 16; x++)
        {
            for (int z = 0; z < 16; z++)
            {
            	result.setBlock(x, y, z, Material.BEDROCK);
            }
        }
        for (y = 1; y < 50; y++)
        {
            for (int x = 0; x < 16; x++)
            {
                for (int z = 0; z < 16; z++)
                {
                	result.setBlock(x, y, z, Material.DIRT);
                }
            }
        }
        y = 50;
        for (int x = 0; x < 16; x++)
        {
            for (int z = 0; z < 16; z++)
            {
            	result.setBlock(x, y, z, Material.GRASS);

            }
        }
        return result;
    }

}
