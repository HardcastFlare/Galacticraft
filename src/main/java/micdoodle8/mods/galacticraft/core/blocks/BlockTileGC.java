package micdoodle8.mods.galacticraft.core.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * An advanced block class that is to be extended for wrenching capabilities.
 */
public abstract class BlockTileGC extends BlockAdvanced implements ITileEntityProvider {

    public BlockTileGC(Material material) {
        super(material);
        this.isBlockContainer = true;
    }

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    @Override
    public void onBlockAdded(World par1World, int par2, int par3, int par4) {
        super.onBlockAdded(par1World, par2, par3, par4);
    }

    /**
     * ejects contained items into the world, and notifies neighbours of an update, as appropriate
     */
    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int par6) {
        this.dropEntireInventory(world, x, y, z, block, par6);
        super.breakBlock(world, x, y, z, block, par6);
    }

    /**
     * Called when the block receives a BlockEvent - see World.addBlockEvent. By default, passes it on to the tile
     * entity at this location. Args: world, x, y, z, blockID, EventID, event parameter
     */
    @Override
    public boolean onBlockEventReceived(World par1World, int par2, int par3, int par4, int par5, int par6) {
        super.onBlockEventReceived(par1World, par2, par3, par4, par5, par6);
        final TileEntity tileentity = par1World.getTileEntity(par2, par3, par4);
        return tileentity != null && tileentity.receiveClientEvent(par5, par6);
    }

    /**
     * Override this if you don't need it. This will eject all items out of this machine if it has an inventory.
     */
    public void dropEntireInventory(World world, int x, int y, int z, Block block, int par6) {
        final TileEntity tileEntity = world.getTileEntity(x, y, z);

        if (tileEntity != null) {
            if (tileEntity instanceof IInventory) {
                final IInventory inventory = (IInventory) tileEntity;

                for (int var6 = 0; var6 < inventory.getSizeInventory(); ++var6) {
                    final ItemStack var7 = inventory.getStackInSlot(var6);

                    if (var7 != null) {
                        final Random random = new Random();
                        final float var8 = random.nextFloat() * 0.8F + 0.1F;
                        final float var9 = random.nextFloat() * 0.8F + 0.1F;
                        final float var10 = random.nextFloat() * 0.8F + 0.1F;

                        while (var7.stackSize > 0) {
                            int var11 = random.nextInt(21) + 10;

                            if (var11 > var7.stackSize) {
                                var11 = var7.stackSize;
                            }

                            var7.stackSize -= var11;
                            final EntityItem var12 = new EntityItem(
                                    world,
                                    x + var8,
                                    y + var9,
                                    z + var10,
                                    new ItemStack(var7.getItem(), var11, var7.getItemDamage()));

                            if (var7.hasTagCompound()) {
                                var12.getEntityItem().setTagCompound((NBTTagCompound) var7.getTagCompound().copy());
                            }

                            final float var13 = 0.05F;
                            var12.motionX = (float) random.nextGaussian() * var13;
                            var12.motionY = (float) random.nextGaussian() * var13 + 0.2F;
                            var12.motionZ = (float) random.nextGaussian() * var13;
                            world.spawnEntityInWorld(var12);
                        }
                    }
                }
            }
        }
    }

    /**
     * Returns the TileEntity used by this block. You should use the metadata sensitive version of this to get the
     * maximum optimization!
     */
    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return null;
    }
}
