package cf.pheonixfirewingz.minecolonies.blocks.abstracts;

import cf.pheonixfirewingz.minecolonies.blocks.abstracts.entity.ATEBarrel;
import cf.pheonixfirewingz.minecolonies.blocks.types.BarrelType;
import net.minecraft.block.*;
import net.minecraft.state.property.*;
import org.jetbrains.annotations.NotNull;

public abstract class ABlockBarrel<B extends ABlockBarrel<B>> extends Block
{
	public static final EnumProperty<BarrelType> VARIANT = EnumProperty.of("variant", BarrelType.class);

	/**
	 * The position it faces.
	 */
	public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;

	public ABlockBarrel(Settings settings)
	{
		super(settings);
	}

	public static BlockState changeStateOverFullness(@NotNull final ATEBarrel te, @NotNull final BlockState blockState)
	{
		/*
		 * 12.8 -> the number of items needed to go up on a state (having 6 filling states)
		 * So items/12.8 -> meta of the state we should get
		 */
		BarrelType type = BarrelType.byMetadata((int) Math.round(te.getItems() / 12.8));

		/*
		 * We check if the barrel is marked as empty but it have items inside. If so, means that it
		 * does not have all the items needed to go on TWENTY state, but we need to mark it so the player
		 * knows it have some items inside
		 */

		if (type.equals(BarrelType.ZERO) && te.getItems() > 0)
		{
			type = BarrelType.TWENTY;
		}
		else if (te.getItems() == ATEBarrel.MAX_ITEMS)
		{
			type = BarrelType.WORKING;
		}
		if (te.isDone())
		{
			type = BarrelType.DONE;
		}

		return blockState.with(ABlockBarrel.VARIANT, type).with(ABlockBarrel.FACING, blockState.get(ABlockBarrel.FACING));
	}
}

