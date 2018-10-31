package net.camtech.fopmremastered.worlds;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.util.noise.SimplexOctaveGenerator;

public class FOPMR_RollinghillsGenerator extends ChunkGenerator {

	@SuppressWarnings("deprecation")
	public void setBlock(int x, int y, int z, byte[][] chunk, Material material) {
		// if the Block section the block is in hasn't been used yet, allocate it
		if (chunk[y >> 4] == null) {
			chunk[y >> 4] = new byte[16 * 16 * 16];
		}
		if (!(y <= 256 && y >= 0 && x <= 16 && x >= 0 && z <= 16 && z >= 0)) {
			return;
		}
		try {
			chunk[y >> 4][((y & 0xF) << 8) | (z << 4) | x] = (byte) material.getId();
		} catch (Exception e) {
			// do nothing
		}
	}
	
	public ChunkData generateChunkData​(World world, Random rand, int ChunkX, int ChunkZ, BiomeGrid biome) {
		// where we will store our blocks
		ChunkData chunk = createChunkData(world);

		SimplexOctaveGenerator overhangs = new SimplexOctaveGenerator(world, 8);
		SimplexOctaveGenerator bottoms = new SimplexOctaveGenerator(world, 8);

		overhangs.setScale(1 / 64.0); // little note: the .0 is VERY important
		bottoms.setScale(1 / 128.0);

		int overhangsMagnitude = 16; // used when we generate the noise for the tops of the overhangs
		int bottomsMagnitude = 32;

		for (int x = 0; x < 16; x++) {
			for (int z = 0; z < 16; z++) {
				int realX = x + ChunkX * 16;
				int realZ = z + ChunkZ * 16;

				int bottomHeight = (int) (bottoms.noise(realX, realZ, 0.5, 0.5) * bottomsMagnitude + 64);
				int maxHeight = (int) overhangs.noise(realX, realZ, 0.5, 0.5) * overhangsMagnitude + bottomHeight + 32;
				double threshold = 0.3;

				// make the terrain
				for (int y = 0; y < maxHeight; y++) {
					if (y > bottomHeight) { // part where we do the overhangs
						double density = overhangs.noise(realX, y, realZ, 0.5, 0.5);

						if (density > threshold) {
							chunk.setBlock(x, y, z, Material.STONE);
						}

					} else {
						chunk.setBlock(x, y, z, Material.STONE);
					}
				}

				// turn the tops into grass
				chunk.setBlock(x, bottomHeight, z, Material.GRASS_BLOCK); // the top of the base hills
				chunk.setBlock(x, bottomHeight - 1, z, Material.DIRT);
				chunk.setBlock(x, bottomHeight - 2, z, Material.DIRT);

				for (int y = bottomHeight + 1; y > bottomHeight && y < maxHeight; y++) { // the overhang
					Material thisblock = chunk.getBlockData(x, y, z).getMaterial();
					Material blockabove = chunk.getBlockData(x, y + 1, z).getMaterial();

					if (thisblock != Material.AIR && blockabove == Material.AIR) {
						chunk.setBlock(x, y, z, Material.GRASS_BLOCK);
						if (chunk.getBlockData(x, y - 1, z).getMaterial() != Material.AIR) {
							chunk.setBlock(x, y - 1, z, Material.DIRT);
						}
						if (chunk.getBlockData(x, y - 2, z).getMaterial() != Material.AIR) {
							chunk.setBlock(x, y - 2, z, Material.DIRT);
						}
					}
				}

			}
		}
		return chunk;
	}

	/**
	 * Returns a list of all of the block populators (that do "little" features) to
	 * be called after the chunk generator
	 *
	 * @param world
	 * @return
	 */
	@Override
	public List<BlockPopulator> getDefaultPopulators(World world) {
		ArrayList<BlockPopulator> pops = new ArrayList<BlockPopulator>();
		// Add Block populators here
		return pops;
	}
}
