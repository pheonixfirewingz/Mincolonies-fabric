package com.minecolonies.api.util;

import net.minecraft.block.*;
import net.minecraft.item.*;
import net.minecraft.tags.*;

/**
 * Class for specific minecraft tag utilities.
 */
public final class TagUtils
{
	private TagUtils()
	{
		throw new IllegalStateException("Tried to initialize: TagUtils but this is a Utility class.");
	}

	/**
	 * Get a tag for items.
	 *
	 * @param resourceLocation the unique id.
	 * @return the tag or an empty placeholder if not existant.
	 */
	public static ITag<Item> getItem(final ResourceLocation resourceLocation)
	{
		return ItemTags.getCollection().getTagByID(resourceLocation);
	}

	/**
	 * Get a tag for items.
	 *
	 * @param resourceLocation the unique id.
	 * @return the tag or an empty placeholder if not existant.
	 */
	public static ITag<Block> getBlock(final ResourceLocation resourceLocation)
	{
		return BlockTags.getCollection().getTagByID(resourceLocation);
	}
}
