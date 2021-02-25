package com.minecolonies.coremod.colony;

import com.minecolonies.api.colony.*;
import com.minecolonies.api.util.Log;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.PacketByteBuf;
import org.jetbrains.annotations.NotNull;

import static com.minecolonies.api.util.constant.NbtTagConstants.TAG_ID;

public class CitizenDataManager
{
    @Override
    public ICitizenData createFromNBT(@NotNull final CompoundTag compound, final Colony colony)
    {
        final int id = compound.getInt(TAG_ID);
        final @NotNull CitizenData citizen = new CitizenData(id, colony);
        citizen.deserializeNBT(compound);
        return citizen;
    }

    @Override
    public ICitizenDataView createFromNetworkData(final int id, @NotNull final PacketByteBuf networkBuffer, final ColonyView colonyView)
    {
        ICitizenDataView citizenDataView = colonyView.getCitizen(id) == null ? new CitizenDataView(id) : colonyView.getCitizen(id);

        try
        {
            citizenDataView.deserialize(networkBuffer);
        }
        catch (final RuntimeException ex)
        {
            Log.getLogger().error(String.format("A CitizenData.View for #%d has thrown an exception during loading, its state cannot be restored. Report this to the mod author",
              citizenDataView.getId()), ex);
            citizenDataView = null;
        }

        return citizenDataView;
    }
}
