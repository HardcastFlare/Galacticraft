package micdoodle8.mods.galacticraft.core.recipe;

import appeng.api.AEApi;
import appeng.api.util.AEColor;
import cpw.mods.fml.common.registry.GameRegistry;
import gregtech.api.GregTech_API;
import gregtech.api.enums.ItemList;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.objects.ItemData;
import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.GT_OreDictUnificator;
import java.lang.reflect.Method;
import java.util.*;

import ic2.core.Ic2Items;
import micdoodle8.mods.galacticraft.api.GalacticraftRegistry;
import micdoodle8.mods.galacticraft.api.recipe.CircuitFabricatorRecipes;
import micdoodle8.mods.galacticraft.api.recipe.CompressorRecipes;
import micdoodle8.mods.galacticraft.api.recipe.SpaceStationRecipe;
import micdoodle8.mods.galacticraft.api.world.SpaceStationType;
import micdoodle8.mods.galacticraft.core.Constants;
import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import micdoodle8.mods.galacticraft.core.blocks.BlockEnclosed.EnumEnclosedBlock;
import micdoodle8.mods.galacticraft.core.blocks.GCBlocks;
import micdoodle8.mods.galacticraft.core.items.GCItems;
import micdoodle8.mods.galacticraft.core.items.ItemBasic;
import micdoodle8.mods.galacticraft.core.items.ItemParaChute;
import micdoodle8.mods.galacticraft.core.util.*;
import micdoodle8.mods.galacticraft.planets.mars.items.MarsItems;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class RecipeManagerGTNH {
    public static ArrayList<ItemStack> aluminumIngots = new ArrayList<>();

    private static ItemStack compressedAl = GT_OreDictUnificator.get(OrePrefixes.compressed, Materials.Aluminium, 1);
    private static ItemStack compressedIron = GT_OreDictUnificator.get(OrePrefixes.compressed, Materials.Iron, 1);
    private static ItemStack compressedSteel = GT_OreDictUnificator.get(OrePrefixes.compressed, Materials.Steel, 1);
    private static ItemStack compressedBronze = GT_OreDictUnificator.get(OrePrefixes.compressed, Materials.Bronze, 1);
    private static ItemStack compressedTin = GT_OreDictUnificator.get(OrePrefixes.compressed, Materials.Tin, 1);
    private static ItemStack compressedCopper = GT_OreDictUnificator.get(OrePrefixes.compressed, Materials.Copper, 1);
    private static ItemStack compressedTi = GT_OreDictUnificator.get(OrePrefixes.compressed, Materials.Titanium, 1);
    // TODO? Not oredicted in dev, results in missing item in recipes
    private static ItemStack compressedDesh = GT_OreDictUnificator.get(OrePrefixes.compressed, Materials.Desh, 1);
    private static ItemStack compressedMeteoricIron = GT_OreDictUnificator.get(OrePrefixes.compressed, Materials.MeteoricIron, 1);

    private static ItemStack solarModuleSingle = new ItemStack(GCItems.basicItem, 1, 0);
    private static ItemStack solarModuleFull = new ItemStack(GCItems.basicItem, 1, 1);
    private static ItemStack frequencyModule = new ItemStack(GCItems.basicItem, 1, 19);

    private static ItemStack bWafer = GT_OreDictUnificator.get(OrePrefixes.wafer, Materials.Basic, 1);
    private static ItemStack advWafer = GT_OreDictUnificator.get(OrePrefixes.wafer, Materials.Advanced, 1);
    private static ItemStack gcAlWire = new ItemStack(GCBlocks.aluminumWire, 1, 0);
    private static ItemStack gcHeavyAlWire = new ItemStack(GCBlocks.aluminumWire, 1, 1);
    private static ItemStack oxygenPipe = new ItemStack(GCBlocks.oxygenPipe, 1, 0);
    private static ItemStack airVent = new ItemStack(GCItems.oxygenVent, 1, 0);
    private static ItemStack heavyPlating = new ItemStack(GCItems.heavyPlatingTier1, 1, 0);
    private static ItemStack heavyDutyPlate = GT_ModHandler.getModItem("GalacticraftMars", "item.itemBasicAsteroids", 1, 0);
    private static ItemStack tinDecoBlock = new ItemStack(GCBlocks.basicBlock, 1, 4);
    private static ItemStack tinDecoBlock2 = new ItemStack(GCBlocks.basicBlock, 1, 3);
    private static ItemStack sensorLens = new ItemStack(GCItems.sensorLens, 1, 0);
    private static ItemStack steelPole = new ItemStack(GCItems.flagPole, 1, 0);

    private static ItemStack advAlloyPlate = GT_OreDictUnificator.get(OrePrefixes.plateAlloy, Materials.Advanced, 1);
    // TODO probably fix
    private static ItemStack compressedDualTitanium = GT_ModHandler.getModItem("dreamcraft", "item.TitaniumDualCompressedPlates", 1, 0);
    private static ItemStack screwMeteor = GT_OreDictUnificator.get(OrePrefixes.screw, Materials.MeteoricSteel, 1);
    private static ItemStack screwSteel = GT_OreDictUnificator.get(OrePrefixes.screw, Materials.Steel, 1);
    private static ItemStack screwSSteel = GT_OreDictUnificator.get(OrePrefixes.screw, Materials.StainlessSteel, 1);
    private static ItemStack boltSSteel = GT_OreDictUnificator.get(OrePrefixes.bolt, Materials.StainlessSteel, 1);
    private static ItemStack boltTSteel = GT_OreDictUnificator.get(OrePrefixes.bolt, Materials.TungstenSteel, 1);
    private static ItemStack boltDesh = GT_OreDictUnificator.get(OrePrefixes.bolt, Materials.Desh, 1);
    // TODO probably fix
    private static ItemStack plateDesh = GT_ModHandler.getModItem("GalacticraftMars", "item.null", 1, 5);
    private static ItemStack ringDesh = GT_OreDictUnificator.get(OrePrefixes.ring, Materials.Desh, 1);
    private static ItemStack ringRedAlloy = GT_OreDictUnificator.get(OrePrefixes.ring, Materials.RedAlloy, 1);
    private static ItemStack alFoil = GT_OreDictUnificator.get(OrePrefixes.foil, Materials.Aluminium, 1);
    private static ItemStack tripleTrinium = GT_OreDictUnificator.get(OrePrefixes.plateTriple, Materials.Trinium, 1);

    private static ItemStack motorLV = ItemList.Electric_Motor_LV.get(1);
    private static ItemStack motorHV = ItemList.Electric_Motor_HV.get(1);
    private static ItemStack conveyorMV = ItemList.Conveyor_Module_MV.get(1);
    private static ItemStack conveyorHV = ItemList.Conveyor_Module_HV.get(1);
    private static ItemStack pistonHV = ItemList.Electric_Piston_HV.get(1);
    private static ItemStack solarPanel = ItemList.Cover_SolarPanel.get(1);
    private static ItemStack pumpLV = ItemList.Pump_LV.get(1);
    private static ItemStack pumpHV = ItemList.Pump_HV.get(1);
    private static ItemStack sensorLV = ItemList.Sensor_LV.get(1);
    private static ItemStack sensorHV = ItemList.Sensor_HV.get(1);
    private static ItemStack emitterHV = ItemList.Emitter_HV.get(1);
    private static ItemStack steelRotor = GT_OreDictUnificator.get(OrePrefixes.rotor, Materials.Steel, 1);
    private static ItemStack rubberSheet = GT_ModHandler.getModItem("IC2", "blockRubber", 1, 0);
    
    private static ItemStack circuitAdv = GT_OreDictUnificator.get(OrePrefixes.circuit, Materials.Advanced, 1);
    private static ItemStack circuitData = GT_OreDictUnificator.get(OrePrefixes.circuit, Materials.Data, 1);

    private static ItemStack cellEmpty = GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Empty, 1);
    private static ItemStack largeCellSteel = ItemList.Large_Fluid_Cell_Steel.get(1);
    private static ItemStack largeCellTungSteel = ItemList.Large_Fluid_Cell_TungstenSteel.get(1);
    private static ItemStack largeCellIridium = ItemList.Large_Fluid_Cell_Iridium.get(1);
    private static ItemStack largeCellOsmium = ItemList.Large_Fluid_Cell_Osmium.get(1);

    private static ItemStack pipeMediumSteel = GT_OreDictUnificator.get(OrePrefixes.pipeMedium, Materials.Steel, 1);
    private static ItemStack pipeMediumBrass = GT_OreDictUnificator.get(OrePrefixes.pipeMedium, Materials.Brass, 1);

    private static ItemStack casingSolidSteel = ItemList.Casing_SolidSteel.get(1);

    private static ItemStack chestBufferHV = ItemList.Automation_ChestBuffer_HV.get(1);

    private static ItemStack computerMonitorCover = ItemList.Cover_Screen.get(1);

    private static ItemStack ReinforcedGlass = GT_OreDictUnificator.get(OrePrefixes.glass, Materials.Reinforced, 1);
    private static ItemStack Stick = GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Wood, 1);
    private static ItemStack Wool = GT_OreDictUnificator.get(OrePrefixes.blockWool, 1);

    private static ItemStack wireRedAlloy1x = GT_OreDictUnificator.get(OrePrefixes.wireGt01, Materials.RedAlloy, 1);

    private static ItemStack cableCopper1x = GT_OreDictUnificator.get(OrePrefixes.cableGt01, Materials.AnyCopper, 1);
    private static ItemStack cableAlu2x = GT_OreDictUnificator.get(OrePrefixes.cableGt02, Materials.Aluminium, 1);
    private static ItemStack cableGold2x = GT_OreDictUnificator.get(OrePrefixes.cableGt02, Materials.Gold, 1);

    private static ItemStack lensDiamond = GT_OreDictUnificator.get(OrePrefixes.lens, Materials.Diamond, 1);
    private static ItemStack lensReinfGlass = GT_OreDictUnificator.get(OrePrefixes.lens, Materials.ReinforceGlass, 1);

    private static String ironBars = "iron_bars";
    private static String string = "string";
    private static String hHammer = "craftingToolHardHammer";
    private static String wrench = "craftingToolWrench";
    private static String file = "craftingToolFile";
    private static String screwdriver = "craftingToolScrewdriver";

    private static ItemStack canisterEmpty = new ItemStack(GCItems.oilCanister, 1, GCItems.oilCanister.getMaxDamage());
    private static ItemStack fuelCanisterFilled = new ItemStack(GCItems.fuelCanister, 1, 1);

    private static ItemStack ironBlock = GT_OreDictUnificator.get(OrePrefixes.block, Materials.Iron, 1);
    private static ItemStack steelBlock = GT_OreDictUnificator.get(OrePrefixes.block, Materials.Steel, 1);

    private static ItemStack stone = GT_OreDictUnificator.get(OrePrefixes.stone, 1);
    private static ItemStack hopper = GT_OreDictUnificator.get(OrePrefixes.block, "Hopper", 1);

    // Ported from GTNH zenscript recipes
    public static void loadRecipes() {

        addShapedRecipes();

        if (CompatibilityManager.isBCraftTransportLoaded()) {
            RecipeManagerGTNH.addBuildCraftCraftingRecipes();
        }

        if (CompatibilityManager.isIc2Loaded()) {
            RecipeManagerGTNH.addIndustrialCraft2Recipes();
        }

        if (CompatibilityManager.isAppEngLoaded()) {
            RecipeManagerGTNH.addAppEngRecipes();
        }

        RecipeManagerGTNH.addUniversalRecipes();

        RecipeManagerGTNH.addExNihiloRecipes();
    }

    /**
     * Copied from GTNH core mod
     * Utility fonction copied from GT++ to strip nulls from the array.
     * @param v The object array to process.
     * @return An object array with null elements removed
     */
    public static Object[] removeNulls(final Object[] v) {
        List<Object> list = new ArrayList<Object>(Arrays.asList(v));
        list.removeAll(Collections.singleton(null));
        return list.toArray(new Object[list.size()]);
    }

    /**
     * Copied from GTNH core mod.
     * Code strongly inspired from what did alkalus in GT++.
     * @param inputs Object array that should contain only the 9 object representing the items in the crafting grid.
     *               Acceptable types are Item, Block, ItemStack, ItemData and null (for empty slots).
     * @param aOutputStack The result of the crafting recipe
     * @return a boolean
     */
    static boolean addShapedRecipe(ItemStack aOutputStack, Object[] inputs) {
        Object[] slots = new Object[9];
        StringBuilder fullString = new StringBuilder();
        String slotMappings = "abcdefghi";
        try {
            for (int i = 0; i < 9; i++) {
                Object o = inputs[i];
                if (o instanceof ItemStack) {
                    final ItemStack itemStack = ((ItemStack) o).copy();
                    itemStack.stackSize = 1;
                    slots[i] = itemStack.copy();
                    fullString.append(slotMappings.charAt(i));
                } else if (o instanceof Item) {
                    final ItemStack itemStack = new ItemStack((Item) o, 1);
                    slots[i] = itemStack.copy();
                    fullString.append(slotMappings.charAt(i));
                } else if (o instanceof Block) {
                    final ItemStack itemStack = new ItemStack((Block) o, 1);
                    slots[i] = itemStack.copy();
                    fullString.append(slotMappings.charAt(i));
                } else if (o instanceof String) {
                    slots[i] = o;
                    fullString.append(slotMappings.charAt(i));
                } else if (o instanceof ItemData) {
                    ItemData data = (ItemData) o;
                    ItemStack itemStack = GT_OreDictUnificator.get(data.mPrefix, data.mMaterial.mMaterial, 1);
                    if (itemStack == null) {
                        throw new NullPointerException("bad item passed in the recipe");
                    } else {
                        slots[i] = itemStack;
                        fullString.append(slotMappings.charAt(i));
                    }

                } else if (o == null) {
                    slots[i] = null;
                    fullString.append(" ");
                } else {
                    slots[i] = null;
                    throw new NullPointerException("bad recipe generated");
                }
            }
            String aRow1 = fullString.substring(0, 3);
            String aRow2 = fullString.substring(3, 6);
            String aRow3 = fullString.substring(6, 9);
            String[] recipeRows = new String[] {aRow1, aRow2, aRow3};
            Object[] recipeInputs = new Object[19];
            recipeInputs[0] = recipeRows;
            int aIndex = 0;
            for (int u = 1; u < 20; u += 2) {
                if (aIndex == 9) {
                    break;
                }
                if (fullString.charAt(aIndex) != (' ')) {
                    recipeInputs[u] = fullString.charAt(aIndex);
                    recipeInputs[u + 1] = slots[aIndex];
                }
                aIndex++;
            }
            recipeInputs = removeNulls(recipeInputs);
            ShapedOreRecipe aRecipe = new ShapedOreRecipe(aOutputStack, recipeInputs);
            GameRegistry.addRecipe(aRecipe);
        } catch (Exception e) {
            GCLog.severe("a recipe went wrong");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private static void addShapedRecipes() {
        // --- Rocket Launch Pad
        addShapedRecipe(new ItemStack(GCBlocks.landingPad, 3, 0), new Object[] {
            compressedIron, compressedIron, compressedIron,
            advAlloyPlate, advAlloyPlate, advAlloyPlate,
            ironBlock, ironBlock, ironBlock
        });

        // --- Buggy Fueling Pad
        addShapedRecipe(new ItemStack(GCBlocks.landingPad, 3, 1), new Object[] {
            compressedSteel, compressedSteel, compressedSteel,
            advAlloyPlate, advAlloyPlate, advAlloyPlate,
            steelBlock, steelBlock, steelBlock
        });

        // --- Oxygen Collector
        addShapedRecipe(new ItemStack(GCBlocks.oxygenCollector, 1, 0), new Object[] {
            compressedAl, GCItems.oxygenConcentrator, compressedAl,
            airVent, GCItems.oxygenFan, motorHV,
            compressedSteel, cableAlu2x, compressedSteel
        });

        // --- Oxygen Compressor
        addShapedRecipe(new ItemStack(GCBlocks.oxygenCompressor, 1, 0), new Object[] {
            compressedAl, GCItems.oxygenConcentrator, compressedAl,
            pistonHV, canisterEmpty, motorHV,
            compressedSteel, compressedBronze, compressedSteel
        });

        // --- Oxygen Decompressor
        addShapedRecipe(new ItemStack(GCBlocks.oxygenCompressor, 1, 4), new Object[] {
            compressedAl, GCItems.oxygenConcentrator, compressedAl,
            motorHV, canisterEmpty, GCItems.oxygenFan,
            compressedSteel, compressedBronze, compressedSteel
        });

        // --- Oxygen Storage Module
        addShapedRecipe(new ItemStack(GCBlocks.machineBase2, 1, 8), new Object[] {
            compressedSteel, GCItems.oxTankUltraHeavy, compressedSteel,
            GCItems.oxTankUltraHeavy, casingSolidSteel, GCItems.oxTankUltraHeavy,
            compressedSteel, GCItems.oxTankUltraHeavy, compressedSteel
        });

        // --- Oxygen Bubble Distributor
        addShapedRecipe(new ItemStack(GCBlocks.oxygenDistributor, 1, 0), new Object[] {
            compressedAl, GCItems.oxygenFan, compressedAl,
            airVent, motorHV, airVent,
            compressedSteel, GCItems.oxygenFan, compressedSteel
        });

        // --- Oxygen Sealer
        addShapedRecipe(new ItemStack(GCBlocks.oxygenSealer, 1, 0), new Object[] {
            compressedAl, airVent, compressedAl,
            airVent, GCBlocks.oxygenDistributor, airVent,
            compressedDesh, GCBlocks.oxygenDetector, compressedDesh
        });

        // --- Oxygen Detector
        addShapedRecipe(new ItemStack(GCBlocks.oxygenDetector, 1, 0), new Object[] {
            compressedDesh, compressedSteel, compressedDesh,
            airVent, sensorHV, airVent,
            compressedAl, wireRedAlloy1x, compressedAl
        });

        // --- Fuel Loader
        addShapedRecipe(new ItemStack(GCBlocks.fuelLoader, 1, 0), new Object[] {
            compressedSteel, bWafer, compressedSteel,
            pumpHV, largeCellSteel, motorHV,
            compressedAl, pipeMediumSteel, compressedAl
        });

        // --- Cargo Loader
        // TODO: fix?
        addShapedRecipe(new ItemStack(GCBlocks.cargoLoader, 1, 0), new Object[] {
            compressedAl, hopper, compressedAl,
            conveyorHV, chestBufferHV, conveyorHV,
            compressedDesh, pipeMediumBrass, compressedDesh
        });

        // --- Cargo Unloader
        addShapedRecipe(new ItemStack(GCBlocks.cargoLoader, 1, 4), new Object[] {
            compressedDesh, pipeMediumBrass, compressedDesh,
            conveyorHV, chestBufferHV, conveyorHV,
            compressedAl, hopper, compressedAl
        });

        // --- Nasa Workbench --- Added by core mod?
        //recipes.addShaped(<GalacticraftCore:tile.rocketWorkbench>, [
        //[<gregtech:gt.metaitem.01:32652>, <gregtech:gt.metaitem.01:32740>, <gregtech:gt.metaitem.01:32652>],
        //[<ore:waferAdvanced>, <ore:circuitElite>, <ore:waferAdvanced>],
        //[<ore:itemCasingStainlessSteel>, <gregtech:gt.blockcasings:3>, <ore:itemCasingStainlessSteel>]]);

        // --- Tin Decoration Block
        addShapedRecipe(tinDecoBlock, new Object[] {
            hHammer, compressedTin, null,
            compressedTin, stone, compressedTin,
            null, compressedTin, wrench
        });
        addShapedRecipe(tinDecoBlock2, new Object[] {
            null, compressedTin, hHammer,
            compressedTin, stone, compressedTin,
            wrench, compressedTin, null
        });
        
        // --- Air Lock Frame
        addShapedRecipe(new ItemStack(GCBlocks.airLockFrame, 2, 0), new Object[] {
            compressedDesh, screwSSteel, compressedDesh,
            airVent, screwdriver, airVent,
            compressedAl, screwSSteel, compressedAl
        });

        // --- Air Lock Controller
        addShapedRecipe(new ItemStack(GCBlocks.airLockFrame, 1, 1), new Object[] {
            compressedDesh, GCItems.oxygenConcentrator, compressedDesh,
            airVent, computerMonitorCover, airVent,
            advWafer, wireRedAlloy1x, advWafer
        });
        
        // --- Sealable Oxygen Pipe
        addShapedRecipe(new ItemStack(GCBlocks.sealableBlock, 1, 1), new Object[] {
                hHammer, GCBlocks.oxygenPipe, null,
            GCBlocks.oxygenPipe, tinDecoBlock, GCBlocks.oxygenPipe,
            null, GCBlocks.oxygenPipe, file
        });

        // --- Sealable ME Wire
        // TODO
//        addShapedRecipe(new ItemStack(GCBlocks.sealableBlock, 1, 13), new Object[] {
//                hHammer, <appliedenergistics2:item.ItemMultiPart:11>, null,
//            <appliedenergistics2:item.ItemMultiPart:11>, tinDecoBlock, <appliedenergistics2:item.ItemMultiPart:11>,
//            null, <appliedenergistics2:item.ItemMultiPart:11>, file
//        });

        // --- Sealable Aluminium Wire
        addShapedRecipe(new ItemStack(GCBlocks.sealableBlock, 1, 14), new Object[] {
            hHammer, gcAlWire, null,
            gcAlWire, tinDecoBlock, gcAlWire,
            null, gcAlWire, file
        });
        

        // --- Sealable heavy Aluminium Wire
        addShapedRecipe(new ItemStack(GCBlocks.sealableBlock, 1, 15), new Object[] {
            hHammer, gcHeavyAlWire, null,
            gcHeavyAlWire, tinDecoBlock, gcHeavyAlWire,
            null, gcHeavyAlWire, file
        });

        // TODO
        // --- Sealable Stone Kinesis Pipe
//        addShapedRecipe(new ItemStack(GCBlocks.sealableBlock:11>, 1, 0), new Object[] {
//            hHammer, <BuildCraft|Transport:item.buildcraftPipe.pipepowerstone>, null,
//            <BuildCraft|Transport:item.buildcraftPipe.pipepowerstone>, tinDecoBlock, <BuildCraft|Transport:item.buildcraftPipe.pipepowerstone>,
//            null, <BuildCraft|Transport:item.buildcraftPipe.pipepowerstone>, file
//        });
//
//        // --- Sealable Gold Kinesis Pipe
//        addShapedRecipe(new ItemStack(GCBlocks.sealableBlock:12>, 1, 0), new Object[] {
//                hHammer, <BuildCraft|Transport:item.buildcraftPipe.pipepowergold>, null,
//            <BuildCraft|Transport:item.buildcraftPipe.pipepowergold>, tinDecoBlock, <BuildCraft|Transport:item.buildcraftPipe.pipepowergold>,
//            null, <BuildCraft|Transport:item.buildcraftPipe.pipepowergold>, file
//        });

        // TODO
        // --- Aluminium Wire
//        recipes.addShapeless(gcAlWire, [<ore:cableGt01Aluminium>]);

        // --- Heavy Aluminium Wire
        addShapedRecipe(new ItemStack(GCBlocks.aluminumWire, 3, 1), new Object[] {
            compressedAl, compressedAl, compressedAl,
            gcAlWire, gcAlWire, gcAlWire,
            compressedAl, compressedAl, compressedAl
        });
        
        // --- Basic Solar Panel
        addShapedRecipe(new ItemStack(GCBlocks.solarPanel, 1, 0), new Object[] {
            compressedAl, solarModuleFull, compressedAl,
            gcAlWire, steelPole, gcAlWire,
            compressedSteel, bWafer, compressedSteel
        });

        // --- Advanced Solar Panel
        addShapedRecipe(new ItemStack(GCBlocks.solarPanel, 1, 4), new Object[] {
            compressedAl, solarModuleFull, compressedAl,
            gcHeavyAlWire, steelPole, gcHeavyAlWire,
            motorLV, advWafer, sensorLV
        });

        // --- Full Solar Panel
        addShapedRecipe(solarModuleFull, new Object[] {
            solarModuleSingle, solarModuleSingle, solarModuleSingle,
            gcAlWire, bWafer, gcAlWire,
            solarModuleSingle, solarModuleSingle, solarModuleSingle
        });

        // --- Energy Storage Module
        addShapedRecipe(new ItemStack(GCBlocks.machineTiered, 1, 0), new Object[] {
            compressedSteel, Ic2Items.chargingREBattery, compressedSteel,
            cableCopper1x, ItemList.Hull_MV.get(1), cableCopper1x,
            bWafer, Ic2Items.chargingREBattery, bWafer
        });

        // --- Energy Storage Cluster
        addShapedRecipe(new ItemStack(GCBlocks.machineTiered, 1, 8), new Object[] {
            compressedTi, Ic2Items.chargingAdvBattery, compressedTi,
            cableGold2x, ItemList.Hull_HV.get(1), cableGold2x,
            advWafer, Ic2Items.chargingAdvBattery, advWafer
        });

        // --- Spin Thruster
        addShapedRecipe(new ItemStack(GCBlocks.spinThruster, 1, 0), new Object[] {
            compressedTi, compressedTi, compressedTi,
            fuelCanisterFilled, advWafer, fuelCanisterFilled,
            GCItems.rocketEngine, heavyPlating, GCItems.rocketEngine
        });

        // --- Display Screen
        addShapedRecipe(new ItemStack(GCBlocks.screen, 1, 0), new Object[] {
            compressedSteel, solarPanel, compressedSteel,
            bWafer, computerMonitorCover, bWafer,
            compressedSteel, compressedSteel, compressedSteel
        });

        // --- Telemetry Unit
        addShapedRecipe(new ItemStack(GCBlocks.telemetry, 1, 0), new Object[] {
            frequencyModule, compressedTin, emitterHV,
            bWafer, compressedTin, bWafer,
            compressedTin, compressedCopper, compressedTin
        });

        // TODO
        // --- Arc Lamp
//        addShapedRecipe(new ItemStack(<GalacticraftCore:tile.arclamp>, 1, 0), new Object[] {
//            deshPlate,deshPlate,deshPlate,
//            deshPlate, <GalaxySpace:ceresglowstone>, <ProjRed|Illumination:projectred.illumination.lamp:16>,
//            deshPlate, deshPlate, deshPlate
//        });
//        addShapedRecipe(new ItemStack(<GalacticraftCore:tile.arclamp>, 1, 0), new Object[] {
//            deshPlate,deshPlate,deshPlate,
//            deshPlate, <GalaxySpace:ioglowstone>, <ProjRed|Illumination:projectred.illumination.lamp:16>,
//            deshPlate, deshPlate, deshPlate
//        });
//        addShapedRecipe(new ItemStack(<GalacticraftCore:tile.arclamp>, 1, 0), new Object[] {
//            deshPlate,deshPlate,deshPlate,
//            deshPlate, <GalaxySpace:enceladusglowstone>, <ProjRed|Illumination:projectred.illumination.lamp:16>,
//            deshPlate, deshPlate, deshPlate
//        });
//        addShapedRecipe(new ItemStack(<GalacticraftCore:tile.arclamp>, 1, 0), new Object[] {
//            deshPlate,deshPlate,deshPlate,
//            deshPlate, <GalaxySpace:proteusglowstone>, <ProjRed|Illumination:projectred.illumination.lamp:16>,
//            deshPlate, deshPlate, deshPlate
//        });
//        addShapedRecipe(new ItemStack(<GalacticraftCore:tile.arclamp>, 1, 0), new Object[] {
//            deshPlate,deshPlate,deshPlate,
//            deshPlate, <GalaxySpace:plutoglowstone>, <ProjRed|Illumination:projectred.illumination.lamp:16>,
//            deshPlate, deshPlate, deshPlate
//        });

        // --- Oxygen Gear
        addShapedRecipe(new ItemStack(GCItems.oxygenGear, 1, 0), new Object[] {
            GCBlocks.oxygenPipe, GCBlocks.oxygenPipe, GCBlocks.oxygenPipe,
            pumpHV, GCItems.oxygenConcentrator, pumpHV,
            GCBlocks.oxygenPipe, motorHV, GCBlocks.oxygenPipe
        });

        // --- Light Oxygen Tank
        addShapedRecipe(new ItemStack(GCItems.oxTankLight, 1, GCItems.oxTankLight.getMaxDamage()), new Object[] {
            compressedAl, GCBlocks.oxygenPipe, compressedAl,
            compressedAl, cellEmpty, compressedAl,
            compressedAl, compressedAl, compressedAl
        });
        addShapedRecipe(new ItemStack(GCItems.oxTankLight, 1, GCItems.oxTankLight.getMaxDamage()), new Object[] {
            compressedAl, GCBlocks.oxygenPipe, compressedAl,
            compressedAl, Ic2Items.FluidCell, compressedAl,
            compressedAl, compressedAl, compressedAl
        });

        // --- Light Oxygen Tank - Original GC recipes
        RecipeUtil.addRecipe(new ItemStack(GCItems.oxTankLight, 1, GCItems.oxTankLight.getMaxDamage()), new Object[] {
                "YZY",
                "YXY",
                "YYY",
                'X',
                new ItemStack(GCItems.canister, 1, 0),
                'Y',
                "compressedAluminum",
                'Z',
                GCBlocks.oxygenPipe
        });
        RecipeUtil.addRecipe(new ItemStack(GCItems.oxTankLight, 1, GCItems.oxTankLight.getMaxDamage()), new Object[] {
                "YZY",
                "YXY",
                "YYY",
                'X',
                new ItemStack(GCItems.canister, 1, 1),
                'Y',
                "compressedAluminum",
                'Z',
                GCBlocks.oxygenPipe
        });

        // --- Medium Oxygen Tank
        addShapedRecipe(new ItemStack(GCItems.oxTankMedium, 1, GCItems.oxTankMedium.getMaxDamage()), new Object[] {
            compressedMeteoricIron, GCBlocks.oxygenPipe, compressedMeteoricIron,
            compressedMeteoricIron, largeCellSteel, compressedMeteoricIron,
            compressedMeteoricIron, compressedMeteoricIron, compressedMeteoricIron
        });

        // --- Heavy Oxygen Tank
        addShapedRecipe(new ItemStack(GCItems.oxTankHeavy, 1, GCItems.oxTankHeavy.getMaxDamage()), new Object[] {
                plateDesh, GCBlocks.oxygenPipe, plateDesh,
                plateDesh, largeCellTungSteel, plateDesh,
                plateDesh, plateDesh, plateDesh
        });

        // --- Super Heavy Oxygen Tank
        addShapedRecipe(new ItemStack(GCItems.oxTankSuperHeavy, 1, GCItems.oxTankSuperHeavy.getMaxDamage()), new Object[] {
            compressedDualTitanium, GCBlocks.oxygenPipe, compressedDualTitanium,
            compressedDualTitanium, largeCellIridium, compressedDualTitanium,
            compressedDualTitanium, compressedDualTitanium, compressedDualTitanium
        });

        // --- Ultra Heavy Oxygen Tank
        addShapedRecipe(new ItemStack(GCItems.oxTankUltraHeavy, 1, GCItems.oxTankUltraHeavy.getMaxDamage()), new Object[] {
            tripleTrinium, GCBlocks.oxygenPipe, tripleTrinium,
            tripleTrinium, largeCellOsmium, tripleTrinium,
            tripleTrinium, tripleTrinium, tripleTrinium
        });

        // --- Sensor Lens
        addShapedRecipe(sensorLens, new Object[] {
            ringRedAlloy, lensDiamond, ringRedAlloy,
            circuitAdv, lensReinfGlass, circuitAdv,
            screwSSteel, screwdriver, screwSSteel
        });

        // --- Sensor Glasses
        addShapedRecipe(new ItemStack(GCItems.sensorGlasses, 1, 0), new Object[] {
            circuitData, screwMeteor, circuitData,
            ringDesh, boltDesh, ringDesh,
            sensorLens, screwdriver, sensorLens
        });
    }

    @SuppressWarnings("unchecked")
    private static void addUniversalRecipes() {

        Object meteoricIronIngot =
                ConfigManagerCore.recipesRequireGCAdvancedMetals ? GCItems.meteoricIronIngot : "ingotMeteoricIron";
        Object meteoricIronPlate = ConfigManagerCore.recipesRequireGCAdvancedMetals
                ? new ItemStack(GCItems.meteoricIronIngot, 1, 1)
                : "compressedMeteoricIron";
        Object deshIngot = GalacticraftCore.isPlanetsLoaded
                ? (ConfigManagerCore.recipesRequireGCAdvancedMetals
                        ? new ItemStack(MarsItems.marsItemBasic, 1, 2)
                        : "ingotDesh")
                : GCItems.heavyPlatingTier1;

        // RocketFuelRecipe.addFuel(GalacticraftCore.fluidFuel,1);
        FurnaceRecipes.smelting()
                .func_151394_a(new ItemStack(GCBlocks.basicBlock, 1, 5), new ItemStack(GCItems.basicItem, 1, 3), 0.5F);
        FurnaceRecipes.smelting()
                .func_151394_a(new ItemStack(GCBlocks.basicBlock, 1, 6), new ItemStack(GCItems.basicItem, 1, 4), 0.5F);
        FurnaceRecipes.smelting()
                .func_151394_a(new ItemStack(GCBlocks.basicBlock, 1, 7), new ItemStack(GCItems.basicItem, 1, 5), 0.5F);
        FurnaceRecipes.smelting()
                .func_151394_a(
                        new ItemStack(GCItems.meteorChunk, 1, 0), new ItemStack(GCItems.meteorChunk, 1, 1), 0.1F);
        FurnaceRecipes.smelting()
                .func_151396_a(GCItems.meteoricIronRaw, new ItemStack(GCItems.meteoricIronIngot), 1.0F);
        FurnaceRecipes.smelting()
                .func_151394_a(new ItemStack(GCBlocks.blockMoon, 1, 0), new ItemStack(GCItems.basicItem, 1, 3), 1.0F);
        FurnaceRecipes.smelting()
                .func_151394_a(new ItemStack(GCBlocks.blockMoon, 1, 1), new ItemStack(GCItems.basicItem, 1, 4), 1.0F);
        FurnaceRecipes.smelting()
                .func_151394_a(new ItemStack(GCBlocks.blockMoon, 1, 2), new ItemStack(GCItems.cheeseCurd), 1.0F);
        // Recycling: smelt tin/copper canisters back into ingots
        FurnaceRecipes.smelting()
                .func_151394_a(new ItemStack(GCItems.canister, 1, 0), new ItemStack(GCItems.basicItem, 3, 4), 1.0F);
        FurnaceRecipes.smelting()
                .func_151394_a(new ItemStack(GCItems.canister, 1, 1), new ItemStack(GCItems.basicItem, 3, 3), 1.0F);

        RecipeUtil.addRecipe(new ItemStack(GCItems.rocketEngine, 1, 1), new Object[] {
            "ZYZ",
            "ZWZ",
            "XVX",
            'V',
            GCItems.oxygenVent,
            'W',
            new ItemStack(GCItems.fuelCanister, 1, 1),
            'X',
            GCItems.heavyPlatingTier1,
            'Y',
            new ItemStack(Blocks.wool, 1, 4),
            'Z',
            meteoricIronPlate
        });

        HashMap<Integer, ItemStack> input = new HashMap<>();
        HashMap<Integer, ItemStack> input2 = new HashMap<>(input);

        input.put(1, new ItemStack(GCItems.basicItem, 1, 19));
        input.put(2, new ItemStack(GCItems.partBuggy, 1, 1));
        if (GalacticraftCore.isGalaxySpaceLoaded) {
            input.put(3, GT_ModHandler.getModItem(Constants.MOD_ID_GALAXYSPACE, "item.RocketControlComputer", 1, 100));
        }
        for (int i = 4; i <= 7; i++) {
            input.put(i, new ItemStack(GCItems.partBuggy));
        }
        for (int i = 8; i <= 11; i++) {
            input.put(i, GT_OreDictUnificator.get(OrePrefixes.stick, Materials.StainlessSteel, 1));
        }
        for (int i = 12; i <= 16; i++) {
            input.put(i, new ItemStack(GCItems.meteoricIronIngot, 1, 1));
        }
        for (int i = 17; i <= 24; i++) {
            input.put(i, GT_OreDictUnificator.get(OrePrefixes.screw, Materials.StainlessSteel, 1));
        }
        for (int i = 25; i <= 34; i++) {
            input.put(i, new ItemStack(GCItems.heavyPlatingTier1));
        }

        input2.put(35, null);
        RecipeUtil.addBuggyBenchRecipe(new ItemStack(GCItems.buggy, 1, 0), input2);

        input2 = new HashMap<Integer, ItemStack>(input);
        input2.put(35, RecipeUtil.getChestItemStack(1, 3));
        RecipeUtil.addBuggyBenchRecipe(new ItemStack(GCItems.buggy, 1, 1), input2);

        input2 = new HashMap<Integer, ItemStack>(input);
        input2.put(35, RecipeUtil.getChestItemStack(1, 0));
        RecipeUtil.addBuggyBenchRecipe(new ItemStack(GCItems.buggy, 1, 2), input2);

        input2 = new HashMap<Integer, ItemStack>(input);
        input2.put(35, RecipeUtil.getChestItemStack(1, 1));
        RecipeUtil.addBuggyBenchRecipe(new ItemStack(GCItems.buggy, 1, 3), input2);

        aluminumIngots.addAll(OreDictionary.getOres("ingotAluminum"));
        ArrayList<ItemStack> addedList = new ArrayList<ItemStack>();
        for (ItemStack ingotNew : OreDictionary.getOres("ingotAluminium")) {
            boolean flag = false;
            for (ItemStack ingotDone : aluminumIngots) {
                if (ItemStack.areItemStacksEqual(ingotNew, ingotDone)) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                addedList.add(ingotNew);
                OreDictionary.registerOre("ingotAluminum", ingotNew);
            }
        }
        if (addedList.size() > 0) {
            aluminumIngots.addAll(addedList);
            addedList.clear();
        }
        for (ItemStack ingotNew : OreDictionary.getOres("ingotNaturalAluminum")) {
            for (ItemStack ingotDone : aluminumIngots) {
                if (!ItemStack.areItemStacksEqual(ingotNew, ingotDone)) addedList.add(ingotNew);
            }
        }
        if (addedList.size() > 0) {
            aluminumIngots.addAll(addedList);
        }

        final HashMap<Object, Integer> inputMap = new HashMap<Object, Integer>();
        inputMap.put(new ItemStack(GCBlocks.basicBlock, 1, 4), 231);
        inputMap.put(new ItemStack(Blocks.glass_pane), 6);
        inputMap.put("circuitAdvanced", 4);
        inputMap.put(new ItemStack(GregTech_API.sBlockMachines, 1, 13), 1); // HV Machine Hull
        GalacticraftRegistry.registerSpaceStation(
                new SpaceStationType(ConfigManagerCore.idDimensionOverworldOrbit, 0, new SpaceStationRecipe(inputMap)));

        RecipeUtil.addRecipe(
                new ItemStack(GCBlocks.aluminumWire, 6),
                new Object[] {"WWW", "CCC", "WWW", 'W', Blocks.wool, 'C', "ingotAluminum"});

        RecipeUtil.addRecipe(new ItemStack(GCBlocks.machineBase, 1, 0), new Object[] {
            "WWW",
            "XZX",
            "XYX",
            'W',
            "ingotCopper",
            'X',
            Items.iron_ingot,
            'Y',
            new ItemStack(GCBlocks.aluminumWire, 1, 0),
            'Z',
            Blocks.furnace
        });
        // Electric Furnace:
        RecipeUtil.addRecipe(new ItemStack(GCBlocks.machineTiered, 1, 4), new Object[] {
            "XXX",
            "XZX",
            "WYW",
            'W',
            "compressedAluminum",
            'X',
            "compressedSteel",
            'Y',
            "waferBasic",
            'Z',
            Blocks.furnace
        });
        if (GalacticraftCore.isPlanetsLoaded) {
            // Electric Arc Furnace:
            RecipeUtil.addRecipe(new ItemStack(GCBlocks.machineTiered, 1, 12), new Object[] {
                "XXX",
                "XZX",
                "WYW",
                'W',
                meteoricIronIngot,
                'X',
                new ItemStack(GCItems.heavyPlatingTier1),
                'Y',
                "waferAdvanced",
                'Z',
                new ItemStack(GCBlocks.machineTiered, 1, 4)
            });
        }
        RecipeUtil.addRecipe(new ItemStack(GCBlocks.machineBase, 1, 12), new Object[] {
            "WXW", "WYW", "WZW", 'W', "ingotAluminum", 'X', Blocks.anvil, 'Y', "ingotCopper", 'Z', "waferBasic"
        });

        RecipeUtil.addRecipe(new ItemStack(GCBlocks.machineBase2, 1, 0), new Object[] {
            "WXW",
            "WYW",
            "VZV",
            'V',
            new ItemStack(GCBlocks.aluminumWire),
            'W',
            "compressedSteel",
            'X',
            Blocks.anvil,
            'Y',
            "compressedBronze",
            'Z',
            "waferAdvanced"
        });

        RecipeUtil.addRecipe(new ItemStack(GCBlocks.machineBase2, 1, 4), new Object[] {
            "WXW",
            "UYU",
            "VZV",
            'U',
            Blocks.stone_button,
            'V',
            new ItemStack(GCBlocks.aluminumWire),
            'W',
            "ingotAluminum",
            'X',
            Blocks.lever,
            'Y',
            Blocks.furnace,
            'Z',
            Blocks.redstone_torch
        });

        RecipeUtil.addRecipe(
                new ItemStack(GCItems.battery, 1, 100),
                new Object[] {" T ", "TRT", "TCT", 'T', "compressedTin", 'R', Items.redstone, 'C', Items.coal});

        RecipeUtil.addRecipe(new ItemStack(GCItems.rocketEngine, 1), new Object[] {
            " YV",
            "XWX",
            "XZX",
            'V',
            Blocks.stone_button,
            'W',
            new ItemStack(GCItems.canister, 1, 0),
            'X',
            GCItems.heavyPlatingTier1,
            'Y',
            Items.flint_and_steel,
            'Z',
            GCItems.oxygenVent
        });

        RecipeUtil.addRecipe(new ItemStack(GCItems.rocketEngine, 1), new Object[] {
            "VY ",
            "XWX",
            "XZX",
            'V',
            Blocks.stone_button,
            'W',
            new ItemStack(GCItems.canister, 1, 0),
            'X',
            GCItems.heavyPlatingTier1,
            'Y',
            Items.flint_and_steel,
            'Z',
            GCItems.oxygenVent
        });

        RecipeUtil.addRecipe(
                new ItemStack(GCItems.partNoseCone, 1),
                new Object[] {" Y ", " X ", "X X", 'X', GCItems.heavyPlatingTier1, 'Y', Blocks.redstone_torch});

        RecipeUtil.addRecipe(
                new ItemStack(GCBlocks.oxygenPipe, 6), new Object[] {"XXX", "   ", "XXX", 'X', Blocks.glass_pane});

        if (!ConfigManagerCore.alternateCanisterRecipe) {
            RecipeUtil.addRecipe(
                    new ItemStack(GCItems.canister, 2, 0), new Object[] {"X X", "X X", "XXX", 'X', "ingotTin"});
            RecipeUtil.addRecipe(
                    new ItemStack(GCItems.canister, 2, 1), new Object[] {"X X", "X X", "XXX", 'X', "ingotCopper"});
        } else {
            RecipeUtil.addRecipe(
                    new ItemStack(GCItems.canister, 2, 0), new Object[] {"XXX", "X  ", "XXX", 'X', "ingotTin"});
            RecipeUtil.addRecipe(
                    new ItemStack(GCItems.canister, 2, 1), new Object[] {"XXX", "X  ", "XXX", 'X', "ingotCopper"});
        }

        RecipeUtil.addRecipe(
                new ItemStack(GCItems.oxMask, 1),
                new Object[] {"XXX", "XYX", "XXX", 'X', Blocks.glass_pane, 'Y', Items.iron_helmet});

        RecipeUtil.addRecipe(
                new ItemStack(GCItems.canvas, 1),
                new Object[] {" XY", "XXX", "YX ", 'Y', Items.stick, 'X', Items.string});

        RecipeUtil.addRecipe(
                new ItemStack(GCItems.parachute, 1, 0),
                new Object[] {"XXX", "Y Y", " Y ", 'X', GCItems.canvas, 'Y', Items.string});

        RecipeUtil.addRecipe(
                new ItemStack(GCItems.flag),
                new Object[] {"XYY", "XYY", "X  ", 'X', GCItems.flagPole, 'Y', GCItems.canvas});

        for (int var2 = 0; var2 < 16; ++var2) {
            CraftingManager.getInstance()
                    .addShapelessRecipe(
                            new ItemStack(GCItems.parachute, 1, ItemParaChute.getParachuteDamageValueFromDye(var2)),
                            new Object[] {new ItemStack(Items.dye, 1, var2), new ItemStack(GCItems.parachute, 1, 0)});
        }

        RecipeUtil.addRecipe(
                new ItemStack(GCItems.partFins, 1),
                new Object[] {" Y ", "XYX", "X X", 'X', GCItems.heavyPlatingTier1, 'Y', "compressedSteel"});

        //        RecipeUtil.addRecipe(
        //                new ItemStack(GCBlocks.landingPad, 9, 0),
        //                new Object[] {"YYY", "XXX", 'X', Blocks.iron_block, 'Y', "compressedIron"});

        //        RecipeUtil.addRecipe(
        //                new ItemStack(GCBlocks.landingPad, 9, 1),
        //                new Object[] {"YYY", "XXX", 'X', Blocks.iron_block, 'Y', "compressedSteel"});

        RecipeUtil.addRecipe(
                new ItemStack(GCItems.partBuggy, 1, 0),
                new Object[] {" W ", "WXW", " W ", 'W', Items.leather, 'X', "compressedSteel"});

        RecipeUtil.addRecipe(new ItemStack(GCItems.partBuggy, 1, 1), new Object[] {
            "  Y", " ZY", "XXX", 'X', "compressedSteel", 'Y', "compressedSteel", 'Z', "compressedIron"
        });

        RecipeUtil.addRecipe(
                new ItemStack(GCItems.partBuggy, 1, 2),
                new Object[] {"XXX", "YZY", "XXX", 'X', "compressedSteel", 'Y', "compressedIron", 'Z', Blocks.chest});

        // Handled by Galaxy Space
        // RecipeUtil.addRecipe(new ItemStack(GCBlocks.nasaWorkbench, 1), new Object[] { "XZX", "UWU", "YVY", 'U',
        // Blocks.lever, 'V', Blocks.redstone_torch, 'W', "waferAdvanced", 'X', "compressedSteel", 'Y',
        // "compressedSteel", 'Z', Blocks.crafting_table });

        // RecipeUtil.addRecipe(new ItemStack(GCItems.oxTankHeavy, 1, GCItems.oxTankHeavy.getMaxDamage()), new Object[]
        // { "ZZZ", "XXX", "YYY", 'X', new ItemStack(GCItems.canister, 1, 0), 'Y', "compressedSteel", 'Z', new
        // ItemStack(Blocks.wool, 1, 14) });

        RecipeUtil.addRecipe(
                new ItemStack(GCItems.oxygenFan, 1),
                new Object[] {"Z Z", " Y ", "ZXZ", 'X', Items.redstone, 'Y', "waferBasic", 'Z', "compressedSteel"});

        RecipeUtil.addRecipe(new ItemStack(GCItems.oxygenConcentrator, 1), new Object[] {
            "ZWZ",
            "WYW",
            "ZXZ",
            'W',
            "compressedTin",
            'X',
            GCItems.oxygenVent,
            'Y',
            new ItemStack(GCItems.canister, 1, 0),
            'Z',
            "compressedSteel"
        });

        RecipeUtil.addRecipe(
                new ItemStack(GCItems.steelPickaxe, 1),
                new Object[] {"YYY", " X ", " X ", 'Y', "compressedSteel", 'X', Items.stick});

        RecipeUtil.addRecipe(
                new ItemStack(GCItems.steelAxe, 1),
                new Object[] {"YY ", "YX ", " X ", 'Y', "compressedSteel", 'X', Items.stick});

        RecipeUtil.addRecipe(
                new ItemStack(GCItems.steelAxe, 1),
                new Object[] {" YY", " XY", " X ", 'Y', "compressedSteel", 'X', Items.stick});

        RecipeUtil.addRecipe(
                new ItemStack(GCItems.steelHoe, 1),
                new Object[] {" YY", " X ", " X ", 'Y', "compressedSteel", 'X', Items.stick});

        RecipeUtil.addRecipe(
                new ItemStack(GCItems.steelHoe, 1),
                new Object[] {"YY ", " X ", " X ", 'Y', "compressedSteel", 'X', Items.stick});

        RecipeUtil.addRecipe(
                new ItemStack(GCItems.steelSpade, 1),
                new Object[] {" Y ", " X ", " X ", 'Y', "compressedSteel", 'X', Items.stick});

        RecipeUtil.addRecipe(
                new ItemStack(GCItems.steelSword, 1),
                new Object[] {" Y ", " Y ", " X ", 'Y', "compressedSteel", 'X', Items.stick});

        RecipeUtil.addRecipe(new ItemStack(GCItems.steelBoots, 1), new Object[] {"X X", "X X", 'X', "compressedSteel"});

        RecipeUtil.addRecipe(
                new ItemStack(GCItems.steelChestplate, 1), new Object[] {"X X", "XXX", "XXX", 'X', "compressedSteel"});

        RecipeUtil.addRecipe(
                new ItemStack(GCItems.steelLeggings, 1), new Object[] {"XXX", "X X", "X X", 'X', "compressedSteel"});

        RecipeUtil.addRecipe(
                new ItemStack(GCItems.steelHelmet, 1), new Object[] {"XXX", "X X", 'X', "compressedSteel"});

        RecipeUtil.addRecipe(
                new ItemStack(GCItems.flagPole, 2, 0), new Object[] {"X", "X", "X", 'X', "compressedSteel"});

        CraftingManager.getInstance()
                .getRecipeList()
                .add(new ShapelessOreRecipe(
                        new ItemStack(GCItems.oxygenVent, 1),
                        new Object[] {"compressedTin", "compressedTin", "compressedTin", "compressedSteel"}));

        // Disable oil extractor:
        // RecipeUtil.addRecipe(new ItemStack(GCItems.oilExtractor), new Object[] { "X  ", " XY", "ZYY", 'X',
        // "compressedSteel", 'Y', "compressedBronze", 'Z', Items.redstone });

        RecipeUtil.addRecipe(new ItemStack(GCItems.basicItem, 1, 20), new Object[] {
            "WVW",
            "YXY",
            "YZY",
            'X',
            "compressedSteel",
            'Y',
            "compressedBronze",
            'Z',
            "waferBasic",
            'W',
            Items.redstone,
            'V',
            GCItems.oxygenVent
        });

        RecipeUtil.addRecipe(new ItemStack(GCItems.oilCanister, 1, GCItems.oilCanister.getMaxDamage()), new Object[] {
            "WXW",
            "WYW",
            "WZW",
            'X',
            "compressedSteel",
            'Y',
            Blocks.glass,
            'Z',
            new ItemStack(GCItems.canister, 1, 0),
            'W',
            "compressedTin"
        });

        RecipeUtil.addRecipe(new ItemStack(GCBlocks.refinery), new Object[] {
            " Z ",
            "WZW",
            "XYX",
            'X',
            "compressedSteel",
            'Y',
            Blocks.furnace,
            'Z',
            new ItemStack(GCItems.canister, 1, 1),
            'W',
            Blocks.stone
        });

        RecipeUtil.addRecipe(new ItemStack(GCItems.basicItem, 2, 0), new Object[] {
            "XXX", "YYY", "ZZZ", 'X', Blocks.glass, 'Y', "waferSolar", 'Z', new ItemStack(GCBlocks.aluminumWire, 1, 0)
        });

        RecipeUtil.addRecipe(
                new ItemStack(GCBlocks.glowstoneTorch, 4),
                new Object[] {"Y", "X", 'X', Items.stick, 'Y', Items.glowstone_dust});

        RecipeUtil.addRecipe(new ItemStack(GCItems.basicItem, 1, 19), new Object[] {
            " X ",
            "YUY",
            "ZWZ",
            'U',
            Items.repeater,
            'W',
            "waferBasic",
            'X',
            "compressedAluminum",
            'Y',
            "compressedIron",
            'Z',
            Items.redstone
        });

        RecipeUtil.addRecipe(
                new ItemStack(GCItems.wrench),
                new Object[] {"  Y", " X ", "X  ", 'X', "compressedBronze", 'Y', "compressedSteel"});

        RecipeUtil.addRecipe(new ItemStack(Blocks.lit_pumpkin), new Object[] {
            "P  ", "T  ", "   ", 'P', new ItemStack(Blocks.pumpkin), 'T', new ItemStack(GCBlocks.unlitTorch)
        });

        RecipeUtil.addRecipe(new ItemStack(GCBlocks.brightLamp), new Object[] {
            "XYX", "YZY", "XYX", 'X', deshIngot, 'Y', Items.glowstone_dust, 'Z', new ItemStack(GCItems.battery, 1, 0)
        });

        RecipeUtil.addBlockRecipe(
                new ItemStack(GCBlocks.basicBlock, 1, 9), "ingotCopper", new ItemStack(GCItems.basicItem, 1, 3));

        RecipeUtil.addBlockRecipe(
                new ItemStack(GCBlocks.basicBlock, 1, 10), "ingotTin", new ItemStack(GCItems.basicItem, 1, 4));

        RecipeUtil.addBlockRecipe(
                new ItemStack(GCBlocks.basicBlock, 1, 11), "ingotAluminum", new ItemStack(GCItems.basicItem, 1, 5));

        RecipeUtil.addRecipe(
                new ItemStack(GCBlocks.basicBlock, 1, 12), new Object[] {"YYY", "YYY", "YYY", 'Y', meteoricIronIngot});

        RecipeUtil.addRecipe(
                new ItemStack(GCItems.basicItem, 9, 3),
                new Object[] {"X", 'X', new ItemStack(GCBlocks.basicBlock, 1, 9)});

        RecipeUtil.addRecipe(
                new ItemStack(GCItems.basicItem, 9, 4),
                new Object[] {"X", 'X', new ItemStack(GCBlocks.basicBlock, 1, 10)});

        RecipeUtil.addRecipe(
                new ItemStack(GCItems.basicItem, 9, 5),
                new Object[] {"X", 'X', new ItemStack(GCBlocks.basicBlock, 1, 11)});

        RecipeUtil.addRecipe(
                new ItemStack(GCItems.meteoricIronIngot, 9, 0),
                new Object[] {"X", 'X', new ItemStack(GCBlocks.basicBlock, 1, 12)});

        RecipeUtil.addRecipe(
                new ItemStack(GCBlocks.cheeseBlock, 1),
                new Object[] {"YYY", "YXY", "YYY", 'X', Items.milk_bucket, 'Y', GCItems.cheeseCurd});

        // Tin Stairs 1
        RecipeUtil.addRecipe(
                new ItemStack(GCBlocks.tinStairs1, 4),
                new Object[] {"  X", " XX", "XXX", 'X', new ItemStack(GCBlocks.basicBlock, 1, 4)});
        RecipeUtil.addRecipe(
                new ItemStack(GCBlocks.tinStairs1, 4),
                new Object[] {"X  ", "XX ", "XXX", 'X', new ItemStack(GCBlocks.basicBlock, 1, 4)});

        // Tin Stairs 2
        RecipeUtil.addRecipe(
                new ItemStack(GCBlocks.tinStairs2, 4),
                new Object[] {"  X", " XX", "XXX", 'X', new ItemStack(GCBlocks.basicBlock, 1, 3)});
        RecipeUtil.addRecipe(
                new ItemStack(GCBlocks.tinStairs2, 4),
                new Object[] {"X  ", "XX ", "XXX", 'X', new ItemStack(GCBlocks.basicBlock, 1, 3)});

        // Moon Stone Stairs
        RecipeUtil.addRecipe(
                new ItemStack(GCBlocks.moonStoneStairs, 4),
                new Object[] {"  X", " XX", "XXX", 'X', new ItemStack(GCBlocks.blockMoon, 1, 4)});
        RecipeUtil.addRecipe(
                new ItemStack(GCBlocks.moonStoneStairs, 4),
                new Object[] {"X  ", "XX ", "XXX", 'X', new ItemStack(GCBlocks.blockMoon, 1, 4)});

        // Moon Dungeon Brick Stairs
        RecipeUtil.addRecipe(
                new ItemStack(GCBlocks.moonBricksStairs, 4),
                new Object[] {"  X", " XX", "XXX", 'X', new ItemStack(GCBlocks.blockMoon, 1, 14)});
        RecipeUtil.addRecipe(
                new ItemStack(GCBlocks.moonBricksStairs, 4),
                new Object[] {"X  ", "XX ", "XXX", 'X', new ItemStack(GCBlocks.blockMoon, 1, 14)});

        // Slab Block
        RecipeUtil.addRecipe(
                new ItemStack(GCBlocks.slabGCHalf, 6, 0),
                new Object[] {"XXX", 'X', new ItemStack(GCBlocks.basicBlock, 1, 4)});
        RecipeUtil.addRecipe(
                new ItemStack(GCBlocks.slabGCHalf, 6, 1),
                new Object[] {"XXX", 'X', new ItemStack(GCBlocks.basicBlock, 1, 3)});
        RecipeUtil.addRecipe(
                new ItemStack(GCBlocks.slabGCHalf, 6, 2),
                new Object[] {"XXX", 'X', new ItemStack(GCBlocks.blockMoon, 1, 4)});
        RecipeUtil.addRecipe(
                new ItemStack(GCBlocks.slabGCHalf, 6, 3),
                new Object[] {"XXX", 'X', new ItemStack(GCBlocks.blockMoon, 1, 14)});

        // Wall Block
        RecipeUtil.addRecipe(
                new ItemStack(GCBlocks.wallGC, 6, 0),
                new Object[] {"XXX", "XXX", 'X', new ItemStack(GCBlocks.basicBlock, 1, 4)});
        RecipeUtil.addRecipe(
                new ItemStack(GCBlocks.wallGC, 6, 1),
                new Object[] {"XXX", "XXX", 'X', new ItemStack(GCBlocks.basicBlock, 1, 3)});
        RecipeUtil.addRecipe(
                new ItemStack(GCBlocks.wallGC, 6, 2),
                new Object[] {"XXX", "XXX", 'X', new ItemStack(GCBlocks.blockMoon, 1, 4)});

        // Dungeon Brick Wall
        RecipeUtil.addRecipe(
                new ItemStack(GCBlocks.wallGC, 6, 3),
                new Object[] {"XXX", "XXX", 'X', new ItemStack(GCBlocks.blockMoon, 1, 14)});

        CraftingManager.getInstance()
                .getRecipeList()
                .add(new ShapelessOreRecipe(
                        new ItemStack(GCItems.basicItem, 1, 15),
                        new Object[] {new ItemStack(GCItems.canister, 1, 0), Items.apple, Items.apple}));

        CraftingManager.getInstance()
                .getRecipeList()
                .add(new ShapelessOreRecipe(
                        new ItemStack(GCItems.basicItem, 1, 16),
                        new Object[] {new ItemStack(GCItems.canister, 1, 0), Items.carrot, Items.carrot}));

        CraftingManager.getInstance()
                .getRecipeList()
                .add(new ShapelessOreRecipe(
                        new ItemStack(GCItems.basicItem, 1, 17),
                        new Object[] {new ItemStack(GCItems.canister, 1, 0), Items.melon, Items.melon}));

        CraftingManager.getInstance()
                .getRecipeList()
                .add(new ShapelessOreRecipe(
                        new ItemStack(GCItems.basicItem, 1, 18),
                        new Object[] {new ItemStack(GCItems.canister, 1, 0), Items.potato, Items.potato}));

        CraftingManager.getInstance()
                .getRecipeList()
                .add(new ShapelessOreRecipe(
                        new ItemStack(GCItems.meteorChunk, 3), new Object[] {GCItems.meteoricIronRaw}));

        for (int i = 3; i < 6; i++) {
            if (ItemBasic.names[i].contains("ingot")) {
                CompressorRecipes.addShapelessRecipe(
                        new ItemStack(GCItems.basicItem, 1, i + 3), ItemBasic.names[i], ItemBasic.names[i]);
            }
        }

        /*        // Support for all the spellings of Aluminum
                for (ItemStack stack : aluminumIngots)
                {
                  	CompressorRecipes.addShapelessRecipe(new ItemStack(GCItems.basicItem, 1, 8), stack, stack);
                }
        */
        if (OreDictionary.getOres("ingotBronze").size() > 0) {
            CompressorRecipes.addShapelessRecipe(new ItemStack(GCItems.basicItem, 1, 10), "ingotBronze", "ingotBronze");
        }

        CompressorRecipes.addShapelessRecipe(
                new ItemStack(GCItems.basicItem, 1, 10),
                new ItemStack(GCItems.basicItem, 1, 6),
                new ItemStack(GCItems.basicItem, 1, 7));
        CompressorRecipes.addShapelessRecipe(
                new ItemStack(GCItems.basicItem, 1, 11), Items.iron_ingot, Items.iron_ingot);
        CompressorRecipes.addShapelessRecipe(new ItemStack(GCItems.meteoricIronIngot, 1, 1), meteoricIronIngot);
        CompressorRecipes.addRecipe(
                new ItemStack(GCItems.heavyPlatingTier1, 2, 0),
                "XYZ",
                "XYZ",
                'X',
                new ItemStack(GCItems.basicItem, 1, 9),
                'Y',
                new ItemStack(GCItems.basicItem, 1, 8),
                'Z',
                new ItemStack(GCItems.basicItem, 1, 10));
    }

    public static void setConfigurableRecipes() {
        ItemStack solarPanels = new ItemStack(GCItems.basicItem, 9, 12);
        ItemStack basicWafers = new ItemStack(GCItems.basicItem, 3, 13);
        ItemStack advancedWafers = new ItemStack(GCItems.basicItem, 1, 14);

        CircuitFabricatorRecipes.removeRecipe(solarPanels);
        CircuitFabricatorRecipes.removeRecipe(basicWafers);
        CircuitFabricatorRecipes.removeRecipe(advancedWafers);
        ArrayList<ItemStack> silicons = OreDictionary.getOres(ConfigManagerCore.otherModsSilicon);
        int siliconCount = silicons.size();
        for (int j = 0; j <= siliconCount; j++) {
            ItemStack silicon;
            if (j == 0) silicon = new ItemStack(GCItems.basicItem, 1, 2);
            else {
                silicon = silicons.get(j - 1);
                if (silicon.getItem() == GCItems.basicItem && silicon.getItemDamage() == 2) continue;
            }
            CircuitFabricatorRecipes.addRecipe(solarPanels, new ItemStack[] {
                new ItemStack(Items.diamond),
                silicon,
                silicon,
                new ItemStack(Items.redstone),
                new ItemStack(Items.dye, 1, 4)
            });
            CircuitFabricatorRecipes.addRecipe(basicWafers, new ItemStack[] {
                new ItemStack(Items.diamond),
                silicon,
                silicon,
                new ItemStack(Items.redstone),
                new ItemStack(Blocks.redstone_torch)
            });
            CircuitFabricatorRecipes.addRecipe(advancedWafers, new ItemStack[] {
                new ItemStack(Items.diamond),
                silicon,
                silicon,
                new ItemStack(Items.redstone),
                new ItemStack(Items.repeater)
            });
        }

        CompressorRecipes.removeRecipe(new ItemStack(GCItems.basicItem, 1, 9));
        boolean steelDone = false;
        if (OreDictionary.getOres("ingotSteel").size() > 0) {
            CompressorRecipes.addShapelessRecipe(new ItemStack(GCItems.basicItem, 1, 9), "ingotSteel", "ingotSteel");
            steelDone = true;
        }
        if (!ConfigManagerCore.hardMode || !steelDone)
            CompressorRecipes.addShapelessRecipe(
                    new ItemStack(GCItems.basicItem, 1, 9),
                    Items.coal,
                    new ItemStack(GCItems.basicItem, 1, 11),
                    Items.coal);
        else
            CompressorRecipes.addShapelessAdventure(
                    new ItemStack(GCItems.basicItem, 1, 9),
                    Items.coal,
                    new ItemStack(GCItems.basicItem, 1, 11),
                    Items.coal);
    }

    private static void addBuildCraftCraftingRecipes() {
        //        boolean refineryDone = false;
        //        boolean newBCAPI = false;
        //    	try
        //        {
        //    		Class<?> clazz = Class.forName("buildcraft.api.recipes.IRefineryRecipeManager");
        //    		Method[] mzz = clazz.getMethods();
        //    		for (Method m : mzz)
        //    		{
        //    			if (m.getName().equals("addRecipe"))
        //    			{
        //    				if (m.getParameterTypes()[0].equals(String.class))
        //    				{
        //    		    		newBCAPI = true;
        //    		    		break;
        //    				}
        //    			}
        //    		}
        //
        //    		if (newBCAPI)
        //    		{
        //	    		//Newer Buildcraft API versions
        //	        	BuildcraftRecipeRegistry.refinery.addRecipe("buildcraft:fuel", new
        // FluidStack(GalacticraftCore.gcFluidOil, 1), new FluidStack(FluidRegistry.getFluid("fuel"), 1), 120, 1);
        //	        	refineryDone = true;
        //    		}
        //    		else
        //    		{
        //	    		//Older Buildcraft API versions
        //	        	BuildcraftRecipes.refinery.addRecipe(new FluidStack(GalacticraftCore.gcFluidOil, 1), new
        // FluidStack(FluidRegistry.getFluid("fuel"), 1), 120, 1);
        //	        	refineryDone = true;
        //    		}
        //        }
        //        catch (Exception e) { }
        //
        //    	if (refineryDone)
        //    		GCLog.info("Successfully added GC oil to Buildcraft Refinery recipes.");

        try {
            Class<?> clazz = Class.forName("buildcraft.BuildCraftTransport");

            Object pipeItemsStone = clazz.getField("pipeItemsStone").get(null);
            Object pipeItemsCobblestone = clazz.getField("pipeItemsCobblestone").get(null);
            Object pipeFluidsCobblestone =
                    clazz.getField("pipeFluidsCobblestone").get(null);
            Object pipeFluidsStone = clazz.getField("pipeFluidsStone").get(null);
            Object pipePowerStone = clazz.getField("pipePowerStone").get(null);
            Object pipePowerGold = clazz.getField("pipePowerGold").get(null);

            RecipeUtil.addRecipe(
                    new ItemStack(GCBlocks.sealableBlock, 1, EnumEnclosedBlock.BC_ITEM_COBBLESTONEPIPE.getMetadata()),
                    new Object[] {"XYX", 'Y', pipeItemsCobblestone, 'X', new ItemStack(GCBlocks.basicBlock, 1, 4)});
            RecipeUtil.addRecipe(
                    new ItemStack(GCBlocks.sealableBlock, 1, EnumEnclosedBlock.BC_ITEM_STONEPIPE.getMetadata()),
                    new Object[] {"XYX", 'Y', pipeItemsStone, 'X', new ItemStack(GCBlocks.basicBlock, 1, 4)});
            RecipeUtil.addRecipe(
                    new ItemStack(GCBlocks.sealableBlock, 1, EnumEnclosedBlock.BC_FLUIDS_COBBLESTONEPIPE.getMetadata()),
                    new Object[] {"XYX", 'Y', pipeFluidsCobblestone, 'X', new ItemStack(GCBlocks.basicBlock, 1, 4)});
            RecipeUtil.addRecipe(
                    new ItemStack(GCBlocks.sealableBlock, 1, EnumEnclosedBlock.BC_FLUIDS_STONEPIPE.getMetadata()),
                    new Object[] {"XYX", 'Y', pipeFluidsStone, 'X', new ItemStack(GCBlocks.basicBlock, 1, 4)});
            RecipeUtil.addRecipe(
                    new ItemStack(GCBlocks.sealableBlock, 1, EnumEnclosedBlock.BC_POWER_STONEPIPE.getMetadata()),
                    new Object[] {"XYX", 'Y', pipePowerStone, 'X', new ItemStack(GCBlocks.basicBlock, 1, 4)});
            RecipeUtil.addRecipe(
                    new ItemStack(GCBlocks.sealableBlock, 1, EnumEnclosedBlock.BC_POWER_GOLDPIPE.getMetadata()),
                    new Object[] {"XYX", 'Y', pipePowerGold, 'X', new ItemStack(GCBlocks.basicBlock, 1, 4)});

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addIndustrialCraft2Recipes() {
        RecipeUtil.addRecipe(
                new ItemStack(GCBlocks.sealableBlock, 1, EnumEnclosedBlock.IC2_COPPER_CABLE.getMetadata()),
                new Object[] {
                    "XYX",
                    'Y',
                    RecipeUtil.getIndustrialCraftItem("insulatedCopperCableItem"),
                    'X',
                    new ItemStack(GCBlocks.basicBlock, 1, 4)
                });
        RecipeUtil.addRecipe(
                new ItemStack(GCBlocks.sealableBlock, 1, EnumEnclosedBlock.IC2_GOLD_CABLE.getMetadata()), new Object[] {
                    "XYX",
                    'Y',
                    RecipeUtil.getIndustrialCraftItem("insulatedGoldCableItem"),
                    'X',
                    new ItemStack(GCBlocks.basicBlock, 1, 4)
                });
        RecipeUtil.addRecipe(new ItemStack(GCBlocks.sealableBlock, 1, 4), new Object[] {
            "XYX",
            'Y',
            RecipeUtil.getIndustrialCraftItem("insulatedIronCableItem"),
            'X',
            new ItemStack(GCBlocks.basicBlock, 1, 4)
        });
        RecipeUtil.addRecipe(
                new ItemStack(GCBlocks.sealableBlock, 1, EnumEnclosedBlock.IC2_GLASS_FIBRE_CABLE.getMetadata()),
                new Object[] {
                    "XYX",
                    'Y',
                    RecipeUtil.getIndustrialCraftItem("glassFiberCableItem"),
                    'X',
                    new ItemStack(GCBlocks.basicBlock, 1, 4)
                });
        RecipeUtil.addRecipe(
                new ItemStack(GCBlocks.sealableBlock, 1, EnumEnclosedBlock.IC2_LV_CABLE.getMetadata()), new Object[] {
                    "XYX",
                    'Y',
                    RecipeUtil.getIndustrialCraftItem("insulatedTinCableItem"),
                    'X',
                    new ItemStack(GCBlocks.basicBlock, 1, 4)
                });

        // try
        // {
        // Class<?> clazz = Class.forName("ic2.core.Ic2Items");
        //
        // Object copperDustObject =
        // clazz.getField("crushedCopperOre").get(null);
        // ItemStack copperDustItemStack = (ItemStack) copperDustObject;
        // Class<?> clazz2 =
        // Class.forName("ic2.api.recipe.RecipeInputItemStack");
        // Object o = clazz2.getConstructor(ItemStack.class).newInstance(new
        // ItemStack(GCCoreBlocks.blockMoon, 1, 0));
        // Method addRecipe =
        // Class.forName("ic2.api.recipe.IMachineRecipeManager").getMethod("addRecipe",
        // Class.forName("ic2.api.recipe.IRecipeInput"), NBTTagCompound.class,
        // ItemStack[].class);
        // addRecipe.invoke(Class.forName("ic2.api.recipe.Recipes").getField("macerator").get(null),
        // o, null, new ItemStack[] { new
        // ItemStack(copperDustItemStack.getItem(), 2,
        // copperDustItemStack.getItemDamage()) });
        //
        // Object tinDustObject = clazz.getField("crushedTinOre").get(null);
        // ItemStack tinDustItemStack = (ItemStack) tinDustObject;
        // o = clazz2.getConstructor(ItemStack.class).newInstance(new
        // ItemStack(GCCoreBlocks.blockMoon, 1, 1));
        // addRecipe.invoke(Class.forName("ic2.api.recipe.Recipes").getField("macerator").get(null),
        // o, null, new ItemStack[] { new ItemStack(tinDustItemStack.getItem(),
        // 2, tinDustItemStack.getItemDamage()) });
        // }
        // catch (Throwable e)
        // {
        // e.printStackTrace();
        // } TODO IC2 recipes
    }

    private static void addAppEngRecipes() {
        RecipeUtil.addRecipe(
                new ItemStack(GCBlocks.sealableBlock, 1, EnumEnclosedBlock.ME_CABLE.getMetadata()), new Object[] {
                    "XYX",
                    'Y',
                    AEApi.instance().definitions().parts().cableGlass().stack(AEColor.Transparent, 1),
                    'X',
                    new ItemStack(GCBlocks.basicBlock, 1, 4)
                });
    }

    private static void addExNihiloRecipes() {
        try {
            Class registry = Class.forName("exnihilo.registries.HeatRegistry");
            Method m = registry.getMethod("register", Block.class, float.class);
            m.invoke(null, GCBlocks.unlitTorchLit, 0.1F);
            for (Block torch : GCBlocks.otherModTorchesLit) m.invoke(null, torch, 0.1F);
            GCLog.info("Successfully added space torches as heat sources for Ex Nihilo crucibles etc");
        } catch (Throwable e) {
        }
    }
}
