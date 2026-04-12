package net.noiamnotarobot.nostalgicversions.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.noiamnotarobot.nostalgicversions.block.AlphaBlocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(CropBlock.class)
public abstract class CropBlockMixin extends Block {
    @ModifyReturnValue(method = "canPlantOnTop", at = @At("RETURN"))
    private boolean makeTilledFieldWorkAsFarmland(boolean original, BlockState floor) {
        return original || floor.isOf(AlphaBlocks.TILLED_FIELD);
    }

    public CropBlockMixin(Settings settings) {
        super(settings);
    }
}