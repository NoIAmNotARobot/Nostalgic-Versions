package net.noiamnotarobot.nostalgicversions.mixin;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.LavaFluid;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.noiamnotarobot.nostalgicversions.NostalgicVersions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LavaFluid.class)
public abstract class LavaFluidMixin extends FlowableFluid {
    @Definition(id = "direction", local = @Local(type = Direction.class, name = "direction", argsOnly = true))
    @Definition(id = "DOWN", field = "Lnet/minecraft/util/math/Direction;DOWN:Lnet/minecraft/util/math/Direction;")
    @Expression("direction == DOWN")
    @ModifyExpressionValue(method = "flow", at = @At("MIXINEXTRAS:EXPRESSION"))
    private boolean makeAlphaLavaPlaceAlphaStone(boolean original, WorldAccess world) {
        return original && ((World) world).getRegistryKey() != NostalgicVersions.ALPHA_DIM;
    }
}