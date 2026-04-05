package net.noiamnotarobot.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.noiamnotarobot.world.gen.AlphaWorldGenBigTree;
import net.noiamnotarobot.world.gen.AlphaWorldGenTrees;
import net.noiamnotarobot.world.gen.AlphaWorldGenerator;

public class AlphaSaplingBlock extends AlphaFlowerBlock {
    public static final IntProperty AGE = Properties.AGE_15;
    public AlphaSaplingBlock(Settings settings) {
        super(settings);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.cuboid(0.1F, 0.0F, 0.1F, 0.9F, 0.8F, 0.9F);
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random rand) {
        super.randomTick(state, world, pos, rand);

        if(world.getLightLevel(pos.up()) >= 9) {
            int var6 = state.get(AGE);
            if(var6 < 15) {
                world.setBlockState(pos, AlphaBlocks.SAPLING.getDefaultState().with(AGE, var6 + 1));
            } else {
                world.setBlockState(pos, Blocks.AIR.getDefaultState());
                AlphaWorldGenerator treeGen = new AlphaWorldGenTrees();
                if(rand.nextInt(10) == 0) {
                    treeGen = new AlphaWorldGenBigTree();
                }

                if(!treeGen.generate(world, new java.util.Random(), pos)) {
                    world.setBlockState(pos, AlphaBlocks.SAPLING.getDefaultState());
                }
            }
        }
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }
}
