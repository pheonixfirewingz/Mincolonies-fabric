package cf.pheonixfirewingz.minecolonies.blocks.entity;

import cf.pheonixfirewingz.minecolonies.*;
import cf.pheonixfirewingz.minecolonies.blocks.abstracts.ABlockBarrel;
import cf.pheonixfirewingz.minecolonies.blocks.abstracts.entity.ATEBarrel;
import cf.pheonixfirewingz.minecolonies.utils.*;
import net.minecraft.block.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraft.nbt.*;
import net.minecraft.network.*;
import net.minecraft.network.packet.s2c.play.*;
import net.minecraft.text.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import org.jetbrains.annotations.Nullable;

public class TEBarrel extends ATEBarrel
{
	/**
	 * True if the barrel has finished composting and the items are ready to harvest
	 */
	private              boolean done          = false;
	/**
	 * The number of items that the barrel contains
	 */
	private              int     items         = 0;
	/**
	 * The timer for the composting process
	 */
	private              int     timer         = 0;
	/**
	 * The number the timer has to reach to finish composting. Number of Minecraft ticks in 2 whole days
	 */
	private static final int     TIMER_END     = 24000;
	/**
	 * The average of ticks that passes between actually ticking the tileEntity
	 */
	private static final int     AVERAGE_TICKS = 20;

	public TEBarrel(BlockPos pos,BlockState state)
	{
		super(MinecoloniesBlocks.barrel_entity_block,pos,state);
	}


/*TODO replace with fabric functions
	/**
	 * Update method to be called by Minecraft every tick
	 *
	@Override
	public void tick()
	{
		final World world = this.getWorld();

		if (!world.isClient && (world.getTime()) % (world.random.nextInt(AVERAGE_TICKS * 2) + 1) == 0))
		{
			this.updateTick(world, this.getPos(), world.getBlockState(this.getPos()), new Random());
		}
	}

	/**
	 * Method that does compost ticks if needed or spawns particles if finished
	 *
	 * @param worldIn the world
	 * @param pos     the position
	 * @param state   the state of the block
	 * @param rand    Random class
	 *
	public void updateTick(final World worldIn, final BlockPos pos, final BlockState state, final Random rand)
	{
		if (getItems() == ATEBarrel.MAX_ITEMS)
		{
			doBarrelCompostTick(worldIn, pos, state);
		}
		if (this.done)
		{
			((ServerWorld) worldIn).spawnParticle(
					ParticleTypes.HAPPY_VILLAGER, this.getPos().getX() + 0.5,
					this.getPos().getY() + 1.5, this.getPos().getZ() + 0.5,
					1, 0.2, 0, 0.2, 0);
		}
	}*/

	private void doBarrelCompostTick(final World worldIn, final BlockPos pos, final BlockState blockState)
	{
		timer++;
		if (timer >= TIMER_END / AVERAGE_TICKS)
		{
			timer = 0;
			items = 0;
			done = true;
			this.updateBlock(worldIn);
		}
	}

	/**
	 * Method called when a player uses the block. Takes the needed itmes from the player if needed.
	 *
	 * @param playerIn  the player
	 * @param itemstack the itemStack on the hand of the player
	 * @return if the barrel took any item
	 */
	public boolean useBarrel(final PlayerEntity playerIn, final ItemStack itemstack)
	{
		if (done)
		{
			playerIn.inventory.addItemStackToInventory(new ItemStack(MinecoloniesItems.compost, 6));
			done = false;
			return true;
		}

		if (!checkCorrectItem(itemstack)) return false;

		if (items == ATEBarrel.MAX_ITEMS)
		{
			playerIn.sendMessage(new TranslatableText("entity.barrel.working"), playerIn.getUuid());
			return false;
		}
		else
		{
			this.consumeNeededItems(itemstack);
			return true;
		}
	}

	private void consumeNeededItems(final ItemStack itemStack)
	{
		//Saplings and seeds counts as 1 item added, the rest counts as 2 items
		final int factor = itemStack.getItem().getTranslationKey().toString().contains("sapling")
				|| itemStack.getItem().getTranslationKey().toString().contains("seed") ? 2 : 4;

		//The available items the player has in his hand (Rotten Flesh counts as the double)
		final int availableItems = itemStack.getCount() * factor;
		//The items we need to complete the barrel
		final int neededItems = ATEBarrel.MAX_ITEMS - items;
		//The quantity of items that we are going to take from the player
		int itemsToRemove = Math.min(neededItems, availableItems);

		//We update the quantities in the player´s inventory and in the barrel
		this.items = this.items + itemsToRemove;
		itemsToRemove = itemsToRemove / factor;
		ItemStackUtils.changeSize(itemStack, -itemsToRemove);
	}

	public static boolean checkCorrectItem(final ItemStack itemStack)
	{
		return IColonyManager.getInstance().getCompatibilityManager().isCompost(itemStack);
	}

	/**
	 * Updates the block between the server and the client
	 *
	 * @param worldIn the world
	 */
	public void updateBlock(final World worldIn)
	{
		final BlockState barrel = world.getBlockState(pos);
		if (barrel.getBlock() == MinecoloniesBlocks.barrel_block)
		{
			worldIn.setBlockState(pos, ABlockBarrel.changeStateOverFullness(this, barrel));
			markDirty();
		}
	}

	@Override public CompoundTag writeNbt(CompoundTag compound)
	{
		super.writeNbt(compound);

		compound.putInt("items", this.items);
		compound.putInt("timer", this.timer);
		compound.putBoolean("done", this.done);

		return compound;
	}

	@Override
	public void readNbt(final CompoundTag compound)
	{
		super.readNbt(compound);
		this.items = compound.getInt("items");
		this.timer = compound.getInt("timer");
		this.done = compound.getBoolean("done");
	}

	@Nullable @Override public BlockEntityUpdateS2CPacket toUpdatePacket()
	{
		final CompoundTag compound = new CompoundTag();
		this.writeNbt(compound);
		return new BlockEntityUpdateS2CPacket(this.pos, 0, compound);
	}

	@Override public CompoundTag toInitialChunkDataNbt()
	{
		return writeNbt(new CompoundTag());
	}

	@Override
	public void onDataPacket(final ClientConnection net, final BlockEntityUpdateS2CPacket packet)
	{
		final CompoundTag compound = packet.getCompoundTag();
		this.readNbt(compound);
		markDirty();
	}

	@Override
	public void markDirty()
	{
		WorldUtil.markChunkDirty(world, pos);
	}

	@Override
	public final void handleUpdateTag(final BlockState state, final CompoundTag tag)
	{
		this.items = tag.getInt("items");
		this.timer = tag.getInt("timer");
		this.done = tag.getBoolean("done");
	}

	/**
	 * Returns the number of items that the block contains
	 *
	 * @return the number of items
	 */
	@Override
	public int getItems()
	{
		return items;
	}

	/**
	 * Returns if the barrel has finished composting
	 *
	 * @return true if done, false if not
	 */
	@Override
	public boolean isDone()
	{
		return this.done;
	}

	//INTERFACE WITH AI PAWNS

	/***
	 * Checks if the barrel is composting
	 * @return true if the number of items is equal to the maximum. If not, false.
	 */
	@Override
	public boolean checkIfWorking()
	{
		return this.items == MAX_ITEMS;
	}

	/***
	 * Lets the AI insert items into the barrel.
	 * @param item the itemStack to be placed inside it.
	 * @return false if the item couldn't be cosumed. True if it could
	 */
	@Override
	public boolean addItem(final ItemStack item)
	{
		if (checkCorrectItem(item) && this.items < MAX_ITEMS)
		{
			this.consumeNeededItems(item);
			this.updateBlock(this.world);
			return true;
		}
		return false;
	}

	/***
	 * Lets the AI retrieve the compost when the barrel has done processing it.
	 * @return The generated compost. If the barrel is not ready yet to be harvested, it will return an empty itemStack.
	 */
	@Override public ItemStack retrieveCompost(double multiplier)
	{
		if (this.done)
		{
			this.done = false;
			this.updateBlock(this.world);
			return new ItemStack(MinecoloniesItems.compost, (int) (6 * multiplier));
		}
		return ItemStack.EMPTY;
	}
}

