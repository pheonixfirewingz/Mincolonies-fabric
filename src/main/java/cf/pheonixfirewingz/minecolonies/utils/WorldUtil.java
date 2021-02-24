package cf.pheonixfirewingz.minecolonies.utils;

import net.minecraft.block.BlockState;
import net.minecraft.server.world.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.util.registry.*;
import net.minecraft.world.*;
import net.minecraft.world.chunk.*;
import net.minecraft.world.dimension.*;
import org.jetbrains.annotations.*;

import static cf.pheonixfirewingz.minecolonies.utils.constants.CitizenConstants.NIGHT;

/**
 * Class which has world related util functions like chunk load checks
 */
public class WorldUtil
{
	/**
	 * Checks if the block is loaded for block access
	 *
	 * @param world world to use
	 * @param pos   position to check
	 * @return true if block is accessible/loaded
	 */
	public static boolean isBlockLoaded(final World world, final BlockPos pos)
	{
		return isChunkLoaded(world, pos.getX() >> 4, pos.getZ() >> 4);
	}

	/**
	 * Returns whether a chunk is fully loaded
	 *
	 * @param world world to check on
	 * @param x     chunk position
	 * @param z     chunk position
	 * @return true if loaded
	 */
	public static boolean isChunkLoaded(final World world, final int x, final int z)
	{
		return world.getChunk(x, z, ChunkStatus.FULL, false) != null;
	}

	/**
	 * Mark a chunk at a position dirty if loaded.
	 *
	 * @param world the world to mark it dirty in.
	 * @param pos   the position within the chunk.
	 */
	public static void markChunkDirty(final World world, final BlockPos pos)
	{
		if (WorldUtil.isBlockLoaded(world, pos))
		{
			world.getChunk(pos.getX() >> 4, pos.getZ() >> 4).markDirty();
			final BlockState state = world.getBlockState(pos);
			world.updateListeners(pos, state, state, 3);
		}
	}

	/**
	 * Returns whether a chunk is fully loaded
	 *
	 * @param world world to check on
	 * @param pos   chunk position
	 * @return true if loaded
	 */
	public static boolean isChunkLoaded(final World world, final ChunkPos pos)
	{
		return isChunkLoaded(world, pos.x, pos.z);
	}

	/**
	 * Checks if the block is loaded for ticking entities(not all chunks tick entities)
	 *
	 * @param world world to use
	 * @param pos   position to check
	 * @return true if block is accessible/loaded
	 */
	public static boolean isEntityBlockLoaded(final World world, final BlockPos pos)
	{
		return isEntityChunkLoaded(world, pos.getX() >> 4, pos.getZ() >> 4);
	}

	/**
	 * Returns whether an entity ticking chunk is loaded at the position
	 *
	 * @param world world to check on
	 * @param x     chunk position
	 * @param z     chunk position
	 * @return true if loaded
	 */
	public static boolean isEntityChunkLoaded(final World world, final int x, final int z)
	{
		return isEntityChunkLoaded(world, new ChunkPos(x, z));
	}

	/**
	 * Returns whether an entity ticking chunk is loaded at the position
	 *
	 * @param world world to check on
	 * @param pos   chunk position
	 * @return true if loaded
	 */
	public static boolean isEntityChunkLoaded(final World world, final ChunkPos pos)
	{
		return world.getChunkManager().isChunkLoaded(pos.x, pos.z);
	}

	/**
	 * Returns whether an axis aligned bb is entirely loaded.
	 *
	 * @param world world to check on.
	 * @param box   the box.
	 * @return true if loaded.
	 */
	public static boolean isAABBLoaded(final World world, final Box box)
	{
		return isChunkLoaded(world, ((int) box.minX) >> 4, ((int) box.minZ) >> 4) && isChunkLoaded(world, ((int) box.maxX) >> 4, ((int) box.maxZ) >> 4);
	}

	/**
	 * Check if it's currently day inn the world.
	 *
	 * @param world the world to check.
	 * @return true if so.
	 */
	public static boolean isDayTime(final World world)
	{
		return world.getTimeOfDay() % 24000 <= NIGHT;
	}

	/**
	 * Check if it's currently day inn the world.
	 *
	 * @param world the world to check.
	 * @return true if so.
	 */
	public static boolean isPastTime(final World world, final int pastTime)
	{
		return world.getTimeOfDay() % 24000 <= pastTime;
	}

	/**
	 * Check if a world is of the overworld type.
	 *
	 * @param world the world to check.
	 * @return true if so.
	 */
	public static boolean isOverworldType(@NotNull final World world)
	{
		return isOfWorldType(world, DimensionType.OVERWORLD_REGISTRY_KEY);
	}

	/**
	 * Check if a world is of the nether type.
	 *
	 * @param world the world to check.
	 * @return true if so.
	 */
	public static boolean isNetherType(@NotNull final World world)
	{
		return isOfWorldType(world, DimensionType.THE_NETHER_REGISTRY_KEY);
	}

	/**
	 * Check if a world has a specific dimension type.
	 *
	 * @param world the world to check.
	 * @param type  the type to compare.
	 * @return true if it matches.
	 */
	public static boolean isOfWorldType(@NotNull final World world, @NotNull final RegistryKey<DimensionType> type)
	{
		DynamicRegistryManager dynRegistries = world.getRegistryManager();
		//TODO: fabric not in map?
		Identifier loc =  null;//dynRegistries.func_230520_a_().getKey(world.getDimensionType());
		if (loc == null)
		{
			//todo Remove this line once forge fixes this.
			if (world.isClient())
				return world.getDimension().getSkyProperties().equals(type.getValue());

			return false;
		}
		RegistryKey<DimensionType> regKey = RegistryKey.of(Registry.DIMENSION_TYPE_KEY, loc);
		return regKey == type;
	}

	/**
	 * Check to see if the world is peaceful.
	 * <p>
	 * There are several checks performed here, currently both gamerule and difficulty.
	 *
	 * @param world world to check
	 * @return true if peaceful
	 */
	public static boolean isPeaceful(@NotNull final World world)
	{
		return !world.getLevelProperties().getGameRules().getBoolean(GameRules.DO_MOB_SPAWNING) || world.getDifficulty().equals(Difficulty.PEACEFUL);
	}

	/**
	 * Custom set block state, with 1 instead of default flag 3, to skip vanilla's path notify upon block change, making setBlockState expensive. The state change still affects
	 * neighbours and is synced
	 *
	 * @param world world to use
	 * @param pos   position to set
	 * @param state state to set
	 */
	public static boolean setBlockState(final World world, final BlockPos pos, final BlockState state)
	{
		if (world.isClient()) return world.setBlockState(pos, state, 3);

		return setBlockState(world, pos, state, 3);
	}

	/**
	 * Custom set block state, skips vanilla's path notify upon block change, making setBlockState expensive.
	 *
	 * @param world world to use
	 * @param pos   position to set
	 * @param state state to set
	 * @param flags flags to use
	 */
	public static boolean setBlockState(final World world, final BlockPos pos, final BlockState state, int flags)
	{
		if(world.isClient())
			return world.setBlockState(pos, state, flags);

		if((flags & 2) != 0)
		{
			flags -= 2;
			((ServerWorld) world).getChunkManager().markForUpdate(pos);
		} else return world.setBlockState(pos, state, flags);

		return false;
	}

	/**
	 * Get all entities within a building.
	 *
	 * @param world     the world to check this for.
	 * @param clazz     the entity class.
	 * @param building  the building to check the range for.
	 * @param predicate the predicate to check
	 * @param <T>       the type of the predicate.
	 * @return a list of all within those borders.
	 */
//TODO: fix for build system
	/*	public static <T extends Entity> List<T> getEntitiesWithinBuilding(
			final @NotNull World world,
			final @NotNull Class<? extends T> clazz,
			final @NotNull IBuilding building,
			@Nullable final Predicate<? super T> predicate)
	{
		final Tuple<BlockPos, BlockPos> corners = building.getCorners();

		int minX = corners.getA().getX() >> 4;
		int maxX = corners.getB().getX() >> 4;
		int minZ = corners.getA().getZ() >> 4;
		int maxZ = corners.getB().getZ() >> 4;
		int minY = corners.getA().getY() >> 4;
		int maxY = corners.getB().getY() >> 4;

		List<T> list = Lists.newArrayList();
		ChunkManager abstractchunkprovider = world.getChunkManager();

		for (int x = minX; x <= maxX; ++x)
		{
			for (int z = minZ; z <= maxZ; ++z)
			{
				if (isEntityChunkLoaded(world, x, z))
				{
					Chunk chunk = abstractchunkprovider.getWorldChunk(x, z);
					if (chunk != null)
					{
						for (int y = minY; y <= maxY; y++)
						{
							for (final T entity : chunk.getEntityLists()[y].getByClass(clazz))
							{
								if (building.isInBuilding(entity.getPos()) && (predicate == null || predicate.test(entity)))
								{
									list.add(entity);
								}
							}
						}
					}
				}
			}
		}

		return list;
	}*/
}
