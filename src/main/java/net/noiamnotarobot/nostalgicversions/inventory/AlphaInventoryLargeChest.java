package net.noiamnotarobot.nostalgicversions.inventory;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.noiamnotarobot.nostalgicversions.NostalgicVersions;

public class AlphaInventoryLargeChest implements Inventory, NamedScreenHandlerFactory {
    private final Inventory upperChest;
    private final Inventory lowerChest;

    public AlphaInventoryLargeChest(Inventory first, Inventory second) {
        this.upperChest = first;
        this.lowerChest = second;
    }

    public int size() {
        return this.upperChest.size() + this.lowerChest.size();
    }

    public boolean isEmpty() {
        return upperChest.isEmpty() && lowerChest.isEmpty();
    }

    public ItemStack getStack(int slot) {
        return slot >= this.upperChest.size() ? this.lowerChest.getStack(slot - this.upperChest.size()) : this.upperChest.getStack(slot);
    }

    public ItemStack removeStack(int slot) {
        ItemStack stack = getStack(slot).copy();
        setStack(slot, ItemStack.EMPTY);
        return stack;
    }

    public ItemStack removeStack(int slot, int amount) {
        return slot >= this.upperChest.size() ? this.lowerChest.removeStack(slot - this.upperChest.size(), amount) : this.upperChest.removeStack(slot, amount);
    }

    public void setStack(int slot, ItemStack stack) {
        if(slot >= this.upperChest.size()) {
            this.lowerChest.setStack(slot - this.upperChest.size(), stack);
        } else {
            this.upperChest.setStack(slot, stack);
        }

    }

    public void markDirty() {
        this.upperChest.markDirty();
        this.lowerChest.markDirty();
    }

    public boolean canPlayerUse(PlayerEntity player) {
        return true;
    }

    public Text getDisplayName() {
        return Text.translatable(NostalgicVersions.TranslationKeys.LARGE_CHEST_NAME);
    }

    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return GenericContainerScreenHandler.createGeneric9x6(syncId, playerInventory, this);
    }

    public void clear() {
        upperChest.clear();
        lowerChest.clear();
    }
}