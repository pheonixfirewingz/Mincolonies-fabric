package cf.pheonixfirewingz.minecolonies.Items;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tag.*;

public class ModTags
{
    /**
     * Flag to check if tags are already loaded.
     */
    public static boolean tagsLoaded = false;

    public static Tag<Block> decorationItems;
    public static Tag<Item>  concretePowder;
    public static Tag<Block> concreteBlock;
    public static Tag<Block> pathingBlocks;

    public static Tag<Block> colonyProtectionException;
    public static Tag<Block> indestructible;

    public static Tag<Block> oreChanceBlocks;
    public static Tag<Item>  compostables;

    public static Tag<Item> fungi;

    public static Tag<Item>  floristFlowersExcluded;


    public static final Map<String, Tag<Item>> crafterProduct              = new HashMap<>();
    public static final Map<String, Tag<Item>> crafterProductExclusions    = new HashMap<>();
    public static final Map<String, Tag<Item>> crafterIngredient           = new HashMap<>();
    public static final Map<String, Tag<Item>> crafterIngredientExclusions = new HashMap<>();
}