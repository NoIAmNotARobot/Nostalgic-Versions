package net.noiamnotarobot.nostalgicversions.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

import java.util.HashMap;

public class AlphaFireBlock extends Block {
    public static final IntProperty AGE = Properties.AGE_15;
    private final HashMap<String, Integer> chanceToEncourageFire = new HashMap<>();
    private final HashMap<String, Integer> abilityToCatchFire = new HashMap<>();

    public AlphaFireBlock(Settings settings) {
        super(settings);
        initializeBlock(AlphaBlocks.PLANKS, 5, 20);
        initializeBlock(AlphaBlocks.WOOD, 5, 5);
        initializeBlock(AlphaBlocks.LEAVES, 30, 60);
        initializeBlock(AlphaBlocks.BOOKSHELF, 30, 20);
        initializeBlock(AlphaBlocks.TNT, 15, 100);
        initializeBlock(AlphaBlocks.CLOTH, 30, 60);
    }

    public void initializeBlock(Block block, int chanceToEncourageFire, int abilityToCatchFire) {
        this.chanceToEncourageFire.put(block.getTranslationKey(), chanceToEncourageFire);
        this.abilityToCatchFire.put(block.getTranslationKey(), abilityToCatchFire);
    }

    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random rand) {
        int var6 = state.get(AGE);
        if (var6 < 15) {
            world.setBlockState(pos, state.with(AGE, var6 + 1));
        }

        if (!canNeighborBurn(world, pos)) {
            if (!world.getBlockState(pos.down()).isFullCube(world, pos.down()) || var6 > 3) {
                world.setBlockState(pos, Blocks.AIR.getDefaultState());
            }

        } else if (!canBlockCatchFire(world, pos.down()) && var6 == 15 && rand.nextInt(4) == 0) {
            world.setBlockState(pos, Blocks.AIR.getDefaultState());
        } else {
            if (var6 % 2 == 0 && var6 > 2) {
                tryToCatchBlockOnFire(world, pos.west(), 300, rand);
                tryToCatchBlockOnFire(world, pos.east(), 300, rand);
                tryToCatchBlockOnFire(world, pos.down(), 200, rand);
                tryToCatchBlockOnFire(world, pos.up(), 250, rand);
                tryToCatchBlockOnFire(world, pos.north(), 300, rand);
                tryToCatchBlockOnFire(world, pos.south(), 300, rand);

                for (int var7 = pos.getX() - 1; var7 <= pos.getX() + 1; ++var7) {
                    for (int var8 = pos.getZ() - 1; var8 <= pos.getZ() + 1; ++var8) {
                        for (int var9 = pos.getY() - 1; var9 <= pos.getY() + 4; ++var9) {
                            if (var7 != pos.getX() || var9 != pos.getY() || var8 != pos.getZ()) {
                                int var10 = 100;
                                if (var9 > pos.getY() + 1) {
                                    var10 += (var9 - (pos.getY() + 1)) * 100;
                                }

                                int var11 = getChanceOfNeighborsEncouragingFire(world, new BlockPos(var7, var9, var8));
                                if (var11 > 0 && rand.nextInt(var10) <= var11) {
                                    world.setBlockState(new BlockPos(var7, var9, var8), getDefaultState());
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void tryToCatchBlockOnFire(World world, BlockPos pos, int strength, Random rand) {
        int var7 = abilityToCatchFire.getOrDefault(world.getBlockState(pos).getBlock().getTranslationKey(), 0);
        if (rand.nextInt(strength) < var7) {
            boolean var8 = world.getBlockState(pos).isOf(AlphaBlocks.TNT);
            if (rand.nextInt(2) == 0) {
                world.setBlockState(pos, getDefaultState());
            } else {
                world.setBlockState(pos, Blocks.AIR.getDefaultState());
            }

            if (var8) {
                AlphaBlocks.TNT.onBroken(world, pos, world.getBlockState(pos));
            }
        }
    }

    private boolean canNeighborBurn(WorldView world, BlockPos pos) {
        return canBlockCatchFire(world, pos.east())
                || canBlockCatchFire(world, pos.west())
                || canBlockCatchFire(world, pos.down())
                || canBlockCatchFire(world, pos.up())
                || canBlockCatchFire(world, pos.north())
                || canBlockCatchFire(world, pos.south());
    }

    private int getChanceOfNeighborsEncouragingFire(World world, BlockPos pos) {
        byte var5 = 0;
        if (!world.getBlockState(pos).isAir()) {
            return 0;
        } else {
            int var6 = getChanceToEncourageFire(world, pos.east(), var5);
            var6 = getChanceToEncourageFire(world, pos.west(), var6);
            var6 = getChanceToEncourageFire(world, pos.down(), var6);
            var6 = getChanceToEncourageFire(world, pos.up(), var6);
            var6 = getChanceToEncourageFire(world, pos.north(), var6);
            var6 = getChanceToEncourageFire(world, pos.south(), var6);
            return var6;
        }
    }

    public boolean canBlockCatchFire(WorldView world, BlockPos pos) {
        return chanceToEncourageFire.containsKey(world.getBlockState(pos).getBlock().getTranslationKey());
    }

    public int getChanceToEncourageFire(WorldView world, BlockPos pos, int oldValue) {
        return Math.max(oldValue, chanceToEncourageFire.getOrDefault(world.getBlockState(pos).getBlock().getTranslationKey(), 0));
    }

    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return world.getBlockState(pos.down()).isFullCube(world, pos) || canNeighborBurn(world, pos);
    }

    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        super.neighborUpdate(state, world, pos, sourceBlock, sourcePos, notify);

        if (!world.getBlockState(pos.down()).isFullCube(world, pos.down()) && !canNeighborBurn(world, pos)) {
            world.setBlockState(pos, Blocks.AIR.getDefaultState());
        }
    }

    @Override
    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        super.onBlockAdded(state, world, pos, oldState, notify);

        if (!world.getBlockState(pos.down()).isFullCube(world, pos.down()) && !canNeighborBurn(world, pos)) {
            world.setBlockState(pos, Blocks.AIR.getDefaultState());
        } else {
            world.scheduleBlockTick(pos, this, 1);
        }
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(AGE);
    }
}