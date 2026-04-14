package net.noiamnotarobot.nostalgicversions;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.chunk.ChunkGenerator;
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
    public static final TagKey<Block> ALPHA_BLOCKS = TagKey.of(RegistryKeys.BLOCK, Util.id("alpha_blocks"));
    public static final TagKey<Item> ALPHA_ITEMS = TagKey.of(RegistryKeys.ITEM, Util.id("alpha_items"));
    public static final RegistryKey<World> ALPHA_DIM = RegistryKey.of(RegistryKeys.WORLD, NostalgicVersions.Util.id("alpha_dim"));
    public static final ScreenHandlerType<AlphaWorkbenchScreenHandler> ALPHA_WORKBENCH_SCREEN_HANDLER = Registry.register(Registries.SCREEN_HANDLER, Util.id("alpha_workbench_screen_handler"), new ScreenHandlerType<>(AlphaWorkbenchScreenHandler::new, FeatureSet.empty()));
    public static final long SKY_COLOR = 8961023L;
    public static final int TELEPORTER_RANGE = 3;

	@Override
	public void onInitialize() {
		LOGGER.info("Initializing {}...", MOD_ID);
        AlphaSounds.init();
        AlphaItems.init();
        AlphaBlockEntityTypes.init();
        AlphaBlocks.init();
        Registry.register(Registries.CHUNK_GENERATOR, Util.id("alpha_chunk_gen"), AlphaChunkGenerator.CODEC);
        ServerLifecycleEvents.SERVER_STARTED.register((server) -> {
            server.getWorlds().forEach((world) -> {
                ChunkGenerator generator = world.getChunkManager().getChunkGenerator();

                if (generator instanceof AlphaChunkGenerator) {
                    ((AlphaChunkGenerator)generator).init(server.getWorld(ALPHA_DIM).getSeed(), WinterModeState.getServerState(server));
                }
            });
        });
        LOGGER.info("Initialized {}!", MOD_ID);
	}

    public static class Util {
        public static Identifier id(String name) {
            return Identifier.of(MOD_ID, name);
        }

        public static int compressBlockPos(BlockPos pos) {
            return (pos.getX() * 16 + pos.getZ()) * 128 + pos.getY();
        }

        public static BlockPos decompressBlockPos(int compressedPos) {
            int y = compressedPos % 128;
            int z = ((compressedPos - y) / 128) % 16;
            int x = (((compressedPos - y) / 128) - z) / 16;
            return new BlockPos(x, y, z);
        }
    }

    public static class TranslationKeys {
        public static String ALPHA_ITEMS = "itemGroup." + MOD_ID + ".alpha_items";
        public static String CHEST_NAME = "container." + MOD_ID + ".chest";
        public static String LARGE_CHEST_NAME = "container." + MOD_ID + ".large_chest";

        public static String MODERN_TELEPORT_ERROR = "err." + MOD_ID + ".modern_teleport";
        public static String ALPHA_TELEPORT_ERROR_OLD = "err." + MOD_ID + ".modern_teleport_old";
        public static String ALPHA_TELEPORT_ERROR_ELSE = "err." + MOD_ID + ".modern_teleport_else";
    }
}