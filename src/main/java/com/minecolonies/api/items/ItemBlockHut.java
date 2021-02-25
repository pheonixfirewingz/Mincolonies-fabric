package com.minecolonies.api.items;

import com.minecolonies.api.blocks.*;
import com.minecolonies.api.colony.IColonyView;
import com.minecolonies.api.util.constant.TranslationConstants;
import net.minecraft.client.util.*;
import net.minecraft.item.*;
import net.minecraft.util.text.*;
import net.minecraft.world.*;
import net.minecraftforge.api.distmarker.*;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * A custom item class for hut blocks.
 */
public class ItemBlockHut extends BlockItem
{

	/**
	 * This items block.
	 */
	private final AbstractBlockHut<?> block;

	/**
	 * Creates a new ItemBlockHut representing the item form of the given {@link AbstractBlockHut}.
	 *
	 * @param block   the {@link AbstractBlockHut} this item represents.
	 * @param builder the item properties to use.
	 */
	public ItemBlockHut(AbstractBlockHut<?> block, Properties builder)
	{
		super(block, builder);
		this.block = block;
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void addInformation(@NotNull ItemStack stack, World worldIn, @NotNull List<ITextComponent> tooltip, @NotNull ITooltipFlag flagIn)
	{
		super.addInformation(stack, worldIn, tooltip, flagIn);
		if(block.needsResearch())
		{
			tooltip.add(new TranslationTextComponent(TranslationConstants.HUT_NEEDS_RESEARCH_TOOLTIP_1, block.getTranslatedName(), block.getName()));
			tooltip.add(new TranslationTextComponent(TranslationConstants.HUT_NEEDS_RESEARCH_TOOLTIP_2, block.getTranslatedName(), block.getName()));
		}
	}

	/**
	 * Checks whether this hut is needs to be researched in this colony.
	 *
	 * @param colony the colony to check.
	 */
	@OnlyIn(Dist.CLIENT)
	public static void checkResearch(final IColonyView colony)
	{
		for(AbstractBlockHut<?> hut : ModBlocks.getHuts())
		{
			hut.checkResearch(colony);
		}
	}

}
