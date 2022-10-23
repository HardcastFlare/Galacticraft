package micdoodle8.mods.galacticraft.api.galaxies;

import com.google.common.collect.Lists;
import java.util.Collections;
import java.util.List;

public class Star extends CelestialBody {
    protected SolarSystem parentSolarSystem = null;

    public Star(String planetName) {
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

        // Preparation to support multi star solar systems
        // for (Planet planet : GalaxyRegistry.getRegisteredStars().values()) {
        //    SolarSystem solarSystem1 = planet.getParentSolarSystem();

        //    if (solarSystem1 != null && solarSystem1.equals(solarSystem)) {
        //        bodyList.add(planet);
        //    }
        // }

        Collections.sort(bodyList);
        return bodyList;
    }

    @Override
    public List<CelestialBody> getChildren() {
        List<CelestialBody> bodyList = Lists.newArrayList();

        List<Planet> planets = GalaxyRegistry.getPlanetsForSolarSystem(getParentSolarSystem());
        bodyList.addAll(planets);

        Collections.sort(bodyList);
        return bodyList;
    }

    public SolarSystem getParentSolarSystem() {
        return this.parentSolarSystem;
    }

    @Override
    public int getID() {
        return this.parentSolarSystem.getID();
    }

    @Override
    public String getUnlocalizedNamePrefix() {
        return "star";
    }

    public Star setParentSolarSystem(SolarSystem galaxy) {
        this.parentSolarSystem = galaxy;
        return this;
    }
}
