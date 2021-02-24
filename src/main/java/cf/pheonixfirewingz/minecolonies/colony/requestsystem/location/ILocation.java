package cf.pheonixfirewingz.minecolonies.colony.requestsystem.location;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.*;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

/**
 * Interface used to describe locations in the world.
 */
public interface ILocation
{

    /**
     * Method to get the location in the dimension
     *
     * @return The location.
     */
    @NotNull
    BlockPos getInDimensionLocation();

    /**
     * Method to get the dimension of the location.
     *
     * @return The dimension of the location.
     */
    @NotNull
    RegistryKey<World> getDimension();

    /**
     * Method to check if this location is reachable from the other.
     *
     * @param location The check if it is reachable from here.
     * @return True when reachable, false when not.
     */
    boolean isReachableFromLocation(@NotNull ILocation location);
}
