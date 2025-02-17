package micdoodle8.mods.galacticraft.core.oxygen;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

import micdoodle8.mods.galacticraft.api.transmission.NetworkType;
import micdoodle8.mods.galacticraft.api.transmission.grid.IElectricityNetwork;
import micdoodle8.mods.galacticraft.api.transmission.grid.IHydrogenNetwork;
import micdoodle8.mods.galacticraft.api.transmission.grid.IOxygenNetwork;
import micdoodle8.mods.galacticraft.api.transmission.tile.IConnector;
import micdoodle8.mods.galacticraft.api.transmission.tile.INetworkProvider;
import micdoodle8.mods.galacticraft.api.vector.BlockVec3;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * A helper class that provides additional useful functions to interact with the ElectricityNetwork
 *
 * @author Calclavia
 */
public class NetworkHelper {

    public static EnumSet<ForgeDirection> getDirections(TileEntity tileEntity, NetworkType type) {
        final EnumSet<ForgeDirection> possibleSides = EnumSet.noneOf(ForgeDirection.class);

        if (tileEntity instanceof IConnector) {
            for (int i = 0; i < 6; i++) {
                final ForgeDirection direction = ForgeDirection.getOrientation(i);
                if (((IConnector) tileEntity).canConnect(direction, type)) {
                    possibleSides.add(direction);
                }
            }
        }

        return possibleSides;
    }

    /**
     * @param tileEntity           - The TileEntity's sides.
     * @param approachingDirection - The directions that can be connected.
     * @return A list of networks from all specified sides. There will be no repeated ElectricityNetworks and it will
     *         never return null.
     */
    public static Set<IElectricityNetwork> getNetworksFromMultipleSides(TileEntity tileEntity,
            EnumSet<ForgeDirection> approachingDirection) {
        final Set<IElectricityNetwork> connectedNetworks = new HashSet<>();

        final BlockVec3 tileVec = new BlockVec3(tileEntity);
        for (final ForgeDirection side : ForgeDirection.VALID_DIRECTIONS) {
            if (approachingDirection.contains(side)) {
                final TileEntity outputConductor = tileVec.getTileEntityOnSide(tileEntity.getWorldObj(), side);
                final IElectricityNetwork electricityNetwork = NetworkHelper
                        .getElectricalNetworkFromTileEntity(outputConductor, side);

                if (electricityNetwork != null) {
                    connectedNetworks.add(electricityNetwork);
                }
            }
        }

        return connectedNetworks;
    }

    /**
     * Tries to find the electricity network based in a tile entity and checks to see if it is a conductor. All machines
     * should use this function to search for a connecting conductor around it.
     *
     * @param conductor         - The TileEntity conductor
     * @param approachDirection - The direction you are approaching this wire from.
     * @return The ElectricityNetwork or null if not found.
     */
    public static IElectricityNetwork getElectricalNetworkFromTileEntity(TileEntity tileEntity,
            ForgeDirection approachDirection) {
        if (tileEntity != null) {
            if (tileEntity instanceof INetworkProvider) {
                if (tileEntity instanceof IConnector) {
                    if (((IConnector) tileEntity).canConnect(approachDirection.getOpposite(), NetworkType.POWER)) {
                        if (((INetworkProvider) tileEntity).getNetwork() instanceof IElectricityNetwork) {
                            return (IElectricityNetwork) ((INetworkProvider) tileEntity).getNetwork();
                        }
                    }
                } else {
                    if (((INetworkProvider) tileEntity).getNetwork() instanceof IElectricityNetwork) {
                        return (IElectricityNetwork) ((INetworkProvider) tileEntity).getNetwork();
                    }
                }
            }
        }

        return null;
    }

    public static IOxygenNetwork getOxygenNetworkFromTileEntity(TileEntity tileEntity,
            ForgeDirection approachDirection) {
        if (tileEntity != null) {
            if (tileEntity instanceof INetworkProvider) {
                if (tileEntity instanceof IConnector) {
                    if (((IConnector) tileEntity).canConnect(approachDirection.getOpposite(), NetworkType.OXYGEN)) {
                        if (((INetworkProvider) tileEntity).getNetwork() instanceof IOxygenNetwork) {
                            return (IOxygenNetwork) ((INetworkProvider) tileEntity).getNetwork();
                        }
                    }
                } else {
                    if (((INetworkProvider) tileEntity).getNetwork() instanceof IOxygenNetwork) {
                        return (IOxygenNetwork) ((INetworkProvider) tileEntity).getNetwork();
                    }
                }
            }
        }

        return null;
    }

    public static IHydrogenNetwork getHydrogenNetworkFromTileEntity(TileEntity tileEntity,
            ForgeDirection approachDirection) {
        if (tileEntity != null) {
            if (tileEntity instanceof INetworkProvider) {
                if (tileEntity instanceof IConnector) {
                    if (((IConnector) tileEntity).canConnect(approachDirection.getOpposite(), NetworkType.HYDROGEN)) {
                        if (((INetworkProvider) tileEntity).getNetwork() instanceof IHydrogenNetwork) {
                            return (IHydrogenNetwork) ((INetworkProvider) tileEntity).getNetwork();
                        }
                    }
                } else {
                    if (((INetworkProvider) tileEntity).getNetwork() instanceof IHydrogenNetwork) {
                        return (IHydrogenNetwork) ((INetworkProvider) tileEntity).getNetwork();
                    }
                }
            }
        }

        return null;
    }
}
