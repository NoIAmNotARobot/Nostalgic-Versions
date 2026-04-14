package net.noiamnotarobot.nostalgicversions.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class AlphaRedstoneOreBlock extends Block implements AlphaEntityWalking {
    private final boolean glowing;

    public AlphaRedstoneOreBlock(Settings settings, boolean glowing) {
        super(settings);
        this.glowing = glowing;
    }

    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        glow(world, pos);
        return super.onUse(state, world, pos, player, hand, hit);
    }

    public void onBlockBreakStart(BlockState state, World world, BlockPos pos, PlayerEntity player) {
        glow(world, pos);
        super.onBlockBreakStart(state, world, pos, player);
    }

    public void onEntityWalking(World world, BlockPos pos, Entity entity) {
        glow(world, pos);
    }

    private void glow(World world, BlockPos pos) {
        sparkle(world, pos, world.random);
        if (!glowing) world.setBlockState(pos, AlphaBlocks.ORE_REDSTONE_GLOWING.getDefaultState());
    }

    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        super.scheduledTick(state, world, pos, random);

        if (glowing) world.setBlockState(pos, AlphaBlocks.ORE_REDSTONE.getDefaultState());
    }

    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        super.randomDisplayTick(state, world, pos, random);

        if (glowing) sparkle(world, pos, random);
    }

    private void sparkle(World world, BlockPos pos, Random rand) {
        double offset = 1.0D / 16.0D;

        for (int side = 0; side < 6; ++side) {
            double x = (float) pos.getX() + rand.nextFloat();
            double y = (float) pos.getY() + rand.nextFloat();
            double z = (float) pos.getZ() + rand.nextFloat();
            if (side == 0 && !world.getBlockState(pos.up()).isFullCube(world, pos)) {
                y = (double) (pos.getY() + 1) + offset;
            }

            if (side == 1 && !world.getBlockState(pos.down()).isFullCube(world, pos)) {
                y = (double) (pos.getY()) - offset;
            }

            if (side == 2 && !world.getBlockState(pos.south()).isFullCube(world, pos)) {
                z = (double) (pos.getZ() + 1) + offset;
            }

            if (side == 3 && !world.getBlockState(pos.north()).isFullCube(world, pos)) {
                z = (double) (pos.getZ()) - offset;
            }

            if (side == 4 && !world.getBlockState(pos.east()).isFullCube(world, pos)) {
                x = (double) (pos.getX() + 1) + offset;
            }

            if (side == 5 && !world.getBlockState(pos.west()).isFullCube(world, pos)) {
                x = (double) (pos.getX()) - offset;
            }

            if (x < (double) pos.getX() || x > (double) (pos.getX() + 1) || y < 0.0D || y > (double) (pos.getY() + 1) || z < (double) pos.getZ() || z > (double) (pos.getZ() + 1)) {
                world.addParticle(DustParticleEffect.DEFAULT, x, y, z, 0.0D, 0.0D, 0.0D);
            }
        }
    }
}