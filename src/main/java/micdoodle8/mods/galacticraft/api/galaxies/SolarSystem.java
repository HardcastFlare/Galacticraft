package micdoodle8.mods.galacticraft.api.galaxies;

import com.google.common.collect.Lists;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import micdoodle8.mods.galacticraft.api.vector.Vector3;
import net.minecraft.util.StatCollector;

public class SolarSystem implements IDisplayableBody {
    protected final String systemName;
    protected String unlocalizedName;
    protected Vector3 mapPosition = null;
    protected Star mainStar = null;
    protected String unlocalizedGalaxyName;

    public SolarSystem(String solarSystem, String parentGalaxy) {
        this.systemName = solarSystem.toLowerCase(Locale.ENGLISH);
        this.unlocalizedName = solarSystem;
        this.unlocalizedGalaxyName = parentGalaxy;
    }

    public String getParentName() {
        return getLocalizedName();
    }

    public String getGrandparentName() {
        return getLocalizedParentGalaxyName();
    }

    public List<CelestialBody> getSiblings() {
        return Lists.newArrayList();
    }

    public List<CelestialBody> getChildren() {
        List<CelestialBody> bodyList = Lists.newArrayList();
        List<Planet> planets = GalaxyRegistry.getPlanetsForSolarSystem(this);
        bodyList.addAll(planets);
        Collections.sort(bodyList);
        return bodyList;
    }

    public String getName() {
        return this.systemName;
    }

    public final int getID() {
        return GalaxyRegistry.getSolarSystemID(this.systemName);
    }

    public String getLocalizedName() {
        String s = this.getUnlocalizedName();
        return s == null ? "" : StatCollector.translateToLocal(s);
    }

    public String getUnlocalizedName() {
        return "solarsystem." + this.unlocalizedName;
    }

    public Vector3 getMapPosition() {
        return this.mapPosition;
    }

    public SolarSystem setMapPosition(Vector3 mapPosition) {
        mapPosition.scale(500D);
        this.mapPosition = mapPosition;
        return this;
    }

    public Star getMainStar() {
        return this.mainStar;
    }

    public SolarSystem setMainStar(Star star) {
        this.mainStar = star;
        return this;
    }

    public String getLocalizedParentGalaxyName() {
        String s = this.getUnlocalizedParentGalaxyName();
        return s == null ? "" : StatCollector.translateToLocal(s);
    }

    public String getUnlocalizedParentGalaxyName() {
        return "galaxy." + this.unlocalizedGalaxyName;
    }
}
