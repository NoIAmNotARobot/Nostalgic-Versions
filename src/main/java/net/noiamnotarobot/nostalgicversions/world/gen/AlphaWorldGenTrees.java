package net.noiamnotarobot.nostalgicversions.world.gen;

import net.minecraft.block.BlockState;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldAccess;
import net.noiamnotarobot.nostalgicversions.block.AlphaBlocks;

import java.util.Random;

public class AlphaWorldGenTrees extends AlphaWorldGenerator {
    @Override
    public boolean generate(WorldAccess world, Random rand, BlockPos pos) {
        int var6 = rand.nextInt(3) + 4;
        boolean var7 = true;
        if(pos.getY() >= 1 && pos.getY() + var6 + 1 <= 128) {
            int var8;
            int var10;
            int var11;
            BlockState var12;
            for(var8 = pos.getY(); var8 <= pos.getY() + 1 + var6; ++var8) {
                byte var9 = 1;
                if(var8 == pos.getY()) {
                    var9 = 0;
                }

                if(var8 >= pos.getY() + 1 + var6 - 2) {
                    var9 = 2;
                }

                for(var10 = pos.getX() - var9; var10 <= pos.getX() + var9 && var7; ++var10) {
                    for(var11 = pos.getZ() - var9; var11 <= pos.getZ() + var9 && var7; ++var11) {
                        if(var8 >= 0 && var8 < 128) {
                            var12 = world.getBlockState(new BlockPos(var10, var8, var11));
                            if(!var12.isAir() && !var12.isOf(AlphaBlocks.LEAVES)) {
                                var7 = false;
                            }
                        } else {
                            var7 = false;
                        }
                    }
                }
            }

            if(!var7) {
                return false;
            } else {
                if((world.getBlockState(pos.down()).isIn(BlockTags.DIRT)) && pos.getY() < 128 - var6 - 1) {
                    world.setBlockState(pos.down(), AlphaBlocks.DIRT.getDefaultState(), 3);

                    int var16;
                    for(var16 = pos.getY() - 3 + var6; var16 <= pos.getY() + var6; ++var16) {
                        var10 = var16 - (pos.getY() + var6);
                        var11 = 1 - var10 / 2;

                        for(int var1000 = pos.getX() - var11; var1000 <= pos.getX() + var11; ++var1000) {
                            int var13 = var1000 - pos.getX();

                            for(int var14 = pos.getZ() - var11; var14 <= pos.getZ() + var11; ++var14) {
                                int var15 = var14 - pos.getZ();
                                if((Math.abs(var13) != var11 || Math.abs(var15) != var11 || rand.nextInt(2) != 0 && var10 != 0) && !world.getBlockState(new BlockPos(var1000, var16, var14)).isOpaque()) {
                                    world.setBlockState(new BlockPos(var1000, var16, var14), AlphaBlocks.LEAVES.getDefaultState(), 3);
                                }
                            }
                        }
                    }

                    for(var16 = 0; var16 < var6; ++var16) {
                        BlockState var1001 = world.getBlockState(pos.up(var16));
                        if(var1001.isAir() || var1001.isOf(AlphaBlocks.LEAVES)) {
                            world.setBlockState(pos.up(var16), AlphaBlocks.WOOD.getDefaultState(), 3);
                        }
                    }

                    return true;
                } else {
                    return false;
                }
            }
        } else {
            return false;
        }
    }
}
