package net.noiamnotarobot.nostalgicversions.block;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface AlphaEntityWalking {
    void onEntityWalking(World world, BlockPos pos, Entity entity);
}