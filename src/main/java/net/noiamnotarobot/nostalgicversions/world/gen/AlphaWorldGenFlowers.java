package net.noiamnotarobot.nostalgicversions.world.gen;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldAccess;
import net.noiamnotarobot.nostalgicversions.block.AlphaFlowerBlock;

import java.util.Random;

public class AlphaWorldGenFlowers extends AlphaWorldGenerator {
    private final AlphaFlowerBlock plantBlock;

    public AlphaWorldGenFlowers(AlphaFlowerBlock plantBlock) {
        this.plantBlock = plantBlock;
    }

    public boolean generate(WorldAccess world, Random rand, BlockPos pos) {
        for(int var6 = 0; var6 < 64; ++var6) {
            BlockPos pos2 = new BlockPos(
                    pos.getX() + rand.nextInt(8) - rand.nextInt(8),
                    pos.getY() + rand.nextInt(4) - rand.nextInt(4),
                    pos.getZ() + rand.nextInt(8) - rand.nextInt(8)
            );
            if(world.getBlockState(pos2).isAir() && plantBlock.canBlockStay(world, pos2)) {
                world.setBlockState(pos2, this.plantBlock.getDefaultState(), 3);
            }
        }
        return true;
    }
}
