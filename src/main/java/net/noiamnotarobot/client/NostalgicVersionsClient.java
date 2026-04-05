package net.noiamnotarobot.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.render.RenderLayer;
import net.noiamnotarobot.block.AlphaBlocks;

public class NostalgicVersionsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(),
                AlphaBlocks.SAPLING,
                AlphaBlocks.LEAVES,
                AlphaBlocks.GLASS,
                AlphaBlocks.PLANT_YELLOW,
                AlphaBlocks.PLANT_RED,
                AlphaBlocks.MUSHROOM_BROWN,
                AlphaBlocks.MUSHROOM_RED
        );
        HudRenderCallback.EVENT.register(new FalseVersionOverlay());
    }
}
