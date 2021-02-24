package cf.pheonixfirewingz.minecolonies.colony.requestsystem.management;

import cf.pheonixfirewingz.minecolonies.colony.requestsystem.manager.IRequestManager;
import cf.pheonixfirewingz.minecolonies.colony.requestsystem.token.IToken;

public interface ITokenHandler
{
    IRequestManager getManager();

    /**
     * Generates a new Token for the request system.
     *
     * @return The new token.
     */
    IToken<?> generateNewToken();
}
