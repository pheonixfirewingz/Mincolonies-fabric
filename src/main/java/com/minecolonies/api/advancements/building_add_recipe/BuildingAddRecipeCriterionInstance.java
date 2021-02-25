package com.minecolonies.api.advancements.building_add_recipe;

import com.minecolonies.api.crafting.IRecipeStorage;
import com.minecolonies.api.util.constant.Constants;
import net.minecraft.entity.ai.*;
import net.minecraft.predicate.item.*;
import net.minecraft.util.*;

/**
 * The test instance to check the various conditions for "building_add_recipe"
 */
public class BuildingAddRecipeCriterionInstance extends AbstractCriterionCondition
{
    private ItemPredicate[] outputItemPredicates;
    private int             craftingSize = -1;

    /**
     * Default instance when no conditions are applied to the trigger
     */
    public BuildingAddRecipeCriterionInstance()
    {
        super(new Identifier(Constants.MOD_ID, Constants.CRITERION_BUILDING_ADD_RECIPE), TargetPredicate.AndPredicate.ANY_AND);
    }

    /**
     * Instance with the condition to check what item recipe was added
     * @param outputItemPredicates the item recipe tester constructed from the advancement information
     */
    public BuildingAddRecipeCriterionInstance(final ItemPredicate[] outputItemPredicates)
    {
        super(new Identifier(Constants.MOD_ID, Constants.CRITERION_BUILDING_ADD_RECIPE), TargetPredicate.AndPredicate.ANY_AND);

        this.outputItemPredicates = outputItemPredicates;
    }

    /**
     * Instance with the condition to check what item recipe was added and at what grid size
     * @param outputItemPredicates the item recipe tester constructed from the advancement information
     * @param craftingSize the NxN size of the crafting grid
     */
    public BuildingAddRecipeCriterionInstance(final ItemPredicate[] outputItemPredicates, final int craftingSize)
    {
        super(new Identifier(Constants.MOD_ID, Constants.CRITERION_BUILDING_ADD_RECIPE), TargetPredicate.AndPredicate.ANY_AND);

        this.outputItemPredicates = outputItemPredicates;
        this.craftingSize = craftingSize;
    }

    /**
     * Performs the check for these criteria
     * @param recipeStorage the recipe that was just added
     * @return whether the check succeeded
     */
    public boolean test(final IRecipeStorage recipeStorage)
    {
        if (this.outputItemPredicates != null)
        {
            boolean outputMatches = false;
            for (ItemPredicate itemPredicate : outputItemPredicates)
            {
                if (itemPredicate.test(recipeStorage.getPrimaryOutput()))
                {
                    outputMatches = true;
                    break;
                }
            }

            if (this.craftingSize != -1)
            {
                return outputMatches && this.craftingSize == recipeStorage.getGridSize();
            }

            return outputMatches;
        }

        return true;
    }
}
