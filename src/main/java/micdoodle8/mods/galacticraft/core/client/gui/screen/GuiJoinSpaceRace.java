package micdoodle8.mods.galacticraft.core.client.gui.screen;

import java.util.ArrayList;
import java.util.List;

import micdoodle8.mods.galacticraft.api.vector.Vector3;
import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import micdoodle8.mods.galacticraft.core.client.gui.element.GuiElementCheckbox;
import micdoodle8.mods.galacticraft.core.client.gui.element.GuiElementCheckbox.ICheckBoxCallback;
import micdoodle8.mods.galacticraft.core.client.gui.element.GuiElementGradientButton;
import micdoodle8.mods.galacticraft.core.client.gui.element.GuiElementTextBox;
import micdoodle8.mods.galacticraft.core.client.gui.element.GuiElementTextBox.ITextBoxCallback;
import micdoodle8.mods.galacticraft.core.client.model.ModelFlag;
import micdoodle8.mods.galacticraft.core.dimension.SpaceRace;
import micdoodle8.mods.galacticraft.core.dimension.SpaceRaceManager;
import micdoodle8.mods.galacticraft.core.entities.EntityFlag;
import micdoodle8.mods.galacticraft.core.entities.player.GCPlayerStatsClient;
import micdoodle8.mods.galacticraft.core.network.PacketSimple;
import micdoodle8.mods.galacticraft.core.network.PacketSimple.EnumSimplePacket;
import micdoodle8.mods.galacticraft.core.util.ColorUtil;
import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import micdoodle8.mods.galacticraft.core.wrappers.FlagData;

import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;

public class GuiJoinSpaceRace extends GuiScreen implements ICheckBoxCallback, ITextBoxCallback {

    protected static final ResourceLocation texture = new ResourceLocation(
            GalacticraftCore.ASSET_PREFIX,
            "textures/gui/gui.png");

    private int ticksPassed;
    private final EntityPlayer thePlayer;
    private boolean initialized;

    private int buttonFlag_height;
    private int buttonFlag_xPosition;
    private int buttonFlag_yPosition;

    private final EntityFlag dummyFlag = new EntityFlag(FMLClientHandler.instance().getClient().theWorld);
    private final ModelFlag dummyModel = new ModelFlag();

    private final SpaceRace spaceRaceData;

    public GuiJoinSpaceRace(EntityClientPlayerMP player) {
        this.thePlayer = player;
        final GCPlayerStatsClient stats = GCPlayerStatsClient.get(player);

        final SpaceRace race = SpaceRaceManager.getSpaceRaceFromID(stats.spaceRaceInviteTeamID);

        if (race != null) {
            this.spaceRaceData = race;
        } else {
            final List<String> playerList = new ArrayList<>();
            playerList.add(player.getGameProfile().getName());
            this.spaceRaceData = new SpaceRace(
                    playerList,
                    SpaceRace.DEFAULT_NAME,
                    new FlagData(48, 32),
                    new Vector3(1, 1, 1));
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void initGui() {
        super.initGui();
        this.buttonList.clear();

        if (this.initialized) {
            final int var5 = (this.width - this.width / 4) / 2;
            final int buttonFlag_width = 81;
            this.buttonFlag_height = 58;
            this.buttonFlag_xPosition = this.width / 2 - buttonFlag_width / 2;
            this.buttonFlag_yPosition = this.height / 2 - this.height / 3 + 10;

            this.buttonList.add(
                    new GuiElementGradientButton(
                            0,
                            this.width / 2 - this.width / 3 + 15,
                            this.height / 2 - this.height / 4 - 15,
                            50,
                            15,
                            GCCoreUtil.translate("gui.spaceRace.create.close.name")));
            final int width = (int) (var5 / 1.0F);
            this.buttonList.add(
                    new GuiElementGradientButton(
                            1,
                            this.width / 2 - width / 2,
                            this.buttonFlag_yPosition + this.buttonFlag_height + 60,
                            width,
                            20,
                            GCCoreUtil
                                    .translateWithFormat("gui.spaceRace.join.name", this.spaceRaceData.getTeamName())));
        }
    }

    @Override
    protected void actionPerformed(GuiButton buttonClicked) {
        switch (buttonClicked.id) {
            case 0:
                this.thePlayer.closeScreen();
                break;
            case 1:
                GalacticraftCore.packetPipeline.sendToServer(
                        new PacketSimple(
                                EnumSimplePacket.S_ADD_RACE_PLAYER,
                                new Object[] { this.thePlayer.getGameProfile().getName(),
                                        this.spaceRaceData.getSpaceRaceID() }));
                this.thePlayer.closeScreen();
                break;
            default:
                break;
        }
    }

    @Override
    protected void mouseClicked(int x, int y, int clickIndex) {
        super.mouseClicked(x, y, clickIndex);
    }

    @Override
    public void updateScreen() {
        super.updateScreen();
        ++this.ticksPassed;

        if (!this.initialized) {}
    }

    @Override
    public void drawScreen(int par1, int par2, float par3) {
        this.drawDefaultBackground();
        if (this.initialized) {
            this.drawCenteredString(
                    this.fontRendererObj,
                    GCCoreUtil.translate("gui.spaceRace.join.title.name"),
                    this.width / 2,
                    this.height / 2 - this.height / 3 - 15,
                    16777215);
            this.drawFlagButton(par1, par2);
            this.drawCenteredString(
                    this.fontRendererObj,
                    GCCoreUtil.translate("gui.spaceRace.join.owner.name") + ": "
                            + this.spaceRaceData.getPlayerNames().get(0),
                    this.width / 2,
                    this.buttonFlag_yPosition + this.buttonFlag_height + 25,
                    ColorUtil.to32BitColor(255, 150, 150, 150));
            this.drawCenteredString(
                    this.fontRendererObj,
                    GCCoreUtil.translateWithFormat(
                            "gui.spaceRace.join.memberCount.name",
                            this.spaceRaceData.getPlayerNames().size()),
                    this.width / 2,
                    this.buttonFlag_yPosition + this.buttonFlag_height + 40,
                    ColorUtil.to32BitColor(255, 150, 150, 150));
            GL11.glPushMatrix();
            GL11.glTranslatef(
                    this.width / 2,
                    this.buttonFlag_yPosition + this.buttonFlag_height
                            + 5
                            + FMLClientHandler.instance().getClient().fontRenderer.FONT_HEIGHT / 2,
                    0);
            GL11.glScalef(1.5F, 1.5F, 1.0F);
            GL11.glTranslatef(
                    -this.width / 2,
                    (-(this.buttonFlag_yPosition + this.buttonFlag_height + 5))
                            - FMLClientHandler.instance().getClient().fontRenderer.FONT_HEIGHT / 2,
                    0);
            this.drawCenteredString(
                    this.fontRendererObj,
                    this.spaceRaceData.getTeamName(),
                    this.width / 2,
                    this.buttonFlag_yPosition + this.buttonFlag_height + 5,
                    ColorUtil.to32BitColor(255, 100, 150, 20));
            GL11.glPopMatrix();
        }

        super.drawScreen(par1, par2, par3);
    }

    private void drawFlagButton(int mouseX, int mouseY) {
        GL11.glPushMatrix();
        GL11.glTranslatef(
                this.buttonFlag_xPosition + 2.9F,
                this.buttonFlag_yPosition + this.buttonFlag_height + 1 - 4,
                0);
        GL11.glScalef(74.0F, 74.0F, 1F);
        GL11.glTranslatef(0.0F, 0.36F, 1.0F);
        GL11.glScalef(1.0F, 1.0F, -1F);
        this.dummyFlag.flagData = this.spaceRaceData.getFlagData();
        this.dummyModel.renderFlag(this.dummyFlag, this.ticksPassed);
        GL11.glColor3f(1, 1, 1);
        GL11.glPopMatrix();
    }

    @Override
    public void drawWorldBackground(int i) {
        if (this.mc.theWorld != null) {
            final int scaleX = Math.min(this.ticksPassed * 14, this.width / 3);
            final int scaleY = Math.min(this.ticksPassed * 14, this.height / 3);

            if (scaleX == this.width / 3 && scaleY == this.height / 3 && !this.initialized) {
                this.initialized = true;
                this.initGui();
            }

            this.drawGradientRect(
                    this.width / 2 - scaleX,
                    this.height / 2 - scaleY,
                    this.width / 2 + scaleX,
                    this.height / 2 + scaleY,
                    -1072689136,
                    -804253680);
        } else {
            this.drawBackground(i);
        }
    }

    @Override
    public void onSelectionChanged(GuiElementCheckbox checkbox, boolean newSelected) {}

    @Override
    public boolean canPlayerEdit(GuiElementCheckbox checkbox, EntityPlayer player) {
        return true;
    }

    @Override
    public boolean getInitiallySelected(GuiElementCheckbox checkbox) {
        return false;
    }

    @Override
    public void onIntruderInteraction() {}

    @Override
    public void onIntruderInteraction(GuiElementTextBox textBox) {}

    @Override
    public boolean canPlayerEdit(GuiElementTextBox textBox, EntityPlayer player) {
        return true;
    }

    @Override
    public void onTextChanged(GuiElementTextBox textBox, String newText) {}

    @Override
    public String getInitialText(GuiElementTextBox textBox) {
        return "";
    }

    @Override
    public int getTextColor(GuiElementTextBox textBox) {
        return ColorUtil.to32BitColor(255, 255, 255, 255);
    }
}
