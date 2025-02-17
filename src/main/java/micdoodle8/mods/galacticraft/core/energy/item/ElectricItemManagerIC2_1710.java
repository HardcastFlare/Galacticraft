package micdoodle8.mods.galacticraft.core.energy.item;

import micdoodle8.mods.galacticraft.api.item.IItemElectricBase;
import micdoodle8.mods.galacticraft.core.energy.EnergyConfigHandler;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

import ic2.api.item.IElectricItemManager;
import ic2.api.item.ISpecialElectricItem;

/*
 * Interface between Galacticraft electric items (batteries) and IC2. Galactricraft items implemented as Tier 1 items
 * when recharging and for discharging purposes (so can power anything)
 */
public class ElectricItemManagerIC2_1710 implements IElectricItemManager {

    @Override
    public double charge(ItemStack itemStack, double amount, int tier, boolean ignoreTransferLimit, boolean simulate) {
        if (itemStack.getItem() instanceof IItemElectricBase) {
            final IItemElectricBase item = (IItemElectricBase) itemStack.getItem();
            if (amount > ((ISpecialElectricItem) item).getMaxCharge(itemStack)) {
                amount = ((ISpecialElectricItem) item).getMaxCharge(itemStack);
            }
            final float energy = (float) amount * EnergyConfigHandler.IC2_RATIO;
            final float rejectedElectricity = Math
                    .max(item.getElectricityStored(itemStack) + energy - item.getMaxElectricityStored(itemStack), 0);
            float energyToReceive = energy - rejectedElectricity;
            if (!ignoreTransferLimit && energyToReceive > item.getMaxTransferGC(itemStack)) {}

            {
                energyToReceive = item.getMaxTransferGC(itemStack);
            }

            if (!simulate) {
                item.setElectricity(itemStack, item.getElectricityStored(itemStack) + energyToReceive);
            }

            return energyToReceive / EnergyConfigHandler.IC2_RATIO;
        }

        return 0D;
    }

    @Override
    public double discharge(ItemStack itemStack, double amount, int tier, boolean ignoreTransferLimit,
            boolean externally, boolean simulate) {
        if (itemStack.getItem() instanceof IItemElectricBase) {
            final IItemElectricBase item = (IItemElectricBase) itemStack.getItem();
            final float energy = (float) amount / EnergyConfigHandler.TO_IC2_RATIO;
            float energyToTransfer = Math.min(item.getElectricityStored(itemStack), energy);
            if (!ignoreTransferLimit) {
                energyToTransfer = Math.min(energyToTransfer, item.getMaxTransferGC(itemStack));
            }

            if (!simulate) {
                item.setElectricity(itemStack, item.getElectricityStored(itemStack) - energyToTransfer);
            }

            return energyToTransfer * EnergyConfigHandler.TO_IC2_RATIO;
        }
        return 0D;
    }

    @Override
    public double getCharge(ItemStack itemStack) {
        if (itemStack.getItem() instanceof IItemElectricBase) {
            final IItemElectricBase item = (IItemElectricBase) itemStack.getItem();
            return item.getElectricityStored(itemStack) * EnergyConfigHandler.TO_IC2_RATIO;
        }
        return 0D;
    }

    @Override
    public boolean canUse(ItemStack itemStack, double amount) {
        if (itemStack.getItem() instanceof IItemElectricBase) {
            return this.getCharge(itemStack) >= amount;
        }
        return false;
    }

    @Override
    public boolean use(ItemStack itemStack, double amount, EntityLivingBase entity) {
        if (itemStack.getItem() instanceof IItemElectricBase) {
            return this.discharge(itemStack, amount, 1, true, false, false) >= amount - 1;
        }
        return false;
    }

    @Override
    public void chargeFromArmor(ItemStack itemStack, EntityLivingBase entity) {}

    @Override
    public String getToolTip(ItemStack itemStack) {
        return null;
    }
}
