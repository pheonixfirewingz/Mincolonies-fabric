package cf.pheonixfirewingz.minecolonies.colony;

import cf.pheonixfirewingz.minecolonies.utils.*;
import cf.pheonixfirewingz.minecolonies.utils.constant.Constants;
import com.minecolonies.api.util.ChunkLoadStorage;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.INBT;
import net.minecraft.util.math.*;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.Constants;
import org.jetbrains.annotations.*;

import java.util.HashMap;
import java.util.Map;

import static cf.pheonixfirewingz.minecolonies.utils.constant.NbtTagConstants.*;

/**
 * Capability for the colony tag for chunks
 */
public interface IChunkmanagerCapability
{
    /**
     * Get the chunkStorage at a certain location.
     *
     * @param chunkX the x chunk location.
     * @param chunkZ the z chunk location.
     * @return the storage or null.
     */
    @Nullable
    ChunkLoadStorage getChunkStorage(int chunkX, int chunkZ);

    /**
     * Add a new chunkStorage.
     *
     * @param chunkX  chunkX the x chunk location.
     * @param chunkZ  chunkX the z chunk location.
     * @param storage the new to add or update.
     * @return true if override else false.
     */
    boolean addChunkStorage(int chunkX, int chunkZ, ChunkLoadStorage storage);

    /**
     * Get all chunk storages for serialization.
     *
     * @return the storages.
     */
    Map<ChunkPos, ChunkLoadStorage> getAllChunkStorages();

    /**
     * The implementation of the colonyTagCapability.
     */
    class Impl implements IChunkmanagerCapability
    {
        /**
         * Map of chunkPos to chunkLoadStorage.
         */
        private final Map<ChunkPos, ChunkLoadStorage> chunkStorages = new HashMap<>();

        @Nullable
        @Override
        public ChunkLoadStorage getChunkStorage(final int chunkX, final int chunkZ)
        {
            return chunkStorages.remove(new ChunkPos(chunkX, chunkZ));
        }

        @Override
        public boolean addChunkStorage(final int chunkX, final int chunkZ, final ChunkLoadStorage storage)
        {
            final ChunkLoadStorage existingStorage = chunkStorages.get(new ChunkPos(chunkX, chunkZ));
            if (existingStorage == null)
            {
                chunkStorages.put(new ChunkPos(chunkX, chunkZ), storage);
                return false;
            }
            else
            {
                existingStorage.merge(storage);
                return true;
            }
        }

        @Override
        public Map<ChunkPos, ChunkLoadStorage> getAllChunkStorages()
        {
            return chunkStorages;
        }
    }

    /**
     * The storage class of the capability.
     */
    class Storage implements Capability.IStorage<IChunkmanagerCapability>
    {
        @Override
        public INBT writeNBT(@NotNull final Capability<IChunkmanagerCapability> capability, @NotNull final IChunkmanagerCapability instance, @Nullable final Direction side)
        {
            final CompoundTag compound = new CompoundTag();
            compound.put(TAG_ALL_CHUNK_STORAGES,
              instance.getAllChunkStorages().entrySet().stream().map(entry -> write(entry.getKey(), entry.getValue())).collect(NBTUtils.toListNBT()));
            return compound;
        }

        @Override
        public void readNBT(
                @NotNull final Capability<IChunkmanagerCapability> capability, @NotNull final IChunkmanagerCapability instance,
                @Nullable final Direction side, @NotNull final INBT nbt)
        {
            if (nbt instanceof CompoundTag && ((CompoundTag) nbt).keySet().contains(TAG_ALL_CHUNK_STORAGES))
            {
                NBTUtils.streamCompound(((CompoundTag) nbt).getList(TAG_ALL_CHUNK_STORAGES, Constants.NBT.TAG_COMPOUND))
                  .map(Storage::read).forEach(key -> instance.addChunkStorage(key.getA().x, key.getA().z, key.getB()));
            }
        }

        /**
         * Write a single ChunkPos, ChunkLoadStorage pair to nbt.
         *
         * @param key   the key.
         * @param value the value
         * @return the resulting compound.
         */
        private static CompoundTag write(final ChunkPos key, final ChunkLoadStorage value)
        {
            final CompoundTag compound = new CompoundTag();
            compound.put(TAG_CHUNK_STORAGE, value.toNBT());
            compound.putInt(TAG_X, key.x);
            compound.putInt(TAG_Z, key.z);
            return compound;
        }

        /**
         * Read a key value pair for the chunkloadstorages.
         *
         * @param compound the compound to read it from.
         * @return a tuple for both.
         */
        private static Tuple<ChunkPos, ChunkLoadStorage> read(final CompoundTag compound)
        {
            final ChunkLoadStorage storage = new ChunkLoadStorage(compound.getCompound(TAG_CHUNK_STORAGE));
            final int x = compound.getInt(TAG_X);
            final int z = compound.getInt(TAG_Z);
            return new Tuple<>(new ChunkPos(x, z), storage);
        }
    }
}
