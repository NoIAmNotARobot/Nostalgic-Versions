package net.noiamnotarobot;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.noiamnotarobot.block.AlphaBlocks;

import java.util.concurrent.CompletableFuture;

public class MinecraftAlphaDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator generator) {
        FabricDataGenerator.Pack pack = generator.createPack();

        pack.addProvider(MinecraftAlphaModelProvider::new);
        pack.addProvider(MinecraftAlphaBlockLootTableProvider::new);
        pack.addProvider(MinecraftAlphaEnglishLangProvider::new);
        pack.addProvider(MinecraftAlphaBlockTagProvider::new);
	}

    private static class MinecraftAlphaModelProvider extends FabricModelProvider {
        public MinecraftAlphaModelProvider(FabricDataOutput output) {
            super(output);
        }

        @Override
        public void generateBlockStateModels(BlockStateModelGenerator generator) {
            generator.registerSimpleCubeAll(AlphaBlocks.STONE);
            generator.registerSimpleCubeAll(AlphaBlocks.DIRT);
            generator.registerSimpleCubeAll(AlphaBlocks.COBBLESTONE);
        }

        @Override
        public void generateItemModels(ItemModelGenerator generator) {
        }
    }

    private static class MinecraftAlphaBlockLootTableProvider extends FabricBlockLootTableProvider {
        protected MinecraftAlphaBlockLootTableProvider(FabricDataOutput dataOutput) {
            super(dataOutput);
        }

        @Override
        public void generate() {
            addDrop(AlphaBlocks.STONE, AlphaBlocks.COBBLESTONE);
            addDrop(AlphaBlocks.GRASS, AlphaBlocks.DIRT);
            addDrop(AlphaBlocks.DIRT);
            addDrop(AlphaBlocks.COBBLESTONE);
        }
    }

    private static class MinecraftAlphaEnglishLangProvider extends FabricLanguageProvider {
        protected MinecraftAlphaEnglishLangProvider(FabricDataOutput dataOutput) {
            super(dataOutput, "en_us");
        }

        @Override
        public void generateTranslations(TranslationBuilder translationBuilder) {
            translationBuilder.add(AlphaBlocks.STONE, "Stone");
            translationBuilder.add(AlphaBlocks.GRASS, "Grass");
            translationBuilder.add(AlphaBlocks.DIRT, "Dirt");
            translationBuilder.add(AlphaBlocks.COBBLESTONE, "Cobblestone");
        }
    }

    private static class MinecraftAlphaBlockTagProvider extends FabricTagProvider.BlockTagProvider {
        public MinecraftAlphaBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
            super(output, completableFuture);
        }

        @Override
        protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
            getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                    .add(AlphaBlocks.COBBLESTONE)
                    .add(AlphaBlocks.STAIR_DOUBLE)
                    .add(AlphaBlocks.STAIR_SINGLE)
                    .add(AlphaBlocks.STONE)
                    .add(AlphaBlocks.COBBLESTONE_MOSSY)
                    .add(AlphaBlocks.ORE_IRON)
                    .add(AlphaBlocks.BLOCK_STEEL)
                    .add(AlphaBlocks.ORE_COAL)
                    .add(AlphaBlocks.BLOCK_GOLD)
                    .add(AlphaBlocks.ORE_DIAMOND)
                    .add(AlphaBlocks.BLOCK_DIAMOND)
                    .add(AlphaBlocks.ICE)
                    .add(AlphaBlocks.OBSIDIAN)
                    .add(AlphaBlocks.ORE_GOLD)
                    .add(AlphaBlocks.ORE_REDSTONE)
                    .add(AlphaBlocks.ORE_REDSTONE_GLOWING);
            getOrCreateTagBuilder(BlockTags.AXE_MINEABLE)
                    .add(AlphaBlocks.PLANKS)
                    .add(AlphaBlocks.BOOKSHELF)
                    .add(AlphaBlocks.WOOD)
                    .add(AlphaBlocks.CHEST);
            getOrCreateTagBuilder(BlockTags.SHOVEL_MINEABLE)
                    .add(AlphaBlocks.GRASS)
                    .add(AlphaBlocks.DIRT)
                    .add(AlphaBlocks.SAND)
                    .add(AlphaBlocks.GRAVEL)
                    .add(AlphaBlocks.SNOW)
                    .add(AlphaBlocks.BLOCK_SNOW)
                    .add(AlphaBlocks.BLOCK_CLAY);
        }
    }
}
