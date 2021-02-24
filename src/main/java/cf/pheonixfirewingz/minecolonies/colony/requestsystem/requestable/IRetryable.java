package cf.pheonixfirewingz.minecolonies.colony.requestsystem.requestable;

/**
 * Marker interface for requests that should be retried when they initially failed a couple of seconds later.
 */
public interface IRetryable extends IRequestable
{
}
