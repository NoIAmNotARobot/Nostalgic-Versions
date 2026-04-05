package net.noiamnotarobot.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
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

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return canThisPlantGrowOnThisBlockID(world.getBlockState(pos.down()));
    }

    protected boolean canThisPlantGrowOnThisBlockID(BlockState floor) {
        return floor.isIn(BlockTags.DIRT) || floor.isOf(AlphaBlocks.TILLED_FIELD);
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        checkFlowerChange((World) world, pos);
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, net.minecraft.util.math.random.Random random) {
        checkFlowerChange(world, pos);
    }

    protected final void checkFlowerChange(World world, BlockPos pos) {
        if(!this.canBlockStay(world, pos)) {
            dropStacks(world.getBlockState(pos), world, pos);
            world.removeBlock(pos, false);
        }
    }

    public boolean canBlockStay(WorldAccess world, BlockPos pos) {
        return (world.getLightLevel(pos) >= 8 || world.isSkyVisible(pos)) && this.canThisPlantGrowOnThisBlockID(world.getBlockState(pos.down()));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.cuboid(0.3F, 0.0F, 0.3F, 0.7F, 0.6F, 0.7F);
    }
}
