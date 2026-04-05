package net.noiamnotarobot;

import net.fabricmc.api.ModInitializer;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.noiamnotarobot.block.AlphaBlocks;
import net.noiamnotarobot.item.AlphaItems;
import net.noiamnotarobot.sound.AlphaSounds;
import net.noiamnotarobot.world.gen.AlphaChunkGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NostalgicVersions implements ModInitializer {
	public static final String MOD_ID = "nostalgic-versions";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static final boolean winterMode = false;

	@Override
	public void onInitialize() {
		LOGGER.info("Initializing {}...", MOD_ID);
        AlphaSounds.init();
        AlphaItems.init();
        AlphaBlocks.init();
        Registry.register(Registries.CHUNK_GENERATOR, id("alpha_chunk_gen"), AlphaChunkGenerator.CODEC);
        LOGGER.info("Initialized {}!", MOD_ID);
	}

    public static Identifier id(String name) {
        return Identifier.of(MOD_ID, name);
    }
}