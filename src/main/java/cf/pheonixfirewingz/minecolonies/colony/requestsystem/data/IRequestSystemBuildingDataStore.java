package cf.pheonixfirewingz.minecolonies.colony.requestsystem.data;

import cf.pheonixfirewingz.minecolonies.colony.requestsystem.token.IToken;
import com.google.common.reflect.TypeToken;

import java.util.Collection;
import java.util.Map;

public interface IRequestSystemBuildingDataStore extends IDataStore
{
    Map<TypeToken<?>, Collection<IToken<?>>> getOpenRequestsByRequestableType();

    Map<Integer, Collection<IToken<?>>> getOpenRequestsByCitizen();

    Map<Integer, Collection<IToken<?>>> getCompletedRequestsByCitizen();

    Map<IToken<?>, Integer> getCitizensByRequest();
}
