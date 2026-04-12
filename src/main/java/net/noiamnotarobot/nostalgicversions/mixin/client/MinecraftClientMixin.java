package net.noiamnotarobot.nostalgicversions.mixin.client;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.WindowEventHandler;
import net.minecraft.util.thread.ReentrantThreadExecutor;
import net.noiamnotarobot.nostalgicversions.NostalgicVersions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin extends ReentrantThreadExecutor<Runnable> implements WindowEventHandler {
    @ModifyReturnValue(method = "isAmbientOcclusionEnabled", at = @At("RETURN"))
    private static boolean disableSmoothLightingInAlphaDim(boolean original) {
        if (MinecraftClient.getInstance().world != null) return original && MinecraftClient.getInstance().world.getRegistryKey() != NostalgicVersions.ALPHA_DIM;
        else return original;
    }

    public MinecraftClientMixin(String string) {
        super(string);
    }
}
