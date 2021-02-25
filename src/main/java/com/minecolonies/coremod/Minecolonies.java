package com.minecolonies.coremod;

import com.minecolonies.apiimp.CommonMinecoloniesAPIImpl;
import com.minecolonies.coremod.colony.*;
import com.minecolonies.coremod.colony.buildings.registry.GuardTypeDataManager;
import com.minecolonies.coremod.colony.interactionhandling.registry.InteractionResponseHandlerManager;
import com.minecolonies.coremod.colony.jobs.registry.JobDataManager;
import com.minecolonies.coremod.entity.ai.registry.MobAIRegistry;
import com.minecolonies.coremod.entity.pathfinding.registry.PathNavigateRegistry;
import com.minecolonies.coremod.research.GlobalResearchTree;
import com.minecolonies.coremod.util.ModIdentifier;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.block.*;
import net.minecraft.item.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Minecolonies implements ModInitializer
{

    public static Logger LOGGER = LogManager.getLogger();

    public static final String MOD_ID = "minecolonies";
    public static final String MOD_NAME = "Minecolonies";

    public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(new ModIdentifier(MOD_ID),
            () -> new ItemStack(Blocks.BARREL));

    @Override
    public void onInitialize()
    {
        log("Initializing");
        MinecoloniesBlocks.registerBlocks();
        MinecoloniesItems.registerItems();
        MinecoloniesWorld.registerWorldGen();
    }

    public static void log(String message)
    {
        LOGGER.log(Level.INFO, "[" + MOD_NAME + "] " + message);
    }

    public static void logWarn(String message)
    {
        LOGGER.log(Level.WARN, "[" + MOD_NAME + "] " + message);
    }

    public static void logError(String message)
    {
        LOGGER.log(Level.ERROR, "[" + MOD_NAME + "] " + message);
    }

}