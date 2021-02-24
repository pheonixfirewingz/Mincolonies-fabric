package cf.pheonixfirewingz.minecolonies.blocks;

import cf.pheonixfirewingz.minecolonies.MinecoloniesBlocks;
import cf.pheonixfirewingz.minecolonies.blocks.abstracts.ABlockBarrel;
import cf.pheonixfirewingz.minecolonies.blocks.entity.TEBarrel;
import cf.pheonixfirewingz.minecolonies.blocks.types.BarrelType;
import net.minecraft.block.*;
import net.minecraft.block.entity.*;
import net.minecraft.entity.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraft.state.*;
import net.minecraft.util.*;
import net.minecraft.util.hit.*;
import net.minecraft.util.math.*;
import net.minecraft.util.shape.*;
import net.minecraft.world.*;
import net.minecraft.world.event.listener.*;
import org.jetbrains.annotations.Nullable;

import static net.minecraft.state.property.Properties.*;

public class BlockBarrel extends ABlockBarrel<BlockBarrel> implements BlockEntityProvider
{
	/**
	 * The hardness this block has.
	 */
	private static final float  BLOCK_HARDNESS = 5F;
	/**
	 * The resistance this block has.
	 */
	private static final float  RESISTANCE     = 1F;

	public BlockBarrel()
	{
		super(Settings.of(Material.WOOD).strength(BLOCK_HARDNESS, RESISTANCE));
		this.setDefaultState(this.getDefaultState().with(ABlockBarrel.FACING, Direction.NORTH).with(VARIANT, BarrelType.ZERO));
	}

	@Override protected void appendProperties(StateManager.Builder<Block, BlockState> builder)
	{
		builder.add(FACING, VARIANT, WATERLOGGED);
	}

	@Nullable @Override public BlockEntity createBlockEntity(BlockPos pos, BlockState state)
	{
		return new TEBarrel(pos,state);
	}

	@Nullable @Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type)
	{
		return null;
	}

	@Nullable @Override
	public <T extends BlockEntity> GameEventListener getGameEventListener(World world, T blockEntity)
	{
		return null;
	}


	@Override public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit)
	{
		final ItemStack itemstack = player.getInventory().getMainHandStack();
		final BlockEntity te = world.getBlockEntity(pos);
		if (te instanceof TEBarrel && !world.isClient())
		{
			((TEBarrel) te).useBarrel(player, itemstack);
			((TEBarrel) te).updateBlock(world);
		}

		return ActionResult.SUCCESS;
	}

	@Override public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context)
	{
		return VoxelShapes.cuboid(0D, 0D, 0D, 1D, 1.5D, 1D);
	}

	/**
	 * Convert the BlockState into the correct metadata value.
	 *
	 * @deprecated (Remove this as soon as minecraft offers anything better).
	 */
	@Override public BlockState rotate(BlockState state, BlockRotation rotation)
	{
		return state.with(ABlockBarrel.FACING, rotation.rotate(state.get(ABlockBarrel.FACING)));
	}

	/**
	 * @deprecated (Remove this as soon as minecraft offers anything better).
	 */
	@Override public BlockState mirror(BlockState state, BlockMirror mirror)
	{
		return  state.rotate(mirror.getRotation(state.get(ABlockBarrel.FACING)));
	}

	@Nullable @Override public BlockState getPlacementState(ItemPlacementContext ctx)
	{
		return super.getPlacementState(ctx).with(ABlockBarrel.FACING, ctx.getPlayerFacing());
	}

	/**
	 * @deprecated (Remove this as soon as minecraft offers anything better).
	 */
	@Override public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos)
	{
		return !world.isAir(pos.down()) && world.getBlockState(pos.down()).getBlock() != MinecoloniesBlocks.barrel_block;
	}
}