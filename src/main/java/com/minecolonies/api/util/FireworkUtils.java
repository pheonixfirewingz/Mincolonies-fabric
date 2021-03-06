package com.minecolonies.api.util;

import net.minecraft.entity.projectile.*;
import net.minecraft.item.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;

import java.util.*;
import java.util.stream.Collectors;

import static com.minecolonies.api.util.constant.NbtTagConstants.*;

/**
 * Utility class for summoning in fireworks.
 */

public final class FireworkUtils
{
	/**
	 * Private constructor to hide the public one
	 */
	private FireworkUtils()
	{

	}

	/**
	 * Spawns in a given number of fireworks at the corners of a given AABB in a given world
	 *
	 * @param realaabb       AABB of the building
	 * @param world          which world to spawn it in from
	 * @param explosionLevel how many fireworks to spawn in each corner
	 */
	public static void spawnFireworksAtAABBCorners(final Tuple<BlockPos, BlockPos> realaabb, final World world, final int explosionLevel)
	{
		final FireworkRocketEntity firework = new FireworkRocketEntity(world, realaabb.getB().getX(), realaabb.getB().getY(), realaabb.getB().getZ(), genFireworkItemStack(explosionLevel));
		world.addEntity(firework);

		final FireworkRocketEntity fireworka = new FireworkRocketEntity(world, realaabb.getB().getX(), realaabb.getB().getY(), realaabb.getA().getZ(), genFireworkItemStack(explosionLevel));
		world.addEntity(fireworka);

		final FireworkRocketEntity fireworkb = new FireworkRocketEntity(world, realaabb.getA().getX(), realaabb.getB().getY(), realaabb.getB().getZ(), genFireworkItemStack(explosionLevel));
		world.addEntity(fireworkb);

		final FireworkRocketEntity fireworkc = new FireworkRocketEntity(world, realaabb.getA().getX(), realaabb.getB().getY(), realaabb.getA().getZ(), genFireworkItemStack(explosionLevel));
		world.addEntity(fireworkc);
	}

	/**
	 * Generates random firework with various properties.
	 *
	 * @param explosionAmount the amount of explosions.
	 * @return ItemStack of random firework.
	 */
	private static ItemStack genFireworkItemStack(final int explosionAmount)
	{
		final Random rand = new Random();
		final ItemStack fireworkItem = new ItemStack(Items.FIREWORK_ROCKET);
		final CompoundNBT itemStackCompound = fireworkItem.getTag() != null ? fireworkItem.getTag() : new CompoundNBT();
		final CompoundNBT fireworksCompound = new CompoundNBT();
		final ListNBT explosionsTagList = new ListNBT();
		final List<Integer> dyeColors = Arrays.stream(DyeColor.values()).map(DyeColor::getFireworkColor).collect(Collectors.toList());

		for(int i = 0; i < explosionAmount; i++)
		{
			final CompoundNBT explosionTag = new CompoundNBT();

			explosionTag.putBoolean(TAG_FLICKER, rand.nextInt(2) == 0);
			explosionTag.putBoolean(TAG_TRAIL, rand.nextInt(2) == 0);
			explosionTag.putInt(TAG_TYPE, rand.nextInt(5));

			final int numberOfColours = rand.nextInt(3) + 1;
			final int[] colors = new int[numberOfColours];

			for(int ia = 0; ia < numberOfColours; ia++)
			{
				colors[ia] = dyeColors.get(rand.nextInt(15));
			}
			explosionTag.putIntArray(TAG_COLORS, colors);
			explosionsTagList.add(explosionTag);
		}
		fireworksCompound.put(TAG_EXPLOSIONS, explosionsTagList);
		itemStackCompound.put(TAG_FIREWORKS, fireworksCompound);
		fireworkItem.setTag(itemStackCompound);
		return fireworkItem;
	}
}
