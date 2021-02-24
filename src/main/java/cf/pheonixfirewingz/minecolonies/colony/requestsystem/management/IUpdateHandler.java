package cf.pheonixfirewingz.minecolonies.colony.requestsystem.management;

import cf.pheonixfirewingz.minecolonies.colony.requestsystem.management.update.UpdateType;
import cf.pheonixfirewingz.minecolonies.colony.requestsystem.manager.IRequestManager;

public interface IUpdateHandler
{
    IRequestManager getManager();

    void handleUpdate(final UpdateType type);

    int getCurrentVersion();
}
