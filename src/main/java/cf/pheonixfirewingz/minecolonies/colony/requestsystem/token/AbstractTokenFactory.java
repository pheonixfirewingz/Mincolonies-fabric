package cf.pheonixfirewingz.minecolonies.colony.requestsystem.token;

import cf.pheonixfirewingz.minecolonies.colony.requestsystem.factory.IFactoryController;
import cf.pheonixfirewingz.minecolonies.utils.constant.TypeConstants;
import com.google.common.reflect.TypeToken;
import net.minecraft.nbt.*;
import net.minecraft.network.*;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * An abstract implementation of the {@link ITokenFactory} interface that handles serialization etc.
 */
public abstract class AbstractTokenFactory<I> implements ITokenFactory<I, StandardToken>
{
    ////// --------------------------- NBTConstants --------------------------- \\\\\\
    public static final String NBT_MSB = "Id_MSB";
    public static final String NBT_LSB = "Id_LSB";
    ////// --------------------------- NBTConstants --------------------------- \\\\\\

    @NotNull
    @Override
    public TypeToken<StandardToken> getFactoryOutputType()
    {
        return TypeConstants.STANDARDTOKEN;
    }

    /**
     * Method to serialize a given constructable.
     *
     * @param controller The controller that can be used to serialize complicated types.
     * @param request    The request to serialize.
     * @return The serialized data of the given requets.
     */
    @NotNull
    @Override
    public CompoundTag serialize(@NotNull final IFactoryController controller, @NotNull final StandardToken request)
    {
        final CompoundTag compound = new CompoundTag();

        compound.putLong(NBT_LSB, request.getIdentifier().getLeastSignificantBits());
        compound.putLong(NBT_MSB, request.getIdentifier().getMostSignificantBits());

        return compound;
    }

    /**
     * Method to deserialize a given constructable.
     *
     * @param controller The controller that can be used to deserialize complicated types.
     * @param nbt        The data of the request that should be deserialized.
     * @return The request that corresponds with the given data in the nbt
     */
    @NotNull
    @Override
    public StandardToken deserialize(@NotNull final IFactoryController controller, @NotNull final CompoundTag nbt)
    {
        final Long msb = nbt.getLong(NBT_MSB);
        final Long lsb = nbt.getLong(NBT_LSB);

        final UUID id = new UUID(msb, lsb);

        return new StandardToken(id);
    }

    @Override
    public void serialize(IFactoryController controller, StandardToken input, PacketByteBuf packetBuffer)
    {
        packetBuffer.writeLong(input.getIdentifier().getLeastSignificantBits());
        packetBuffer.writeLong(input.getIdentifier().getMostSignificantBits());
    }

    @Override
    public StandardToken deserialize(IFactoryController controller, PacketByteBuf buffer) throws Throwable
    {
        final long lsb = buffer.readLong();
        final long msb = buffer.readLong();
        final UUID id = new UUID(msb, lsb);
        return new StandardToken(id);
    }
}
