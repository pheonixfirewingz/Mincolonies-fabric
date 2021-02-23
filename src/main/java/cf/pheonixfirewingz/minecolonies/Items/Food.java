package cf.pheonixfirewingz.minecolonies.Items;

import cf.pheonixfirewingz.minecolonies.utils.MineColoniesItemSettings;
import net.minecraft.item.*;

public class Food extends Item
{
	public Food(int food_count,float sat)
	{
		super(new MineColoniesItemSettings(food_count,sat));
	}
}
