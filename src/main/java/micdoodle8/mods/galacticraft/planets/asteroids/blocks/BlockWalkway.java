package micdoodle8.mods.galacticraft.planets.asteroids.blocks;

import java.util.List;

import micdoodle8.mods.galacticraft.api.transmission.NetworkType;
import micdoodle8.mods.galacticraft.api.vector.Vector3;
import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import micdoodle8.mods.galacticraft.core.blocks.BlockTransmitter;
import micdoodle8.mods.galacticraft.core.energy.EnergyUtil;
import micdoodle8.mods.galacticraft.core.items.ItemBlockDesc;
import micdoodle8.mods.galacticraft.core.tile.TileEntityAluminumWire;
import micdoodle8.mods.galacticraft.core.tile.TileEntityOxygenPipe;
import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import micdoodle8.mods.galacticraft.core.util.OxygenUtil;
import micdoodle8.mods.galacticraft.planets.GalacticraftPlanets;
import micdoodle8.mods.galacticraft.planets.asteroids.AsteroidsModule;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockWalkway extends BlockTransmitter implements ITileEntityProvider, ItemBlockDesc.IBlockShiftDesc {

    protected BlockWalkway(String assetName) {
        super(Material.iron);
        this.setHardness(1.0F);
        this.setBlockTextureName(AsteroidsModule.TEXTURE_PREFIX + "walkway");
        this.setBlockName(assetName);
        this.setStepSound(Block.soundTypeMetal);
        this.isBlockContainer = true;
        this.minVector = new Vector3(0.0, 0.32, 0.0);
        this.maxVector = new Vector3(1.0, 1.0, 1.0);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public boolean shouldSideBeRendered(IBlockAccess worldIn, int x, int y, int z, int side) {
        return true;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public CreativeTabs getCreativeTabToDisplayOn() {
        return GalacticraftCore.galacticraftBlocksTab;
    }

    @Override
    public int getRenderType() {
        return GalacticraftPlanets.getBlockRenderID(this);
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta) {
        return this.getWalkwayOrientation(world, x, y, z);
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block blockChanged) {
        world.setBlock(x, y, z, this, this.getWalkwayOrientation(world, x, y, z), 3);

        if (this.getNetworkType() != null) {
            super.onNeighborBlockChange(world, x, y, z, blockChanged);
        }
    }

    public int getWalkwayOrientation(World world, int x, int y, int z) {
        final int connectedNorth = this.isBlockNormalCube(world.getBlock(x, y, z - 1))
                || world.getBlock(x, y, z - 1) instanceof BlockWalkway ? 1 : 0;
        final int connectedEast = this.isBlockNormalCube(world.getBlock(x + 1, y, z))
                || world.getBlock(x + 1, y, z) instanceof BlockWalkway ? 2 : 0;
        final int connectedSouth = this.isBlockNormalCube(world.getBlock(x, y, z + 1))
                || world.getBlock(x, y, z + 1) instanceof BlockWalkway ? 4 : 0;
        final int connectedWest = this.isBlockNormalCube(world.getBlock(x - 1, y, z))
                || world.getBlock(x - 1, y, z) instanceof BlockWalkway ? 8 : 0;

        return connectedNorth | connectedEast | connectedSouth | connectedWest;
    }

    public boolean isBlockNormalCube(Block block) {
        return block.getMaterial().blocksMovement() && block.renderAsNormalBlock();
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        if (this == AsteroidBlocks.blockWalkwayOxygenPipe) {
            return new TileEntityOxygenPipe();
        }

        if (this == AsteroidBlocks.blockWalkwayWire) {
            return new TileEntityAluminumWire(2);
        }

        return null;
    }

    @Override
    public NetworkType getNetworkType() {
        if (this == AsteroidBlocks.blockWalkwayOxygenPipe) {
            return NetworkType.OXYGEN;
        }

        if (this == AsteroidBlocks.blockWalkwayWire) {
            return NetworkType.POWER;
        }

        return null;
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
        final TileEntity tileEntity = world.getTileEntity(x, y, z);
        TileEntity[] connectable = new TileEntity[6];

        if (tileEntity != null) {
            if (this.getNetworkType() != null) {
                switch (this.getNetworkType()) {
                    case OXYGEN:
                        connectable = OxygenUtil.getAdjacentOxygenConnections(tileEntity);
                        break;
                    case POWER:
                        connectable = EnergyUtil.getAdjacentPowerConnections(tileEntity);
                        break;
                    default:
                        break;
                }
            }

            final float minX = 0.0F;
            float minY = 0.32F;
            final float minZ = 0.0F;
            final float maxX = 1.0F;
            final float maxY = 1.0F;
            final float maxZ = 1.0F;

            if (connectable[0] != null) {
                minY = 0.0F;
            }

            this.setBlockBounds(minX, minY, minZ, maxX, maxY, maxZ);
        }
    }

    private void addCollisionBox(World world, int x, int y, int z, AxisAlignedBB aabb, List list) {
        final AxisAlignedBB axisalignedbb1 = this.getCollisionBoundingBoxFromPool(world, x, y, z);

        if (axisalignedbb1 != null && aabb.intersectsWith(axisalignedbb1)) {
            list.add(axisalignedbb1);
        }
    }

    @SuppressWarnings("rawtypes")
    @Override
    public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB axisalignedbb, List list,
            Entity entity) {
        final TileEntity tileEntity = world.getTileEntity(x, y, z);
        TileEntity[] connectable = new TileEntity[6];

        if (this.getNetworkType() != null) {
            switch (this.getNetworkType()) {
                case OXYGEN:
                    connectable = OxygenUtil.getAdjacentOxygenConnections(tileEntity);
                    break;
                case POWER:
                    connectable = EnergyUtil.getAdjacentPowerConnections(tileEntity);
                    break;
                default:
                    break;
            }
        }

        this.setBlockBounds(
                (float) this.minVector.x,
                (float) this.minVector.y,
                (float) this.minVector.z,
                (float) this.maxVector.x,
                (float) this.maxVector.y,
                (float) this.maxVector.z);
        this.addCollisionBox(world, x, y, z, axisalignedbb, list);

        this.setBlockBounds(0.0F, 0.9F, 0.0F, 1.0F, 1.0F, 1.0F);
        this.addCollisionBox(world, x, y, z, axisalignedbb, list);

        if (connectable[4] != null) {
            this.setBlockBounds(
                    0,
                    (float) this.minVector.y,
                    (float) this.minVector.z,
                    (float) this.maxVector.x,
                    (float) this.maxVector.y,
                    (float) this.maxVector.z);
            this.addCollisionBox(world, x, y, z, axisalignedbb, list);
        }

        if (connectable[5] != null) {
            this.setBlockBounds(
                    (float) this.minVector.x,
                    (float) this.minVector.y,
                    (float) this.minVector.z,
                    1,
                    (float) this.maxVector.y,
                    (float) this.maxVector.z);
            this.addCollisionBox(world, x, y, z, axisalignedbb, list);
        }

        if (connectable[0] != null) {
            this.setBlockBounds(
                    (float) this.minVector.x,
                    0,
                    (float) this.minVector.z,
                    (float) this.maxVector.x,
                    (float) this.maxVector.y,
                    (float) this.maxVector.z);
            this.addCollisionBox(world, x, y, z, axisalignedbb, list);
        }

        if (connectable[1] != null) {
            this.setBlockBounds(
                    (float) this.minVector.x,
                    (float) this.minVector.y,
                    (float) this.minVector.z,
                    (float) this.maxVector.x,
                    1,
                    (float) this.maxVector.z);
            this.addCollisionBox(world, x, y, z, axisalignedbb, list);
        }

        if (connectable[2] != null) {
            this.setBlockBounds(
                    (float) this.minVector.x,
                    (float) this.minVector.y,
                    0,
                    (float) this.maxVector.x,
                    (float) this.maxVector.y,
                    (float) this.maxVector.z);
            this.addCollisionBox(world, x, y, z, axisalignedbb, list);
        }

        if (connectable[3] != null) {
            this.setBlockBounds(
                    (float) this.minVector.x,
                    (float) this.minVector.y,
                    (float) this.minVector.z,
                    (float) this.maxVector.x,
                    (float) this.maxVector.y,
                    1);
            this.addCollisionBox(world, x, y, z, axisalignedbb, list);
        }

        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    }

    @Override
    public String getShiftDescription(int meta) {
        if (this == AsteroidBlocks.blockWalkway) {
            return GCCoreUtil.translate("tile.walkway.description");
        } else if (this == AsteroidBlocks.blockWalkwayWire) {
            return GCCoreUtil.translate("tile.walkwayAluminumWire.description");
        } else if (this == AsteroidBlocks.blockWalkwayOxygenPipe) {
            return GCCoreUtil.translate("tile.walkwayOxygenPipe.description");
        }

        return "";
    }

    @Override
    public boolean showDescription(int meta) {
        return true;
    }
}
