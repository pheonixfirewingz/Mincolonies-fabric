package com.minecolonies.api.advancements.all_towers;

import com.minecolonies.api.util.constant.Constants;
import net.minecraft.advancement.criterion.AbstractCriterionConditions;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.util.Identifier;

/**
 * All towers criterion instance.
 */
public class AllTowersCriterionInstance extends AbstractCriterionConditions {
    public AllTowersCriterionInstance() {
        super(new Identifier(Constants.MOD_ID, Constants.CRITERION_ALL_TOWERS), EntityPredicate.Extended.EMPTY);
    }
}
