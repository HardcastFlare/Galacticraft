package micdoodle8.mods.galacticraft.core.inventory;

import micdoodle8.mods.galacticraft.core.tile.TileEntityIngotCompressor;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;

public class ContainerIngotCompressor extends Container {

    private final TileEntityIngotCompressor tileEntity;

    public ContainerIngotCompressor(InventoryPlayer par1InventoryPlayer, TileEntityIngotCompressor tileEntity) {
        this.tileEntity = tileEntity;
        tileEntity.compressingCraftMatrix.eventHandler = this;

        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                this.addSlotToContainer(
                        new Slot(tileEntity.compressingCraftMatrix, y + x * 3, 19 + y * 18, 18 + x * 18));
            }
        }

        // Coal slot
        this.addSlotToContainer(new Slot(tileEntity, 0, 55, 75));

        // Smelting result
        this.addSlotToContainer(new SlotFurnace(par1InventoryPlayer.player, tileEntity, 1, 138, 38));
        int var3;

        for (var3 = 0; var3 < 3; ++var3) {
            for (int var4 = 0; var4 < 9; ++var4) {
                this.addSlotToContainer(
                        new Slot(par1InventoryPlayer, var4 + var3 * 9 + 9, 8 + var4 * 18, 110 + var3 * 18));
            }
        }

        for (var3 = 0; var3 < 9; ++var3) {
            this.addSlotToContainer(new Slot(par1InventoryPlayer, var3, 8 + var3 * 18, 168));
        }

        tileEntity.playersUsing.add(par1InventoryPlayer.player);
    }

    @Override
    public void onContainerClosed(EntityPlayer entityplayer) {
        super.onContainerClosed(entityplayer);
        this.tileEntity.playersUsing.remove(entityplayer);
    }

    @Override
    public boolean canInteractWith(EntityPlayer par1EntityPlayer) {
        return this.tileEntity.isUseableByPlayer(par1EntityPlayer);
    }

    @Override
    public void onCraftMatrixChanged(IInventory par1IInventory) {
        super.onCraftMatrixChanged(par1IInventory);
        this.tileEntity.updateInput();
    }

    /**
     * Called to transfer a stack from one inventory to the other eg. when shift clicking.
     */
    @Override
    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par1) {
        ItemStack var2 = null;
        final Slot slot = (Slot) this.inventorySlots.get(par1);

        if (slot != null && slot.getHasStack()) {
            final ItemStack var4 = slot.getStack();
            var2 = var4.copy();

            if (par1 <= 10) {
                if (!this.mergeItemStack(var4, 11, 47, true)) {
                    return null;
                }

                if (par1 == 1) {
                    slot.onSlotChange(var4, var2);
                }
            } else {
                if (TileEntityFurnace.getItemBurnTime(var4) > 0) {
                    if (!this.mergeItemStack(var4, 9, 10, false)) {
                        return null;
                    }
                } else if (par1 < 38) {
                    if (!this.mergeItemStack(var4, 0, 9, false) && !this.mergeItemStack(var4, 38, 47, false)) {
                        return null;
                    }
                } else if (!this.mergeItemStack(var4, 0, 9, false) && !this.mergeItemStack(var4, 11, 38, false)) {
                    return null;
                }
            }

            if (var4.stackSize == 0) {
                slot.putStack(null);
            } else {
                slot.onSlotChanged();
            }

            if (var4.stackSize == var2.stackSize) {
                return null;
            }

            slot.onPickupFromSlot(par1EntityPlayer, var4);
        }

        return var2;
    }

    // Can only split-drag into the crafting table slots
    @Override
    public boolean canDragIntoSlot(Slot par1Slot) {
        return par1Slot.slotNumber < 9;
    }
}
