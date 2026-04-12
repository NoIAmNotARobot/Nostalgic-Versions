package net.noiamnotarobot.nostalgicversions.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SnowyBlock;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class AlphaGrassBlock extends SnowyBlock {
    public AlphaGrassBlock(Settings settings) {
        super(settings);
    }

    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random rand) {
        world.setBlockState(pos, state.with(SNOWY, world.getBlockState(pos.up()).isIn(BlockTags.SNOW)));

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

    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        super.neighborUpdate(state, world, pos, sourceBlock, sourcePos, notify);

        world.setBlockState(pos, state.with(SNOWY, world.getBlockState(pos.up()).isIn(BlockTags.SNOW)));
    }
}