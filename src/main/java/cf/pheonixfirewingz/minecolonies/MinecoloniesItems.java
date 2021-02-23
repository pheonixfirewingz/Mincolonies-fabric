package cf.pheonixfirewingz.minecolonies;


import cf.pheonixfirewingz.minecolonies.Items.*;
import cf.pheonixfirewingz.minecolonies.utils.*;
import net.minecraft.entity.*;
import net.minecraft.item.*;

public class MinecoloniesItems
{
	private static final ModArmorMaterial pirate = new ModArmorMaterial(new int[]{13, 15, 16, 11},
																		new int[]{1,1,1,1},25,
																"pirate");
	public static Item supplyChest;
	public static Item permTool;
	public static Item scepterGuard;
	public static Item bannerRallyGuards;
	public static Item supplyCamp;
	public static Item ancientTome;
	public static Item chiefSword;
	public static Item scimitar;
	public static Item scepterLumberjack;
	public static Item pharaoscepter;
	public static Item firearrow;
	public static Item scepterBeekeeper;

	public static Item clipboard;
	public static Item compost;
	public static Item resourceScroll;

	public static Item pirateHelmet_1;
	public static Item pirateChest_1;
	public static Item pirateLegs_1;
	public static Item pirateBoots_1;

	public static Item pirateHelmet_2;
	public static Item pirateChest_2;
	public static Item pirateLegs_2;
	public static Item pirateBoots_2;

	public static Item santaHat;

	public static Item flagBanner;
	public static Item irongate;
	public static Item woodgate;

	public static Item scrollColonyTP;
	public static Item scrollColonyAreaTP;
	public static Item scrollBuff;
	public static Item scrollGuardHelp;
	public static Item scrollHighLight;

	public static void registerItems()
	{
		FabricWrapper.regItem(new ArmorItem(pirate, EquipmentSlot.HEAD,new MineColoniesItemSettings()),"pirate_hat");
		FabricWrapper.regItem(new ArmorItem(pirate, EquipmentSlot.HEAD,new MineColoniesItemSettings()),"pirate_cap");
		FabricWrapper.regItem(new ArmorItem(pirate, EquipmentSlot.CHEST,new MineColoniesItemSettings()),"pirate_chest");
		FabricWrapper.regItem(new ArmorItem(pirate, EquipmentSlot.CHEST,new MineColoniesItemSettings()),"pirate_top");
		FabricWrapper.regItem(new ArmorItem(pirate, EquipmentSlot.LEGS,new MineColoniesItemSettings()),"pirate_leggins");
		FabricWrapper.regItem(new ArmorItem(pirate, EquipmentSlot.LEGS,new MineColoniesItemSettings()),"pirate_legs");
		FabricWrapper.regItem(new ArmorItem(pirate, EquipmentSlot.FEET,new MineColoniesItemSettings()),"pirate_shoes");
		FabricWrapper.regItem(new ModItem(),"bread_dough");
		FabricWrapper.regItem(new ModItem(),"cake_batter");
		FabricWrapper.regItem(new Food(0,0),"cookie_dough");
		FabricWrapper.regItem(new Food(0,0),"raw_pumpkin_pie");
		FabricWrapper.regItem(new Food(0,0),"milky_bread");
		FabricWrapper.regItem(new Food(0,0),"sugary_bread");
		FabricWrapper.regItem(new Food(0,0),"golden_bread");
		FabricWrapper.regItem(new Food(0,0),"chorus_bread");
		FabricWrapper.regItem(new BuildWand(),"sceptergold");
	}
}
