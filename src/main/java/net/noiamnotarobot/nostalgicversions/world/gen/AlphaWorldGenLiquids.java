package net.noiamnotarobot.nostalgicversions.world.gen;

import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldAccess;
import net.noiamnotarobot.nostalgicversions.block.AlphaBlocks;

import java.util.Random;

public class AlphaWorldGenLiquids extends AlphaWorldGenerator {
    private final Block liquidBlock;

    public AlphaWorldGenLiquids(Block block) {
        this.liquidBlock = block;
    }

    public boolean generate(WorldAccess world, Random rand, BlockPos pos) {
        if (!world.getBlockState(pos.up()).isOf(AlphaBlocks.STONE)) {
            return false;
        } else if (!world.getBlockState(pos.down()).isOf(AlphaBlocks.STONE)) {
            return false;
        } else if (!world.getBlockState(pos).isAir() && !world.getBlockState(pos).isOf(AlphaBlocks.STONE)) {
            return false;
        } else {
            int closedSides = 0;
            if (world.getBlockState(pos.north()).isOf(AlphaBlocks.STONE)) {
                ++closedSides;
            }

            if (world.getBlockState(pos.south()).isOf(AlphaBlocks.STONE)) {
                ++closedSides;
            }

            if (world.getBlockState(pos.west()).isOf(AlphaBlocks.STONE)) {
                ++closedSides;
            }

            if (world.getBlockState(pos.east()).isOf(AlphaBlocks.STONE)) {
                ++closedSides;
            }

            int openSides = 0;
            if (world.getBlockState(pos.north()).isAir()) {
                ++openSides;
            }

            if (world.getBlockState(pos.south()).isAir()) {
                ++openSides;
            }

            if (world.getBlockState(pos.west()).isAir()) {
                ++openSides;
            }

            if (world.getBlockState(pos.east()).isAir()) {
                ++openSides;
            }

            if (closedSides == 3 && openSides == 1) {
                world.setBlockState(pos, liquidBlock.getDefaultState(), 3);
                world.scheduleFluidTick(pos, liquidBlock.getDefaultState().getFluidState().getFluid(), 1);
            }

            return true;
        }
    }
}