package net.noiamnotarobot.world.gen;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldAccess;

import java.util.Random;

public abstract class AlphaWorldGenerator {
    public abstract boolean generate(WorldAccess World, Random rand, BlockPos pos);

    public void setScale(double var1, double var3, double var5) {
    }
}
