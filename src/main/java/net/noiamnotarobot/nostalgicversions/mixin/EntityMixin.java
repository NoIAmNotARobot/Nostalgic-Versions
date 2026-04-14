package net.noiamnotarobot.nostalgicversions.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.fabricmc.fabric.api.attachment.v1.AttachmentTarget;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.server.command.CommandOutput;
import net.minecraft.util.Nameable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.entity.EntityLike;
import net.noiamnotarobot.nostalgicversions.block.AlphaEntityWalking;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Entity.class)
public abstract class EntityMixin implements Nameable, EntityLike, CommandOutput, AttachmentTarget {
    @Shadow
    private World world;

    @WrapOperation(method = "move", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;stepOnBlock(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;ZZLnet/minecraft/util/math/Vec3d;)Z"))
    private boolean activateFarmlandWalkEffects(Entity instance, BlockPos pos, BlockState state, boolean playSound, boolean emitEvent, Vec3d movement, Operation<Boolean> original) {
        if (state.getBlock() instanceof AlphaEntityWalking) ((AlphaEntityWalking) state.getBlock()).onEntityWalking(world, pos, (Entity)(Object)this);
        return original.call(instance, pos, state, playSound, emitEvent, movement);
    }
}