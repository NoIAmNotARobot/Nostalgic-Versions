package net.noiamnotarobot.nostalgicversions.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;
import net.noiamnotarobot.nostalgicversions.NostalgicVersions;
import net.noiamnotarobot.nostalgicversions.block.AlphaBlocks;
import net.noiamnotarobot.nostalgicversions.client.render.FalseVersionOverlay;
import net.noiamnotarobot.nostalgicversions.client.screen.WorkbenchScreen;

public class NostalgicVersionsClient implements ClientModInitializer {
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
                AlphaBlocks.CROPS,
                AlphaBlocks.REED
        );
        BlockRenderLayerMap.INSTANCE.putBlock(AlphaBlocks.ICE, RenderLayer.getTranslucent());
        HudRenderCallback.EVENT.register(new FalseVersionOverlay());
        HandledScreens.register(NostalgicVersions.ALPHA_WORKBENCH_SCREEN_HANDLER, WorkbenchScreen::new);
    }
}
