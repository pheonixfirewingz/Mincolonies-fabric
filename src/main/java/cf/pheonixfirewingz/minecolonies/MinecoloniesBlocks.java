package cf.pheonixfirewingz.minecolonies;

import cf.pheonixfirewingz.minecolonies.blocks.*;
import cf.pheonixfirewingz.minecolonies.blocks.entity.TEBarrel;
import cf.pheonixfirewingz.minecolonies.utils.FabricWrapper;
import net.minecraft.block.*;
import net.minecraft.block.entity.*;

public class MinecoloniesBlocks
{
	public static Block barrel_block;
	public static BlockEntityType<TEBarrel> barrel_entity_block;
	public static void registerBlocks()
	{
		barrel_block = FabricWrapper.regBlockWithRet(new BlockBarrel(),"barrel_block");
		barrel_entity_block = FabricWrapper.regBlockEntity(
				BlockEntityType.Builder.create(TEBarrel::new, barrel_block).build(null),"barrel_entity_block");
		FabricWrapper.regBlock(new BlockGate(2,7,9),"gate_wood");
		FabricWrapper.regBlock(new BlockGate(2,7,9),"gate_iron");
	}

}
