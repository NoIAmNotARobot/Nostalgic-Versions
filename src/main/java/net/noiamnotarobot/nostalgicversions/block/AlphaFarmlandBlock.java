package net.noiamnotarobot.nostalgicversions.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
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

public class AlphaFarmlandBlock extends Block implements AlphaEntityWalking {
    public static final IntProperty MOISTURE = Properties.MOISTURE;

    public AlphaFarmlandBlock(Settings settings) {
        super(settings);
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return createCuboidShape(0, 0, 0, 16, 15, 16);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.fullCube();
    }

    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random rand) {
        if (rand.nextInt(5) == 0) {
            if (isWaterNearby(world, pos)) {
                world.setBlockState(pos, state.with(MOISTURE, 7));
            } else {
                int oldMoisture = state.get(MOISTURE);
                if (oldMoisture > 0) {
                    world.setBlockState(pos, state.with(MOISTURE, oldMoisture - 1));
                } else if (!hasCrop(world, pos)) {
                    world.setBlockState(pos, AlphaBlocks.DIRT.getDefaultState());
                }
            }
        }
    }

    public void onEntityWalking(World world, BlockPos pos, Entity entity) {
        if (entity instanceof LivingEntity && !entity.isSneaking() && world.random.nextInt(4) == 0) {
            world.setBlockState(pos, AlphaBlocks.DIRT.getDefaultState());
        }
    }

    private boolean hasCrop(World world, BlockPos pos) {
        return world.getBlockState(pos.up()).isIn(BlockTags.MAINTAINS_FARMLAND);
    }

    private boolean isWaterNearby(World world, BlockPos pos) {
        for (int x = pos.getX() - 4; x <= pos.getX() + 4; ++x) {
            for (int y = pos.getY(); y <= pos.getY() + 1; ++y) {
                for (int z = pos.getZ() - 4; z <= pos.getZ() + 4; ++z) {
                    if (world.getBlockState(new BlockPos(x, y, z)).getFluidState().isIn(FluidTags.WATER)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        super.neighborUpdate(state, world, pos, sourceBlock, sourcePos, notify);

        if (world.getBlockState(pos.up()).isSolidBlock(world, pos.up())) {
            world.setBlockState(pos, AlphaBlocks.DIRT.getDefaultState());
        }
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(MOISTURE);
    }
}