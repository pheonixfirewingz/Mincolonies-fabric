package com.minecolonies.api.colony.buildings.registry;

import com.minecolonies.api.IMinecoloniesAPI;
import net.minecraft.util.registry.Registry;

public interface IBuildingRegistry
{

    static Registry<BuildingEntry> getInstance()
    {
        return IMinecoloniesAPI.getInstance().getBuildingRegistry();
    }
}
