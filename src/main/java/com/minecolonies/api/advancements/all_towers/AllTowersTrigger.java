package com.minecolonies.api.advancements.all_towers;

import com.google.gson.JsonObject;
import com.minecolonies.api.advancements.AbstractCriterionTrigger;
import com.minecolonies.api.advancements.CriterionListeners;
import com.minecolonies.api.util.constant.Constants;
import net.minecraft.predicate.entity.AdvancementEntityPredicateDeserializer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

/**
 * Triggered when all barracks towers have been fully upgraded on any one barracks
 */
public class AllTowersTrigger extends AbstractCriterionTrigger<CriterionListeners<AllTowersCriterionInstance>, AllTowersCriterionInstance>
{
    /** How this trigger is referenced in JSON and identified in the registry */
    private final static Identifier ID = new Identifier(Constants.MOD_ID, Constants.CRITERION_ALL_TOWERS);

    /** Construct the trigger instance with the right ID */
    public AllTowersTrigger()
    {
        super(ID, CriterionListeners::new);
    }

    /**
     * Triggers the listener checks if there is any listening in
     * @param player the player the check regards
     */
    public void trigger(final ServerPlayerEntity player)
    {
        final CriterionListeners<AllTowersCriterionInstance> listeners = this.getListeners(player.getAdvancementTracker());
        if (listeners != null)
        {
            listeners.trigger();
        }
    }

    @Override
    public AllTowersCriterionInstance conditionsFromJson(JsonObject obj, AdvancementEntityPredicateDeserializer predicateDeserializer) {
        return new AllTowersCriterionInstance();
    }
}
