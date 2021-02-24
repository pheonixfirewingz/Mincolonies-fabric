package cf.pheonixfirewingz.minecolonies.colony.requestsystem.data;

import cf.pheonixfirewingz.minecolonies.colony.requestsystem.request.IRequest;
import cf.pheonixfirewingz.minecolonies.colony.requestsystem.token.IToken;

/**
 * The KV-Store for the requests and their identities. Extends the {@link IIdentitiesDataStore} with {@link IToken} as key type and {@link IRequest} as value type.
 */
public interface IRequestIdentitiesDataStore extends IIdentitiesDataStore<IToken<?>, IRequest<?>>
{
}
