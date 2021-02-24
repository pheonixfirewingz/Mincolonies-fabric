package cf.pheonixfirewingz.minecolonies.colony.requestsystem.factory.standard;

import cf.pheonixfirewingz.minecolonies.colony.requestsystem.factory.*;
import cf.pheonixfirewingz.minecolonies.utils.constant.*;
import com.google.common.reflect.TypeToken;

import net.minecraft.nbt.*;
import net.minecraft.network.*;
import org.jetbrains.annotations.NotNull;

public class IntegerFactory implements IFactory<FactoryVoidInput, Integer>
{
    @NotNull
    @Override
    public TypeToken<? extends Integer> getFactoryOutputType()
    {
        return TypeConstants.INTEGER;
    }

    @NotNull
    @Override
    public TypeToken<? extends FactoryVoidInput> getFactoryInputType()
    {
        return (TypeToken<? extends FactoryVoidInput>) TypeConstants.FACTORYVOIDINPUT;
    }

    @NotNull
    @Override
    public Integer getNewInstance(
      @NotNull final IFactoryController factoryController, @NotNull final FactoryVoidInput factoryVoidInput, @NotNull final Object... context) throws IllegalArgumentException
    {
        return new Integer(0);
    }

    @NotNull
    @Override
    public CompoundTag serialize(@NotNull final IFactoryController controller, @NotNull final Integer integer)
    {
        CompoundTag compound = new CompoundTag();

        compound.putInt(NbtTagConstants.TAG_VALUE, integer);

        return compound;
    }

    @NotNull
    @Override
    public Integer deserialize(@NotNull final IFactoryController controller, @NotNull final CompoundTag nbt)
    {
        return nbt.getInt(NbtTagConstants.TAG_VALUE);
    }

    @Override
    public void serialize(IFactoryController controller, Integer input, PacketByteBuf packetBuffer)
    {
        packetBuffer.writeInt(input);
    }

    @Override
    public Integer deserialize(IFactoryController controller, PacketByteBuf buffer) throws Throwable
    {
        return buffer.readInt();
    }

    @Override
    public short getSerializationId()
    {
        return 30;
    }
}
