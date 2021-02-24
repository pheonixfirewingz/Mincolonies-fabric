package cf.pheonixfirewingz.minecolonies.colony.requestsystem.factory.standard;

import cf.pheonixfirewingz.minecolonies.colony.requestsystem.factory.*;
import cf.pheonixfirewingz.minecolonies.utils.ReflectionUtils;
import cf.pheonixfirewingz.minecolonies.utils.constant.*;
import com.google.common.reflect.TypeToken;

import net.minecraft.nbt.*;
import net.minecraft.network.*;
import org.jetbrains.annotations.NotNull;

public class TypeTokenFactory implements IFactory<Class<?>, TypeToken<?>>
{
    @NotNull
    @Override
    public TypeToken<? extends TypeToken<?>> getFactoryOutputType()
    {
        return TypeConstants.TYPETOKEN;
    }

    @NotNull
    @Override
    public TypeToken<? extends Class<?>> getFactoryInputType()
    {
        return TypeConstants.CLASS;
    }

    @NotNull
    @Override
    public TypeToken<?> getNewInstance(
            @NotNull final IFactoryController factoryController, @NotNull final Class<?> aClass, @NotNull final Object... context) throws IllegalArgumentException
    {
        return TypeToken.of(aClass);
    }

    @NotNull
    @Override
    public CompoundTag serialize(@NotNull final IFactoryController controller, @NotNull final TypeToken<?> typeToken)
    {
        CompoundTag compound = new CompoundTag();

        compound.putString(NbtTagConstants.TAG_VALUE, typeToken.getRawType().getName());

        return compound;
    }

    @NotNull
    @Override
    public TypeToken<?> deserialize(@NotNull final IFactoryController controller, @NotNull final CompoundTag nbt) throws Throwable
    {
        try
        {
            return TypeToken.of(Class.forName(nbt.getString(NbtTagConstants.TAG_VALUE)));
        }
        catch (ClassNotFoundException e)
        {
            throw new IllegalStateException("Failed to create TypeToken", e);
        }
    }

    public static class TypeTokenSubTypeOverrideHandler implements ITypeOverrideHandler<TypeToken<?>>
    {

        @Override
        public boolean matches(final TypeToken<?> inputType)
        {
            return ReflectionUtils.getSuperClasses(inputType).contains(TypeConstants.TYPETOKEN);
        }

        @Override
        public TypeToken<TypeToken<?>> getOutputType()
        {
            return TypeConstants.TYPETOKEN;
        }
    }

    @Override
    public void serialize(IFactoryController controller, TypeToken<?> input, PacketByteBuf packetBuffer)
    {
        packetBuffer.writeString(input.getRawType().getName());
    }

    @Override
    public TypeToken<?> deserialize(IFactoryController controller, PacketByteBuf buffer) throws Throwable
    {
        try
        {
            return TypeToken.of(Class.forName(buffer.readString(32767)));
        }
        catch (ClassNotFoundException e)
        {
            throw new IllegalStateException("Failed to create TypeToken", e);
        }
    }

    @Override
    public short getSerializationId()
    {
        return 31;
    }
}
