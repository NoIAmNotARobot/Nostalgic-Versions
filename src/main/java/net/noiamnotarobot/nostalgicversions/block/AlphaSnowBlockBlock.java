package net.noiamnotarobot.nostalgicversions.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.LightType;

public class AlphaSnowBlockBlock extends Block {
    public AlphaSnowBlockBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        super.scheduledTick(state, world, pos, random);

        if (world.getLightLevel(LightType.BLOCK, pos) > 11) {
            dropStacks(world.getBlockState(pos), world, pos);
            world.removeBlock(pos, false);
        }
    }
}