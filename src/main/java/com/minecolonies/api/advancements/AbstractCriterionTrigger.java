package com.minecolonies.api.advancements;

import com.google.common.collect.Maps;
import net.minecraft.advancement.PlayerAdvancementTracker;
import net.minecraft.advancement.criterion.Criterion;
import net.minecraft.advancement.criterion.CriterionConditions;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.function.Function;

/**
 * The base class of a Trigger that tracks listeners and defines criterion
 * related to the trigger, and how that is fetched from JSON
 * @param <T> the class of the individual listener that will perform the trigger
 * @param <U> the criterion the individual listener is checking
 */
public abstract class AbstractCriterionTrigger<T extends CriterionListeners<U>, U extends CriterionConditions> implements Criterion<U>
{
    /** The designation for this trigger. Used by JSON advancement data. */
    private final Identifier                id;

    /** The factory method to create a listener each time this trigger is utilised */
    private final Function<PlayerAdvancementTracker, T> createNew;

    /** A map tracking each of the listeners */
    private final Map<PlayerAdvancementTracker, T>      listeners = Maps.newHashMap();

    /**
     * The trigger constructor that will define the trigger to be called
     * @param id the designation for this trigger. Used by JSON.
     * @param createNew the factory method for the listener
     */
    protected AbstractCriterionTrigger(Identifier id, Function<PlayerAdvancementTracker, T> createNew)
    {
        this.id = id;
        this.createNew = createNew;
    }

    @NotNull
    @Override
    public Identifier getId()
    {
        return id;
    }

    @Override
    public void beginTrackingCondition(PlayerAdvancementTracker manager, ConditionsContainer<U> conditions) {
        T listeners = this.listeners.get(manager);
        if (listeners == null)
        {
            listeners = createNew.apply(manager);
            this.listeners.put(manager, listeners);
        }
        listeners.add(conditions);
    }

    @Override
    public void endTrackingCondition(PlayerAdvancementTracker manager, ConditionsContainer<U> conditions) {
        final T listeners = this.listeners.get(manager);

        if (listeners != null)
        {
            listeners.remove(conditions);
            if (listeners.isEmpty())
            {
                this.listeners.remove(manager);
            }
        }
    }

    @Override
    public void endTracking(PlayerAdvancementTracker tracker) {
        this.listeners.remove(tracker);
    }

    @Nullable
    protected T getListeners(PlayerAdvancementTracker playerAdvancements)
    {
        return this.listeners.get(playerAdvancements);
    }

}
