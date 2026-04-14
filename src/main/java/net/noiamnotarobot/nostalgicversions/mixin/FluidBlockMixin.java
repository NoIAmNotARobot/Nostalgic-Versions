package net.noiamnotarobot.nostalgicversions.mixin;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.block.Block;
import net.minecraft.block.FluidBlock;
import net.minecraft.block.FluidDrainable;
import net.minecraft.world.World;
import net.noiamnotarobot.nostalgicversions.NostalgicVersions;
import net.noiamnotarobot.nostalgicversions.block.AlphaBlocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(FluidBlock.class)
public abstract class FluidBlockMixin extends Block implements FluidDrainable {
    @Definition(id = "OBSIDIAN", field = "Lnet/minecraft/block/Blocks;OBSIDIAN:Lnet/minecraft/block/Block;")
    @Expression("OBSIDIAN")
    @ModifyExpressionValue(method = "receiveNeighborFluids", at = @At("MIXINEXTRAS:EXPRESSION"))
    private Block makeAlphaFluidsPlaceObsidian(Block original, World world) {
        return world.getRegistryKey() == NostalgicVersions.ALPHA_DIM ? AlphaBlocks.OBSIDIAN : original;
    }

    @Definition(id = "COBBLESTONE", field = "Lnet/minecraft/block/Blocks;COBBLESTONE:Lnet/minecraft/block/Block;")
    @Expression("COBBLESTONE")
    @ModifyExpressionValue(method = "receiveNeighborFluids", at = @At("MIXINEXTRAS:EXPRESSION"))
    private Block makeAlphaFluidsPlaceCobblestone(Block original, World world) {
        return world.getRegistryKey() == NostalgicVersions.ALPHA_DIM ? AlphaBlocks.COBBLESTONE : original;
    }

    public FluidBlockMixin(Settings settings) {
        super(settings);
    }
}