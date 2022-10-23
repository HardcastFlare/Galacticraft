package micdoodle8.mods.galacticraft.api.galaxies;

import java.util.List;

public interface IDisplayableBody {
    /** Get the name of the parent of this celestial object
     *
     * @return Name of the parent celestial object if valid, else the string "Null"
     */
    public abstract String getParentName();

    /** Get the name of the grandparent of this celestial object
     *
     * @return Name of the grandparent celestial object if valid, else the string "Null"
     */
    public abstract String getGrandparentName();

    /** Get the siblings of this celestial object
     *
     * @return Siblings of this celestial object
     */
    public abstract List<CelestialBody> getSiblings();

    /** Get the children of this celestial object
     *
     * @return Children of this celestial object
     */
    public abstract List<CelestialBody> getChildren();
}
