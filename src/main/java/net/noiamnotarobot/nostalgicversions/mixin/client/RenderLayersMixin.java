package net.noiamnotarobot.nostalgicversions.mixin.client;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.client.render.RenderLayers;
import net.noiamnotarobot.nostalgicversions.block.AlphaBlocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(RenderLayers.class)
public abstract class RenderLayersMixin {
    @Definition(id = "block", local = @Local(type = Block.class))
    @Definition(id = "LeavesBlock", type = LeavesBlock.class)
    @Expression("block instanceof LeavesBlock")
    @ModifyExpressionValue(method = "getBlockLayer", at = @At(value = "MIXINEXTRAS:EXPRESSION"))
    private static boolean makeAlphaLeavesGraphicsLevelDependent(boolean original, BlockState state) {
        return original || state.isOf(AlphaBlocks.LEAVES);
    }
}
