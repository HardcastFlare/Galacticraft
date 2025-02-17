package micdoodle8.mods.galacticraft.core.dimension;

import java.util.ArrayList;

import micdoodle8.mods.galacticraft.api.galaxies.GalaxyRegistry;
import micdoodle8.mods.galacticraft.api.galaxies.Satellite;
import micdoodle8.mods.galacticraft.core.entities.player.GCPlayerStats;
import micdoodle8.mods.galacticraft.core.util.ConfigManagerCore;
import micdoodle8.mods.galacticraft.core.util.GCLog;
import micdoodle8.mods.galacticraft.core.util.WorldUtil;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;
import net.minecraftforge.common.DimensionManager;

public class SpaceStationWorldData extends WorldSavedData {

    private String spaceStationName = "NoName";
    private String owner = "NoOwner";
    private final ArrayList<String> allowedPlayers;
    private boolean allowAllPlayers;
    private int homePlanet;
    private int dimensionIdDynamic;
    private int dimensionIdStatic;
    private NBTTagCompound dataCompound;

    public SpaceStationWorldData(String par1Str) {
        super(par1Str);

        this.allowedPlayers = new ArrayList<String>() {

            private static final long serialVersionUID = 1079079229788066770L;

            // Override contains so it ignores case.
            @Override
            public boolean contains(Object o) {
                if (o instanceof String) {
                    final String paramStr = (String) o;

                    for (final String s : this) {
                        if (paramStr.equalsIgnoreCase(s)) {
                            return true;
                        }
                    }
                }

                return false;
            }
        };
    }

    public ArrayList<String> getAllowedPlayers() {
        return this.allowedPlayers;
    }

    public boolean getAllowedAll() {
        return this.allowAllPlayers;
    }

    public void setAllowedAll(boolean b) {
        this.allowAllPlayers = b;
        this.markDirty();
    }

    public String getOwner() {
        return this.owner;
    }

    public void setOwner(String name) {
        this.owner = name.replace(".", "");
        this.markDirty();
    }

    public String getSpaceStationName() {
        return this.spaceStationName;
    }

    public int getHomePlanet() {
        return this.homePlanet;
    }

    public void setSpaceStationName(String string) {
        this.spaceStationName = string;
    }

    public int getDimensionIdStatic() {
        return this.dimensionIdStatic;
    }

    public void setDimensionIdStatic(int dimensionIdStatic) {
        this.dimensionIdStatic = dimensionIdStatic;
    }

    public int getDimensionIdDynamic() {
        return this.dimensionIdDynamic;
    }

    public void setDimensionIdDynamic(int dimensionIdDynamic) {
        this.dimensionIdDynamic = dimensionIdDynamic;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbttagcompound) {
        this.owner = nbttagcompound.getString("owner").replace(".", "");
        this.spaceStationName = nbttagcompound.getString("spaceStationName");

        if (nbttagcompound.hasKey("dataCompound")) {
            this.dataCompound = nbttagcompound.getCompoundTag("dataCompound");
        } else {
            this.dataCompound = new NBTTagCompound();
        }

        if (nbttagcompound.hasKey("homePlanet")) {
            this.homePlanet = nbttagcompound.getInteger("homePlanet");
        } else {
            GCLog.info(
                    "Home planet data not found in space station save file for \"" + this.spaceStationName
                            + "\". Using default overworld.");
            this.homePlanet = 0; // Overworld dimension ID
        }

        if (nbttagcompound.hasKey("dimensionIdStatic")) {
            this.dimensionIdStatic = nbttagcompound.getInteger("dimensionIdStatic");
        } else {
            GCLog.info(
                    "Static dimension ID not found in space station save file for \"" + this.spaceStationName
                            + "\". Using default overworld.");
            this.dimensionIdStatic = ConfigManagerCore.idDimensionOverworldOrbitStatic;
        }

        if (nbttagcompound.hasKey("dimensionIdDynamic")) {
            this.dimensionIdDynamic = nbttagcompound.getInteger("dimensionIdDynamic");
        } else {
            GCLog.info(
                    "Dynamic dimension ID not found in space station save file for \"" + this.spaceStationName
                            + "\". Using default overworld.");
            this.dimensionIdDynamic = ConfigManagerCore.idDimensionOverworldOrbit;
        }

        this.allowAllPlayers = nbttagcompound.getBoolean("allowedAll");

        final NBTTagList var2 = nbttagcompound.getTagList("allowedPlayers", 10);
        this.allowedPlayers.clear();

        for (int var3 = 0; var3 < var2.tagCount(); ++var3) {
            final NBTTagCompound var4 = var2.getCompoundTagAt(var3);
            final String var5 = var4.getString("allowedPlayer");

            if (!this.allowedPlayers.contains(var5)) {
                this.allowedPlayers.add(var5);
            }
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbttagcompound) {
        nbttagcompound.setString("owner", this.owner);
        nbttagcompound.setString("spaceStationName", this.spaceStationName);
        nbttagcompound.setInteger("homePlanet", this.homePlanet);
        nbttagcompound.setInteger("dimensionIdDynamic", this.dimensionIdDynamic);
        nbttagcompound.setInteger("dimensionIdStatic", this.dimensionIdStatic);
        nbttagcompound.setTag("dataCompound", this.dataCompound);
        nbttagcompound.setBoolean("allowedAll", this.allowAllPlayers);

        final NBTTagList var2 = new NBTTagList();

        for (final String player : this.allowedPlayers) {
            if (player != null) {
                final NBTTagCompound var4 = new NBTTagCompound();
                var4.setString("allowedPlayer", player);
                var2.appendTag(var4);
            }
        }

        nbttagcompound.setTag("allowedPlayers", var2);
    }

    /**
     * Retrieve an already created space station date entry
     */
    public static SpaceStationWorldData getStationData(World world, int stationID, EntityPlayer player) {
        return getStationData(world, stationID, -1, -1, -1, player);
    }

    /**
     * Retrieve a space station data entry, creating if necessary (with provided data)
     */
    public static SpaceStationWorldData getStationData(World world, int stationID, int homeID, int providerIdDynamic,
            int providerIdStatic, EntityPlayer owner) {
        final int providerType = DimensionManager.getProviderType(stationID);

        boolean foundMatch = false;

        // Loop through all registered satellites, checking for a provider ID match. If
        // none is found, this method is
        // being called on an incorrect
        for (final Satellite satellite : GalaxyRegistry.getRegisteredSatellites().values()) {
            if (satellite.getDimensionIdStatic() == providerType || satellite.getDimensionID() == providerType) {
                foundMatch = true;
                break;
            }
        }

        if (!foundMatch) {
            return null;
        } else {
            final String stationIdentifier = SpaceStationWorldData.getSpaceStationID(stationID);
            SpaceStationWorldData stationData = (SpaceStationWorldData) world
                    .loadItemData(SpaceStationWorldData.class, stationIdentifier);

            if (stationData == null) {
                stationData = new SpaceStationWorldData(stationIdentifier);
                world.setItemData(stationIdentifier, stationData);
                stationData.dataCompound = new NBTTagCompound();

                if (owner != null) {
                    stationData.owner = owner.getGameProfile().getName().replace(".", "");
                }

                stationData.spaceStationName = "Station: " + stationData.owner;

                if (owner != null) {
                    stationData.allowedPlayers.add(owner.getGameProfile().getName());
                }

                if (homeID == -1) {
                    throw new RuntimeException("Space station being created on bad home planet ID!");
                } else {
                    stationData.homePlanet = homeID;
                }

                if (providerIdDynamic == -1 || providerIdStatic == -1) {
                    throw new RuntimeException("Space station being created on bad provider IDs!");
                } else {
                    stationData.dimensionIdDynamic = providerIdDynamic;
                    stationData.dimensionIdStatic = providerIdStatic;
                }

                stationData.markDirty();
            }

            if (stationData.getSpaceStationName().replace(" ", "").isEmpty()) {
                stationData.setSpaceStationName("Station: " + stationData.owner);
                stationData.markDirty();
            }

            return stationData;
        }
    }

    public static SpaceStationWorldData getMPSpaceStationData(World var0, int var1, EntityPlayer player) {
        final String var2 = SpaceStationWorldData.getSpaceStationID(var1);
        if (var0 == null) {
            var0 = DimensionManager.getProvider(0).worldObj;
        }
        SpaceStationWorldData var3 = (SpaceStationWorldData) var0.loadItemData(SpaceStationWorldData.class, var2);

        if (var3 == null) {
            var3 = new SpaceStationWorldData(var2);
            var0.setItemData(var2, var3);
            var3.dataCompound = new NBTTagCompound();

            if (player != null) {
                var3.owner = player.getGameProfile().getName().replace(".", "");
            }

            var3.spaceStationName = "Station: " + var3.owner;

            if (player != null) {
                var3.allowedPlayers.add(player.getGameProfile().getName());
            }

            var3.markDirty();
        }

        if (var3.getSpaceStationName().replace(" ", "").isEmpty()) {
            var3.setSpaceStationName("Station: " + var3.owner);
            var3.markDirty();
        }

        return var3;
    }

    public static String getSpaceStationID(int dimID) {
        return "spacestation_" + dimID;
    }

    public static void updateSSOwnership(EntityPlayerMP player, String playerName, GCPlayerStats stats, int stationID,
            SpaceStationWorldData stationData) {
        if (stationData == null) {
            stationData = SpaceStationWorldData.getMPSpaceStationData(null, stationID, null);
        }

        if (stationData.owner.equals(playerName)) {
            // This player is the owner of the station - ensure stats data matches
            if (!stats.spaceStationDimensionData.containsValue(stationID)) {
                GCLog.debug(
                        "Player owns station: " + stationData.getSpaceStationName()
                                + " with home planet "
                                + stationData.getHomePlanet());
                stats.spaceStationDimensionData.put(stationData.getHomePlanet(), stationID);
            }
        } else {
            // This player is the owner of the station - remove from stats data
            final Integer savedOwned = stats.spaceStationDimensionData.get(stationData.getHomePlanet());
            if (savedOwned != null && savedOwned == stationID) {
                GCLog.debug(
                        "Player does not own station: " + stationData.getSpaceStationName()
                                + " with home planet "
                                + stationData.getHomePlanet());
                stats.spaceStationDimensionData.remove(savedOwned);
            }
        }
    }

    public static void checkAllStations(EntityPlayerMP thePlayer, GCPlayerStats stats) {
        final String name = thePlayer.getGameProfile().getName().replace(".", "");
        for (final int id : WorldUtil.registeredSpaceStations.keySet()) {
            SpaceStationWorldData.updateSSOwnership(thePlayer, name, stats, id, null);
        }
    }
}
