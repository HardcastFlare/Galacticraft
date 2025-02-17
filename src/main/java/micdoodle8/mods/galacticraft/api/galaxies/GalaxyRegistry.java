package micdoodle8.mods.galacticraft.api.galaxies;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import net.minecraftforge.common.MinecraftForge;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import cpw.mods.fml.common.eventhandler.Event;

/**
 * Credits to KingLemming and CovertJaguar, since this is based on the Liquid/Fluid API
 */
public class GalaxyRegistry {

    static int maxSolarSystemID = 0;
    static int maxPlanetID = 0;
    static int maxMoonID = 0;
    static int maxSatelliteID = 0;
    static HashMap<String, SolarSystem> solarSystems = Maps.newHashMap();
    static BiMap<String, Integer> solarSystemIDs = HashBiMap.create();
    static TreeMap<String, Planet> planets = Maps.newTreeMap();
    static BiMap<String, Integer> planetIDs = HashBiMap.create();
    static TreeMap<String, Moon> moons = Maps.newTreeMap();
    static BiMap<String, Integer> moonIDs = HashBiMap.create();
    static HashMap<String, Satellite> satellites = Maps.newHashMap();
    static BiMap<String, Integer> satelliteIDs = HashBiMap.create();
    static HashMap<Planet, List<Moon>> moonList = Maps.newHashMap();
    static HashMap<CelestialBody, List<Satellite>> satelliteList = Maps.newHashMap();
    static HashMap<SolarSystem, List<Planet>> solarSystemList = Maps.newHashMap();

    public static CelestialBody getCelestialBodyFromDimensionID(int dimensionID) {
        for (final Planet planet : GalaxyRegistry.planets.values()) {
            if (planet.getDimensionID() == dimensionID) {
                return planet;
            }
        }

        for (final Moon moon : GalaxyRegistry.moons.values()) {
            if (moon.getDimensionID() == dimensionID) {
                return moon;
            }
        }

        for (final Satellite satellite : GalaxyRegistry.satellites.values()) {
            if (satellite.getDimensionID() == dimensionID) {
                return satellite;
            }
        }

        return null;
    }

    public static void refreshGalaxies() {
        GalaxyRegistry.moonList.clear();
        GalaxyRegistry.satelliteList.clear();
        GalaxyRegistry.solarSystemList.clear();

        for (final Moon moon : GalaxyRegistry.getRegisteredMoons().values()) {
            final Planet planet = moon.getParentPlanet();
            List<Moon> listOfMoons = GalaxyRegistry.moonList.get(planet);
            if (listOfMoons == null) {
                listOfMoons = new ArrayList<>();
            }
            listOfMoons.add(moon);
            GalaxyRegistry.moonList.put(planet, listOfMoons);
        }

        for (final Satellite satellite : GalaxyRegistry.getRegisteredSatellites().values()) {
            final CelestialBody celestialBody = satellite.getParentPlanet();
            List<Satellite> satelliteList1 = GalaxyRegistry.satelliteList.get(celestialBody);
            if (satelliteList1 == null) {
                satelliteList1 = new ArrayList<>();
            }
            satelliteList1.add(satellite);
            GalaxyRegistry.satelliteList.put(celestialBody, satelliteList1);
        }

        for (final Planet planet : GalaxyRegistry.getRegisteredPlanets().values()) {
            final SolarSystem solarSystem = planet.getParentSolarSystem();
            List<Planet> planetList = GalaxyRegistry.solarSystemList.get(solarSystem);
            if (planetList == null) {
                planetList = new ArrayList<>();
            }
            planetList.add(planet);
            GalaxyRegistry.solarSystemList.put(solarSystem, planetList);
        }
    }

    public static List<Planet> getPlanetsForSolarSystem(SolarSystem solarSystem) {
        final List<Planet> solarSystemListLocal = GalaxyRegistry.solarSystemList.get(solarSystem);

        if (solarSystemListLocal == null) {
            return new ArrayList<>();
        }

        return ImmutableList.copyOf(solarSystemListLocal);
    }

    public static List<Moon> getMoonsForPlanet(Planet planet) {
        final List<Moon> moonListLocal = GalaxyRegistry.moonList.get(planet);

        if (moonListLocal == null) {
            return new ArrayList<>();
        }

        return ImmutableList.copyOf(moonListLocal);
    }

    public static List<Satellite> getSatellitesForCelestialBody(CelestialBody celestialBody) {
        final List<Satellite> satelliteList1 = GalaxyRegistry.satelliteList.get(celestialBody);

        if (satelliteList1 == null) {
            return new ArrayList<>();
        }

        return ImmutableList.copyOf(satelliteList1);
    }

    public static CelestialBody getCelestialBodyFromUnlocalizedName(String unlocalizedName) {
        for (final Planet planet : GalaxyRegistry.planets.values()) {
            if (planet.getUnlocalizedName().equals(unlocalizedName)) {
                return planet;
            }
        }

        for (final Moon moon : GalaxyRegistry.moons.values()) {
            if (moon.getUnlocalizedName().equals(unlocalizedName)) {
                return moon;
            }
        }

        return null;
    }

    public static boolean registerSolarSystem(SolarSystem solarSystem) {
        if (GalaxyRegistry.solarSystemIDs.containsKey(solarSystem.getName())) {
            return false;
        }

        GalaxyRegistry.solarSystems.put(solarSystem.getName(), solarSystem);
        GalaxyRegistry.solarSystemIDs.put(solarSystem.getName(), ++GalaxyRegistry.maxSolarSystemID);

        MinecraftForge.EVENT_BUS
                .post(new SolarSystemRegisterEvent(solarSystem.getName(), GalaxyRegistry.maxSolarSystemID));
        return true;
    }

    public static boolean registerPlanet(Planet planet) {
        if (GalaxyRegistry.planetIDs.containsKey(planet.getName())) {
            return false;
        }

        GalaxyRegistry.planets.put(planet.getName(), planet);
        GalaxyRegistry.planetIDs.put(planet.getName(), ++GalaxyRegistry.maxPlanetID);

        MinecraftForge.EVENT_BUS.post(new PlanetRegisterEvent(planet.getName(), GalaxyRegistry.maxPlanetID));
        return true;
    }

    public static boolean registerMoon(Moon moon) {
        if (GalaxyRegistry.moonIDs.containsKey(moon.getName())) {
            return false;
        }

        GalaxyRegistry.moons.put(moon.getName(), moon);
        GalaxyRegistry.moonIDs.put(moon.getName(), ++GalaxyRegistry.maxMoonID);

        MinecraftForge.EVENT_BUS.post(new MoonRegisterEvent(moon.getName(), GalaxyRegistry.maxMoonID));
        return true;
    }

    public static boolean registerSatellite(Satellite satellite) {
        if (GalaxyRegistry.satelliteIDs.containsKey(satellite.getName())) {
            return false;
        }

        if (satellite.getParentPlanet() == null) {
            throw new RuntimeException("Registering satellite without a parent!!!");
        }

        GalaxyRegistry.satellites.put(satellite.getName(), satellite);
        GalaxyRegistry.satelliteIDs.put(satellite.getName(), ++GalaxyRegistry.maxSatelliteID);

        MinecraftForge.EVENT_BUS.post(new SatelliteRegisterEvent(satellite.getName(), GalaxyRegistry.maxSatelliteID));
        return true;
    }

    /**
     * Returns a read-only map containing Solar System Names and their associated Solar Systems.
     */
    public static Map<String, SolarSystem> getRegisteredSolarSystems() {
        return ImmutableMap.copyOf(GalaxyRegistry.solarSystems);
    }

    /**
     * Returns a read-only map containing Solar System Names and their associated IDs.
     */
    public static Map<String, Integer> getRegisteredSolarSystemIDs() {
        return ImmutableMap.copyOf(GalaxyRegistry.solarSystemIDs);
    }

    /**
     * Returns a read-only map containing Planet Names and their associated Planets.
     */
    public static Map<String, Planet> getRegisteredPlanets() {
        return (Map<String, Planet>) GalaxyRegistry.planets.clone();
    }

    /**
     * Returns a read-only map containing Planet Names and their associated IDs.
     */
    public static Map<String, Integer> getRegisteredPlanetIDs() {
        return ImmutableMap.copyOf(GalaxyRegistry.planetIDs);
    }

    /**
     * Returns a read-only map containing Moon Names and their associated Moons.
     */
    public static Map<String, Moon> getRegisteredMoons() {
        return (Map<String, Moon>) GalaxyRegistry.moons.clone();
    }

    /**
     * Returns a read-only map containing Moon Names and their associated IDs.
     */
    public static Map<String, Integer> getRegisteredMoonIDs() {
        return ImmutableMap.copyOf(GalaxyRegistry.moonIDs);
    }

    /**
     * Returns a read-only map containing Satellite Names and their associated Satellite.
     */
    public static Map<String, Satellite> getRegisteredSatellites() {
        return ImmutableMap.copyOf(GalaxyRegistry.satellites);
    }

    /**
     * Returns a read-only map containing Satellite Names and their associated IDs.
     */
    public static Map<String, Integer> getRegisteredSatelliteIDs() {
        return ImmutableMap.copyOf(GalaxyRegistry.satelliteIDs);
    }

    public static int getSolarSystemID(String solarSystemName) {
        return GalaxyRegistry.solarSystemIDs.get(solarSystemName);
    }

    public static int getPlanetID(String planetName) {
        return GalaxyRegistry.planetIDs.get(planetName);
    }

    public static int getMoonID(String moonName) {
        return GalaxyRegistry.moonIDs.get(moonName);
    }

    public static int getSatelliteID(String satelliteName) {
        return GalaxyRegistry.satelliteIDs.get(satelliteName);
    }

    public static class SolarSystemRegisterEvent extends Event {

        public final String solarSystemName;
        public final int solarSystemID;

        public SolarSystemRegisterEvent(String solarSystemName, int solarSystemID) {
            this.solarSystemName = solarSystemName;
            this.solarSystemID = solarSystemID;
        }
    }

    public static class PlanetRegisterEvent extends Event {

        public final String planetName;
        public final int planetID;

        public PlanetRegisterEvent(String planetName, int planetID) {
            this.planetName = planetName;
            this.planetID = planetID;
        }
    }

    public static class MoonRegisterEvent extends Event {

        public final String moonName;
        public final int moonID;

        public MoonRegisterEvent(String moonName, int moonID) {
            this.moonName = moonName;
            this.moonID = moonID;
        }
    }

    public static class SatelliteRegisterEvent extends Event {

        public final String satelliteName;
        public final int satelliteID;

        public SatelliteRegisterEvent(String satelliteName, int satelliteID) {
            this.satelliteName = satelliteName;
            this.satelliteID = satelliteID;
        }
    }
}
