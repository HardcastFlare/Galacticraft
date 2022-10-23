package micdoodle8.mods.galacticraft.api.galaxies;

import com.google.common.collect.Lists;
import java.util.Collections;
import java.util.List;
import net.minecraft.world.WorldProvider;

public class Satellite extends CelestialBody implements IChildBody {
    protected Planet parentCelestialBody = null;
    protected int dimensionIdStatic = 0;

    public Satellite(String satelliteName) {
        super(satelliteName);
    }

    @Override
    public String getParentName() {
        Planet parentPlanet = getParentPlanet();

        if (parentPlanet != null) {
            return parentPlanet.getLocalizedName();
        }

        return "Null";
    }

    @Override
    public String getGrandparentName() {
        Planet parentPlanet = getParentPlanet();

        if (parentPlanet != null) {
            SolarSystem parentSolarSystem = parentPlanet.getParentSolarSystem();

            if (parentSolarSystem != null) {
                return parentSolarSystem.getLocalizedName();
            }
        }

        return "Null";
    }

    @Override
    public List<CelestialBody> getSiblings() {
        List<CelestialBody> bodyList = Lists.newArrayList();

        Planet planet = getParentPlanet();

        for (Moon moon : GalaxyRegistry.getRegisteredMoons().values()) {
            Planet planet1 = moon.getParentPlanet();

            if (planet1 != null && planet1.equals(planet)) {
                bodyList.add(moon);
            }
        }

        Collections.sort(bodyList);
        return bodyList;
    }

    @Override
    public List<CelestialBody> getChildren() {
        return Lists.newArrayList();
    }

    @Override
    public Planet getParentPlanet() {
        return this.parentCelestialBody;
    }

    public Satellite setParentBody(Planet parentCelestialBody) {
        this.parentCelestialBody = parentCelestialBody;
        return this;
    }

    @Override
    @Deprecated
    public CelestialBody setDimensionInfo(
            int providerId, Class<? extends WorldProvider> providerClass, boolean autoRegister) {
        // Since satellites need a static ID, the subclass method will not work correctly.
        throw new UnsupportedOperationException(
                "Satellite registered using an outdated method (setDimensionInfo)! Tell Galacticraft addon authors to update to the latest API.");
    }

    public CelestialBody setDimensionInfo(
            int providerIdDynamic, int providerIdStatic, Class<? extends WorldProvider> providerClass) {
        this.dimensionID = providerIdDynamic;
        this.dimensionIdStatic = providerIdStatic;
        this.providerClass = providerClass;
        this.autoRegisterDimension = false; // Addons need to register satellites manually
        this.isReachable = true;
        return this;
    }

    @Override
    public int getID() {
        return GalaxyRegistry.getSatelliteID(this.bodyName);
    }

    @Override
    public String getUnlocalizedNamePrefix() {
        return "satellite";
    }

    public int getDimensionIdStatic() {
        return dimensionIdStatic;
    }
}
