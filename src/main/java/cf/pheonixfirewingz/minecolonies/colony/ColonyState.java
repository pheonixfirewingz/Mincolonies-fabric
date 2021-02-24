package cf.pheonixfirewingz.minecolonies.colony;

import cf.pheonixfirewingz.minecolonies.entity.ai.statemachine.states.IAIState;

/**
 * THe states a colony can be in.
 */
public enum ColonyState implements IAIState
{
    /**
     * Colony is not ticking
     */
    INACTIVE,

    /**
     * Colony is active
     */
    ACTIVE,

    /**
     * Colony chunks are not loaded
     */
    UNLOADED;

    @Override
    public boolean isOkayToEat()
    {
        return false;
    }
}

