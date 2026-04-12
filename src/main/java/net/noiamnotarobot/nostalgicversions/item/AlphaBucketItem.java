package net.noiamnotarobot.nostalgicversions.item;

import net.minecraft.block.*;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class AlphaBucketItem extends Item {
    private final @Nullable Block containedBlock;

    public AlphaBucketItem(Settings settings, @Nullable Block containedBlock) {
        super(settings);
        this.containedBlock = containedBlock;
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        HitResult result = user.raycast(5, 1, containedBlock == Blocks.AIR);
        if (result.getType() != HitResult.Type.MISS) {
            if (result.getType() == HitResult.Type.BLOCK) {
                BlockHitResult blockResult = (BlockHitResult) result;
                BlockPos pos = blockResult.getBlockPos();
                Direction side = blockResult.getSide();
                if (containedBlock == Blocks.AIR) {
                    if (world.getBlockState(pos).getFluidState().isIn(FluidTags.WATER) && world.getBlockState(pos).getFluidState().isStill()) {
                        world.setBlockState(pos, Blocks.AIR.getDefaultState());
                        return TypedActionResult.pass(new ItemStack(AlphaItems.BUCKET_WATER));
                    }

                    if (world.getBlockState(pos).getFluidState().isIn(FluidTags.LAVA) && world.getBlockState(pos).getFluidState().isStill()) {
                        world.setBlockState(pos, Blocks.AIR.getDefaultState());
                        return TypedActionResult.pass(new ItemStack(AlphaItems.BUCKET_LAVA));
                    }
                } else {
                    if (containedBlock == null) {
                        return TypedActionResult.pass(new ItemStack(AlphaItems.BUCKET_EMPTY));
                    }

                    if (world.getBlockState(pos.offset(side)).isAir() || !world.getBlockState(pos.offset(side)).isSolidBlock(world, pos.offset(side))) {
                        world.setBlockState(pos.offset(side), containedBlock.getDefaultState());
                        return TypedActionResult.pass(new ItemStack(AlphaItems.BUCKET_EMPTY));
                    }
                }
            } else if (containedBlock == Blocks.AIR && ((EntityHitResult) result).getEntity() instanceof CowEntity) {
                return TypedActionResult.pass(new ItemStack(AlphaItems.BUCKET_MILK));
            }

        }
        return TypedActionResult.pass(user.getStackInHand(hand));
    }
}
