package net.noiamnotarobot.nostalgicversions.client.render;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.noiamnotarobot.nostalgicversions.NostalgicVersions;

import java.util.Objects;

public class FalseVersionOverlay implements HudRenderCallback {
    @Override
    public void onHudRender(DrawContext drawContext, float v) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client != null
                && client.player != null
                && !client.options.debugEnabled
                && Objects.equals(
                client.player.getEntityWorld().getRegistryKey().getValue().toString(),
                NostalgicVersions.Util.id("alpha_dim").toString())
        ) {
            drawContext.drawText(client.textRenderer, "Minecraft Alpha 1.1.2_01", 2, 2, 16777215, true);
        }
    }
}