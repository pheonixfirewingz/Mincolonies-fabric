package com.minecolonies.coremod.blocks;

import com.minecolonies.api.blocks.AbstractBlockMinecolonies;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.Material;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.Direction;
import org.jetbrains.annotations.NotNull;

import static com.minecolonies.api.util.constant.Suppression.DEPRECATION;

public class BlockBarracksTowerSubstitution extends AbstractBlockMinecolonies<BlockBarracksTowerSubstitution>
{

    /**
     * Our Substitution bock's Facing.
     */
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;

    /**
     * The hardness this block has.
     */
    private static final float BLOCK_HARDNESS = 0.0F;

    /**
     * This blocks name.
     */
    private static final String BLOCK_NAME = "blockbarrackstowersubstitution";

    /**
     * The resistance this block has.
     */
    private static final float RESISTANCE = 1F;

    /**
     * Constructor for the Substitution block. sets the creative tab, as well as the resistance and the hardness.
     */
    public BlockBarracksTowerSubstitution() {
        super(Settings.of(Material.WOOD).strength(BLOCK_HARDNESS, RESISTANCE));
        this.setDefaultState(this.getDefaultState().with(FACING, Direction.NORTH));
    }

    /**
     * Convert the BlockState into the correct metadata value.
     *
     * @deprecated (Remove this as soon as minecraft offers anything better).
     */
    @SuppressWarnings(DEPRECATION)
    @NotNull
    @Override
    @Deprecated
    public BlockState rotate(@NotNull final BlockState state, final BlockRotation rot)
    {
        return state.with(FACING, rot.rotate(state.get(FACING)));
    }

    /**
     * @deprecated (Remove this as soon as minecraft offers anything better).
     */
    @SuppressWarnings(DEPRECATION)
    @NotNull
    @Override
    @Deprecated
    public BlockState mirror(@NotNull final BlockState state, final BlockMirror mirrorIn)
    {
        return state.rotate(mirrorIn.getRotation(state.get(FACING)));
    }

    @Override
    public BlockState getPlacementState(final ItemPlacementContext context)
    {
        @NotNull final Direction direction = (context.getPlayer() == null) ? Direction.NORTH : Direction.fromRotation(context.getPlayer().yaw);
        return this.getDefaultState().with(FACING, direction);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder)
    {
        builder.add(FACING);
    }
}
