package net.noiamnotarobot.nostalgicversions.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.enums.ChestType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.noiamnotarobot.nostalgicversions.block.entity.AlphaBlockEntityTypes;
import net.noiamnotarobot.nostalgicversions.block.entity.AlphaChestBlockEntity;
import net.noiamnotarobot.nostalgicversions.inventory.AlphaInventoryLargeChest;

public class AlphaChestBlock extends BlockWithEntity {
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
    public static final EnumProperty<ChestType> CHEST_TYPE = Properties.CHEST_TYPE;

    protected AlphaChestBlock(Settings settings) {
        super(settings);
    }

    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        int var5 = 0;
        if (world.getBlockState(pos.north()).isOf(this)) {
            ++var5;
        }

        if (world.getBlockState(pos.south()).isOf(this)) {
            ++var5;
        }

        if (world.getBlockState(pos.east()).isOf(this)) {
            ++var5;
        }

        if (world.getBlockState(pos.west()).isOf(this)) {
            ++var5;
        }

        return var5 <= 1 && this.noNeighborChest(world, pos.north()) && this.noNeighborChest(world, pos.south()) && this.noNeighborChest(world, pos.east()) && this.noNeighborChest(world, pos.west());
    }

    private boolean noNeighborChest(WorldView world, BlockPos pos) {
        return !world.getBlockState(pos).isOf(this) || (!world.getBlockState(pos.north()).isOf(this) && (!world.getBlockState(pos.south()).isOf(this) && (!world.getBlockState(pos.east()).isOf(this) && !world.getBlockState(pos.west()).isOf(this))));
    }

    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (!state.isOf(newState.getBlock())) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof Inventory) {
                ItemScatterer.spawn(world, pos, (Inventory)blockEntity);
                world.updateComparators(pos, this);
            }

            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        NamedScreenHandlerFactory var6 = (NamedScreenHandlerFactory) world.getBlockEntity(pos);
        if (world.isClient) return ActionResult.SUCCESS;
        if (world.getBlockState(pos.up()).isSolidBlock(world, pos)) return ActionResult.SUCCESS;
        if (world.getBlockState(pos.north()).isOf(this) && world.getBlockState(pos.north().up()).isSolidBlock(world, pos)) return ActionResult.SUCCESS;
        if (world.getBlockState(pos.south()).isOf(this) && world.getBlockState(pos.south().up()).isSolidBlock(world, pos)) return ActionResult.SUCCESS;
        if (world.getBlockState(pos.east()).isOf(this) && world.getBlockState(pos.east().up()).isSolidBlock(world, pos)) return ActionResult.SUCCESS;
        if (world.getBlockState(pos.west()).isOf(this) && world.getBlockState(pos.west().up()).isSolidBlock(world, pos)) return ActionResult.SUCCESS;
        if (world.getBlockState(pos.north()).isOf(this)) {
            var6 = new AlphaInventoryLargeChest((Inventory) world.getBlockEntity(pos.north()), (Inventory) var6);
        }

        if (world.getBlockState(pos.south()).isOf(this)) {
            var6 = new AlphaInventoryLargeChest((Inventory) var6, (Inventory) world.getBlockEntity(pos.south()));
        }

        if (world.getBlockState(pos.east()).isOf(this)) {
            var6 = new AlphaInventoryLargeChest((Inventory) world.getBlockEntity(pos.east()), (Inventory) var6);
        }

        if (world.getBlockState(pos.west()).isOf(this)) {
            var6 = new AlphaInventoryLargeChest((Inventory) var6, (Inventory) world.getBlockEntity(pos.west()));
        }

        player.openHandledScreen(var6);
        return ActionResult.SUCCESS;
    }

    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new AlphaChestBlockEntity(pos, state);
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(FACING, CHEST_TYPE);
    }

    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, AlphaBlockEntityTypes.CHEST, AlphaChestBlockEntity::tick);
    }
}