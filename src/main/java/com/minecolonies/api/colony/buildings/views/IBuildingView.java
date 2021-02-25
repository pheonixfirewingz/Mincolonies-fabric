package com.minecolonies.api.colony.buildings.views;

import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;
import com.minecolonies.api.colony.ICitizenDataView;
import com.minecolonies.api.colony.requestsystem.request.IRequest;
import com.minecolonies.api.colony.requestsystem.requester.IRequester;
import com.minecolonies.api.colony.requestsystem.token.IToken;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import static com.minecolonies.api.util.constant.Suppression.GENERIC_WILDCARD;

public interface IBuildingView extends IRequester
{
    /**
     * Gets the id for this building.
     *
     * @return A BlockPos because the building ID is its location.
     */
    @NotNull
    BlockPos getID();

    /**
     * Gets the location of this building.
     *
     * @return A BlockPos, where this building is.
     */
    @NotNull
    BlockPos getPosition();

    /**
     * Get the current level of the building.
     *
     * @return AbstractBuilding current level.
     */
    int getBuildingLevel();

    /**
     * Get the BlockPos of the Containers.
     *
     * @return containerList.
     */
    List<BlockPos> getContainerList();

    /**
     * Get the max level of the building.
     *
     * @return AbstractBuilding max level.
     */
    int getBuildingMaxLevel();

    /**
     * Checks if this building is at its max level.
     *
     * @return true if the building is at its max level.
     */
    boolean isBuildingMaxLevel();

    /**
     * Get the current work order level.
     *
     * @return 0 if none, othewise the current level worked on
     */
    int getCurrentWorkOrderLevel();

    /**
     * Getter for the schematic name.
     *
     * @return the schematic name.
     */
    String getSchematicName();

    /**
     * Getter for the custom building name.
     *
     * @return the name.
     */
    String getCustomName();

    /**
     * Getter for the style.
     *
     * @return the style string.
     */
    String getStyle();

    /**
     * Getter for the rotation.
     *
     * @return the rotation.
     */
    int getRotation();

    /**
     * Getter for the mirror.
     *
     * @return true if mirrored.
     */
    boolean isMirrored();

    /**
     * Get the current work order level.
     *
     * @return 0 if none, othewise the current level worked on
     */
    boolean hasWorkOrder();

    /**
     * Check if the building is current being built.
     *
     * @return true if so.
     */
    boolean isBuilding();

    /**
     * Check if the building is currently being repaired.
     *
     * @return true if so.
     */
    boolean isRepairing();

    /**
     * Check if the building is currently being deconstructed..
     *
     * @return true if so.
     */
    boolean isDeconstructing();

    /**
     * Get the claim radius for the building.
     *
     * @return the radius.
     */
    int getClaimRadius();

    /**
     * Open the associated BlockOut window for this building. If the player is sneaking open the inventory else open the GUI directly.
     *
     * @param shouldOpenInv if the player is sneaking.
     */
    void openGui(boolean shouldOpenInv);

    /**
     * Will return the window if this building has an associated BlockOut window.
     *
     * @return BlockOut window.
     */
    //TODO
//    @Nullable
//    Window getWindow();

    /**
     * Read this view from a {@link PacketByteBuf}.
     *
     * @param buf The buffer to read this view from.
     */
    void deserialize(@NotNull PacketByteBuf buf);

    Map<Integer, Collection<IToken<?>>> getOpenRequestsByCitizen();

    @SuppressWarnings(GENERIC_WILDCARD)
    <R> ImmutableList<IRequest<? extends R>> getOpenRequestsOfType(@NotNull ICitizenDataView citizenData, Class<R> requestType);

    ImmutableList<IRequest<?>> getOpenRequests(@NotNull ICitizenDataView data);

    ImmutableList<IRequest<?>> getOpenRequestsOfBuilding();

    /**
     * Gets the ColonyView that this building belongs to.
     *
     * @return ColonyView, client side interpretations of Colony.
     */
    IColonyView getColony();

    @SuppressWarnings(GENERIC_WILDCARD)
    <R> ImmutableList<IRequest<? extends R>> getOpenRequestsOfTypeFiltered(
      @NotNull ICitizenDataView citizenData,
      Class<R> requestType,
      Predicate<IRequest<? extends R>> filter);

    /**
     * Get the delivery priority of the building.
     *
     * @return int, delivery priority.
     */
    int getBuildingDmPrio();

    ImmutableCollection<IToken<?>> getResolverIds();

    /**
     * Setter for the custom name. Sets the name on the client side and sends it to the server.
     *
     * @param name the new name.
     */
    void setCustomName(String name);

    /**
     * Check if the building was deconstructed.
     *
     * @return true if so.
     */
    boolean isDeconstructed();
}
