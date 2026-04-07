package net.noiamnotarobot.nostalgicversions.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

public class AlphaLeavesBlock extends Block {
    private int decayCounter = 0;
    public static final IntProperty DISTANCE = IntProperty.of("distance", 0, 15);

    public AlphaLeavesBlock(Settings settings) {
        super(settings);
    }

    @Override
    public int getOpacity(BlockState state, BlockView world, BlockPos pos) {
        return 1;
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        decayCounter = 0;
        updateCurrentLeaves(world, pos);
        return state;
    }

    public void updateConnectedLeaves(WorldAccess world, BlockPos pos, int var5) {
        if (world.getBlockState(pos).getBlock() == AlphaBlocks.LEAVES) {
            int var6 = world.getBlockState(pos).get(DISTANCE);
            if(var6 != 0 && var6 == var5 - 1) {
                this.updateCurrentLeaves(world, pos);
            }
        }
    }

    public void updateCurrentLeaves(WorldAccess world, BlockPos pos) {
        if (this.decayCounter++ < 100) {
            int var5 = world.getBlockState(pos.down()).isSolid() ? 16 : 0;
            int var6 = world.getBlockState(pos).get(DISTANCE);
            if(var6 == 0) {
                var6 = 1;
                world.setBlockState(pos, world.getBlockState(pos).with(DISTANCE, 1), 3);
            }

            var5 = this.getConnectionStrength(world, pos.down(), var5);
            var5 = this.getConnectionStrength(world, pos.north(), var5);
            var5 = this.getConnectionStrength(world, pos.south(), var5);
            var5 = this.getConnectionStrength(world, pos.west(), var5);
            var5 = this.getConnectionStrength(world, pos.east(), var5);
            int var7 = var5 - 1;
            if(var7 < 10) {
                var7 = 1;
            }

            if(var7 != var6) {
                world.setBlockState(pos, world.getBlockState(pos).with(DISTANCE, var7), 3);
                this.updateConnectedLeaves(world, pos.down(), var6);
                this.updateConnectedLeaves(world, pos.up(), var6);
                this.updateConnectedLeaves(world, pos.north(), var6);
                this.updateConnectedLeaves(world, pos.south(), var6);
                this.updateConnectedLeaves(world, pos.west(), var6);
                this.updateConnectedLeaves(world, pos.east(), var6);
            }

        }
    }

    private int getConnectionStrength(WorldAccess world, BlockPos pos, int var5) {
        Block var6 = world.getBlockState(pos).getBlock();
        if(var6 == AlphaBlocks.WOOD) {
            return 16;
        } else {
            if(var6 == AlphaBlocks.LEAVES) {
                int var7 = world.getBlockState(pos).get(DISTANCE);
                if(var7 != 0 && var7 > var5) {
                    return var7;
                }
            }

            return var5;
        }
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random rand) {
        int var6 = world.getBlockState(pos).get(DISTANCE);
        if(var6 == 0) {
            this.decayCounter = 0;
            this.updateCurrentLeaves(world, pos);
        } else if(var6 == 1) {
            this.removeLeaves(world, pos);
        } else if(rand.nextInt(10) == 0) {
            this.updateCurrentLeaves(world, pos);
        }
    }

    private void removeLeaves(World world, BlockPos pos) {
        dropStacks(world.getBlockState(pos), world, pos);
        world.removeBlock(pos, false);
    }

    /*public boolean shouldSideBeRendered(IBlockAccess var1, int var2, int var3, int var4, int var5) {
        int var6 = var1.getBlockId(var2, var3, var4);
        return !this.graphicsLevel && var6 == this.blockID ? false : super.shouldSideBeRendered(var1, var2, var3, var4, var5);
    }*/

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(DISTANCE);
    }
}