package micdoodle8.mods.galacticraft.api.galaxies;

import com.google.common.collect.Lists;
import java.util.Collections;
import java.util.List;

public class Moon extends CelestialBody implements IChildBody {
    protected Planet parentPlanet = null;

    public Moon(String moonName) {
        super(moonName);
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

    public Moon setParentPlanet(Planet planet) {
        this.parentPlanet = planet;
        return this;
    }

    @Override
    public int getID() {
        return GalaxyRegistry.getMoonID(this.bodyName);
    }

    @Override
    public String getUnlocalizedNamePrefix() {
        return "moon";
    }

    @Override
    public Planet getParentPlanet() {
        return this.parentPlanet;
    }
}
