package net.noiamnotarobot.nostalgicversions.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

public class AlphaReedBlock extends Block {
    public static final IntProperty AGE = Properties.AGE_15;

    public AlphaReedBlock(Settings settings) {
        super(settings);
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        float var3 = 6.0F / 16.0F;
        return VoxelShapes.cuboid(0.5F - var3, 0.0F, 0.5F - var3, 0.5F + var3, 1.0F, 0.5F + var3);
    }

    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        super.scheduledTick(state, world, pos, random);

        if (world.getBlockState(pos.up()).isAir()) {
            int plantSize;
            for (plantSize = 1; world.getBlockState(pos.down(plantSize)).isOf(this); ++plantSize) {
            }

            if (plantSize < 3) {
                int age = state.get(AGE);
                if (age == 15) {
                    world.setBlockState(pos.up(), getDefaultState());
                    world.setBlockState(pos, state.with(AGE, 0));
                } else {
                    world.setBlockState(pos, state.with(AGE, age + 1));
                }
            }
        }
    }

    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return world.getBlockState(pos.down()).isOf(this)
                || (world.getBlockState(pos.down()).isIn(BlockTags.DIRT)
                && (world.getBlockState(pos.down().north()).getFluidState().isIn(FluidTags.WATER)
                || world.getBlockState(pos.down().south()).getFluidState().isIn(FluidTags.WATER)
                || world.getBlockState(pos.down().east()).getFluidState().isIn(FluidTags.WATER)
                || world.getBlockState(pos.down().west()).getFluidState().isIn(FluidTags.WATER)));
    }

    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        super.neighborUpdate(state, world, pos, sourceBlock, sourcePos, notify);

        if (!canPlaceAt(state, world, pos)) {
            dropStacks(world.getBlockState(pos), world, pos);
            world.removeBlock(pos, false);
        }
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(AGE);
    }
}