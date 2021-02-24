package cf.pheonixfirewingz.minecolonies.colony.requestsystem.data;

import cf.pheonixfirewingz.minecolonies.colony.requestsystem.token.IToken;

import java.util.LinkedList;
import java.util.Set;

/**
 * Specific datastore for couriers.
 */
public interface IRequestSystemDeliveryManJobDataStore extends IDataStore
{
    /**
     * Get the list of all scheduled deliveries.
     * @return the ordered list.
     */
    LinkedList<IToken<?>> getQueue();

    /**
     * Get a list of all the currently ongoing deliveries.
     * @return the list.
     */
    Set<IToken<?>> getOngoingDeliveries();
}
