package micdoodle8.mods.galacticraft.api.galaxies;

import com.google.common.collect.Lists;
import java.util.Collections;
import java.util.List;

public class Planet extends CelestialBody {
    protected SolarSystem parentSolarSystem = null;

    public Planet(String planetName) {
        super(planetName);
    }

    @Override
    public String getParentName() {
        SolarSystem parentSolarSystem = getParentSolarSystem();

        if (parentSolarSystem != null) {
            return parentSolarSystem.getLocalizedName();
        }

        return "Null";
    }

    @Override
    public String getGrandparentName() {
        SolarSystem parentSolarSystem = getParentSolarSystem();

        if (parentSolarSystem != null) {
            return parentSolarSystem.getLocalizedParentGalaxyName();
        }

        return "Null";
    }

    @Override
    public List<CelestialBody> getSiblings() {
        List<CelestialBody> bodyList = Lists.newArrayList();
        SolarSystem solarSystem = getParentSolarSystem();

        for (Planet planet : GalaxyRegistry.getRegisteredPlanets().values()) {
            SolarSystem solarSystem1 = planet.getParentSolarSystem();

            if (solarSystem1 != null && solarSystem1.equals(solarSystem)) {
                bodyList.add(planet);
            }
        }

        Collections.sort(bodyList);
        return bodyList;
    }

    @Override
    public List<CelestialBody> getChildren() {
        List<CelestialBody> bodyList = Lists.newArrayList();
        List<Moon> moons = GalaxyRegistry.getMoonsForPlanet(this);
        bodyList.addAll(moons);
        Collections.sort(bodyList);
        return bodyList;
    }

    public SolarSystem getParentSolarSystem() {
        return this.parentSolarSystem;
    }

    @Override
    public int getID() {
        return GalaxyRegistry.getPlanetID(this.bodyName);
    }

    @Override
    public String getUnlocalizedNamePrefix() {
        return "planet";
    }

    public Planet setParentSolarSystem(SolarSystem galaxy) {
        this.parentSolarSystem = galaxy;
        return this;
    }
}
