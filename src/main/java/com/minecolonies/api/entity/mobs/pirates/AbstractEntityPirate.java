package com.minecolonies.api.entity.mobs.pirates;

import com.minecolonies.api.entity.mobs.*;
import com.minecolonies.api.entity.pathfinding.AbstractAdvancedPathNavigate;
import net.minecraft.entity.*;
import net.minecraft.util.*;
import net.minecraft.world.*;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

import static com.minecolonies.api.util.constant.RaiderConstants.*;

/**
 * Abstract for all Barbarian entities.
 */
public abstract class AbstractEntityPirate extends AbstractEntityMinecoloniesMob
{
    /**
     * Amount of unique pirate textures.
     */
    private static final int PIRATE_TEXTURES = 4;

    /**
     * Texture id of the pirates.
     */
    private final int textureId;

    /**
     * Constructor method for Abstract Barbarians.
     *
     * @param type  the type.
     * @param world the world.
     */
    public AbstractEntityPirate(final EntityType<? extends AbstractEntityPirate> type, final World world)
    {
        super(type, world);
        this.textureId = new Random().nextInt(PIRATE_TEXTURES);
    }

    @Override
    public void playAmbientSound()
    {
        final SoundEvent soundevent = this.getAmbientSound();

        if (soundevent != null && world.rand.nextInt(OUT_OF_ONE_HUNDRED) <= ONE)
        {
            this.playSound(soundevent, this.getSoundVolume(), this.getSoundPitch());
        }
    }

    @Override
    public boolean canSpawn(final IWorld worldIn, final SpawnReason spawnReasonIn)
    {
        return true;
    }

    /**
     * Get the unique texture id.
     *
     * @return the texture id.
     */
    public int getTextureId()
    {
        return this.textureId;
    }

    @NotNull
    @Override
    public AbstractAdvancedPathNavigate getNavigator()
    {
        AbstractAdvancedPathNavigate navigator = super.getNavigator();
        navigator.getPathingOptions().withStartSwimCost(2.5D).withSwimCost(1.1D);
        return navigator;
    }

    @Override
    public RaiderType getRaiderType()
    {
        return RaiderType.PIRATE;
    }
}
