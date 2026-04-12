package net.noiamnotarobot.nostalgicversions.world.gen;

import net.minecraft.registry.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.WorldAccess;
import net.noiamnotarobot.nostalgicversions.block.AlphaBlocks;

import java.util.Random;

public class AlphaWorldGenClay extends AlphaWorldGenerator {
    private final int blockCount;

    public AlphaWorldGenClay(int blockCount) {
        this.blockCount = blockCount;
    }

    public boolean generate(WorldAccess world, Random rand, BlockPos pos) {
        if (!world.getBlockState(pos).getFluidState().isIn(FluidTags.WATER)) return false;
        float var6 = rand.nextFloat() * (float) Math.PI;
        double var7 = (float) (pos.getX() + 8) + MathHelper.sin(var6) * (float) this.blockCount / 8.0F;
        double var9 = (float) (pos.getX() + 8) - MathHelper.sin(var6) * (float) this.blockCount / 8.0F;
        double var11 = (float) (pos.getZ() + 8) + MathHelper.cos(var6) * (float) this.blockCount / 8.0F;
        double var13 = (float) (pos.getZ() + 8) - MathHelper.cos(var6) * (float) this.blockCount / 8.0F;
        double var15 = pos.getY() + rand.nextInt(3) + 2;
        double var17 = pos.getY() + rand.nextInt(3) + 2;

        for (int var19 = 0; var19 <= this.blockCount; ++var19) {
            double var20 = var7 + (var9 - var7) * (double) var19 / (double) this.blockCount;
            double var22 = var15 + (var17 - var15) * (double) var19 / (double) this.blockCount;
            double var24 = var11 + (var13 - var11) * (double) var19 / (double) this.blockCount;
            double var26 = rand.nextDouble() * (double) this.blockCount / 16.0D;
            double var28 = (double) (MathHelper.sin((float) var19 * (float) Math.PI / (float) this.blockCount) + 1.0F) * var26 + 1.0D;
            double var30 = (double) (MathHelper.sin((float) var19 * (float) Math.PI / (float) this.blockCount) + 1.0F) * var26 + 1.0D;

            for (int x = (int) (var20 - var28 / 2.0D); x <= (int) (var20 + var28 / 2.0D); ++x) {
                for (int y = (int) (var22 - var30 / 2.0D); y <= (int) (var22 + var30 / 2.0D); ++y) {
                    for (int z = (int) (var24 - var28 / 2.0D); z <= (int) (var24 + var28 / 2.0D); ++z) {
                        double var35 = ((double) x + 0.5D - var20) / (var28 / 2.0D);
                        double var37 = ((double) y + 0.5D - var22) / (var30 / 2.0D);
                        double var39 = ((double) z + 0.5D - var24) / (var28 / 2.0D);
                        if (var35 * var35 + var37 * var37 + var39 * var39 < 1.0D) {
                            if (world.getBlockState(new BlockPos(x, y, z)).isOf(AlphaBlocks.SAND)) {
                                world.setBlockState(new BlockPos(x, y, z), AlphaBlocks.BLOCK_CLAY.getDefaultState(), 3);
                            }
                        }
                    }
                }
            }
        }

        return true;
    }
}