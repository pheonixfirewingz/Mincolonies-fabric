package com.minecolonies.api.colony.buildings.views;

import com.minecolonies.api.IMinecoloniesAPI;
import net.minecraft.entity.*;
import net.minecraft.nbt.*;
import net.minecraft.network.*;
import net.minecraft.util.*;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

/**
 * Used by the guards for information about Mobs.
 */
public class MobEntryView
{
    /**
     * The priority for attacking this mob.
     */
    private int priority;

    /**
     * The ResourceLocation of the mob.
     */
    private Identifier location;

    /**
     * Whether the guards should attack this mob
     */
    private boolean shouldAttack;

    /**
     * Public constructor of the WorkOrderView.
     *
     * @param location     the location.
     * @param priority     the mob priority.
     * @param shouldAttack if it should be attacked or not.
     */
    public MobEntryView(final Identifier location, final Boolean shouldAttack, final Integer priority)
    {
        this.location = location;
        this.shouldAttack = shouldAttack;
        this.priority = priority;
    }

    /**
     * Writes the Location, Attack, and Priority to a {@link PacketByteBuf}.
     *
     * @param buf   Buf to write to.
     * @param entry Entry to write.
     */
    public static void writeToByteBuf(@NotNull final PacketByteBuf buf, @NotNull final MobEntryView entry)
    {
        buf.writeString(entry.getLocation().toString());
        buf.writeBoolean(entry.shouldAttack());
        buf.writeInt(entry.getPriority());
    }

    /**
     * Reads the Location, Attack, and Priority from a {@link PacketByteBuf} to create a MobEntryView
     *
     * @param buf Buf to read from.
     * @return MobEntryView that was created.
     */
    @NotNull
    public static MobEntryView readFromByteBuf(@NotNull final PacketByteBuf buf)
    {
        final Identifier location = new Identifier(buf.readString(32767));
        final Boolean attack = buf.readBoolean();
        final Integer priority = buf.readInt();

        return new MobEntryView(location, attack, priority);
    }

    /**
     * Writes the Location, Attack, and Priority to NBT
     *
     * @param compound Compound to write to.
     * @param name     Name of the tag.
     * @param entry    the View to write
     */
    public static void write(@NotNull final CompoundTag compound, final String name, @NotNull final MobEntryView entry)
    {
        @NotNull final CompoundTag coordsCompound = new CompoundTag();
        coordsCompound.putString("location", entry.getLocation().toString());
        coordsCompound.putBoolean("attack", entry.shouldAttack());
        coordsCompound.putInt("priority", entry.getPriority());
        compound.put(name, coordsCompound);
    }

    /**
     * Reads the Location, Attack, and Priority from nbt to create a MobEntryView
     *
     * @param compound Compound to read data from.
     * @param name     Tag name to read data from.
     * @return The new MobEntryView
     */
    @NotNull
    public static MobEntryView read(@NotNull final CompoundTag compound, final String name)
    {
        final CompoundTag entryCompound = compound.getCompound(name);
        final Identifier location = new Identifier(entryCompound.getString("location"));
        final Boolean attack = entryCompound.getBoolean("attack");
        final Integer priority = entryCompound.getInt("priority");
        return new MobEntryView(location, attack, priority);
    }

    /**
     * Priority getter.
     *
     * @return the priority.
     */
    public int getPriority()
    {
        return priority;
    }

    /**
     * Setter for the priority.
     *
     * @param priority the new priority attribute.
     */
    public void setPriority(final int priority)
    {
        this.priority = priority;
    }

    /**
     * location getter.
     *
     * @return the priority.
     */
    public Identifier getLocation()
    {
        return location;
    }

    /**
     * Setter for the location.
     *
     * @param location the new location attribute.
     */
    public void setLocation(final Identifier location)
    {
        this.location = location;
    }

    /**
     * Returns if the guard should attack this mob
     *
     * @return true if guard should attack this.
     */
    public boolean shouldAttack()
    {
        return shouldAttack;
    }

    /**
     * Setter for whether the guard should attack this mob.
     *
     * @param shouldAttack the new attack attribute.
     */
    public void setShouldAttack(final boolean shouldAttack)
    {
        this.shouldAttack = shouldAttack;
    }

    /**
     * Getter for the Mob Entry's name
     *
     * @return the Translated name.
     */
    public String getName()
    {
        if (IMinecoloniesAPI.getInstance().getConfig().getServer().enableInDevelopmentFeatures)
        {
            return String.format("%s:%d", Registry.ENTITY_TYPE.get(this.location).getTranslationKey(), this.priority);
        }
        return Registry.ENTITY_TYPE.get(this.location).getName().getString();
    }

    public EntityType<?> getEntityEntry()
    {
        return Registry.ENTITY_TYPE.get(this.location);
    }
}
