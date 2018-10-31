package net.camtech.fopmremastered.worlds;

import java.util.Random;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;

public class FOPMR_CheckerBoardGenerator extends ChunkGenerator {

	@Override
	public boolean canSpawn(World world, int x, int z) {
		return true;
	}

	public int xyzToByte(int x, int y, int z) {
		return (x * 16 + z) * 128 + y;
	}

	@Override
	public ChunkData generateChunkData(World world, Random random, int chunkx, int chunkz, BiomeGrid biome) {
		int even = 0;
		if (chunkx % 2 == 0) {
			even++;
		}
		if (chunkz % 2 == 0) {
			even++;
		}
		ChunkData result = createChunkData(world);
		int y = 0;
		for (int x = 0; x < 16; x++) {
			for (int z = 0; z < 16; z++) {
				if (even == 1) {
					result.setBlock(x, y, z, Material.BEDROCK);
				} else {
					result.setBlock(x, y, z, Material.AIR);
				}
			}
		}
		for (y = 1; y < 50; y++) {
			for (int x = 0; x < 16; x++) {
				for (int z = 0; z < 16; z++) {
					if (even == 1) {
						result.setBlock(x, y, z, Material.DIRT);
					} else {
						result.setBlock(x, y, z, Material.AIR);
					}
				}
			}
		}
		y = 50;
		for (int x = 0; x < 16; x++) {
			for (int z = 0; z < 16; z++) {
				if (even == 1) {
					result.setBlock(x, y, z, Material.GRASS_BLOCK);
				} else {
					result.setBlock(x, y, z, Material.AIR);
				}
			}
		}
		return result;
	}

}
