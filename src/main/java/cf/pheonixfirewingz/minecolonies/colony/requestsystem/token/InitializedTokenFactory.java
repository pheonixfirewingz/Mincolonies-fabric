package cf.pheonixfirewingz.minecolonies.colony.requestsystem.token;

import cf.pheonixfirewingz.minecolonies.colony.requestsystem.factory.FactoryVoidInput;
import cf.pheonixfirewingz.minecolonies.utils.constant.TypeConstants;
import com.google.gson.reflect.TypeToken;
import org.jetbrains.annotations.NotNull;

/**
 * {@link IToken} factory that produces an {@link IToken} from a random {@link java.util.UUID}
 */
public class InitializedTokenFactory extends AbstractTokenFactory<FactoryVoidInput>
{
    @NotNull
    @Override
    public StandardToken getNewInstance(@NotNull final FactoryVoidInput input)
    {
        return new StandardToken();
    }

    @NotNull
    @Override
    public TypeToken<? extends FactoryVoidInput> getFactoryInputType()
    {
        return TypeConstants.FACTORYVOIDINPUT;
    }

    @Override
    public short getSerializationId()
    {
        return 1;
    }
}
