package net.noiamnotarobot.nostalgicversions.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.noiamnotarobot.nostalgicversions.NostalgicVersions;
import net.noiamnotarobot.nostalgicversions.item.AlphaItems;

public class AlphaCropsBlock extends AlphaFlowerBlock {
    public static final IntProperty AGE = Properties.AGE_7;

    public AlphaCropsBlock(Settings settings) {
        super(settings);
    }

    public boolean canThisPlantGrowOnThisBlock(BlockState floor) {
        return floor.isOf(AlphaBlocks.TILLED_FIELD) || floor.isOf(Blocks.FARMLAND);
    }

    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random rand) {
        super.scheduledTick(state, world, pos, rand);
        if (world.getLightLevel(pos.up()) >= 9) {
            int age = state.get(AGE);
            if (age < 7) {
                if (rand.nextInt((int) (100.0F / getGrowthRate(world, pos))) == 0) {
                    ++age;
                    world.setBlockState(pos, state.with(AGE, age));
                }
            }
        }
    }

    private float getGrowthRate(World world, BlockPos pos) {
        float rate = 1.0F;
        BlockState westState = world.getBlockState(pos.west());
        BlockState eastState = world.getBlockState(pos.east());
        BlockState northState = world.getBlockState(pos.north());
        BlockState southState = world.getBlockState(pos.south());
        BlockState northWestState = world.getBlockState(pos.north().west());
        BlockState southWestState = world.getBlockState(pos.south().west());
        BlockState southEastState = world.getBlockState(pos.south().east());
        BlockState northEastState = world.getBlockState(pos.north().east());
        boolean northSouthThis = northState.isOf(this) || southState.isOf(this);
        boolean eastWestThis = westState.isOf(this) || eastState.isOf(this);
        boolean diagonalThis = northWestState.isOf(this) || southWestState.isOf(this) || southEastState.isOf(this) || northEastState.isOf(this);

        for (int x = pos.getX() - 1; x <= pos.getX() + 1; ++x) {
            for (int z = pos.getZ() - 1; z <= pos.getZ() + 1; ++z) {
                float r = 0.0F;
                if (world.getBlockState(new BlockPos(x, pos.getY() - 1, z)).isOf(AlphaBlocks.TILLED_FIELD)) {
                    r = 1.0F;
                    if (world.getBlockState(new BlockPos(x, pos.getY() - 1, z)).get(AlphaFarmlandBlock.MOISTURE) > 0) {
                        r = 3.0F;
                    }
                }

                if (x != pos.getX() || z != pos.getZ()) {
                    r /= 4.0F;
                }

                rate += r;
            }
        }

        if (diagonalThis || northSouthThis && eastWestThis) {
            rate /= 2.0F;
        }

        return rate;
    }

    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        super.onBreak(world, pos, state, player);

        for (int i = 0; i < 3; ++i) {
            if (world.getRandom().nextInt(15) <= state.get(AGE)) {
                float var7 = 0.7F;
                float var8 = world.getRandom().nextFloat() * var7 + (1.0F - var7) * 0.5F;
                float var9 = world.getRandom().nextFloat() * var7 + (1.0F - var7) * 0.5F;
                float var10 = world.getRandom().nextFloat() * var7 + (1.0F - var7) * 0.5F;
                ItemEntity item = new ItemEntity(world, (float) pos.getX() + var8, (float) pos.getY() + var9, (float) pos.getZ() + var10, new ItemStack(AlphaItems.SEEDS));
                item.setPickupDelay(10);
                world.spawnEntity(item);
            }
        }
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.cuboid(0.0F, 0.0F, 0.0F, 1.0F, 0.25F, 1.0F);
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(AGE);
    }
}