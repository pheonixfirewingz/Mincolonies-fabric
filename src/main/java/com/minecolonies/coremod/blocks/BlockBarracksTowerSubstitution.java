package com.minecolonies.coremod.blocks;

import net.minecraft.block.*;
import net.minecraft.state.property.*;
import net.minecraft.util.math.*;
import org.jetbrains.annotations.NotNull;

import static com.minecolonies.api.util.constant.Suppression.DEPRECATION;

public class BlockBarracksTowerSubstitution extends Block
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
     * The resistance this block has.
     */
    private static final float RESISTANCE = 1F;

    /**
     * Constructor for the Substitution block. sets the creative tab, as well as the resistance and the hardness.
     */
    public BlockBarracksTowerSubstitution()
    {
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
