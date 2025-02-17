package micdoodle8.mods.galacticraft.core.inventory;

import micdoodle8.mods.galacticraft.core.tile.TileEntityCoalGenerator;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ContainerCoalGenerator extends Container {

    private final TileEntityCoalGenerator tileEntity;

    public ContainerCoalGenerator(InventoryPlayer par1InventoryPlayer, TileEntityCoalGenerator tileEntity) {
        this.tileEntity = tileEntity;
        this.addSlotToContainer(
                new SlotSpecific(
                        tileEntity,
                        0,
                        33,
                        34,
                        new ItemStack(Items.coal),
                        new ItemStack(Item.getItemFromBlock(Blocks.coal_block))));
        int var3;

        for (var3 = 0; var3 < 3; ++var3) {
            for (int var4 = 0; var4 < 9; ++var4) {
                this.addSlotToContainer(
                        new Slot(par1InventoryPlayer, var4 + var3 * 9 + 9, 8 + var4 * 18, 84 + var3 * 18));
            }
        }

        for (var3 = 0; var3 < 9; ++var3) {
            this.addSlotToContainer(new Slot(par1InventoryPlayer, var3, 8 + var3 * 18, 142));
        }
    }

    @Override
    public void onContainerClosed(EntityPlayer entityplayer) {
        super.onContainerClosed(entityplayer);
    }

    @Override
    public boolean canInteractWith(EntityPlayer par1EntityPlayer) {
        return this.tileEntity.isUseableByPlayer(par1EntityPlayer);
    }

    /**
     * Called to transfer a stack from one inventory to the other eg. when shift clicking.
     */
    @Override
    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par1) {
        ItemStack var2 = null;
        final Slot var3 = (Slot) this.inventorySlots.get(par1);

        if (var3 != null && var3.getHasStack()) {
            final ItemStack var4 = var3.getStack();
            var2 = var4.copy();

            if (par1 != 0) {
                if (var4.getItem() == Items.coal) {
                    if (!this.mergeItemStack(var4, 0, 1, false)) {
                        return null;
                    }
                } else if (par1 >= 28) {
                    if (!this.mergeItemStack(var4, 1, 28, false)) {
                        return null;
                    }
                } else if (!this.mergeItemStack(var4, 28, 37, false)) {
                    return null;
                }

            } else if (!this.mergeItemStack(var4, 1, 37, false)) {
                return null;
            }

            if (var4.stackSize == 0) {
                var3.putStack(null);
            } else {
                var3.onSlotChanged();
            }

            if (var4.stackSize == var2.stackSize) {
                return null;
            }

            var3.onPickupFromSlot(par1EntityPlayer, var4);
        }

        return var2;
    }
}
