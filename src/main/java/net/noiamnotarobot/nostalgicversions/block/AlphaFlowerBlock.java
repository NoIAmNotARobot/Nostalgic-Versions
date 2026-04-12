package net.noiamnotarobot.nostalgicversions.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

public class AlphaFlowerBlock extends Block {
    public AlphaFlowerBlock(Settings settings) {
        super(settings);
    }

    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return canThisPlantGrowOnThisBlock(world.getBlockState(pos.down()));
    }

    public boolean canThisPlantGrowOnThisBlock(BlockState floor) {
        return floor.isIn(BlockTags.DIRT) || floor.isOf(AlphaBlocks.TILLED_FIELD);
    }

    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        checkFlowerChange(world, pos);
        super.neighborUpdate(state, world, pos, sourceBlock, sourcePos, notify);
    }

    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, net.minecraft.util.math.random.Random random) {
        checkFlowerChange(world, pos);
    }

    protected final void checkFlowerChange(World world, BlockPos pos) {
        if (!canBlockStay(world, pos)) {
            dropStacks(world.getBlockState(pos), world, pos);
            world.removeBlock(pos, false);
        }
    }

    public boolean canBlockStay(WorldAccess world, BlockPos pos) {
        return (world.getLightLevel(pos) >= 8 || world.isSkyVisible(pos)) && canThisPlantGrowOnThisBlock(world.getBlockState(pos.down()));
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.cuboid(0.3F, 0.0F, 0.3F, 0.7F, 0.6F, 0.7F);
    }
}