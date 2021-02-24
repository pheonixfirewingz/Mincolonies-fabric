package cf.pheonixfirewingz.minecolonies.colony.requestsystem.data;

import cf.pheonixfirewingz.minecolonies.colony.requestsystem.token.IToken;
import com.google.common.reflect.TypeToken;

import java.util.function.Supplier;

public interface IDataStoreManager
{
    <T extends IDataStore> T get(IToken<?> id, TypeToken<T> type);

    <T extends IDataStore> T get(IToken<?> id, Supplier<T> factory);

    void remove(IToken<?> id);

    void removeAll();
}
