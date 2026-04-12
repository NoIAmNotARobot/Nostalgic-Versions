package net.noiamnotarobot.nostalgicversions.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.render.RenderLayer;
import net.noiamnotarobot.nostalgicversions.block.AlphaBlocks;
import net.noiamnotarobot.nostalgicversions.client.render.FalseVersionOverlay;

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
                AlphaBlocks.MUSHROOM_RED,
                AlphaBlocks.MOB_SPAWNER,
                AlphaBlocks.CROPS
        );
        BlockRenderLayerMap.INSTANCE.putBlock(AlphaBlocks.ICE, RenderLayer.getTranslucent());
        HudRenderCallback.EVENT.register(new FalseVersionOverlay());
    }
}
