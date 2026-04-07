package net.noiamnotarobot.nostalgicversions.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.enums.ChestType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.noiamnotarobot.nostalgicversions.NostalgicVersions;
import net.noiamnotarobot.nostalgicversions.block.AlphaBlocks;
import net.noiamnotarobot.nostalgicversions.block.AlphaChestBlock;

import java.util.Arrays;

public class AlphaChestBlockEntity extends BlockEntity implements Inventory, NamedScreenHandlerFactory {
    private ItemStack[] chestContents = new ItemStack[36];

    public AlphaChestBlockEntity(BlockPos pos, BlockState state) {
        super(AlphaBlockEntityTypes.CHEST, pos, state);
        Arrays.fill(chestContents, ItemStack.EMPTY);
    }

    public int size() {
        return 27;
    }

    // I know it would be more accurate to have empty slots be null, but I would prefer if the game did not crash.
    public void correctNullItems() {
        for (int slot = 0; slot < size(); slot++) {
            if (this.chestContents[slot] == null) this.chestContents[slot] = ItemStack.EMPTY;
        }
    }

    public ItemStack getStack(int slot) {
        correctNullItems();
        return this.chestContents[slot];
    }

    public ItemStack removeStack(int slot, int amount) {
        correctNullItems();
        if (!this.chestContents[slot].isEmpty()) {
            ItemStack var3;
            if (this.chestContents[slot].getCount() <= amount) {
                var3 = this.chestContents[slot];
                this.chestContents[slot] = ItemStack.EMPTY;
                this.markDirty();
                return var3;
            } else {
                var3 = this.chestContents[slot].split(amount);
                if (this.chestContents[slot].getCount() == 0) {
                    this.chestContents[slot] = ItemStack.EMPTY;
                }

                this.markDirty();
                return var3;
            }
        } else {
            return ItemStack.EMPTY;
        }
    }

    public ItemStack removeStack(int slot) {
        correctNullItems();
        ItemStack stack = getStack(slot).copy();
        setStack(slot, ItemStack.EMPTY);
        return stack;
    }

    public void setStack(int slot, ItemStack stack) {
        correctNullItems();
        this.chestContents[slot] = stack;
        if (!stack.isEmpty() && stack.getCount() > this.getMaxCountPerStack()) {
            stack.setCount(this.getMaxCountPerStack());
        }

        this.markDirty();
    }

    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        NbtList var2 = nbt.getList("Items", 10);
        this.chestContents = new ItemStack[this.size()];

        for (NbtElement elem : var2) {
            NbtCompound compound = (NbtCompound) elem;
            int slot = compound.getByte("Slot") & 255;
            if (slot < this.chestContents.length) {
                this.chestContents[slot] = ItemStack.fromNbt(compound);
            }
        }

        correctNullItems();
    }

    public void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        correctNullItems();
        NbtList var2 = new NbtList();

        for (int slot = 0; slot < this.chestContents.length; ++slot) {
            NbtCompound var4 = new NbtCompound();
            var4.putByte("Slot", (byte) slot);
            this.chestContents[slot].writeNbt(var4);
            var2.add(var4);
        }

        nbt.put("Items", var2);
    }

    public boolean isEmpty() {
        for (ItemStack stack : chestContents) {
            if (!stack.isEmpty()) return false;
        }
        return true;
    }

    public boolean canPlayerUse(PlayerEntity player) {
        return true;
    }

    public void clear() {
        Arrays.fill(chestContents, ItemStack.EMPTY);
    }

    public Text getDisplayName() {
        return Text.translatable(NostalgicVersions.TranslationKeys.CHEST_NAME);
    }

    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return GenericContainerScreenHandler.createGeneric9x3(syncId, playerInventory, this);
    }

    public static void tick(World world, BlockPos pos, BlockState state, AlphaChestBlockEntity blockEntity) {
        BlockState northState = world.getBlockState(pos.north());
        BlockState southState = world.getBlockState(pos.south());
        BlockState eastState = world.getBlockState(pos.west());
        BlockState westState = world.getBlockState(pos.east());
        BlockState var11;
        BlockState var12;
        Direction direction;

        if (!northState.isOf(AlphaBlocks.CHEST) && !southState.isOf(AlphaBlocks.CHEST)) {
            if (!eastState.isOf(AlphaBlocks.CHEST) && !westState.isOf(AlphaBlocks.CHEST)) {
                Direction var14 = Direction.SOUTH;

                if (southState.isOpaque() && !northState.isOpaque()) var14 = Direction.NORTH;
                if (eastState.isOpaque() && !westState.isOpaque()) var14 = Direction.EAST;
                if (westState.isOpaque() && !eastState.isOpaque()) var14 = Direction.WEST;

                world.setBlockState(pos, state.with(AlphaChestBlock.FACING, var14).with(AlphaChestBlock.CHEST_TYPE, ChestType.SINGLE));
            } else {
                var11 = world.getBlockState(new BlockPos(eastState.isOf(AlphaBlocks.CHEST) ? pos.getX() - 1 : pos.getX() + 1, pos.getY(), pos.getZ() - 1));
                var12 = world.getBlockState(new BlockPos(eastState.isOf(AlphaBlocks.CHEST) ? pos.getX() - 1 : pos.getX() + 1, pos.getY(), pos.getZ() + 1));

                direction = Direction.SOUTH;

                if ((southState.isOpaque() || var12.isOpaque()) && !northState.isOpaque() && !var11.isOpaque()) {
                    direction = Direction.NORTH;
                }

                world.setBlockState(pos, state.with(AlphaChestBlock.FACING, direction).with(AlphaChestBlock.CHEST_TYPE, eastState.isOf(AlphaBlocks.CHEST) ^ direction == Direction.NORTH ? ChestType.LEFT : ChestType.RIGHT));
            }
        } else {
            var11 = world.getBlockState(new BlockPos(pos.getX() - 1, pos.getY(), northState.isOf(AlphaBlocks.CHEST) ? pos.getZ() - 1 : pos.getZ() + 1));
            var12 = world.getBlockState(new BlockPos(pos.getX() + 1, pos.getY(), northState.isOf(AlphaBlocks.CHEST) ? pos.getZ() - 1 : pos.getZ() + 1));

            direction = Direction.EAST;

            if ((westState.isOpaque() || var12.isOpaque()) && !eastState.isOpaque() && !var11.isOpaque()) {
                direction = Direction.WEST;
            }

            world.setBlockState(pos, state.with(AlphaChestBlock.FACING, direction).with(AlphaChestBlock.CHEST_TYPE, northState.isOf(AlphaBlocks.CHEST) ^ direction == Direction.EAST ? ChestType.LEFT : ChestType.RIGHT));
        }
    }
}