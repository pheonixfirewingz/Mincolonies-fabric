package com.minecolonies.api.blocks.decorative;

import com.minecolonies.api.blocks.interfaces.IBlockMinecolonies;
import com.minecolonies.api.colony.*;
import com.minecolonies.api.tileentities.TileEntityColonyFlag;
import net.minecraft.block.*;
import net.minecraft.entity.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraft.tileentity.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import net.minecraftforge.registries.IForgeRegistry;

import javax.annotation.Nullable;

/**
 * Represents the common functions of both the wall and floor colony flag banner blocks
 */
public class AbstractColonyFlagBanner<B extends AbstractColonyFlagBanner<B>> extends AbstractBannerBlock implements IBlockMinecolonies<AbstractColonyFlagBanner<B>>
{
	public static final String REGISTRY_NAME = "colony_banner";
	public static final String REGISTRY_NAME_WALL = "colony_wall_banner";

	public AbstractColonyFlagBanner()
	{
		super(
				DyeColor.WHITE,
				Properties.create(Material.WOOD)
						.doesNotBlockMovement()
						.hardnessAndResistance(1F)
						.sound(SoundType.WOOD)
		);
	}

	@Override
	public TileEntity createNewTileEntity(IBlockReader worldIn)
	{
		return new TileEntityColonyFlag();
	}

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack)
	{
		if(worldIn.isRemote) return;

		TileEntity te = worldIn.getTileEntity(pos);
		if(te instanceof TileEntityColonyFlag)
		{
			IColony colony = IColonyManager.getInstance().getIColony(worldIn, pos);

			// Allow the player to place their own beyond the colony
			if(colony == null && placer instanceof PlayerEntity)
				IColonyManager.getInstance().getIColonyByOwner(worldIn, (PlayerEntity) placer);

			if(colony != null)
				((TileEntityColonyFlag) te).colonyId = colony.getID();
		}

	}

	@Override
	public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state)
	{
		TileEntity tileentity = worldIn.getTileEntity(pos);
		return tileentity instanceof TileEntityColonyFlag ? ((TileEntityColonyFlag) tileentity).getItem() : super.getItem(worldIn, pos, state);
	}

	@Override
	public AbstractColonyFlagBanner<B> registerBlock(IForgeRegistry<Block> registry)
	{
		registry.register(this);
		return this;
	}

	@Override
	public void registerBlockItem(IForgeRegistry<Item> registry, Item.Properties properties)
	{
		/* Registration occurs in ModItems */
	}
}
