package net.noiamnotarobot.nostalgicversions.world.gen;

import net.minecraft.registry.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldAccess;
import net.noiamnotarobot.nostalgicversions.block.AlphaBlocks;

import java.util.Random;

public class AlphaWorldGenReed extends AlphaWorldGenerator {
    public boolean generate(WorldAccess world, Random rand, BlockPos pos) {
        for (int i = 0; i < 20; ++i) {
            BlockPos pos2 = new BlockPos(pos.getX() + rand.nextInt(4) - rand.nextInt(4), pos.getY(), pos.getZ() + rand.nextInt(4) - rand.nextInt(4));
            if (world.getBlockState(pos2).isAir()
                    && (world.getBlockState(pos.down().north()).getFluidState().isIn(FluidTags.WATER)
                    || world.getBlockState(pos.down().south()).getFluidState().isIn(FluidTags.WATER)
                    || world.getBlockState(pos.down().west()).getFluidState().isIn(FluidTags.WATER)
                    || world.getBlockState(pos.down().east()).getFluidState().isIn(FluidTags.WATER))) {
                for (int j = 0; j < 2 + rand.nextInt(rand.nextInt(3) + 1); ++j) {
                    if (AlphaBlocks.REED.canPlaceAt(world.getBlockState(pos2.withY(pos2.getY() + j)), world, pos2.withY(pos2.getY() + j))) {
                        world.setBlockState(pos2.withY(pos2.getY() + j), AlphaBlocks.REED.getDefaultState(), 3);
                    }
                }
            }
        }

        return true;
    }
}