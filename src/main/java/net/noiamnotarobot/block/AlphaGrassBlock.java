package net.noiamnotarobot.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.SnowyBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;

public class AlphaGrassBlock extends SnowyBlock {
    public AlphaGrassBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random rand) {
        if(world.getLightLevel(pos.up()) < 4 && !world.getBlockState(pos.up()).isAir()) {
            if(rand.nextInt(4) != 0) {
                return;
            }

            world.setBlockState(pos, AlphaBlocks.DIRT.getDefaultState());
        } else if(world.getLightLevel(pos.up()) >= 9) {
            BlockPos pos2 = new BlockPos(
                    pos.getX() + rand.nextInt(3) - 1,
                    pos.getY() + rand.nextInt(5) - 3,
                    pos.getZ() + rand.nextInt(3) - 1);
            if(world.getBlockState(pos2).getBlock() == AlphaBlocks.DIRT && world.getLightLevel(pos2.up()) >= 4 && world.getBlockState(pos2.up()).isAir()) {
                world.setBlockState(pos2, AlphaBlocks.GRASS.getDefaultState());
            }
        }
    }
}