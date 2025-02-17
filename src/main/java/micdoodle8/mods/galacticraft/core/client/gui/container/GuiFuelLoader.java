package micdoodle8.mods.galacticraft.core.client.gui.container;

import java.util.ArrayList;
import java.util.List;

import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import micdoodle8.mods.galacticraft.core.client.gui.element.GuiElementInfoRegion;
import micdoodle8.mods.galacticraft.core.energy.EnergyDisplayHelper;
import micdoodle8.mods.galacticraft.core.inventory.ContainerFuelLoader;
import micdoodle8.mods.galacticraft.core.network.PacketSimple;
import micdoodle8.mods.galacticraft.core.network.PacketSimple.EnumSimplePacket;
import micdoodle8.mods.galacticraft.core.tile.TileEntityFuelLoader;
import micdoodle8.mods.galacticraft.core.util.EnumColor;
import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class GuiFuelLoader extends GuiContainerGC {

    private static final ResourceLocation fuelLoaderTexture = new ResourceLocation(
            GalacticraftCore.ASSET_PREFIX,
            "textures/gui/fuel_loader.png");

    private final TileEntityFuelLoader fuelLoader;

    private GuiButton buttonLoadFuel;
    private final GuiElementInfoRegion electricInfoRegion = new GuiElementInfoRegion(
            (this.width - this.xSize) / 2 + 112,
            (this.height - this.ySize) / 2 + 65,
            56,
            9,
            new ArrayList<String>(),
            this.width,
            this.height,
            this);

    private final List<String> fuelTankDesc = new ArrayList<>();

    public GuiFuelLoader(InventoryPlayer par1InventoryPlayer, TileEntityFuelLoader par2TileEntityAirDistributor) {
        super(new ContainerFuelLoader(par1InventoryPlayer, par2TileEntityAirDistributor));
        this.fuelLoader = par2TileEntityAirDistributor;
        this.ySize = 180;
    }

    @Override
    protected void actionPerformed(GuiButton par1GuiButton) {
        switch (par1GuiButton.id) {
            case 0:
                GalacticraftCore.packetPipeline.sendToServer(
                        new PacketSimple(
                                EnumSimplePacket.S_UPDATE_DISABLEABLE_BUTTON,
                                new Object[] { this.fuelLoader.xCoord, this.fuelLoader.yCoord, this.fuelLoader.zCoord,
                                        0 }));
                break;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void initGui() {
        super.initGui();
        this.fuelTankDesc.clear();
        this.fuelTankDesc.add(GCCoreUtil.translate("gui.fuelTank.desc.2"));
        this.fuelTankDesc.add(GCCoreUtil.translate("gui.fuelTank.desc.3"));
        this.fuelTankDesc.add("0.0/0.0 B");
        this.infoRegions.add(
                new GuiElementInfoRegion(
                        (this.width - this.xSize) / 2 + 7,
                        (this.height - this.ySize) / 2 + 33,
                        16,
                        38,
                        this.fuelTankDesc,
                        this.width,
                        this.height,
                        this));
        final List<String> batterySlotDesc = new ArrayList<>();
        batterySlotDesc.add(GCCoreUtil.translate("gui.batterySlot.desc.0"));
        batterySlotDesc.add(GCCoreUtil.translate("gui.batterySlot.desc.1"));
        this.infoRegions.add(
                new GuiElementInfoRegion(
                        (this.width - this.xSize) / 2 + 50,
                        (this.height - this.ySize) / 2 + 54,
                        18,
                        18,
                        batterySlotDesc,
                        this.width,
                        this.height,
                        this));
        final List<String> electricityDesc = new ArrayList<>();
        electricityDesc.add(GCCoreUtil.translate("gui.energyStorage.desc.0"));
        electricityDesc.add(
                EnumColor.YELLOW + GCCoreUtil.translate("gui.energyStorage.desc.1")
                        + ((int) Math.floor(this.fuelLoader.getEnergyStoredGC()) + " / "
                                + (int) Math.floor(this.fuelLoader.getMaxEnergyStoredGC())));
        this.electricInfoRegion.tooltipStrings = electricityDesc;
        this.electricInfoRegion.xPosition = (this.width - this.xSize) / 2 + 112;
        this.electricInfoRegion.yPosition = (this.height - this.ySize) / 2 + 65;
        this.electricInfoRegion.parentWidth = this.width;
        this.electricInfoRegion.parentHeight = this.height;
        this.infoRegions.add(this.electricInfoRegion);
        this.buttonList.add(
                this.buttonLoadFuel = new GuiButton(
                        0,
                        this.width / 2 + 2,
                        this.height / 2 - 49,
                        76,
                        20,
                        GCCoreUtil.translate("gui.button.loadfuel.name")));
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        this.fontRendererObj.drawString(this.fuelLoader.getInventoryName(), 60, 10, 4210752);
        this.buttonLoadFuel.enabled = this.fuelLoader.disableCooldown == 0
                && this.fuelLoader.fuelTank.getFluid() != null
                && this.fuelLoader.fuelTank.getFluid().amount != 0;
        this.buttonLoadFuel.displayString = !this.fuelLoader.getDisabled(0)
                ? GCCoreUtil.translate("gui.button.stoploading.name")
                : GCCoreUtil.translate("gui.button.loadfuel.name");
        this.fontRendererObj.drawString(
                GCCoreUtil.translate("gui.message.status.name") + ": " + this.getStatus(),
                28,
                45 + 23 - 46,
                4210752);
        // this.fontRendererObj.drawString("" + this.fuelLoader.storage.getMaxExtract(),
        // 28, 56 + 23 - 46, 4210752);
        // this.fontRendererObj.drawString(ElectricityDisplay.getDisplay(this.fuelLoader.getVoltage(),
        // ElectricUnit.VOLTAGE), 28, 68 + 23 - 46, 4210752);
        this.fontRendererObj
                .drawString(GCCoreUtil.translate("container.inventory"), 8, this.ySize - 118 + 2 + 11, 4210752);
    }

    private String getStatus() {
        if (this.fuelLoader.fuelTank.getFluid() == null || this.fuelLoader.fuelTank.getFluid().amount == 0) {
            return EnumColor.DARK_RED + GCCoreUtil.translate("gui.status.nofuel.name");
        }
        if (!this.fuelLoader.coorectTier) {
            return EnumColor.DARK_RED + GCCoreUtil.translate("gui.status.lowtier.name");
        }
        return this.fuelLoader.getGUIstatus();
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(GuiFuelLoader.fuelLoaderTexture);
        final int var5 = (this.width - this.xSize) / 2;
        final int var6 = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(var5, var6 + 5, 0, 0, this.xSize, 181);

        final int fuelLevel = this.fuelLoader.getScaledFuelLevel(38);
        this.drawTexturedModalRect(
                (this.width - this.xSize) / 2 + 7,
                (this.height - this.ySize) / 2 + 17 + 54 - fuelLevel,
                176,
                38 - fuelLevel,
                16,
                fuelLevel);

        final List<String> electricityDesc = new ArrayList<>();
        electricityDesc.add(GCCoreUtil.translate("gui.energyStorage.desc.0"));
        EnergyDisplayHelper.getEnergyDisplayTooltip(
                this.fuelLoader.getEnergyStoredGC(),
                this.fuelLoader.getMaxEnergyStoredGC(),
                electricityDesc);
        // electricityDesc.add(EnumColor.YELLOW +
        // GCCoreUtil.translate("gui.energyStorage.desc.1") + ((int)
        // Math.floor(this.fuelLoader.getEnergyStoredGC()) + " / " + (int)
        // Math.floor(this.fuelLoader.getMaxEnergyStoredGC())));
        this.electricInfoRegion.tooltipStrings = electricityDesc;

        final String fuelStr = String.format(
                "%.1f/%.1f B",
                this.fuelLoader.fuelTank.getFluidAmount() / 1000.0f,
                this.fuelLoader.fuelTank.getCapacity() / 1000.0f);
        this.fuelTankDesc.set(2, fuelStr);

        if (this.fuelLoader.getEnergyStoredGC() > 0) {
            this.drawTexturedModalRect(var5 + 99, var6 + 65, 192, 7, 11, 10);
        }

        this.drawTexturedModalRect(
                var5 + 113,
                var6 + 66,
                192,
                0,
                Math.min(this.fuelLoader.getScaledElecticalLevel(54), 54),
                7);
    }
}
