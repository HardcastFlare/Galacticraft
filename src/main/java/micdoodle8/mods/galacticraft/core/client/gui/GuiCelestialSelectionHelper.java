package micdoodle8.mods.galacticraft.core.client.gui;

import cpw.mods.fml.client.FMLClientHandler;
import micdoodle8.mods.galacticraft.api.galaxies.*;
import micdoodle8.mods.galacticraft.api.recipe.SpaceStationRecipe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatAllowedCharacters;
import org.lwjgl.util.vector.Vector2f;

/** Helper class for the GuiCelestialSelection screen
 * @author minecraft7771
 */
public class GuiCelestialSelectionHelper {

    /** Get the interpolated point between two points, depending on the input time
     *
     * @param v0 Point 1
     * @param v1 Point 2
     * @param t Time
     * @return Point on the linear connection between the two points
     */
    public static float lerp(float v0, float v1, float t) {
        return v0 + t * (v1 - v0);
    }

    /** Get the interpolated point between two points, depending on the input time
     *
     * @param v0 Point 1
     * @param v1 Point 2
     * @param t Time
     * @return Point on the linear connection between the two points
     */
    public static Vector2f lerpVec2(Vector2f v0, Vector2f v1, float t) {
        return new Vector2f(v0.x + t * (v1.x - v0.x), v0.y + t * (v1.y - v0.y));
    }

    /** Check if the input string is valid for chat display
     *
     * @param string String that will be checked
     * @return True if valid, else false
     */
    public static boolean isValid(String string) {
        if (string.length() <= 0) {
            return false;
        }

        return ChatAllowedCharacters.isAllowedCharacter(string.charAt(string.length() - 1));
    }

    /** Get the scale of a celestial body
     *
     * @param celestialBody Celestial body of which the scale will be gotten
     * @return Scale of the inptu celestial body
     */
    public static float getScale(CelestialBody celestialBody) {
        return 3.0F
                * celestialBody.getRelativeDistanceFromCenter().unScaledDistance
                * (celestialBody instanceof Planet ? 25.0F : 1.0F / 5.0F);
    }

    /** Get the ID of the input satellites parent
     *
     * @param satellite Satellite of which the parent ID will be gotten
     * @return ID of the parent
     */
    public static int getSatelliteParentID(Satellite satellite) {
        return satellite.getParentPlanet().getDimensionID();
    }

    /** Get the amount of the input item, that the player holds in their inventory
     *
     * @param stack Item that should be checked
     * @return Amount of the input item
     */
    public static int getAmountInInventory(ItemStack stack) {
        int amountInInv = 0;

        for (int x = 0;
                x
                        < FMLClientHandler.instance()
                                .getClientPlayerEntity()
                                .inventory
                                .getSizeInventory();
                x++) {
            final ItemStack slot = FMLClientHandler.instance()
                    .getClientPlayerEntity()
                    .inventory
                    .getStackInSlot(x);

            if (slot != null) {
                if (SpaceStationRecipe.checkItemEquals(stack, slot)) {
                    amountInInv += slot.stackSize;
                }
            }
        }

        return amountInInv;
    }
}
