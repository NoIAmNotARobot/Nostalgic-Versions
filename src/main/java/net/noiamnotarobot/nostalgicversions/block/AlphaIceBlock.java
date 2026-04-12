package net.noiamnotarobot.nostalgicversions.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockView;
import net.minecraft.world.LightType;
import net.minecraft.world.WorldAccess;

public class AlphaIceBlock extends Block {
    public AlphaIceBlock(Settings settings) {
        super(settings);
    }

    public void onBroken(WorldAccess world, BlockPos pos, BlockState state) {
        BlockState var5 = world.getBlockState(pos.down());
        if (var5.isSolidBlock(world, pos) || !var5.getFluidState().isEmpty()) {
            world.setBlockState(pos, Blocks.WATER.getDefaultState(), 3);
        }
    }

    public int getOpacity(BlockState state, BlockView world, BlockPos pos) {
        return 3;
    }

    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        super.scheduledTick(state, world, pos, random);

        if (world.getLightLevel(LightType.BLOCK, pos) > 11 - getOpacity(state, world, pos)) {
            dropStacks(world.getBlockState(pos), world, pos);
            world.setBlockState(pos, Blocks.WATER.getDefaultState());
        }
    }

    @Override
    public boolean isSideInvisible(BlockState state, BlockState stateFrom, Direction direction) {
        return stateFrom.isOf(this) || super.isSideInvisible(state, stateFrom, direction);
    }
}