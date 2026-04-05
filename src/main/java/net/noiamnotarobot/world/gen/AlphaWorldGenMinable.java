package net.noiamnotarobot.world.gen;

import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.WorldAccess;
import net.noiamnotarobot.block.AlphaBlocks;

import java.util.Random;

public class AlphaWorldGenMinable extends AlphaWorldGenerator {
    private final Block minableBlock;
    private final int numberOfBlocks;

    public AlphaWorldGenMinable(Block block, int num) {
        minableBlock = block;
        numberOfBlocks = num;
    }

    @Override
    public boolean generate(WorldAccess world, Random rand, BlockPos pos) {
        float var6 = rand.nextFloat() * (float)Math.PI;
        double var7 = (float)(pos.getX() + 8) + MathHelper.sin(var6) * (float)numberOfBlocks / 8.0F;
        double var9 = (float)(pos.getX() + 8) - MathHelper.sin(var6) * (float)numberOfBlocks / 8.0F;
        double var11 = (float)(pos.getY() + 8) + MathHelper.cos(var6) * (float)numberOfBlocks / 8.0F;
        double var13 = (float)(pos.getY() + 8) - MathHelper.cos(var6) * (float)numberOfBlocks / 8.0F;
        double var15 = pos.getZ() + rand.nextInt(3) + 2;
        double var17 = pos.getZ() + rand.nextInt(3) + 2;

        for(int var19 = 0; var19 <= numberOfBlocks; ++var19) {
            double var20 = var7 + (var9 - var7) * (double)var19 / (double)numberOfBlocks;
            double var22 = var15 + (var17 - var15) * (double)var19 / (double)numberOfBlocks;
            double var24 = var11 + (var13 - var11) * (double)var19 / (double)numberOfBlocks;
            double var26 = rand.nextDouble() * (double)numberOfBlocks / 16.0D;
            double var28 = (double)(MathHelper.sin((float)var19 * (float)Math.PI / (float)numberOfBlocks) + 1.0F) * var26 + 1.0D;
            double var30 = (double)(MathHelper.sin((float)var19 * (float)Math.PI / (float)numberOfBlocks) + 1.0F) * var26 + 1.0D;

            for(int x = (int)(var20 - var28 / 2.0D); x <= (int)(var20 + var28 / 2.0D); ++x) {
                for(int z = (int)(var22 - var30 / 2.0D); z <= (int)(var22 + var30 / 2.0D); ++z) {
                    for(int y = (int)(var24 - var28 / 2.0D); y <= (int)(var24 + var28 / 2.0D); ++y) {
                        double var35 = ((double)x + 0.5D - var20) / (var28 / 2.0D);
                        double var37 = ((double)z + 0.5D - var22) / (var30 / 2.0D);
                        double var39 = ((double)y + 0.5D - var24) / (var28 / 2.0D);
                        if(Math.pow(var35, 2) + Math.pow(var37, 2) + Math.pow(var39, 2) < 1.0D && world.getBlockState(new BlockPos(x, y, z)).isOf(AlphaBlocks.STONE)) {
                            world.setBlockState(new BlockPos(x, y, z), minableBlock.getDefaultState(), 3);
                        }
                    }
                }
            }
        }

        return true;
    }
}
