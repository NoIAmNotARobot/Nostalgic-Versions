package net.noiamnotarobot.nostalgicversions;

import net.fabricmc.api.ModInitializer;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.noiamnotarobot.nostalgicversions.block.AlphaBlocks;
import net.noiamnotarobot.nostalgicversions.block.entity.AlphaBlockEntityTypes;
import net.noiamnotarobot.nostalgicversions.item.AlphaItems;
import net.noiamnotarobot.nostalgicversions.sound.AlphaSounds;
import net.noiamnotarobot.nostalgicversions.world.gen.AlphaChunkGenerator;
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
        AlphaBlockEntityTypes.init();
        AlphaBlocks.init();
        Registry.register(Registries.CHUNK_GENERATOR, id("alpha_chunk_gen"), AlphaChunkGenerator.CODEC);
        LOGGER.info("Initialized {}!", MOD_ID);
	}

    public static Identifier id(String name) {
        return Identifier.of(MOD_ID, name);
    }

    public static class TranslationKeys {
        public static String ALPHA_ITEMS = "itemGroup.minecraft-alpha.alpha_items";
        public static String CHEST_NAME = "container.minecraft-alpha.chest";
        public static String LARGE_CHEST_NAME = "container.minecraft-alpha.large_chest";
    }
}