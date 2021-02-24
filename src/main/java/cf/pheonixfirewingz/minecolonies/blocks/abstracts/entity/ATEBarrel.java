package cf.pheonixfirewingz.minecolonies.blocks.abstracts.entity;

import net.minecraft.block.*;
import net.minecraft.block.entity.*;
import net.minecraft.item.*;
import net.minecraft.util.math.*;

public abstract class ATEBarrel extends BlockEntity
{
	/**
	 * The number of items it needs to start composting
	 */
	public static final int MAX_ITEMS = 64;

	public ATEBarrel(BlockEntityType<?> type, BlockPos pos, BlockState state)
	{
		super(type, pos, state);
	}

	/**
	 * Returns the number of items that the block contains
	 *
	 * @return the number of items
	 */
	public abstract int getItems();

	public abstract boolean isDone();

	public abstract boolean checkIfWorking();

	public abstract boolean addItem(ItemStack item);

	public abstract ItemStack retrieveCompost(double multiplier);
}
