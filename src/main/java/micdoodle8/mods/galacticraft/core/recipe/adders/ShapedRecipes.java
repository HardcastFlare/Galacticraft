package micdoodle8.mods.galacticraft.core.recipe.adders;

import cpw.mods.fml.common.registry.GameRegistry;
import gregtech.api.enums.ItemList;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.objects.ItemData;
import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.GT_OreDictUnificator;
import ic2.core.Ic2Items;
import micdoodle8.mods.galacticraft.core.blocks.GCBlocks;
import micdoodle8.mods.galacticraft.core.items.GCItems;
import micdoodle8.mods.galacticraft.core.util.GCLog;
import micdoodle8.mods.galacticraft.core.util.RecipeUtil;
import micdoodle8.mods.galacticraft.planets.asteroids.items.AsteroidsItems;
import micdoodle8.mods.galacticraft.planets.mars.items.MarsItems;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ShapedRecipes implements Runnable {

	ItemStack compressedAl = GT_OreDictUnificator.get(OrePrefixes.compressed, Materials.Aluminium, 1);
	ItemStack compressedIron = GT_OreDictUnificator.get(OrePrefixes.compressed, Materials.Iron, 1);
	ItemStack compressedSteel = GT_OreDictUnificator.get(OrePrefixes.compressed, Materials.Steel, 1);
	ItemStack compressedBronze = GT_OreDictUnificator.get(OrePrefixes.compressed, Materials.Bronze, 1);
	ItemStack compressedTin = GT_OreDictUnificator.get(OrePrefixes.compressed, Materials.Tin, 1);
	ItemStack compressedCopper = GT_OreDictUnificator.get(OrePrefixes.compressed, Materials.Copper, 1);
	ItemStack compressedTi = GT_OreDictUnificator.get(OrePrefixes.compressed, Materials.Titanium, 1);
	ItemStack compressedDesh = GT_OreDictUnificator.get(OrePrefixes.compressed, Materials.Desh, 1);
	ItemStack compressedMeteoricIron = GT_OreDictUnificator.get(OrePrefixes.compressed, Materials.MeteoricIron, 1);
	ItemStack compressedSDHD120 = GT_ModHandler.getModItem("GalaxySpace", "item.CompressedSDHD120", 1, 0);

	ItemStack solarModuleSingle = new ItemStack(GCItems.basicItem, 1, 0);
	ItemStack solarModuleFull = new ItemStack(GCItems.basicItem, 1, 1);
	ItemStack frequencyModule = new ItemStack(GCItems.basicItem, 1, 19);

	ItemStack bWafer = GT_OreDictUnificator.get(OrePrefixes.wafer, Materials.Basic, 1);
	ItemStack advWafer = GT_OreDictUnificator.get(OrePrefixes.wafer, Materials.Advanced, 1);
	ItemStack gcAlWire = new ItemStack(GCBlocks.aluminumWire, 1, 0);
	ItemStack gcHeavyAlWire = new ItemStack(GCBlocks.aluminumWire, 1, 1);
	ItemStack oxygenPipe = new ItemStack(GCBlocks.oxygenPipe, 1, 0);
	ItemStack airVent = new ItemStack(GCItems.oxygenVent, 1, 0);
	ItemStack heavyDutyPlateT1 = new ItemStack(GCItems.heavyPlatingTier1, 1, 0);
	ItemStack heavyDutyPlateT2 = new ItemStack(MarsItems.marsItemBasic, 1, 3);
	ItemStack heavyDutyPlateT3 = new ItemStack(AsteroidsItems.basicItem, 1, 0);
	ItemStack tinDecoBlock = new ItemStack(GCBlocks.basicBlock, 1, 4);
	ItemStack tinDecoBlock2 = new ItemStack(GCBlocks.basicBlock, 1, 3);
	ItemStack sensorLens = new ItemStack(GCItems.sensorLens, 1, 0);
	ItemStack steelPole = new ItemStack(GCItems.flagPole, 1, 0);

	ItemStack advAlloyPlate = GT_OreDictUnificator.get(OrePrefixes.plateAlloy, Materials.Advanced, 1);
	ItemStack compressedDualTitanium = GT_ModHandler.getModItem("dreamcraft", "item.TitaniumDualCompressedPlates", 1, 0);
	ItemStack screwMeteor = GT_OreDictUnificator.get(OrePrefixes.screw, Materials.MeteoricSteel, 1);
	ItemStack screwSteel = GT_OreDictUnificator.get(OrePrefixes.screw, Materials.Steel, 1);
	ItemStack screwSSteel = GT_OreDictUnificator.get(OrePrefixes.screw, Materials.StainlessSteel, 1);
	ItemStack boltSSteel = GT_OreDictUnificator.get(OrePrefixes.bolt, Materials.StainlessSteel, 1);
	ItemStack boltTSteel = GT_OreDictUnificator.get(OrePrefixes.bolt, Materials.TungstenSteel, 1);
	ItemStack boltDesh = GT_OreDictUnificator.get(OrePrefixes.bolt, Materials.Desh, 1);
	ItemStack plateDesh = GT_ModHandler.getModItem("GalacticraftMars", "item.null", 1, 5);
	ItemStack ringDesh = GT_OreDictUnificator.get(OrePrefixes.ring, Materials.Desh, 1);
	ItemStack ringRedAlloy = GT_OreDictUnificator.get(OrePrefixes.ring, Materials.RedAlloy, 1);
	ItemStack alFoil = GT_OreDictUnificator.get(OrePrefixes.foil, Materials.Aluminium, 1);
	ItemStack tripleTrinium = GT_OreDictUnificator.get(OrePrefixes.plateTriple, Materials.Trinium, 1);

	ItemStack motorLV = ItemList.Electric_Motor_LV.get(1);
	ItemStack motorHV = ItemList.Electric_Motor_HV.get(1);
	ItemStack conveyorMV = ItemList.Conveyor_Module_MV.get(1);
	ItemStack conveyorHV = ItemList.Conveyor_Module_HV.get(1);
	ItemStack pistonHV = ItemList.Electric_Piston_HV.get(1);
	ItemStack solarPanel = ItemList.Cover_SolarPanel.get(1);
	ItemStack pumpLV = ItemList.Pump_LV.get(1);
	ItemStack pumpHV = ItemList.Pump_HV.get(1);
	ItemStack sensorLV = ItemList.Sensor_LV.get(1);
	ItemStack sensorHV = ItemList.Sensor_HV.get(1);
	ItemStack emitterHV = ItemList.Emitter_HV.get(1);
	ItemStack rotorSteel = GT_OreDictUnificator.get(OrePrefixes.rotor, Materials.Steel, 1);
	ItemStack rubberSheet = GT_ModHandler.getModItem("IC2", "blockRubber", 1, 0);

	ItemStack circuitAdv = GT_OreDictUnificator.get(OrePrefixes.circuit, Materials.Advanced, 1);
	ItemStack circuitData = GT_OreDictUnificator.get(OrePrefixes.circuit, Materials.Data, 1);

	ItemStack cellEmpty = GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Empty, 1);
	ItemStack largeCellSteel = ItemList.Large_Fluid_Cell_Steel.get(1);
	ItemStack largeCellTungSteel = ItemList.Large_Fluid_Cell_TungstenSteel.get(1);
	ItemStack largeCellIridium = ItemList.Large_Fluid_Cell_Iridium.get(1);
	ItemStack largeCellOsmium = ItemList.Large_Fluid_Cell_Osmium.get(1);

	ItemStack pipeMediumSteel = GT_OreDictUnificator.get(OrePrefixes.pipeMedium, Materials.Steel, 1);
	ItemStack pipeMediumBrass = GT_OreDictUnificator.get(OrePrefixes.pipeMedium, Materials.Brass, 1);

	ItemStack casingSolidSteel = ItemList.Casing_SolidSteel.get(1);

	ItemStack chestBufferHV = ItemList.Automation_ChestBuffer_HV.get(1);

	ItemStack computerMonitorCover = ItemList.Cover_Screen.get(1);

	ItemStack reinforcedGlass = GT_OreDictUnificator.get(OrePrefixes.glass, Materials.Reinforced, 1);
	ItemStack wool = GT_OreDictUnificator.get(OrePrefixes.blockWool, 1);
	ItemStack stick = GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Wood, 1);
	ItemStack stickDesh = GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Desh, 1);

	ItemStack wireRedAlloy1x = GT_OreDictUnificator.get(OrePrefixes.wireGt01, Materials.RedAlloy, 1);

	ItemStack cableCopper1x = GT_OreDictUnificator.get(OrePrefixes.cableGt01, Materials.AnyCopper, 1);
	ItemStack cableAlu2x = GT_OreDictUnificator.get(OrePrefixes.cableGt02, Materials.Aluminium, 1);
	ItemStack cableGold2x = GT_OreDictUnificator.get(OrePrefixes.cableGt02, Materials.Gold, 1);

	ItemStack lensDiamond = GT_OreDictUnificator.get(OrePrefixes.lens, Materials.Diamond, 1);
	ItemStack lensReinfGlass = GT_OreDictUnificator.get(OrePrefixes.lens, Materials.ReinforceGlass, 1);

	ItemStack gemExquisiteRuby = GT_OreDictUnificator.get(OrePrefixes.gemExquisite, Materials.Ruby, 1);

	String ironBars = "iron_bars";
	String string = "string";
	String hHammer = "craftingToolHardHammer";
	String wrench = "craftingToolWrench";
	String file = "craftingToolFile";
	String screwdriver = "craftingToolScrewdriver";
	String saw = "craftingToolSaw";

	ItemStack canisterEmpty = new ItemStack(GCItems.oilCanister, 1, GCItems.oilCanister.getMaxDamage());
	ItemStack fuelCanisterFilled = new ItemStack(GCItems.fuelCanister, 1, 1);

	ItemStack ironBlock = GT_OreDictUnificator.get(OrePrefixes.block, Materials.Iron, 1);
	ItemStack steelBlock = GT_OreDictUnificator.get(OrePrefixes.block, Materials.Steel, 1);

	ItemStack stone = GT_OreDictUnificator.get(OrePrefixes.stone, 1);
	ItemStack hopper = GT_OreDictUnificator.get(OrePrefixes.block, "Hopper", 1);

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
	public static boolean addShapedRecipe(ItemStack aOutputStack, Object[] inputs) {
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

	@Override
	public void run() {
		// --- Rocket Launch Pad
		addShapedRecipe(
			new ItemStack(GCBlocks.landingPad, 3, 0),
			new Object[] {
				compressedIron, compressedIron, compressedIron,
				advAlloyPlate, advAlloyPlate, advAlloyPlate,
				ironBlock, ironBlock, ironBlock
			}
		);

		// --- Buggy Fueling Pad
		addShapedRecipe(
			new ItemStack(GCBlocks.landingPad, 3, 1),
			new Object[] {
				compressedSteel, compressedSteel, compressedSteel,
				advAlloyPlate, advAlloyPlate, advAlloyPlate,
				steelBlock, steelBlock, steelBlock
			}
		);

		// --- Oxygen Collector
		addShapedRecipe(
			new ItemStack(GCBlocks.oxygenCollector, 1, 0),
			new Object[] {
				compressedAl, GCItems.oxygenConcentrator, compressedAl,
				airVent, GCItems.oxygenFan, motorHV,
				compressedSteel, cableAlu2x, compressedSteel
			}
		);

		// --- Oxygen Compressor
		addShapedRecipe(
			new ItemStack(GCBlocks.oxygenCompressor, 1, 0),
			new Object[] {
				compressedAl, GCItems.oxygenConcentrator, compressedAl,
				pistonHV, canisterEmpty, motorHV,
				compressedSteel, compressedBronze, compressedSteel
			}
		);

		// --- Oxygen Decompressor
		addShapedRecipe(
			new ItemStack(GCBlocks.oxygenCompressor, 1, 4),
			new Object[] {
				compressedAl, GCItems.oxygenConcentrator, compressedAl,
				motorHV, canisterEmpty, GCItems.oxygenFan,
				compressedSteel, compressedBronze, compressedSteel
			}
		);

		// --- Oxygen Storage Module
		addShapedRecipe(
			new ItemStack(GCBlocks.machineBase2, 1, 8),
			new Object[] {
				compressedSteel, GCItems.oxTankUltraHeavy, compressedSteel,
				GCItems.oxTankUltraHeavy, casingSolidSteel, GCItems.oxTankUltraHeavy,
				compressedSteel, GCItems.oxTankUltraHeavy, compressedSteel
			}
		);

		// --- Oxygen Bubble Distributor
		addShapedRecipe(
			new ItemStack(GCBlocks.oxygenDistributor, 1, 0),
			new Object[] {
				compressedAl, GCItems.oxygenFan, compressedAl,
				airVent, motorHV, airVent,
				compressedSteel, GCItems.oxygenFan, compressedSteel
			}
		);

		// --- Oxygen Sealer
		addShapedRecipe(
			new ItemStack(GCBlocks.oxygenSealer, 1, 0),
			new Object[] {
				compressedAl, airVent, compressedAl,
				airVent, GCBlocks.oxygenDistributor, airVent,
				compressedDesh, GCBlocks.oxygenDetector, compressedDesh
			}
		);

		// --- Oxygen Detector
		addShapedRecipe(
			new ItemStack(GCBlocks.oxygenDetector, 1, 0),
			new Object[] {
				compressedDesh, compressedSteel, compressedDesh,
				airVent, sensorHV, airVent,
				compressedAl, wireRedAlloy1x, compressedAl
			}
		);

		// --- Fuel Loader
		addShapedRecipe(
			new ItemStack(GCBlocks.fuelLoader, 1, 0),
			new Object[] {
				compressedSteel, bWafer, compressedSteel,
				pumpHV, largeCellSteel, motorHV,
				compressedAl, pipeMediumSteel, compressedAl
			}
		);

		// --- Cargo Loader
		// TODO: fix?
		addShapedRecipe(
			new ItemStack(GCBlocks.cargoLoader, 1, 0),
			new Object[] {
				compressedAl, hopper, compressedAl,
				conveyorHV, chestBufferHV, conveyorHV,
				compressedDesh, pipeMediumBrass, compressedDesh
			}
		);

		// --- Cargo Unloader
		addShapedRecipe(
			new ItemStack(GCBlocks.cargoLoader, 1, 4),
			new Object[] {
				compressedDesh, pipeMediumBrass, compressedDesh,
				conveyorHV, chestBufferHV, conveyorHV,
				compressedAl, hopper, compressedAl
			}
		);

		// --- Nasa Workbench --- Added by core mod?
		//recipes.addShaped(<GalacticraftCore:tile.rocketWorkbench>, [
		//[<gregtech:gt.metaitem.01:32652>, <gregtech:gt.metaitem.01:32740>, <gregtech:gt.metaitem.01:32652>],
		//[<ore:waferAdvanced>, <ore:circuitElite>, <ore:waferAdvanced>],
		//[<ore:itemCasingStainlessSteel>, <gregtech:gt.blockcasings:3>, <ore:itemCasingStainlessSteel>]]);

		// --- Tin Decoration Block
		addShapedRecipe(
			tinDecoBlock,
			new Object[] {
				hHammer, compressedTin, null,
				compressedTin, stone, compressedTin,
				null, compressedTin, wrench
			}
		);
		addShapedRecipe(
			tinDecoBlock2,
			new Object[] {
				null, compressedTin, hHammer,
				compressedTin, stone, compressedTin,
				wrench, compressedTin, null
			}
		);

		// --- Air Lock Frame
		addShapedRecipe(
			new ItemStack(GCBlocks.airLockFrame, 2, 0),
			new Object[] {
				compressedDesh, screwSSteel, compressedDesh,
				airVent, screwdriver, airVent,
				compressedAl, screwSSteel, compressedAl
			}
		);

		// --- Air Lock Controller
		addShapedRecipe(
			new ItemStack(GCBlocks.airLockFrame, 1, 1),
			new Object[] {
				compressedDesh, GCItems.oxygenConcentrator, compressedDesh,
				airVent, computerMonitorCover, airVent,
				advWafer, wireRedAlloy1x, advWafer
			}
		);

		// --- Sealable Oxygen Pipe
		addShapedRecipe(
			new ItemStack(GCBlocks.sealableBlock, 1, 1),
			new Object[] {
				hHammer, GCBlocks.oxygenPipe, null,
				GCBlocks.oxygenPipe, tinDecoBlock, GCBlocks.oxygenPipe,
				null, GCBlocks.oxygenPipe, file
			}
		);

		// --- Sealable ME Wire
//        addShapedRecipe(
//			new ItemStack(GCBlocks.sealableBlock, 1, 13),
//			new Object[] {
//                hHammer, <appliedenergistics2:item.ItemMultiPart:11>, null,
//            <appliedenergistics2:item.ItemMultiPart:11>, tinDecoBlock, <appliedenergistics2:item.ItemMultiPart:11>,
//            null, <appliedenergistics2:item.ItemMultiPart:11>, file
//			}
//		);

		// --- Sealable Aluminium Wire
		addShapedRecipe(
			new ItemStack(GCBlocks.sealableBlock, 1, 14),
			new Object[] {
				hHammer, gcAlWire, null,
				gcAlWire, tinDecoBlock, gcAlWire,
				null, gcAlWire, file
			}
		);


		// --- Sealable heavy Aluminium Wire
		addShapedRecipe(
			new ItemStack(GCBlocks.sealableBlock, 1, 15),
			new Object[] {
				hHammer, gcHeavyAlWire, null,
				gcHeavyAlWire, tinDecoBlock, gcHeavyAlWire,
				null, gcHeavyAlWire, file
			}
		);

		// TODO
		// --- Sealable Stone Kinesis Pipe
//        addShapedRecipe(
//			new ItemStack(GCBlocks.sealableBlock:11>, 1, 0),
//			new Object[] {
//            hHammer, <BuildCraft|Transport:item.buildcraftPipe.pipepowerstone>, null,
//            <BuildCraft|Transport:item.buildcraftPipe.pipepowerstone>, tinDecoBlock, <BuildCraft|Transport:item.buildcraftPipe.pipepowerstone>,
//            null, <BuildCraft|Transport:item.buildcraftPipe.pipepowerstone>, file
//			}
//		);
//
//        // --- Sealable Gold Kinesis Pipe
//        addShapedRecipe(
//			new ItemStack(GCBlocks.sealableBlock:12>, 1, 0),
//			new Object[] {
//                hHammer, <BuildCraft|Transport:item.buildcraftPipe.pipepowergold>, null,
//            <BuildCraft|Transport:item.buildcraftPipe.pipepowergold>, tinDecoBlock, <BuildCraft|Transport:item.buildcraftPipe.pipepowergold>,
//            null, <BuildCraft|Transport:item.buildcraftPipe.pipepowergold>, file
//			}
//		);

		// TODO
		// --- Aluminium Wire
//        recipes.addShapeless(gcAlWire, [<ore:cableGt01Aluminium>]);

		// --- Heavy Aluminium Wire
		addShapedRecipe(
			new ItemStack(GCBlocks.aluminumWire, 3, 1),
			new Object[] {
				compressedAl, compressedAl, compressedAl,
				gcAlWire, gcAlWire, gcAlWire,
				compressedAl, compressedAl, compressedAl
			}
		);

		// --- Basic Solar Panel
		addShapedRecipe(
			new ItemStack(GCBlocks.solarPanel, 1, 0),
			new Object[] {
				compressedAl, solarModuleFull, compressedAl,
				gcAlWire, steelPole, gcAlWire,
				compressedSteel, bWafer, compressedSteel
			}
		);

		// --- Advanced Solar Panel
		addShapedRecipe(
			new ItemStack(GCBlocks.solarPanel, 1, 4),
			new Object[] {
				compressedAl, solarModuleFull, compressedAl,
				gcHeavyAlWire, steelPole, gcHeavyAlWire,
				motorLV, advWafer, sensorLV
			}
		);

		// --- Full Solar Panel
		addShapedRecipe(
			solarModuleFull,
			new Object[] {
				solarModuleSingle, solarModuleSingle, solarModuleSingle,
				gcAlWire, bWafer, gcAlWire,
				solarModuleSingle, solarModuleSingle, solarModuleSingle
			}
		);

		// --- Energy Storage Module
		addShapedRecipe(
			new ItemStack(GCBlocks.machineTiered, 1, 0),
			new Object[] {
				compressedSteel, Ic2Items.chargingREBattery, compressedSteel,
				cableCopper1x, ItemList.Hull_MV.get(1), cableCopper1x,
				bWafer, Ic2Items.chargingREBattery, bWafer
			}
		);

		// --- Energy Storage Cluster
		addShapedRecipe(
			new ItemStack(GCBlocks.machineTiered, 1, 8),
			new Object[] {
				compressedTi, Ic2Items.chargingAdvBattery, compressedTi,
				cableGold2x, ItemList.Hull_HV.get(1), cableGold2x,
				advWafer, Ic2Items.chargingAdvBattery, advWafer
			}
		);

		// --- Spin Thruster
		addShapedRecipe(
			new ItemStack(GCBlocks.spinThruster, 1, 0),
			new Object[] {
				compressedTi, compressedTi, compressedTi,
				fuelCanisterFilled, advWafer, fuelCanisterFilled,
				GCItems.rocketEngine, heavyDutyPlateT1, GCItems.rocketEngine
			}
		);

		// --- Display Screen
		addShapedRecipe(
			new ItemStack(GCBlocks.screen, 1, 0),
			new Object[] {
				compressedSteel, solarPanel, compressedSteel,
				bWafer, computerMonitorCover, bWafer,
				compressedSteel, compressedSteel, compressedSteel
			}
		);

		// --- Telemetry Unit
		addShapedRecipe(
			new ItemStack(GCBlocks.telemetry, 1, 0),
			new Object[] {
				frequencyModule, compressedTin, emitterHV,
				bWafer, compressedTin, bWafer,
				compressedTin, compressedCopper, compressedTin
			}
		);

		// TODO
		// --- Arc Lamp
//        addShapedRecipe(
//			new ItemStack(<GalacticraftCore:tile.arclamp>, 1, 0),
//			new Object[] {
//            deshPlate,deshPlate,deshPlate,
//            deshPlate, <GalaxySpace:ceresglowstone>, <ProjRed|Illumination:projectred.illumination.lamp:16>,
//            deshPlate, deshPlate, deshPlate
//			}
//		);
//        addShapedRecipe(
//			new ItemStack(<GalacticraftCore:tile.arclamp>, 1, 0),
//			new Object[] {
//            deshPlate,deshPlate,deshPlate,
//            deshPlate, <GalaxySpace:ioglowstone>, <ProjRed|Illumination:projectred.illumination.lamp:16>,
//            deshPlate, deshPlate, deshPlate
//			}
//		);
//        addShapedRecipe(
//			new ItemStack(<GalacticraftCore:tile.arclamp>, 1, 0),
//			new Object[] {
//            deshPlate,deshPlate,deshPlate,
//            deshPlate, <GalaxySpace:enceladusglowstone>, <ProjRed|Illumination:projectred.illumination.lamp:16>,
//            deshPlate, deshPlate, deshPlate
//			}
//		);
//        addShapedRecipe(
//			new ItemStack(<GalacticraftCore:tile.arclamp>, 1, 0),
//			new Object[] {
//            deshPlate,deshPlate,deshPlate,
//            deshPlate, <GalaxySpace:proteusglowstone>, <ProjRed|Illumination:projectred.illumination.lamp:16>,
//            deshPlate, deshPlate, deshPlate
//			}
//		);
//        addShapedRecipe(
//			new ItemStack(<GalacticraftCore:tile.arclamp>, 1, 0),
//			new Object[] {
//            deshPlate,deshPlate,deshPlate,
//            deshPlate, <GalaxySpace:plutoglowstone>, <ProjRed|Illumination:projectred.illumination.lamp:16>,
//            deshPlate, deshPlate, deshPlate
//			}
//		);

		// --- Oxygen Gear
		addShapedRecipe(
			new ItemStack(GCItems.oxygenGear, 1, 0),
			new Object[] {
				GCBlocks.oxygenPipe, GCBlocks.oxygenPipe, GCBlocks.oxygenPipe,
				pumpHV, GCItems.oxygenConcentrator, pumpHV,
				GCBlocks.oxygenPipe, motorHV, GCBlocks.oxygenPipe
			}
		);

		// --- Light Oxygen Tank
		addShapedRecipe(
			new ItemStack(GCItems.oxTankLight, 1, GCItems.oxTankLight.getMaxDamage()),
			new Object[] {
				compressedAl, GCBlocks.oxygenPipe, compressedAl,
				compressedAl, cellEmpty, compressedAl,
				compressedAl, compressedAl, compressedAl
			}
		);
		addShapedRecipe(
			new ItemStack(GCItems.oxTankLight, 1, GCItems.oxTankLight.getMaxDamage()),
			new Object[] {
				compressedAl, GCBlocks.oxygenPipe, compressedAl,
				compressedAl, Ic2Items.FluidCell, compressedAl,
				compressedAl, compressedAl, compressedAl
			}
		);

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
		addShapedRecipe(
			new ItemStack(GCItems.oxTankMedium, 1, GCItems.oxTankMedium.getMaxDamage()),
			new Object[] {
				compressedMeteoricIron, GCBlocks.oxygenPipe, compressedMeteoricIron,
				compressedMeteoricIron, largeCellSteel, compressedMeteoricIron,
				compressedMeteoricIron, compressedMeteoricIron, compressedMeteoricIron
			}
		);

		// --- Heavy Oxygen Tank
		addShapedRecipe(
			new ItemStack(GCItems.oxTankHeavy, 1, GCItems.oxTankHeavy.getMaxDamage()),
			new Object[] {
				plateDesh, GCBlocks.oxygenPipe, plateDesh,
				plateDesh, largeCellTungSteel, plateDesh,
				plateDesh, plateDesh, plateDesh
			}
		);

		// --- Super Heavy Oxygen Tank
		addShapedRecipe(
			new ItemStack(GCItems.oxTankSuperHeavy, 1, GCItems.oxTankSuperHeavy.getMaxDamage()),
			new Object[] {
				compressedDualTitanium, GCBlocks.oxygenPipe, compressedDualTitanium,
				compressedDualTitanium, largeCellIridium, compressedDualTitanium,
				compressedDualTitanium, compressedDualTitanium, compressedDualTitanium
			}
		);

		// --- Ultra Heavy Oxygen Tank
		addShapedRecipe(
			new ItemStack(GCItems.oxTankUltraHeavy, 1, GCItems.oxTankUltraHeavy.getMaxDamage()),
			new Object[] {
				tripleTrinium, GCBlocks.oxygenPipe, tripleTrinium,
				tripleTrinium, largeCellOsmium, tripleTrinium,
				tripleTrinium, tripleTrinium, tripleTrinium
			}
		);

		// --- Sensor Lens
		addShapedRecipe(
			sensorLens,
			new Object[] {
				ringRedAlloy, lensDiamond, ringRedAlloy,
				circuitAdv, lensReinfGlass, circuitAdv,
				screwSSteel, screwdriver, screwSSteel
			}
		);

		// --- Sensor Glasses
		addShapedRecipe(
			new ItemStack(GCItems.sensorGlasses, 1, 0),
			new Object[] {
				circuitData, screwMeteor, circuitData,
				ringDesh, boltDesh, ringDesh,
				sensorLens, screwdriver, sensorLens
			}
		);

		// TODO - below here incomplete/unchecked
		// --- Heavy Duty Pickaxe
		addShapedRecipe(
			new ItemStack(GCItems.steelPickaxe, 1, 0),
			new Object[] {
				compressedSteel, compressedSteel,compressedSteel,
				file, stick, hHammer,
				null, stick, null
			}
		);

		// --- Heavy Duty Axe
		addShapedRecipe(
			new ItemStack(GCItems.steelAxe, 1, 0),
			new Object[] {
				compressedSteel, compressedSteel, hHammer,
				compressedSteel, stick, null,
				file, stick, null
			}
		);

		// --- Heavy Duty Hoe
		addShapedRecipe(
			new ItemStack(GCItems.steelHoe, 1, 0),
			new Object[] {
				compressedSteel, compressedSteel, hHammer,
				file, stick, null,
				null, stick, null
			}
		);

		// --- Heavy Duty Shovel
		addShapedRecipe(
			new ItemStack(GCItems.steelSpade, 1, 0),
			new Object[] {
				file, compressedSteel, hHammer,
				null, stick, null,
				null, stick, null
			}
		);

		// --- Heavy Duty Sword
		addShapedRecipe(
			new ItemStack(GCItems.steelSword, 1, 0),
			new Object[] {
				null, compressedSteel, null,
				file, compressedSteel, hHammer,
				null, stick, null
			}
		);

		// --- Heavy Duty Helmet
		addShapedRecipe(
			new ItemStack(GCItems.steelHelmet, 1, 0),
			new Object[] {
				compressedSteel, compressedSteel, compressedSteel,
				compressedSteel, hHammer, compressedSteel,
				null, null, null
			}
		);

		// --- Heavy Duty Chest Plate
		addShapedRecipe(
			new ItemStack(GCItems.steelChestplate, 1, 0),
			new Object[] {
				compressedSteel, hHammer, compressedSteel,
				compressedSteel, compressedSteel, compressedSteel,
				compressedSteel, compressedSteel, compressedSteel
			}
		);

		// --- Heavy Duty Leggings
		addShapedRecipe(
			new ItemStack(GCItems.steelLeggings, 1, 0),
			new Object[] {
				compressedSteel, compressedSteel, compressedSteel,
				compressedSteel, hHammer, compressedSteel,
				compressedSteel, null, compressedSteel
			}
		);

		// --- Heavy Duty Boots
		addShapedRecipe(
			new ItemStack(GCItems.steelBoots, 1, 0),
			new Object[] {
				compressedSteel, null, compressedSteel,
				compressedSteel, hHammer, compressedSteel,
				null, null, null
			}
		);

		// --- Desh Helmet
		addShapedRecipe(
			new ItemStack(MarsItems.deshHelmet, 1, 0),
			new Object[] {
				plateDesh, plateDesh, plateDesh,
				plateDesh, hHammer, plateDesh,
				null, null, null
			}
		);

		// --- Desh Chest Plate
		addShapedRecipe(
			new ItemStack(MarsItems.deshChestplate, 1, 0),
			new Object[] {
				plateDesh, hHammer, plateDesh,
				plateDesh, plateDesh, plateDesh,
				plateDesh, plateDesh, plateDesh
			}
		);

		// --- Desh Leggings
		addShapedRecipe(
			new ItemStack(MarsItems.deshLeggings, 1, 0),
			new Object[] {
				plateDesh, plateDesh, plateDesh,
				plateDesh, hHammer, plateDesh,
				plateDesh, null, plateDesh
			}
		);

		// --- Desh Boots
		addShapedRecipe(
			new ItemStack(MarsItems.deshBoots, 1, 0),
			new Object[] {
				plateDesh, null, plateDesh,
				plateDesh, hHammer, plateDesh,
				null, null, null
			}
		);

		// --- Titanium Pickaxe
		addShapedRecipe(
			new ItemStack(AsteroidsItems.titaniumPickaxe, 1, 0),
			new Object[] {
				compressedTi, compressedTi,compressedTi,
				file, stick, hHammer,
				null, stick, null
			}
		);

		// --- Titanium Axe
		addShapedRecipe(
			new ItemStack(AsteroidsItems.titaniumAxe, 1, 0),
			new Object[] {
				compressedTi, compressedTi, hHammer,
				compressedTi, stick, null,
				file, stick, null
			}
		);

		// --- Titanium Hoe
		addShapedRecipe(
			new ItemStack(AsteroidsItems.titaniumHoe, 1, 0),
			new Object[] {
				compressedTi, compressedTi, hHammer,
				file, stick, null,
				null, stick, null
			}
		);

		// --- Titanium Shovel
		addShapedRecipe(
			new ItemStack(AsteroidsItems.titaniumSpade, 1, 0),
			new Object[] {
				file, compressedTi, hHammer,
				null, stick, null,
				null, stick, null
			}
		);

		// --- Titanium Sword
		addShapedRecipe(
			new ItemStack(AsteroidsItems.titaniumSword, 1, 0),
			new Object[] {
				null, compressedTi, null,
				file, compressedTi, hHammer,
				null, stick, null
			}
		);

		// --- Desh Pickaxe
		addShapedRecipe(
			new ItemStack(MarsItems.deshPickaxe, 1, 0),
			new Object[] {
				gemExquisiteRuby, plateDesh,plateDesh,
				file, stickDesh, plateDesh,
				null, stickDesh, hHammer
			}
		);

		// --- Desh Axe
		addShapedRecipe(
			new ItemStack(MarsItems.deshAxe, 1, 0),
			new Object[] {
				plateDesh, plateDesh, gemExquisiteRuby,
				plateDesh, stickDesh, hHammer,
				file, stickDesh, null
			}
		);

		// --- Desh Hoe
		addShapedRecipe(
			new ItemStack(MarsItems.deshHoe, 1, 0),
			new Object[] {
				plateDesh, plateDesh, gemExquisiteRuby,
				file, stickDesh, hHammer,
				null, stickDesh, null
			}
		);

		// --- Desh Shovel
		addShapedRecipe(
			new ItemStack(MarsItems.deshSpade, 1, 0),
			new Object[] {
				file, plateDesh, gemExquisiteRuby,
				null, stickDesh, hHammer,
				null, stickDesh, null
			}
		);

		// --- Desh Sword
		addShapedRecipe(
			new ItemStack(MarsItems.deshSword, 1, 0),
			new Object[] {
				null, plateDesh, gemExquisiteRuby,
				file, plateDesh, hHammer,
				null, stickDesh, null
			}
		);

		// --- Titanium Helm
		addShapedRecipe(
			new ItemStack(AsteroidsItems.titaniumHelmet, 1, 0),
			new Object[] {
				compressedTi, compressedTi, compressedTi,
				compressedTi, hHammer, compressedTi,
				null, null, null
			}
		);

		// --- Titanium Chest Plate
		addShapedRecipe(
			new ItemStack(AsteroidsItems.titaniumChestplate, 1, 0),
			new Object[] {
				compressedTi, hHammer, compressedTi,
				compressedTi, compressedTi, compressedTi,
				compressedTi, compressedTi, compressedTi
			}
		);

		// --- Titanium Leggings
		addShapedRecipe(
			new ItemStack(AsteroidsItems.titaniumLeggings, 1, 0),
			new Object[] {
				compressedTi, compressedTi, compressedTi,
				compressedTi, hHammer, compressedTi,
				compressedTi, null, compressedTi
			}
		);

		// --- Titanium Boots
		addShapedRecipe(
			new ItemStack(AsteroidsItems.titaniumBoots, 1, 0),
			new Object[] {
				compressedTi, null, compressedTi,
				compressedTi, hHammer, compressedTi,
				null, null, null
			}
		);

		// --- Oxygen Vent
		addShapedRecipe(
			airVent,
			new Object[] {
				ironBars, compressedTin, ironBars,
				compressedTin, compressedSteel, compressedTin,
				ironBars, compressedTin, ironBars
			}
		);

		// --- Oxygen Fan
		addShapedRecipe(
			new ItemStack(GCItems.oxygenFan, 1, 0),
			new Object[] {
				screwSteel, screwdriver, screwSteel,
				rotorSteel, <ore:stickLongStainlessSteel>, rotorSteel,
				screwSteel, wrench, screwSteel
			}
		);

		// --- Oxygen Concentrator
		addShapedRecipe(
			new ItemStack(GCItems.oxygenConcentrator, 1, 0),
			new Object[] {
				compressedSteel, GCItems.oxygenVent, compressedSteel,
				compressedSteel, GCItems.oxygenFan, compressedSteel,
				GCItems.canister, pumpHV, GCItems.canister
			}
		);

		// --- Tier 1 Rocket Engine
		addShapedRecipe(
			new ItemStack(GCItems.rocketEngine, 1, 0),
			new Object[] {
				compressedSDHD120, canisterEmpty, compressedSDHD120,
				heavyDutyPlateT1, ItemList.Casing_Firebox_Steel.get(1), heavyDutyPlateT1,
				heavyDutyPlateT1, ItemList.Cover_ActivityDetector.get(1), heavyDutyPlateT1
			}
		);

		// --- Tier 1 Booster
		addShapedRecipe(
			new ItemStack(GCItems.rocketEngine, 1, 1),
			new Object[] {
				compressedMeteoricIron, compressedMeteoricIron, compressedMeteoricIron,
				heavyDutyPlateT1, fuelCanisterFilled, heavyDutyPlateT1,
				heavyDutyPlateT1, GCItems.oxygenVent, heavyDutyPlateT1
			}
		);

		// --- Nose Cone
		ItemStack cageLampInverted = GT_ModHandler.getModItem("ProjRed|Illumination", "projectred.illumination.cagelamp2.inv", 1, 14);
		addShapedRecipe(
			new ItemStack(GCItems.partNoseCone, 1, 0),
			new Object[] {
				screwdriver, cageLampInverted, hHammer,
				screwSSteel, heavyDutyPlateT1, screwSSteel,
				heavyDutyPlateT1, heavyDutyPlateT1, heavyDutyPlateT1
			}
		);

		// --- Rocket Fins
		addShapedRecipe(
			new ItemStack(GCItems.partFins, 1, 0),
			new Object[] {
				hHammer, compressedSteel, file,
				heavyDutyPlateT1, compressedSteel, heavyDutyPlateT1,
				heavyDutyPlateT1, saw, heavyDutyPlateT1
			}
		);

		// --- Heavy Rocket Fins
		addShapedRecipe(
			new ItemStack(<GalacticraftMars:item.itemBasicAsteroids:2>, 1, 0),
			new Object[] {
				hHammer, heavyDutyPlateT2, file,
				heavyDutyPlateT3, heavyDutyPlateT2, heavyDutyPlateT3,
				heavyDutyPlateT3, saw, heavyDutyPlateT3
			}
		);

		// --- Oil Extractor
		addShapedRecipe(
			new ItemStack(<GalacticraftCore:item.oilExtractor>, 1, 0),
			new Object[] {
				<ore:pipeTinySteel>, screwdriver, screwSSteel,
				screwSSteel, <GalacticraftCore:item.oilCanisterPartial:1001>, compressedBronze,
				<ProjRed|Illumination:projectred.illumination.lightbutton:14>, compressedBronze, compressedBronze
			}
		);

		// --- Buggy Wheel
		addShapedRecipe(
			new ItemStack(<GalacticraftCore:item.buggymat>, 1, 0),
			new Object[] {
				compressedSteel, <ore:plateAnyRubber>, compressedSteel,
				<ore:plateAnyRubber>, compressedTi, <ore:plateAnyRubber>,
				compressedSteel, <ore:plateAnyRubber>, compressedSteel
			}
		);

		// --- Buggy Seat
		addShapedRecipe(
			new ItemStack(<GalacticraftCore:item.buggymat:1>, 1, 0),
			new Object[] {
				null, rubberSheet, compressedSteel,
				rubberSheet, rubberSheet, compressedSteel,
				compressedSteel, compressedSteel, compressedSteel
			}
		);

		// --- Buggy Storage Box
		addShapedRecipe(
			new ItemStack(<GalacticraftCore:item.buggymat:2>, 1, 0),
			new Object[] {
				compressedSteel, rubberSheet, compressedSteel,
				compressedSteel, <IronChest:BlockIronChest>, compressedSteel,
				compressedSteel, compressedSteel, compressedSteel
			}
		);

		// --- Frequency Module
		addShapedRecipe(
			new ItemStack(<GalacticraftCore:item.basicItem:19>, 1, 0),
			new Object[] {
				compressedAl, <gregtech:gt.metaitem.01:32692>, compressedAl,
				bWafer, <gregtech:gt.metaitem.01:32740>, bWafer,
				compressedTin, <gregtech:gt.metaitem.01:32500>, compressedTin
			}
		);

		// --- Walkway
		addShapedRecipe(
			new ItemStack(<GalacticraftMars:tile.walkway> * 2, 1, 0),
			new Object[] {
				plateDesh, plateDesh, plateDesh,
				null, <ore:blockDesh>, null,
				plateDesh, plateDesh, plateDesh
			}
		);

//// --- Desh Sticks
//        recipes.addShapeless(<GalacticraftMars:item.null:1>, [<gregtech:gt.metaitem.01:23884>]);
//
//// --- Desh Rod
//        recipes.addShapeless(<gregtech:gt.metaitem.01:23884>, [<GalacticraftMars:item.null:1>]);
//
//        // --- Battery
//        recipes.addShapeless(<GalacticraftCore:item.battery:*>, [<gregtech:gt.metaitem.01:32500>]);

		// --- Standard Wrench
		addShapedRecipe(
			new ItemStack(<GalacticraftCore:item.standardWrench>, 1, 0),
			new Object[] {
				<ore:plateSteel>, saw, <ore:plateSteel>,
				<ore:screwSteel>, <ore:stickSteel>, <ore:screwSteel>,
				screwdriver, <ore:stickSteel>, file
			}
		);

		// --- Heavy Rocket Engine
		addShapedRecipe(
			new ItemStack(<GalacticraftMars:item.itemBasicAsteroids:1> , 1, 0),
			new Object[] {
				<GalacticraftCore:item.engine:1>, heavyDutyPlateT3, <GalacticraftCore:item.engine:1>,
				heavyDutyPlateT3, heavyDutyPlateT3, heavyDutyPlateT3,
				<GalacticraftCore:item.engine>, heavyDutyPlateT3, <GalacticraftCore:item.engine>
			}
		);

		// --- Heavy Nose Cone
		addShapedRecipe(
			new ItemStack(<GalacticraftMars:item.heavyNoseCone>, 1, 0),
			new Object[] {
				screwdriver, <GalacticraftCore:item.noseCone>, hHammer,
				<ore:screwTitanium>, heavyDutyPlateT3, <ore:screwTitanium>,
				heavyDutyPlateT3, heavyDutyPlateT3, heavyDutyPlateT3
			}
		);

		// --- Red Core
		addShapedRecipe(
			new ItemStack(<GalacticraftMars:item.itemBasicAsteroids:8>, 1, 0),
			new Object[] {
				<ore:ringRedAlloy>, CompressedIron, <ore:ringRedAlloy>,
				CompressedIron, <ore:lensDiamond>, CompressedIron,
				<ore:ringRedAlloy>, CompressedIron, <ore:ringRedAlloy>
			}
		);

		// --- Energy Beam Reflector
		addShapedRecipe(
			new ItemStack(<GalacticraftMars:tile.beamReflector>, 1, 0),
			new Object[] {
				DeshRing, <GalacticraftMars:item.itemBasicAsteroids:8>, DeshRing,
				<ore:screwDesh>, stickDesh, <ore:screwDesh>,
				plateDesh, <ore:blockDesh>, plateDesh
			}
		);

		// --- Energy Beam Receiver
		addShapedRecipe(
			new ItemStack(<GalacticraftMars:tile.beamReceiver>, 1, 0),
			new Object[] {
				compressedTin, DeshRing, compressedTin,
				DeshRing, <GalacticraftMars:item.itemBasicAsteroids:8>, DeshRing,
				compressedTin, DeshRing, compressedTin
			}
		);

		// --- Short range Teleporter
		addShapedRecipe(
			new ItemStack(<GalacticraftMars:tile.telepadShort>, 1, 0),
			new Object[] {
				compressedTi, plateDesh, compressedTi,
				<GalacticraftMars:item.itemBasicAsteroids:8>, <gregtech:gt.metaitem.01:32672>, <GalacticraftMars:item.itemBasicAsteroids:8>,
				compressedTi, plateDesh, compressedTi
			}
		);

		// --- Cryogenic Chamber
		addShapedRecipe(
			new ItemStack(<GalacticraftMars:tile.marsMachine:4>, 1, 0),
			new Object[] {
				heavyDutyPlateT3, <GraviSuite:itemSimpleItem:2>, heavyDutyPlateT3,
				heavyDutyPlateT2, <CarpentersBlocks:itemCarpentersBed>, heavyDutyPlateT2,
				heavyDutyPlateT3, <minecraft:clock>, heavyDutyPlateT3
			}
		);

		// --- Terraformer
		addShapedRecipe(
			new ItemStack(<GalacticraftMars:tile.marsMachine>, 1, 0),
			new Object[] {
				compressedTi, <GalacticraftCore:item.oxygenConcentrator>, compressedTi,
				plateDesh, <gregtech:gt.blockcasings2:4>, plateDesh,
				<gregtech:gt.metaitem.01:32602>, <extracells:certustank>, <gregtech:gt.metaitem.01:32612>
			}
		);

		// --- Launch Controller
		addShapedRecipe(
			new ItemStack(<GalacticraftMars:tile.marsMachine:8>, 1, 0),
			new Object[] {
				AdvWafer, <GalacticraftCore:item.basicItem:19>, AdvWafer,
				plateDesh, <gregtech:gt.blockmachines:13>, plateDesh,
				<ore:cableGt02Aluminium>, plateDesh, <ore:cableGt02Aluminium>
			}
		);

		// --- Grappler
		addShapedRecipe(
			new ItemStack(<GalacticraftMars:item.grapple>, 1, 0),
			new Object[] {
				null, null, <ore:toolHeadArrowMeteoricSteel>,
				<dreamcraft:item.MeteoricIronString>, <dreamcraft:item.MeteoricIronString>, <dreamcraft:item.MeteoricIronString>,
				<ore:ringMeteoricSteel>, null, null
			}
		);

		// --- Astro Miner Base
		addShapedRecipe(
			new ItemStack(<GalacticraftMars:tile.minerBase> * 4, 1, 0),
			new Object[] {
				<ore:compressedTitanium>, <ore:chestSteel>, <ore:compressedTitanium>,
				<GalacticraftMars:item.itemBasicAsteroids:8>, <ore:frameGtTungsten>, <GalacticraftMars:item.itemBasicAsteroids:8>,
				<ore:compressedDesh>, <GalacticraftCore:tile.machineTiered>, <ore:compressedDesh>
			}
		);

		// --- Orion Drive
		addShapedRecipe(
			new ItemStack(<GalacticraftMars:item.orionDrive>, 1, 0),
			new Object[] {
				<ore:compressedTitanium>, <GalacticraftMars:item.itemBasicAsteroids:8>, <ore:compressedTitanium>,
				<ore:circuitMaster>, <ore:oc:hdd3>, <ore:circuitMaster>,
				<ore:compressedSteel>, <GalacticraftMars:item.itemBasicAsteroids:8>, <ore:compressedSteel>
			}
		);

		// --- Canvas
		addShapedRecipe(
			new ItemStack(<GalacticraftCore:item.canvas>, 1, 0),
			new Object[] {
				null, <harvestcraft:wovencottonItem>, <ore:stickPlastic>,
				<harvestcraft:wovencottonItem>, <ore:stickPlastic>, <harvestcraft:wovencottonItem>,
				<ore:stickPlastic>, <harvestcraft:wovencottonItem>, null
			}
		);

		// --- Ambient Thermal Controller
		addShapedRecipe(
			new ItemStack(<GalacticraftCore:item.basicItem:20>, 1, 0),
			new Object[] {
				<ore:circuitAdvanced>, <GalacticraftCore:item.airVent>, <ore:circuitAdvanced>,
				<GalacticraftCore:item.basicItem:10>, compressedSteel, <GalacticraftCore:item.basicItem:10>,
				<GalacticraftCore:item.basicItem:8>, <GalacticraftCore:item.basicItem:13>, <GalacticraftCore:item.basicItem:8>
			}
		);

		// --- Schematics Moon Buggy
		recipes.addShapeless(<GalacticraftCore:item.schematic>, [<GalacticraftCore:item.schematic:1>]);

		// --- Schematics Tier 2 Rocket
		recipes.addShapeless(<GalacticraftCore:item.schematic:1>, [<GalacticraftCore:item.schematic>]);

		// --- Schematics Tier 3 Rocket
		addShapedRecipe(
			new ItemStack(<GalacticraftMars:item.schematic:1>, 1, 0),
			new Object[] {
				<GalacticraftMars:item.schematic>, null, null,
				null, null, null,
				null, null, null
			}
		);
		// -
		addShapedRecipe(
			new ItemStack(<GalacticraftMars:item.schematic:2>, 1, 0),
			new Object[] {
				null, <GalacticraftMars:item.schematic>, null,
				null, null, null,
				null, null, null
			}
		);

		// --- Schematics Cargo Rocket
		addShapedRecipe(
			new ItemStack(<GalacticraftMars:item.schematic>, 1, 0),
			new Object[] {
				<GalacticraftMars:item.schematic:1>, null, null,
				null, null, null,
				null, null, null
			}
		);
		// -
		addShapedRecipe(
			new ItemStack(<GalacticraftMars:item.schematic:2>, 1, 0),
			new Object[] {
				null, <GalacticraftMars:item.schematic:1>, null,
				null, null, null,
				null, null, null
			}
		);

		// --- Schematics Astro Miner
		addShapedRecipe(
			new ItemStack(<GalacticraftMars:item.schematic>, 1, 0),
			new Object[] {
				<GalacticraftMars:item.schematic:2>, null, null,
				null, null, null,
				null, null, null
			}
		);
		// -
		addShapedRecipe(
			new ItemStack(<GalacticraftMars:item.schematic:1>, 1, 0),
			new Object[] {
				null, <GalacticraftMars:item.schematic:2>, null,
				null, null, null,
				null, null, null
			}
		);

		// --- Parachute
		addShapedRecipe(
			new ItemStack(<GalacticraftCore:item.parachute>, 1, 0),
			new Object[] {
				<GalacticraftCore:item.canvas>, <GalacticraftCore:item.canvas>, <GalacticraftCore:item.canvas>,
				<ore:wireFineSteel>, null, <ore:wireFineSteel>,
				<ore:wireFineSteel>, <ore:wireFineSteel>, <ore:wireFineSteel>
			}
		);

// --- Raw Meteoric Iron
//        recipes.addShapeless(<GalacticraftCore:item.meteoricIronRaw>, [<GalacticraftCore:item.meteoricIronRaw:*>]);
//
//// --- Rocket Tier 1
//        recipes.addShapeless(<GalacticraftCore:item.spaceship>, [<GalacticraftCore:item.spaceship:*>]);
//
//// --- Moon Buggy
//        recipes.addShapeless(<GalacticraftCore:item.buggy>, [<GalacticraftCore:item.buggy:*>]);
//
//// --- Rocket Tier 2
//        recipes.addShapeless(<GalacticraftMars:item.spaceshipTier2>, [<GalacticraftMars:item.spaceshipTier2:1>]);
//// -
//        recipes.addShapeless(<GalacticraftMars:item.spaceshipTier2>, [<GalacticraftMars:item.spaceshipTier2:2>]);
//// -
//        recipes.addShapeless(<GalacticraftMars:item.spaceshipTier2>, [<GalacticraftMars:item.spaceshipTier2:3>]);
//
//// --- Rocket Tier 3
//        recipes.addShapeless(<GalacticraftMars:item.itemTier3Rocket>, [<GalacticraftMars:item.itemTier3Rocket:*>]);
//
//// --- Cargo Rocket 1
//        recipes.addShapeless(<GalacticraftMars:item.spaceshipTier2:11>, [<GalacticraftMars:item.spaceshipTier2:12>]);
//// -
//        recipes.addShapeless(<GalacticraftMars:item.spaceshipTier2:11>, [<GalacticraftMars:item.spaceshipTier2:13>]);
	}
}
